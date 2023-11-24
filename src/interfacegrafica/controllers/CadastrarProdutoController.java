package interfacegrafica.controllers;

import sistema.Tag;
import sistema.Especificacao;
import sistema.Produto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.input.MouseEvent;
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
        /* Adicionando a especificação no array de texto */
        Text especificacao = new Text(this.especificacaoNome.getText() + " : " + this.especificacaoValor.getText());

        // TODO: checar se o nome já existe
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

    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        /* Excluindo foto do produto se já estiver salva */
        if (adicionouFotoProduto)
        {
            this.excluirFotoDoProduto();
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

        /* Excluindo foto do produto se já estiver salva */
        if (adicionouFotoProduto)
        {
            this.excluirFotoDoProduto();
        }
    }

    public void cadastrarFotoProduto(ActionEvent action)
    {
        String filePath = this.abrirFileChooser(action);

        String caminhoPastaDestino = "src/img/users";

        String nomeDaImagem = null;

        Random random = new Random();
        int numeroIntervalo = random.nextInt(1, 3123123) + 1;

        try
        {
            nomeDaImagem = this.copiarImagem(filePath, caminhoPastaDestino, Controller.idUsuario, String.valueOf(numeroIntervalo));
            this.adicionouFotoProduto = true;
        }
        catch(IOException e)
        {
            //TODO: aparecer erro na tela quando der exception
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
        String nomeDoProduto = this.nomeDoProduto.getText();
        String linkDoProduto = this.linkDoProduto.getText();
        String valorArrecadado = this.valorArrecadado.getText();
        String valorDoProduto = this.valorDoProduto.getText();
        String valorDoFrete = this.valorDoFrete.getText();

        for (Text especificacao : this.especificacoesText) 
        {
            int indexOf = especificacao.getText().lastIndexOf(":");

            String nomeEspecificacao = especificacao.getText().substring(0, indexOf - 1);
            String valorEspecificacao = especificacao.getText().substring(indexOf + 2, especificacao.getText().length());

            Especificacao novaEspecificacao = new Especificacao(nomeEspecificacao, valorEspecificacao);

            this.especificacoes.add(novaEspecificacao);
        }

        for (Text tag : this.tagsText) 
        {
            Tag novaTag = new Tag(tag.getText());

            this.tags.add(novaTag);
        }

        String categoria = this.categoriasSplitDown.getText();

        String cor = this.cor.getText();
        String tamanho = this.tamanho.getText();
        String material = this.material.getText();
        String altura = this.altura.getText();
        String largura = this.largura.getText();
        String comprimento = this.comprimento.getText();
        String autor = this.autor.getText();
        String genero = this.genero.getText();

        String descricao = this.descricao.getText();
        
        String fotoDoProduto = this.fotoProdutoImg.getImage().getUrl();

        /* 
         * TODO: luis
         */
        // Criar produto
    }

    public void adicionarCategoriaLista(MouseEvent mouse)
    {
        /* 
         * TODO: luis
         */
        /* Puxar categorias do banco de dados e botar nesse array */
        ArrayList<String> categoriasLista = new ArrayList<String>();

        this.categoriasSplitDown.getItems().clear();

        for (String categoria : categoriasLista)
        {
            MenuItem novaCategoria = new MenuItem(categoria);

            this.categoriasSplitDown.getItems().add(novaCategoria);
        }
    }

    public void filtrarCamposPorCategoria(ActionEvent action)
    {
        String categoriaProduto = this.categoriasSplitDown.getText();

        this.cor.setOpacity(0.5);
        this.cor.setDisable(true);

        this.tamanho.setOpacity(0.5);
        this.tamanho.setDisable(true);

        this.material.setOpacity(0.5);
        this.material.setDisable(true);

        this.altura.setOpacity(0.5);
        this.altura.setDisable(true);

        this.largura.setOpacity(0.5);
        this.largura.setDisable(true);

        this.comprimento.setOpacity(0.5);
        this.comprimento.setDisable(true);

        this.autor.setOpacity(0.5);
        this.autor.setDisable(true);

        this.genero.setOpacity(0.5);
        this.genero.setDisable(true);

        if (categoriaProduto == Produto.Categorias.LIVRO.getCategoria())
        {
            this.autor.setOpacity(1);
            this.autor.setDisable(false);

            this.genero.setOpacity(1);
            this.genero.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.ROUPA.getCategoria() || categoriaProduto == Produto.Categorias.ACESSORIO.getCategoria() || categoriaProduto == Produto.Categorias.CALCADO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.tamanho.setOpacity(1);
            this.tamanho.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.ELETRONICO.getCategoria() || categoriaProduto == Produto.Categorias.ELETRODOMESTICO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.MOBILIA.getCategoria() || categoriaProduto == Produto.Categorias.CASAEJARDIM.getCategoria() || categoriaProduto == Produto.Categorias.AUTOMOTIVO.getCategoria())
        {
            this.cor.setOpacity(0.5);
            this.cor.setDisable(true);

            this.tamanho.setOpacity(0.5);
            this.tamanho.setDisable(true);

            this.material.setOpacity(0.5);
            this.material.setDisable(true);

            this.altura.setOpacity(0.5);
            this.altura.setDisable(true);

            this.largura.setOpacity(0.5);
            this.largura.setDisable(true);

            this.comprimento.setOpacity(0.5);
            this.comprimento.setDisable(true);
        }
    }

    private void excluirFotoDoProduto()
    {
        int lastIndexOf = this.fotoProdutoImg.getImage().getUrl().lastIndexOf("/");
        String nomeDoArquivo = this.fotoProdutoImg.getImage().getUrl().substring(lastIndexOf + 1, this.fotoProdutoImg.getImage().getUrl().length());

        Path caminhoArquivo = Paths.get("src/img/users/" + nomeDoArquivo);

        try
        {
            Files.delete(caminhoArquivo);
        }
        catch (IOException e)
        {

        }
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

    private ArrayList<Tag> tags;
    private ArrayList<Especificacao> especificacoes;

    private ArrayList<Text> especificacoesText;
    private ArrayList<Text> tagsText;

    private boolean adicionouFotoProduto;
}
