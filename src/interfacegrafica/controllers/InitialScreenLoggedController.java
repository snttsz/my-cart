package interfacegrafica.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import interfacegrafica.models.PainelProduto;
import interfacegrafica.models.PainelProdutoJAVAFX;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class InitialScreenLoggedController extends ControllerLogged
{
    /**
     * Override do método padrão de inicialização da classe Controller do javafx.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        super.initialize(location, resources);
        
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        Platform.runLater(() -> 
        {
            this.initializeModels();

            int qtdProdutosUsuario = this.produtoDAO.contarProdutosDoUsuario(ControllerLogged.idUsuario);

            this.exibirProdutosDoBanco();
            
            if (qtdProdutosUsuario == 0)
            {
                this.listaVaziaInicio.setOpacity(1);
            }
        }
        );
    }

    private void exibirProdutosDoBanco()
    {
        PainelProduto[] paineis = new PainelProduto[3];

        paineis[0] = null;
        paineis[1] = null;
        paineis[2] = null;

        ArrayList<Integer> produtosBD = this.produtoDAO.selectProdutosCadastradosRecentementePorUser(3, ControllerLogged.idUsuario);

        for (int i = 0; i < 3; i++)
        {
            try
            {
                paineis[i] = new PainelProduto(produtoDAO.selectById(produtosBD.get(i)));
            }
            catch(IndexOutOfBoundsException exception)
            {

            }
        }

        this.painelProduto1.atualizarAtributosComPainelProduto(paineis[0]);
        this.painelProduto2.atualizarAtributosComPainelProduto(paineis[1]);
        this.painelProduto3.atualizarAtributosComPainelProduto(paineis[2]);
    }

    private void initializeModels()
    {
        this.painelProduto1 = new PainelProdutoJAVAFX(this.produto, this.nomeProduto, this.valorPrecoProduto, this.valorArrecadadoProduto, this.valorFaltamProduto, this.fotoProduto);
        this.painelProduto2 = new PainelProdutoJAVAFX(this.produto1, this.nomeProduto1, this.valorPrecoProduto1, this.valorArrecadadoProduto1, this.valorFaltamProduto1, this.fotoProduto1);
        this.painelProduto3 = new PainelProdutoJAVAFX(this.produto2, this.nomeProduto2, this.valorPrecoProduto2, this.valorArrecadadoProduto2, this.valorFaltamProduto2, this.fotoProduto2);
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

    @FXML
    public void exibirProdutoSelecionado(ActionEvent action)
    {
        Node button = (Node) action.getSource();

        if (button.getId() == botao_produto.getId())
        {
            ControllerLogged.idProdutoAtual = this.painelProduto1.getProduto().getIdProduto();
        }
        else if (button.getId() == botao_produto1.getId())
        {
            ControllerLogged.idProdutoAtual = this.painelProduto2.getProduto().getIdProduto();
        }
        else if (button.getId() == botao_produto2.getId())
        {
            ControllerLogged.idProdutoAtual = this.painelProduto3.getProduto().getIdProduto();
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
    private Button botao_produto;

    @FXML
    private Button botao_produto1;

    @FXML
    private Button botao_produto2;

    /* 
     *    Texto
     */

    @FXML
    private Text listaVaziaInicio;

    // Produtos

    //produto 1
    @FXML
    private Pane produto;
    
    @FXML
    private Text nomeProduto;

    @FXML 
    private Text valorPrecoProduto;

    @FXML 
    private Text valorArrecadadoProduto;

    @FXML
    private Text valorFaltamProduto;

    @FXML
    private ImageView fotoProduto;

    //produto2
    @FXML
    private Pane produto1;
    
    @FXML
    private Text nomeProduto1;

    @FXML 
    private Text valorPrecoProduto1;

    @FXML 
    private Text valorArrecadadoProduto1;

    @FXML
    private Text valorFaltamProduto1;

    @FXML
    private ImageView fotoProduto1;

    //produto3
    @FXML
    private Pane produto2;
    
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

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    private PainelProdutoJAVAFX painelProduto1;
    private PainelProdutoJAVAFX painelProduto2;
    private PainelProdutoJAVAFX painelProduto3;
}
