package interfacegrafica.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import interfacegrafica.models.PainelProduto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import sistema.Especificacao;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.ProdutoLivro;
import sistema.Tag;
import sistema.Produto.Categorias;

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

            especificacoes.add(especificacao1);
            especificacoes.add(especificacao1);
            especificacoes.add(especificacao1);
            especificacoes.add(especificacao1);
            especificacoes.add(especificacao2);

            ArrayList<Tag> tags = new ArrayList<Tag>();
            
            Tag tag1 = new Tag("pc");
            Tag tag2 = new Tag("componente de pc");
            
            tags.add(tag1);
            tags.add(tag1);
            tags.add(tag1);
            tags.add(tag1);
            tags.add(tag1);
            tags.add(tag2);

            ProdutoLivro produtoTeste = new ProdutoLivro("Teste Descrição", "Memória Ram", 300, "https://teste.com", "img/myCart.png", 100, 40, Categorias.LIVRO.getCategoria(), especificacoes, tags, "Terror", "Glenda", Controller.idUsuario, LojasCadastradasController.idLojaAtual);
            
            this.exibirProduto(produtoTeste);
        });
    }

    private void exibirProduto(Produto produto)
    {
        PainelProduto painelProduto = new PainelProduto(produto);

        this.nomeProduto.setText(painelProduto.getNomeDoProduto());
        this.valorProduto.setText(painelProduto.getValorProdutoString());
        this.valorArrecadado.setText(painelProduto.getValorArrecadadoString());
        this.valorFaltam.setText(painelProduto.getValorFaltamString());

        this.mostrarPorcentagemParaValorProduto(produto.getValorArrecadado(), produto.getPreco() + produto.getValorFrete());
        this.setarAtributosCategoria(produto.getCategoria());
        this.setarEspecificacoes(produto.getEspecificacoes());
        this.setarTags(produto.getTags());
    }

    private void setarEspecificacoes(ArrayList<Especificacao> especificacoes)
    {
        Font systemFont = Font.font("Arial", FontPosture.ITALIC, 17);

        for (Especificacao especificacao : especificacoes)
        {
            Text textEspecificacao = new Text(especificacao.getNome() + " : " + especificacao.getValor() + "\n\n");
            textEspecificacao.setFont(systemFont);

            this.textFlowEspecificacao.getChildren().add(textEspecificacao);

            if (especificacoes.size() > 2)
            {
                this.anchorEspecificacao.setPrefHeight(this.anchorEspecificacao.getHeight() + 100);
            }
        }
    }

    private void setarTags(ArrayList<Tag> tags)
    {
        Font systemFont = Font.font("Arial", FontPosture.ITALIC, 17);

        for (Tag tag : tags)
        {
            Text textTag = new Text(tag.getNome() + "\n\n");
            textTag.setFont(systemFont);

            this.textFlowTag.getChildren().add(textTag);

            if (tags.size() > 2)
            {
                this.anchorTag.setPrefHeight(this.anchorTag.getHeight() + 100);
            }
        }
    }

    private void setarAtributosCategoria(String categoriaProduto)
    {
        this.nomeCategoria.setText(categoriaProduto);

        /* 
         * TODO: luis
         * Como nós temos alguns atributos que são próprios de cada
         * classe, essa função irá puxar esses atributos a parte
         * e setar as coisas na tela de acordo
         */

        String autor = null;
        String genero = null;
        String tamanho = null;
        String cor = null;
        String material = null;
        String largura = null;
        String altura = null;
        String comprimento = null;

        /* 
         * TODO: luis
         * id do objeto: ControllerLogged.idProdutoAtual
         */
        /* Puxar do banco os atributos dos objetos */

        if (categoriaProduto == Produto.Categorias.LIVRO.getCategoria())
        {
            autor = "Glenda";
            genero = "Terror";
        }
        else if (categoriaProduto == Produto.Categorias.ROUPA.getCategoria() || categoriaProduto == Produto.Categorias.ACESSORIO.getCategoria() || categoriaProduto == Produto.Categorias.CALCADO.getCategoria())
        {
            tamanho = null;
            cor = null;
            material = null;
        }
        else if (categoriaProduto == Produto.Categorias.ELETRONICO.getCategoria() || categoriaProduto == Produto.Categorias.ELETRODOMESTICO.getCategoria())
        {
            cor = null;
            material = null;
        }
        else if (categoriaProduto == Produto.Categorias.MOBILIA.getCategoria() || categoriaProduto == Produto.Categorias.CASAEJARDIM.getCategoria() || categoriaProduto == Produto.Categorias.AUTOMOTIVO.getCategoria())
        {
            material = null;
            cor = null;
            altura = null;
            largura = null;
            comprimento = null;
        }

        Font systemFont = Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, Font.getDefault().getSize());

        if (autor != null)
        {
            this.autor.setText(autor);
            this.autor.setFont(systemFont);
        }
        if (genero != null)
        {
            this.genero.setText(genero);
            this.genero.setFont(systemFont);
        }
        if (tamanho != null)
        {
            this.tamanho.setText(tamanho);
            this.tamanho.setFont(systemFont);
        }
        if (cor != null)
        {
            this.cor.setText(cor);
            this.cor.setFont(systemFont);
        }
        if (material != null)
        {
            this.material.setText(material);
            this.material.setFont(systemFont);
        }
        if (altura != null)
        {
            this.altura.setText(altura);
            this.altura.setFont(systemFont);
        }
        if (largura != null)
        {
            this.altura.setText(altura);
            this.altura.setFont(systemFont);
        }
        if (comprimento != null)
        {
            this.comprimento.setText(comprimento);
            this.comprimento.setFont(systemFont);
        }
    }

    private void mostrarPorcentagemParaValorProduto(double valorArrecadado, double valorProduto)
    {
        double porcentagem = (100 * valorArrecadado) / valorProduto;
        double porcentagemDecimal = porcentagem / 100;

        double tamanhoBarra = this.widthBarraProduto * porcentagemDecimal;

        this.porcentagemProduto.setText(new DecimalFormat("#." + "0".repeat(2)).format(porcentagem) + "%");
        this.retanguloPorcentagem.setWidth(tamanhoBarra);
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
    private Text nomeCategoria;

    @FXML
    private Text porcentagemProduto;

    @FXML
    private Text nomeProduto;

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

    /* 
     *  Figuras Geométricas
     */

    @FXML
    private Rectangle retanguloPorcentagem;

    /* 
     *  Paineis
     */

    @FXML
    private AnchorPane anchorTag;

    @FXML
    private AnchorPane anchorEspecificacao;

    @FXML
    private AnchorPane anchorDescricao;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    // Constante com a largura padrão da barra do produto
    private final int widthBarraProduto = 213;
}
