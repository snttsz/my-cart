package interfacegrafica.controllers;

import sistema.Tag;
import sistema.Especificacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CadastrarProdutoController extends ControllerLogged
{
    public CadastrarProdutoController()
    {
        this.tags = new ArrayList<Tag>();
        this.especificacoes = new ArrayList<Especificacao>();

        this.tagsText = new ArrayList<Text>();
        this.especificacoesText = new ArrayList<Text>();

        this.categoriasMenuItens = new ArrayList<MenuItem>();
    }

    public void adicionarTagNoCampo(ActionEvent action)
    {
        /* Adicionando a tag no array de texto */
        Text tag = new Text(this.adicionarTagCampo.getText());

        // checar se o nome já existe
        this.tagsText.add(tag);

        if (this.tagsText.size() > 7)
        {
            this.aumentarPainelEspecificacao();
        }

        /* Mostrando a especificação na tela de adicionar produtos, para que
         * fique visível ao usuário
         */

        tag.setText(tag.getText() + "\n");
        this.textFlowTag.getChildren().add(tag);
    }

    public void adicionarEspecificacaoNoCampo(ActionEvent action)
    {
        // Bloco abaixo vai pra outra função
        // =================================
        /* Adicionando a especificação no array de especificações */

        // String especificacaoNome = this.especificacaoNome.getText();
        // String especificacaoValor = this.especificacaoValor.getText();

        // Especificacao novaEspecificacao = new Especificacao(especificacaoNome, especificacaoValor);

        // this.especificacoes.add(novaEspecificacao);

        // =================================

        /* Adicionando a especificação no array de texto */
        Text especificacao = new Text(this.especificacaoNome.getText() + " : " + this.especificacaoValor.getText());

        // checar se o nome já existe
        this.especificacoesText.add(especificacao);

        if (this.especificacoesText.size() > 7)
        {
            this.aumentarPainelEspecificacao();
        }

        /* 
         * Mostrando a especificação na tela de adicionar produtos, para que
         * fique visível ao usuário
         */

        especificacao.setText(especificacao.getText() + "\n");
        this.textFlowEspecificacao.getChildren().add(especificacao);
    }
    
    public void excluirTag(ActionEvent action)
    {
        String nomeTag = this.removerTagCampo.getText();

        /* Remover especificacao da lista */
        Iterator<Text> iterator = this.tagsText.iterator();

        while (iterator.hasNext()) 
        {
            Text tag = iterator.next();

            if (tag.getText().contains(nomeTag)) 
            {
                iterator.remove();
            }
        }

        /* Remover especificacao da tela */
        Iterator<Node> iteratorTela = this.textFlowTag.getChildren().iterator();

        while (iteratorTela.hasNext()) 
        {
            Node children = iteratorTela.next();

            if (children instanceof Text) {
                Text text = (Text) children;

                if (text.getText().contains(nomeTag)) 
                {
                    iteratorTela.remove();
                }
            }
        }
    }

    public void excluirEspecificacao(ActionEvent action)
    {
        String nomeEspecificacao = this.removerEspecificacaoCampo.getText();

        /* Remover especificacao da lista */
        Iterator<Text> iterator = this.especificacoesText.iterator();

        while (iterator.hasNext()) 
        {
            Text especificacao = iterator.next();

            if (especificacao.getText().contains(nomeEspecificacao)) 
            {
                iterator.remove();
            }
        }

        /* Remover especificacao da tela */
        Iterator<Node> iteratorTela = this.textFlowEspecificacao.getChildren().iterator();

        while (iteratorTela.hasNext()) 
        {
            Node children = iteratorTela.next();

            if (children instanceof Text) {
                Text text = (Text) children;

                if (text.getText().contains(nomeEspecificacao)) 
                {
                    iteratorTela.remove();
                }
            }
        }
    }

    public void aumentarPainelEspecificacao()
    {
        this.anchorEspecificacao.setPrefHeight(this.anchorEspecificacao.getHeight() + 5);
    }

    public void aumentarPainelTag()
    {
        this.anchorTag.setPrefHeight(this.anchorTag.getHeight() + 5);
    }

    public void voltarParaOInicio(ActionEvent action)
    {
        this.mudarScene("ScreenLogged.fxml");
    }

    public void cadastrarFotoProduto(ActionEvent action)
    {
        String filePath = this.abrirFileChooser(action);

        String caminhoPastaDestino = "src/img/users";

        String nomeDaImagem = null;

        Random random = new Random();
        int numeroIntervalo = random.nextInt(1, 3123123) + 1;

        // TODO: só copiar a imagem quando o usuário clicar em cadastrar produto
        try
        {
            nomeDaImagem = this.copiarImagem(filePath, caminhoPastaDestino, 0, String.valueOf(numeroIntervalo));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        String caminhoFinal = "../../img/users/" + nomeDaImagem;

        while(true)
        {
            try
            {
                Image image = new Image(getClass().getResource(caminhoFinal).toExternalForm());
                this.fotoProdutoImg.setImage(image);
                break;
            }
            catch (Exception e)
            {

            }
        }
    }

    public void cadastrarProdutoBD(ActionEvent action)
    {

    }

    public void adicionarCategoriaLista(ActionEvent action)
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

    @FXML
    private TextField removerEspecificacaoCampo;

    @FXML
    private TextField removerTagCampo;

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
     *  ImageView
     */

    @FXML 
    private ImageView fotoProdutoImg;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    ArrayList<Tag> tags;
    ArrayList<Especificacao> especificacoes;

    ArrayList<Text> especificacoesText;
    ArrayList<Text> tagsText;

    ArrayList<MenuItem> categoriasMenuItens;
}
