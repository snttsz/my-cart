package interfacegrafica.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// JavaFX libraries
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

// Java libraries
import java.util.Stack;

// Local libraries
import interfacegrafica.models.PaginaLoja;
import interfacegrafica.models.PainelLoja;
import interfacegrafica.models.PainelLojaJAVAFX;
import interfacegrafica.models.PainelProduto;
import interfacegrafica.models.PainelProdutoJAVAFX;
import sistema.Loja;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.Produto.Categorias;

public class LojasCadastradasController extends ControllerLogged
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        super.initialize(location, resources);

        this.pilhaLojas = new Stack<PaginaLoja>();
        
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        Platform.runLater(() -> 
        {
            this.inicializarModels();

            // TODO: luis
            // setar essa variavel abaixo
            this.qtdDeLojasCadastradas = 2;

            this.addNovaPaginaLoja();
            this.exibirNovaPaginaLoja();
        }
        );
    }

    public void mostrarPaneSemLoja2()
    {
        this.paneSemLoja.toFront();
        this.paneSemLoja.setOpacity(1);
        
        this.proxPagina.setOpacity(0.5);
        this.proxPagina.setDisable(true);
    }

    @FXML
    public void botaoAddMouseOff(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.0);
    }

    @FXML
    public void botaoAddMouseOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.17);

        this.cursorOn(mouse);
    }

    @FXML
    public void botaoAddClicked(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.3);

        this.cursorNormal(mouse);

        if (source.getId() == adicionarProduto.getId())
        {   
            this.mudarScene("ScreenAdicionarProduto.fxml");
        }   
        else if (source.getId() == adicionarLoja.getId())
        {
            this.mudarScene("ScreenCadastrarLoja.fxml");
        }
    }

    @FXML
    public void addNovaPaginaLoja()
    {
        // TODO: luis
        // cada chamada dessa função, preciso que retorne duas lojas
        // usa o this.marcadorLojaAtual pra saber de qual até qual loja pegar
        // tipo: o marcador tá na quinta loja, vc vai retornar a quinta loja e a sexta
        // caso não tenha mais duas lojas, pode retornar a segunda nula
        ArrayList<Loja> lojas = new ArrayList<Loja>();

        // TODO: luis
        // preciso de 3 produtos de cada loja
        // caso não tenha 3, pode retornar o resto nulo
        ArrayList<Produto> produtosLoja1 = new ArrayList<Produto>();
        ArrayList<Produto> produtosLoja2 = new ArrayList<Produto>();


        // TODO: descomentar isso aqui quando os comandos do banco estiverem prontos
        // if (lojas.get(1) == null)
        // {
        //     this.mostrarPaneSemLoja2();
        // }
        
        // TODO: pode apagar o debug quando os comandos estiverem prontos
        /* DEBUG */

        produtosLoja1.add(new ProdutoEletronico("Nenhuma", "Josemar com risadinha", 300, "https://teste.com", "img/produto-foto-test.jpeg", 100, 50, Categorias.ELETRODOMESTICO.getCategoria(), null, null, Controller.idUsuario, 1));
        produtosLoja2.add(new ProdutoEletronico("Nenhuma", "Josemar sem risadinha", 200, "https://teste.com", "img/produto-foto-test2.jpg", 100, 50, Categorias.ELETRODOMESTICO.getCategoria(), null, null, Controller.idUsuario, 2));

        lojas.add(new Loja(1, "Aliexpress", "https://aliexpress.com", "img/aliexpress.png"));
        lojas.add(new Loja(2, "Shein", "https://br.shein.com", "img/shein.png"));

        Loja loja1 = lojas.get(0);
        Loja loja2 = lojas.get(1);

        /* END OF DEBUG */

        // TODO: consertar os gets com o index certo quando os comandos do banco estiverem 
        // prontos

        PainelProduto produto1_loja1 = new PainelProduto(produtosLoja1.get(0));
        PainelProduto produto2_loja1 = new PainelProduto(produtosLoja1.get(0));
        PainelProduto produto3_loja1 = new PainelProduto(produtosLoja1.get(0));

        PainelProduto produto1_loja2 = new PainelProduto(produtosLoja2.get(0));
        PainelProduto produto2_loja2 = new PainelProduto(produtosLoja2.get(0));
        PainelProduto produto3_loja2 = new PainelProduto(produtosLoja2.get(0));

        PainelLoja painelLoja1 = new PainelLoja(loja1, produto1_loja1, produto2_loja1, produto3_loja1);
        PainelLoja painelLoja2 = new PainelLoja(loja2, produto1_loja2, produto2_loja2, produto3_loja2);

        PaginaLoja paginaLoja = new PaginaLoja(painelLoja2, painelLoja1);

        this.pilhaLojas.add(paginaLoja);
    }

    @FXML
    public void proximaPaginaLoja(ActionEvent action)
    {
        this.addNovaPaginaLoja();
        
        this.marcadorLojaAtual += 2;

        this.checarBotoesProxAnt();

        this.exibirNovaPaginaLoja();
    }

    private void checarBotoesProxAnt()
    {
        if (this.marcadorLojaAtual >= this.qtdDeLojasCadastradas)
        {
            this.proxPagina.setOpacity(0.5);
            this.proxPagina.setDisable(true);
        }
        else
        {
            this.proxPagina.setOpacity(1);
            this.proxPagina.setDisable(false);
        }

        if (this.marcadorLojaAtual <= 1)
        {
            this.pagAnterior.setOpacity(0.5);
            this.pagAnterior.setDisable(true);
        }
        else
        {
            this.pagAnterior.setOpacity(1);
            this.pagAnterior.setDisable(false);
        }
    }

    @FXML
    public void verMaisProdutosDaLoja(ActionEvent action)
    {
        Node buttonSource = (Node) action.getSource();
        String fxmlName = null;

        if (buttonSource.getId() == maisProdutosLoja1.getId())
        {
            fxmlName = "teste";
            LojasCadastradasController.idLojaAtual = this.pilhaLojas.firstElement().getLoja1().getIdLoja();
        }
        else
        {
            fxmlName = "teste";
            LojasCadastradasController.idLojaAtual = this.pilhaLojas.firstElement().getLoja2().getIdLoja();   
        }

        /* 
        * TODO: luis
        * setar essa varivel pra true se a loja tiver mais de
        * tres produtos e false se tiver 3 ou menos de 3
        * id da loja: LojasCadastradasController.idLojaAtual
        */
        boolean exibirVerMais = false;

        if (exibirVerMais)
        {
            /* TODO: mudar scene pra mostrar os produtos */
        }
        else
        {
            this.abrirErroStage("A loja não tem mais produtos para exibir!");
        }   

    }

    @FXML
    public void anteriorPaginaLoja(ActionEvent action)
    {
        this.pilhaLojas.pop();
        
        this.marcadorLojaAtual -= 2;
        
        this.checarBotoesProxAnt();

        this.exibirNovaPaginaLoja();
    }

    @FXML
    public void exibirNovaPaginaLoja()
    {
        this.addNovaPaginaLoja();

        PaginaLoja paginaAtual = this.pilhaLojas.firstElement();

        this.loja1.atualizarAtributosComPainelLoja(paginaAtual.getLoja1());
        this.loja2.atualizarAtributosComPainelLoja(paginaAtual.getLoja2());
    }

    @FXML
    public void inicializarModels()
    {
        PainelProdutoJAVAFX produto1 = new PainelProdutoJAVAFX(this.produtoLoja1, this.nomeProduto, this.valorPrecoProduto, this.valorArrecadadoProduto, this.valorFaltamProduto, this.fotoProduto);
        PainelProdutoJAVAFX produto2 = new PainelProdutoJAVAFX(this.produtoLoja2, this.nomeProduto2, this.valorPrecoProduto2, this.valorArrecadadoProduto2, this.valorFaltamProduto2, this.fotoProduto2);;
        PainelProdutoJAVAFX produto3 = new PainelProdutoJAVAFX(this.produtoLoja3, this.nomeProduto3, this.valorPrecoProduto3, this.valorArrecadadoProduto3, this.valorFaltamProduto3, this.fotoProduto3);

        this.loja1 = new PainelLojaJAVAFX(this.paneLoja1, this.nomeLoja1, this.fotoLoja1, this.maisProdutosLoja1, produto1, produto2, produto3);
        
        PainelProdutoJAVAFX produto1_2 = new PainelProdutoJAVAFX(this.produtoLoja1_2, this.nomeProduto1_2, this.valorPrecoProduto1_2, this.valorArrecadadoProduto1_2, this.valorFaltamProduto1_2, this.fotoProduto1_2);
        PainelProdutoJAVAFX produto2_2 = new PainelProdutoJAVAFX(this.produtoLoja2_2, this.nomeProduto2_2, this.valorPrecoProduto2_2, this.valorArrecadadoProduto2_2, this.valorFaltamProduto2_2, this.fotoProduto2_2);
        PainelProdutoJAVAFX produto3_2 = new PainelProdutoJAVAFX(this.produtoLoja3_2, this.nomeProduto3_2, this.valorPrecoProduto3_2, this.valorArrecadadoProduto3_2, this.valorFaltamProduto3_2, this.fotoProduto3_2);

        this.loja2 = new PainelLojaJAVAFX(this.paneLoja2, this.nomeLoja2, this.fotoLoja2, this.maisProdutosLoja2, produto1_2, produto2_2, produto3_2);
    }
    
    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    protected Node root;

    /* 
     *    Botões
     */

    @FXML
    private Button adicionarProduto;

    @FXML
    private Button adicionarLoja;

    /* 
     *    Panes
     */

    @FXML
    private AnchorPane defaultLojaPane;

    @FXML
    private Pane defaultProdutoLoja;

    @FXML
    private Pane paneSemLoja;

    @FXML
    private Text numeroPagina;

    @FXML
    private Button pagAnterior;

    @FXML
    private Button proxPagina;

    /* 
     *    Texto
     */

    @FXML
    private Text arrecadadoPaneDefault;

    @FXML
    private Text faltamPaneDefault;

    @FXML
    private Text precoProdutoPaneDefault;

    @FXML
    private Text nomeProdutoPaneDefault;

    @FXML 
    private Text precoProduto;

    @FXML 
    private Text precoArrecadado;

    @FXML 
    private Text precoFaltam;

    /* 
     *    Imagens
     */

    @FXML
    private ImageView fotoPaneDefault;

    /* 
     *    
     *      Painéis das lojas
     *  
     */

    /* 
    * Pane loja 1 
    */

    @FXML
    private AnchorPane paneLoja1;

    @FXML
    private Text nomeLoja1;

    @FXML
    private ImageView fotoLoja1;

    @FXML 
    private Button maisProdutosLoja1;

    // primeiro produto

    @FXML
    private Text nomeProduto;

    @FXML
    private Pane produtoLoja1;

    @FXML 
    private Text valorPrecoProduto;

    @FXML 
    private Text valorArrecadadoProduto;

    @FXML
    private Text valorFaltamProduto;

    @FXML
    private ImageView fotoProduto;

    // segundo produto

    @FXML 
    private Pane produtoLoja2;

    @FXML
    private Text nomeProduto2;

    @FXML 
    private Text valorPrecoProduto2;

    @FXML 
    private Text valorArrecadadoProduto2;

    @FXML
    private Text valorFaltamProduto2;

    @FXML
    private ImageView fotoProduto2;

    // terceiro produto

    @FXML 
    private Pane produtoLoja3;

    @FXML
    private Text nomeProduto3;

    @FXML 
    private Text valorPrecoProduto3;

    @FXML 
    private Text valorArrecadadoProduto3;

    @FXML
    private Text valorFaltamProduto3;

    @FXML
    private ImageView fotoProduto3;

    /* 
    * Pane loja 2
    */

    @FXML
    private AnchorPane paneLoja2;

    @FXML
    private Text nomeLoja2;

    @FXML
    private ImageView fotoLoja2;

    @FXML 
    private Button maisProdutosLoja2;

    // primeiro produto

    @FXML
    private Pane produtoLoja1_2;

    @FXML
    private Text nomeProduto1_2;

    @FXML 
    private Text valorPrecoProduto1_2;

    @FXML 
    private Text valorArrecadadoProduto1_2;

    @FXML
    private Text valorFaltamProduto1_2;

    @FXML
    private ImageView fotoProduto1_2;

    // segundo produto

    @FXML 
    private Pane produtoLoja2_2;

    @FXML
    private Text nomeProduto2_2;

    @FXML 
    private Text valorPrecoProduto2_2;

    @FXML 
    private Text valorArrecadadoProduto2_2;

    @FXML
    private Text valorFaltamProduto2_2;

    @FXML
    private ImageView fotoProduto2_2;

    // terceiro produto

    @FXML 
    private Pane produtoLoja3_2;

    @FXML
    private Text nomeProduto3_2;

    @FXML 
    private Text valorPrecoProduto3_2;

    @FXML 
    private Text valorArrecadadoProduto3_2;

    @FXML
    private Text valorFaltamProduto3_2;

    @FXML
    private ImageView fotoProduto3_2;

    /* 
     * 
     *      ATRIBUTOS INTERNOS  
     * 
     */

    private Stack<PaginaLoja> pilhaLojas;

    private int marcadorLojaAtual = 1;
    private int qtdDeLojasCadastradas;

    private PainelLojaJAVAFX loja1;
    private PainelLojaJAVAFX loja2;

    public static int idLojaAtual;
}
