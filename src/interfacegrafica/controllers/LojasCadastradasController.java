package interfacegrafica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

// JavaFX libraries
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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


/**
 * 
 * Classe responsável por definir as implementações da tela de lojas cadastradas.
 * 
 * @author Glenda
 * 
 */
public class LojasCadastradasController extends ControllerLogged
{
    /**
     * Override do método padrão de inicialização da classe Controller do javafx.
     */
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

            this.checarBotoesProxAnt();
            
            this.addNovaPaginaLoja(this.getLojas());
            
            this.exibirNovaPaginaLoja();
        }
        );
    }

    /**
     * Exibir mensagem de "Sem mais lojas" no painel da segunda loja
     */
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
            /* 
             * Boolean indicando que o carregamento da página será para
             * cadastrar um produto, e não editá-lo.
             */
            ControllerLogged.editarProduto = false;
            
            this.mudarScene("ScreenAdicionarProduto.fxml");
        }   
        else if (source.getId() == adicionarLoja.getId())
        {
            this.mudarScene("ScreenCadastrarLoja.fxml");
        }
    }

    /**
     * Função para puxar lojas do banco de dados.
     */
    private Loja[] getLojas()
    {
        // TODO: luis
        // cada chamada dessa função, preciso que retorne duas lojas
        // usa o this.marcadorLojaAtual pra saber de qual até qual loja pegar
        // tipo: o marcador tá na quinta loja, vc vai retornar a quinta loja e a sexta
        // caso não tenha mais duas lojas, pode retornar a segunda nula
        Loja[] lojas = new Loja[2];
        
        /* DEBUG */
        lojas[0] = new Loja(1, "Shein", "https://br.shein.com", "img/shein.png");
        lojas[1] = new Loja(2, "Aliexpress", "https://aliexpress.com", "img/aliexpress.png");
        /* END OF DEBUG */

        return lojas;
    }

    /**
     * Função para puxar produtos de uma loja do banco de dados.
     * 
     * @param loja
     * Loja a qual os produtos serão puxados.
     */
    private Produto[] getProdutos(Loja loja)
    {
        // TODO: descomentar quando a função com o BD estiver pronta
        // if (loja == null)
        // {
        //     return null;
        // }

        // TODO: luis
        // preciso de 3 produtos de cada loja
        // caso não tenha 3, pode retornar o resto nulo
        Produto[] produtosLoja = new Produto[3];

        /* DEBUG */
        if (loja.getId() == 1)
        {
            produtosLoja[0] = new ProdutoEletronico("Nenhuma", "Josemar com risadinha", 300, "https://teste.com", "img/produto-foto-test.jpeg", 100, 50, Categorias.ELETRODOMESTICO.getCategoria(), null, null, Controller.idUsuario, 1);
        }
        else
        {
            produtosLoja[0] = new ProdutoEletronico("Nenhuma", "Josemar sem risadinha", 200, "https://teste.com", "img/produto-foto-test2.jpg", 100, 50, Categorias.ELETRODOMESTICO.getCategoria(), null, null, Controller.idUsuario, 2);
        }
        /* END OF DEBUG */

        return produtosLoja;
    }

    /**
     * Função para adicionar nova pagina de lojas à pilha de páginas.
     * 
     * @param lojas
     * Vetor de lojas com as lojas que serão exibidas na tela.
     */
    public void addNovaPaginaLoja(Loja[] lojas)
    {
        // TODO: descomentar isso aqui quando os comandos do banco estiverem prontos
        // if (lojas.get(1) == null)
        // {
        //     this.mostrarPaneSemLoja2();
        // }

        PainelProduto[] produtosLoja1 = this.getPaineisProdutos(this.getProdutos(lojas[0]));
        PainelProduto[] produtosLoja2 = this.getPaineisProdutos(this.getProdutos(lojas[1]));
    
        // TODO: consertar os produtos com o index certo quando 
        // os comandos do banco estiverem 
        // prontos 
        PainelLoja painelLoja1 = new PainelLoja(lojas[0], produtosLoja1[0], produtosLoja1[0], produtosLoja1[0]);
        PainelLoja painelLoja2 = new PainelLoja(lojas[1], produtosLoja2[0], produtosLoja2[0], produtosLoja2[0]);

        PaginaLoja paginaLoja = new PaginaLoja(painelLoja1, painelLoja2);

        this.pilhaLojas.push(paginaLoja);
    }

    /**
     * Retorna um vetor de PainelProduto com os produtos recebidos por parâmetro.
     * 
     * @param produtos
     * Produtos que serão transformados em PainelProduto.
     */
    private PainelProduto[] getPaineisProdutos(Produto[] produtos)
    {
        PainelProduto[] paineis = new PainelProduto[3];

        paineis[0] = null;
        paineis[1] = null;
        paineis[2] = null;

        for (int i = 0; i < 3; i++)
        {
            if (produtos[i] != null)
            {
                paineis[i] = new PainelProduto(produtos[i]);
            }
        }

        return paineis;
    }

    /**
     * Função acionada quando o usuário clicar no botão "próxima página"
     * 
     * A função irá chamar os métodos necessários para exibir os produtos da pŕoxima página.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void proximaPaginaLoja(ActionEvent action)
    {
        this.marcadorLojaAtual += 2;
        
        this.addNovaPaginaLoja(this.getLojas());

        this.checarBotoesProxAnt();

        this.exibirNovaPaginaLoja();
    }

    /**
     * Função para desabilitar os botões de próxima página e página anterior quando necessário.
     */
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

    /**
     * Função acionada quando o usuário clicar no botão "página anterior"
     * 
     * A função irá chamar os métodos necessários para exibir os produtos da página anterior.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void anteriorPaginaLoja(ActionEvent action)
    {
        this.pilhaLojas.pop();
        
        this.marcadorLojaAtual -= 2;
        
        this.checarBotoesProxAnt();

        this.exibirNovaPaginaLoja();
    }

    /**
     * Função para setar os atributos necessários para exibir a página atual.
     */
    public void exibirNovaPaginaLoja()
    {
        PaginaLoja paginaAtual = this.pilhaLojas.firstElement();

        this.loja1.atualizarAtributosComPainelLoja(paginaAtual.getLoja1());
        this.loja2.atualizarAtributosComPainelLoja(paginaAtual.getLoja2());
    }

    /**
     * Função para inicializar o vetor de painéis do javafx.
     */
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

    /**
     * Função acionada quando o usuário pressiona enter dentro do campo de pesquisar por loja.
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void pesquisarPorLojaNoBD(KeyEvent key)
    {
        if (key.getCode() == KeyCode.ENTER)
        {
            String nomeDaLoja = this.pesquisarLojaField.getText();

            /* 
             * TODO: luis
             * procurar nome da loja no BD, se não tiver nenhuma,
             * seta o obj abaixo pra nulo
             */
            Loja lojaEncontrada = new Loja("Teste", "https://teste.com", "img/myCart.png");

            if (lojaEncontrada == null)
            {
                /* TODO: mudar a scene */
            }
            else
            {
                Loja[] lojas = new Loja[2];
                
                lojas[0] = lojaEncontrada;
                lojas[1] = null;

                this.addNovaPaginaLoja(lojas);
            }
        }
    }

    /**
     * Função acionada quando o usuário clica em um produto.
     * 
     * A função irá chamar a tela de exibição dos detalhes de um produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void exibirProdutoSelecionado(ActionEvent action)
    {
        Node button = (Node) action.getSource();

        PainelLoja loja1 = this.pilhaLojas.firstElement().getLoja1();
        PainelLoja loja2 = this.pilhaLojas.firstElement().getLoja2();

        /* Primeira loja */
        if (button.getId() == produto1_loja1_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja1.getProduto1().getIdProduto();
        }
        else if (button.getId() == produto2_loja1_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja1.getProduto2().getIdProduto();
        }
        else if (button.getId() == produto3_loja1_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja1.getProduto3().getIdProduto();
        }
        /* Segunda loja */
        else if (button.getId() == produto1_loja2_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja2.getProduto1().getIdProduto();
        }
        else if (button.getId() == produto2_loja2_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja2.getProduto2().getIdProduto();
        }
        else if (button.getId() == produto3_loja2_button.getId())
        {
            ControllerLogged.idProdutoAtual = loja2.getProduto3().getIdProduto();
        }

        this.mudarScene("ScreenExibirProduto.fxml");
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

    @FXML
    private TextField pesquisarLojaField;

    @FXML
    private Button produto1_loja1_button;

    @FXML
    private Button produto2_loja1_button;

    @FXML
    private Button produto3_loja1_button;

    @FXML
    private Button produto1_loja2_button;

    @FXML
    private Button produto2_loja2_button;

    @FXML
    private Button produto3_loja2_button;

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
    public static int idProdutoAtual;
}
