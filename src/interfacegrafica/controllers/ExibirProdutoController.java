package interfacegrafica.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.LojaDAO;
import DAO.ProdutoDAO;
import interfacegrafica.models.PainelProduto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import sistema.Especificacao;
import sistema.Loja;
import sistema.Produto;
import sistema.Tag;
import sistema.ProdutoEletronico.ColunaProdutoEletronico;
import sistema.ProdutoLivro.ColunaProdutoLivro;
import sistema.ProdutoMobilia.ColunaProdutoMobilia;
import sistema.ProdutoModa.ColunaProdutoModa;

/**
 * 
 * Classe responsável por definir as implementações da tela de exibir os detalhes de um produto.
 * 
 * @author Glenda
 * 
 */
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
            this.exibirProduto(produtoDAO.selectById(ControllerLogged.idProdutoAtual));
        });
    }

    /**
     * Função acionada quando o usuário clicar no botão "sair"
     * 
     * A função irá voltar para o inicio do programa.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void voltarParaInicio(ActionEvent action)
    {
        this.mudarScene("ScreenLogged.fxml");
    }

    /**
     * Função acionada quando o usuário clicar no botão "remover produto"
     * 
     * A função irá remover o produto do banco de dados e exibir uma janela informando ao usuário
     * que o produto foi removido. Logo após, irá voltar para a tela de inicio.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void removerProduto(ActionEvent action)
    {
        Produto produtoAtual = produtoDAO.selectById(ControllerLogged.idProdutoAtual);
        produtoDAO.delete(produtoAtual);

        this.abrirErroStage("O produto foi removido do sistema!");
        this.voltarParaInicio(action);
    }

    /**
     * Função acionada quando o usuário clicar no botão "editar produto"
     * 
     * A função irá setar o parâmetro de edição de produto para "true" na tela seguinte
     * e carregar a tela.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void editarProduto(ActionEvent action)
    {
        /* 
        * Boolean indicando que o carregamento da página será para
        * editar um produto.
        */
        ControllerLogged.editarProduto = true;

        this.mudarScene("ScreenAdicionarProduto.fxml");
    }

    /**
     * Função para setar os objetos da tela com as informações de um produto.
     * 
     * @param produto
     * Produto o qual as informações serão exibidas na tela.
     */
    private void exibirProduto(Produto produto)
    {
        PainelProduto painelProduto = new PainelProduto(produto);

        this.nomeProduto.setText(painelProduto.getNomeDoProduto());
        this.valorProduto.setText(painelProduto.getValorProdutoString());
        this.valorArrecadado.setText(painelProduto.getValorArrecadadoString());
        this.valorFaltam.setText(painelProduto.getValorFaltamString());
        this.linkProduto.setText(painelProduto.getUrlProduto());

        if (!painelProduto.getUrlImagem().isEmpty())
        {
            Image img = new Image(painelProduto.getUrlImagem());
            this.fotoProduto.setImage(img);
        }

        this.mostrarPorcentagemParaValorProduto(produto.getValorArrecadado(), produto.getPreco() + produto.getValorFrete());
        this.setarEspecificacoes(produto.getEspecificacoes());
        this.setarTags(produto.getTags());
        this.setarDescricao(produto.getDescricao());
        this.setarLojaDoProduto();
        
        this.setarAtributosCategoria(produto.getCategoria());
    }

    /**
     * Função para setar os objetos da tela com as especificações de um produto.
     * 
     * @param especificacoes
     * Especificações do produto o qual as informações serão exibidas na tela.
     */
    private void setarEspecificacoes(ArrayList<Especificacao> especificacoes)
    {
        Font systemFont = Font.font("Arial", FontPosture.ITALIC, 17);

        if (especificacoes == null)
        {
            return;
        }

        for (Especificacao especificacao : especificacoes)
        {
            Text textEspecificacao = new Text(especificacao.getNome() + " : " + especificacao.getValor() + "\n\n");
            textEspecificacao.setFont(systemFont);

            this.textFlowEspecificacao.getChildren().add(textEspecificacao);
        }

        this.anchorEspecificacao.setPrefHeight(this.textFlowEspecificacao.getMaxHeight());
    }

    /**
     * Função para setar os objetos da tela com as tags de um produto.
     * 
     * @param tags
     * Tags do produto o qual as informações serão exibidas na tela.
     */
    private void setarTags(ArrayList<Tag> tags)
    {
        Font systemFont = Font.font("Arial", FontPosture.ITALIC, 17);

        if (tags == null)
        {
            return;
        }

        for (Tag tag : tags)
        {
            Text textTag = new Text('"' + tag.getNome() + '"' + " ");
            textTag.setFont(systemFont);

            this.textFlowTag.getChildren().add(textTag);
        }

        this.anchorTag.setPrefHeight(this.textFlowTag.getMaxHeight());
    }

    /**
     * Função para setar os objetos da tela com a descricao de um produto.
     * 
     * @param descricao
     * Descricao do produto o qual as informações serão exibidas na tela.
     */
    private void setarDescricao(String descricao)
    {
        Font systemFont = Font.font("Arial", FontPosture.ITALIC, 17);

        Text descricaoText = new Text(descricao);
        descricaoText.setFont(systemFont);

        this.textFlowDescricao.getChildren().add(descricaoText);
        this.anchorDescricao.setPrefHeight(this.textFlowDescricao.getMaxHeight());
    }   

    /**
     * Função para setar os objetos da tela com a loja associada a um produto.
     * 
     * A função irá puxar as informações da loja do banco de dados e atribuir as informações
     * aos objetos necessários.
     */
    private void setarLojaDoProduto()
    {
        Loja loja = null;
        Produto produto = produtoDAO.selectById(ControllerLogged.idProdutoAtual);
        
        if(produto != null)
        {
            loja = lojaDAO.selectById(produto.getIdLoja());
        }

        if (loja != null)
        {
            this.nomeDaLoja.setText(loja.getNome());

            Image imgLoja = new Image(loja.getUrl_foto());
            this.fotoDaLoja.setImage(imgLoja);
        }
        else
        {
            this.nomeDaLoja.setText("");
        }
    }

    /**
     * Função para setar os objetos da tela associados com a categoria de um produto.
     * 
     * A função irá puxar as informações necessárias do banco de dados.
     * 
     * @param categoria
     * Categoria do produto o qual as informações serão exibidas na tela.
     */
    private void setarAtributosCategoria(String categoriaProduto)
    {
        this.nomeCategoria.setText(categoriaProduto);

        String autor = null;
        String genero = null;
        String tamanho = null;
        String cor = null;
        String material = null;
        String largura = null;
        String altura = null;
        String comprimento = null;

        if (categoriaProduto.equals(Produto.Categorias.LIVRO.getCategoria()))
        {
            autor = produtoDAO.getColunaDeProduto(ColunaProdutoLivro.AUTOR.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            genero = produtoDAO.getColunaDeProduto(ColunaProdutoLivro.GENERO.getNomeColuna(), ControllerLogged.idProdutoAtual);
        }

        else if (categoriaProduto.equals(Produto.Categorias.ROUPA.getCategoria()) || categoriaProduto.equals(Produto.Categorias.ACESSORIO.getCategoria()) || categoriaProduto.equals(Produto.Categorias.CALCADO.getCategoria()))
        {
            tamanho = produtoDAO.getColunaDeProduto(ColunaProdutoModa.TAMANHO.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            cor = produtoDAO.getColunaDeProduto(ColunaProdutoModa.COR.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            material = produtoDAO.getColunaDeProduto(ColunaProdutoModa.MATERIAL.getNomeColuna(), ControllerLogged.idProdutoAtual);
        }
        
        else if (categoriaProduto.equals(Produto.Categorias.ELETRONICO.getCategoria()) || categoriaProduto.equals(Produto.Categorias.ELETRODOMESTICO.getCategoria()))
        {
            cor = produtoDAO.getColunaDeProduto(ColunaProdutoEletronico.COR.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            material = produtoDAO.getColunaDeProduto(ColunaProdutoEletronico.MATERIAL.getNomeColuna(), ControllerLogged.idProdutoAtual);
        }

        else if (categoriaProduto.equals(Produto.Categorias.MOBILIA.getCategoria()) || categoriaProduto.equals(Produto.Categorias.CASAEJARDIM.getCategoria()) || categoriaProduto.equals(Produto.Categorias.AUTOMOTIVO.getCategoria()) || categoriaProduto.equals(Produto.Categorias.FERRAMENTA.getCategoria()))
        {
            cor = produtoDAO.getColunaDeProduto(ColunaProdutoMobilia.COR.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            material = produtoDAO.getColunaDeProduto(ColunaProdutoMobilia.MATERIAL.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            altura = produtoDAO.getColunaDeProduto(ColunaProdutoMobilia.ALTURA.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            largura = produtoDAO.getColunaDeProduto(ColunaProdutoMobilia.LARGURA.getNomeColuna(), ControllerLogged.idProdutoAtual);
            
            comprimento = produtoDAO.getColunaDeProduto(ColunaProdutoMobilia.COMPRIMENTO.getNomeColuna(), ControllerLogged.idProdutoAtual);
        }

        Font systemFont = Font.font("System", FontPosture.REGULAR, 15);

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

    /**
     * Função para setar a barra de progresso do produto.
     * 
     * @param valorArrecadado
     * Valor arrecadado do produto
     * 
     * @param valorProduto
     * Preço total do produto
     */
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

    @FXML
    private TextField linkProduto;

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
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private LojaDAO lojaDAO = new LojaDAO();
}
