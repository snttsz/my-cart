package interfacegrafica.controllers;

import sistema.Tag;
import sistema.Especificacao;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

public class CadastrarProdutoController extends ControllerLogged
{
    public CadastrarProdutoController()
    {
        this.tags = new ArrayList<Tag>();
        this.especificacoes = new ArrayList<Especificacao>();
    }

    public void adicionarEspecificacaoNoCampo(ActionEvent action)
    {
        
    }

    public void removerEspecificacaoNoCampo(ActionEvent action)
    {
        
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    protected Node root;

    /* 
     *  Textfields
     */
    

    @FXML
    private TextField nomeDoProduto;

    @FXML
    private TextField linkDoProduto;

    @FXML
    private TextField especificacaoNome;

    @FXML
    private TextField especificacaoValor;

    @FXML
    private TextField valorArrecadado;

    @FXML
    private TextField valorDoFrete;

    @FXML
    private TextField valorDoProduto;

    @FXML
    private TextField adicionarTagCampo;

    @FXML
    private TextField cor;

    @FXML
    private TextField tamanho;

    @FXML
    private TextField material;

    @FXML
    private TextField altura;

    @FXML
    private TextField largura;

    @FXML
    private TextField comprimento;

    @FXML
    private TextField autor;

    @FXML
    private TextField genero;

    @FXML
    private TextArea descricao;

    /* 
     *  Botões
     */

    @FXML
    private Button adicionarEspecificacao;

    @FXML
    private Button adicionarTag;

    @FXML
    private Button removerEspecificacao;    

    @FXML
    private Button removerTag;   

    @FXML
    private Button adicionarCategoria;   
    
    /* 
     *  Panes
     */

    @FXML 
    private AnchorPane anchorEspecificacao;

    @FXML 
    private AnchorPane anchorTag;

    /* 
     *  Texto
     */

    @FXML 
    private TextFlow textFlowEspecificacao;

    @FXML 
    private TextFlow textFlowTag;

    /* 
     *  SplitDownButton
     */

    @FXML
    private SplitMenuButton categoriasSplitDown;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    ArrayList<Tag> tags;
    ArrayList<Especificacao> especificacoes;
}
