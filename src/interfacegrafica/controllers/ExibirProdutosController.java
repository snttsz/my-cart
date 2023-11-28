package interfacegrafica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import interfacegrafica.models.PainelProdutoJAVAFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sistema.Produto;

public class ExibirProdutosController extends ControllerLogged
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(() -> 
        {
            this.inicializarPaineis();

            /* 
             * TODO: luis
             * setar a variavel abaixo
             */
            this.qtdTotalProdutos = 7;
        });
    }

    private void carregarProdutosDoBD()
    {
        /* 
         * TODO: luis
         * retornar um vetor com 6 produtos. caso não tenha +6 produtos,
         * retornar o resto nulo.
         */
        Produto[] produtos = new Produto[6];
    }

    private void inicializarPaineis()
    {
        this.painelProduto1 = new PainelProdutoJAVAFX(produto1, nomeProduto, valorPrecoProduto, valorArrecadadoProduto, valorFaltamProduto, fotoProduto);
        this.painelProduto2 = new PainelProdutoJAVAFX(produto2, nomeProduto1, valorPrecoProduto1, valorArrecadadoProduto1, valorFaltamProduto1, fotoProduto1);
        this.painelProduto3 = new PainelProdutoJAVAFX(produto3, nomeProduto2, valorPrecoProduto2, valorArrecadadoProduto2, valorFaltamProduto2, fotoProduto2);
        this.painelProduto4 = new PainelProdutoJAVAFX(produto4, nomeProduto3, valorPrecoProduto3, valorArrecadadoProduto3, valorFaltamProduto3, fotoProduto3);
        this.painelProduto5 = new PainelProdutoJAVAFX(produto5, nomeProduto4, valorPrecoProduto4, valorArrecadadoProduto4, valorFaltamProduto4, fotoProduto4);
        this.painelProduto6 = new PainelProdutoJAVAFX(produto6, nomeProduto5, valorPrecoProduto5, valorArrecadadoProduto5, valorFaltamProduto5, fotoProduto5);

        this.paineisProdutos = new PainelProdutoJAVAFX[6];

        this.paineisProdutos[0] = painelProduto1;
        this.paineisProdutos[1] = painelProduto2;
        this.paineisProdutos[2] = painelProduto3;
        this.paineisProdutos[3] = painelProduto4;
        this.paineisProdutos[4] = painelProduto5;
        this.paineisProdutos[5] = painelProduto6;
    }


    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    // primeiro produto
    @FXML
    private Pane produto1;
    
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

    // segundo produto
    @FXML
    private Pane produto2;
    
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

    // terceiro produto
    @FXML
    private Pane produto3;
    
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

    // quarto produto
    @FXML
    private Pane produto4;
    
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

    // quinto produto
    @FXML
    private Pane produto5;
    
    @FXML
    private Text nomeProduto4;

    @FXML 
    private Text valorPrecoProduto4;

    @FXML 
    private Text valorArrecadadoProduto4;

    @FXML
    private Text valorFaltamProduto4;

    @FXML
    private ImageView fotoProduto4;

    // sexto produto
    @FXML
    private Pane produto6;
    
    @FXML
    private Text nomeProduto5;

    @FXML 
    private Text valorPrecoProduto5;

    @FXML 
    private Text valorArrecadadoProduto5;

    @FXML
    private Text valorFaltamProduto5;

    @FXML
    private ImageView fotoProduto5;

    /* 
     * 
     *      ATRIBUTOS INTERNOS  
     * 
     */

    private PainelProdutoJAVAFX painelProduto1;
    private PainelProdutoJAVAFX painelProduto2;
    private PainelProdutoJAVAFX painelProduto3;
    private PainelProdutoJAVAFX painelProduto4;
    private PainelProdutoJAVAFX painelProduto5;
    private PainelProdutoJAVAFX painelProduto6;

    private PainelProdutoJAVAFX[] paineisProdutos;

    private int qtdTotalProdutos;
}
