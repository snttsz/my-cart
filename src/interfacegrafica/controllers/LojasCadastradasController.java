package interfacegrafica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

// JavaFX libraries
import javafx.application.Platform;
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

import interfacegrafica.models.PaginaLoja;
// Local Libraries
import interfacegrafica.models.PainelLoja;
import interfacegrafica.models.PainelProduto;

public class LojasCadastradasController extends ControllerLogged
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        super.initialize(location, resources);

        this.pilhaLojas = new Stack<PainelLoja>();
        
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        Platform.runLater(() -> 
        {
            // TODO: usar pilhas para gerenciar as páginas
            // TODO: mover esse bloco de código pra uma função separada
            /* 
             * Setando a altura do pane de acordo com a quantidade de lojas.
             * 
             * Sendo que são disponibilizadas 3 lojas por página, a cada
             * página será verificado a quantidade de lojas para determinar
             * qual será a altura do pane e não deixar espaços em branco
             * desproporcionais na página.
             */
            int quantidadeDeLojas = 2;
        }
        );
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
    }

    @FXML
    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        Node botao = (Node) mouse.getSource();

        if (botao.getId() == this.lojasCadastradas.getId())
        {
            this.mudarSceneLojasCadastradas();
        }
        else if (botao.getId() == this.categorias.getId())
        {
            this.mudarSceneCategorias();
        }
        else if (botao.getId() == this.inicio.getId())
        {
            this.mudarSceneInicio();
        }
    }

    
    @Override
    public void mudarSceneInicio()
    {
        this.carregarNovaScene("ScreenLogged.fxml");
    }

    @Override
    public void mudarSceneCategorias()
    {   
        
    }

    @Override 
    public void mudarSceneLojasCadastradas()
    {
        this.carregarNovaScene("ScreenLojasCadastradas.fxml");
    }

    // TODO: pra criar as pilhas de página, não vou precisar armazenar atributos
    // do modulo do javafx, só atributos comuns como nome da loja, caminho da imagem, etc.
    // coisas que vem do banco de dados. Daí quando for mudar a página eu só vou alterando
    // os atributos do javafx com os atributos do banco.

    // TODO: talvez essa função tenha que ir pra classe pai controller
    private PainelProduto criarPainelProduto(String nomeDoProduto, double valorProduto, double valorArrecadado)
    {

    }

    private PainelLoja criarPainelLoja(int idLoja, String nomeDaLoja, String caminhoImagem, PainelProduto produto1, PainelProduto produto2)
    {

    }

    private PaginaLoja criarPaginaLoja()
    {

    }
    
    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

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

    Stack<PaginaLoja> pilhaLojas;
}
