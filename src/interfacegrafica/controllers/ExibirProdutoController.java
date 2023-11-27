package interfacegrafica.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.HTML.Tag;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import sistema.Especificacao;
import sistema.Produto;

public class ExibirProdutoController extends ControllerLogged
{
    /**
     * Override do método padrão de inicialização da classe Controller do javafx.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        super.initialize(location, resources);

        Platform.runLater(() -> 
        {
            /* 
             * TODO: luis
             * // Puxar produto do banco de dados
             * id do produto: ControllerLogged.idProdutoAtual
             */
            Produto produto;
            
            /* DEBUG */

            ArrayList<Especificacao> especificacoes = new ArrayList<Especificacao>();

            Especificacao especificacao1 = new Especificacao("Tipo de Memoria", "DDR3");
            Especificacao especificacao2 = new Especificacao("Espaço de armazenamento: ", "16gb");

            ArrayList<Tag> tags = new ArrayList<Tag>();

            Produto produtoTeste = new Produto("Teste Descrição", "Memória Ram", );
            
        });
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
     *  Imageviews
     */

    @FXML
    private ImageView fotoProduto;

    @FXML
    private ImageView fotoDaLoja;

    /* 
     *  Botões
     */

    @FXML 
    private Button editarProduto;

    @FXML 
    private Button removerProduto;

    @FXML
    private Button sair;

    /* 
     *  Texto
     */

    @FXML
    private Text valorArrecadado;

    @FXML
    private Text valorProduto;

    @FXML
    private Text valorFaltam;

    @FXML
    private Text cor;

    @FXML
    private Text tamanho;

    @FXML
    private Text material;

    @FXML
    private Text altura;

    @FXML
    private Text largura;

    @FXML
    private Text comprimento;

    @FXML
    private Text autor;

    @FXML
    private Text genero;
    
    @FXML
    private Text nomeDaLoja;

    @FXML
    private TextFlow textFlowEspecificacao;

    @FXML
    private TextFlow textFlowTag;

    @FXML
    private TextFlow textFlowDescricao;

}
