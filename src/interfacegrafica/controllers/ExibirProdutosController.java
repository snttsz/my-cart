package interfacegrafica.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.swing.Action;

import interfacegrafica.models.PaginaProduto;
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
import sistema.Produto;


/**
 * 
 * Classe responsável por definir as implementações da tela de exibir lista de produtos.
 * 
 * @author Glenda
 * 
 */
public class ExibirProdutosController extends ControllerLogged
{
    /**
     * Override do método padrão de inicialização da classe Controller do javafx.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        super.initialize(location, resources);

        this.pilhaPaginas = new Stack<PaginaProduto>();

        this.produtos = new ArrayList<Integer>();

        Platform.runLater(() -> 
        {
            this.inicializarPaineis();

            this.puxarProdutos();

            this.addNovaPagina();

            this.marcadorProdutoAtual += 6;
            
            this.checarBotoesProxAnt();
            
            this.exibirPagina();
        });
    }

    private void puxarProdutos()
    {
        if (ControllerLogged.labelPaginaProdutoAtual.equals(labelsPaginasProduto.CATEGORIA.getNomeLabel()))
        {
            this.qtdTotalProdutos = this.produtoDAO.contarProdutosCategorizadosDoUsuario(ControllerLogged.idUsuario, ControllerLogged.valorLabelAtual);

            this.produtos = this.produtoDAO.selectProdutosCategorizadosDoUsuario(ControllerLogged.idUsuario, ControllerLogged.valorLabelAtual);
        }
        if (ControllerLogged.labelPaginaProdutoAtual.equals(labelsPaginasProduto.NOME.getNomeLabel()))
        {
            this.qtdTotalProdutos = this.produtoDAO.countProdutosPorColuna("Produto." + Produto.Coluna.NOME.getNomeColuna(), ControllerLogged.valorLabelAtual, ControllerLogged.idUsuario);

            this.produtos = this.produtoDAO.selectProdutosPorColuna("Produto." + Produto.Coluna.NOME.getNomeColuna(), ControllerLogged.valorLabelAtual, ControllerLogged.idUsuario);
        }
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
            this.mudarScene("ScreenAdicionarProduto.fxml");
        }   
        else if (source.getId() == adicionarLoja.getId())
        {
            this.mudarScene("ScreenCadastrarLoja.fxml");
        }
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
    public void proximaPagina(ActionEvent action)
    {
        this.marcadorProdutoAtual += 6;

        this.addNovaPagina();

        this.checarBotoesProxAnt();

        this.exibirPagina();
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
    public void paginaAnterior(ActionEvent action)
    {
        this.pilhaPaginas.pop();

        this.marcadorProdutoAtual -= 6;

        this.checarBotoesProxAnt();

        this.exibirPagina();
    }

    /**
     * Função para desabilitar os botões de próxima página e página anterior quando necessário.
     */
    private void checarBotoesProxAnt()
    {
        if (this.marcadorProdutoAtual >= this.qtdTotalProdutos)
        {
            this.proxPagina.setOpacity(0.5);
            this.proxPagina.setDisable(true);
        }
        else
        {
            this.proxPagina.setOpacity(1);
            this.proxPagina.setDisable(false);
        }

        if (this.marcadorProdutoAtual <= 6)
        {
            this.antPagina.setOpacity(0.5);
            this.antPagina.setDisable(true);
        }
        else
        {
            this.antPagina.setOpacity(1);
            this.antPagina.setDisable(false);
        }
    }

    /**
     * Função para adicionar uma nova página na pilha de páginas.
     */
    private void addNovaPagina()
    {
        Produto[] produtos = new Produto[6];

        produtos[0] = null;
        produtos[1] = null;
        produtos[2] = null;
        produtos[3] = null;
        produtos[4] = null;
        produtos[5] = null;

        for (int i = this.marcadorProdutoAtual; i < marcadorProdutoAtual + 6; i++)
        {
            try
            {
                produtos[i] = this.produtoDAO.selectById(this.produtos.get(i));
            }
            catch (IndexOutOfBoundsException exception)
            {

            }
        }

        PaginaProduto paginaProduto = new PaginaProduto(produtos);

        this.pilhaPaginas.push(paginaProduto);
    }

    /**
     * Função para inicializar o vetor de painéis de produtos do javafx.
     */
    private void inicializarPaineis()
    {
        this.paineisProdutos = new PainelProdutoJAVAFX[6];

        this.paineisProdutos[0] = new PainelProdutoJAVAFX(this.produto1, this.nomeProduto, this.valorPrecoProduto, this.valorArrecadadoProduto, this.valorFaltamProduto, this.fotoProduto);
        this.paineisProdutos[1] = new PainelProdutoJAVAFX(this.produto2, this.nomeProduto1, this.valorPrecoProduto1, this.valorArrecadadoProduto1, this.valorFaltamProduto1, this.fotoProduto1);
        this.paineisProdutos[2] = new PainelProdutoJAVAFX(this.produto3, this.nomeProduto2, this.valorPrecoProduto2, this.valorArrecadadoProduto2, this.valorFaltamProduto2, this.fotoProduto2);
        this.paineisProdutos[3] = new PainelProdutoJAVAFX(this.produto4, this.nomeProduto3, this.valorPrecoProduto3, this.valorArrecadadoProduto3, this.valorFaltamProduto3, this.fotoProduto3);
        this.paineisProdutos[4] = new PainelProdutoJAVAFX(this.produto5, this.nomeProduto4, this.valorPrecoProduto4, this.valorArrecadadoProduto4, this.valorFaltamProduto4, this.fotoProduto4);
        this.paineisProdutos[5] = new PainelProdutoJAVAFX(this.produto6, this.nomeProduto5, this.valorPrecoProduto5, this.valorArrecadadoProduto5, this.valorFaltamProduto5, this.fotoProduto5);
    }

    /**
     * Função para setar os atributos necessários para exibir a página atual.
     */
    private void exibirPagina()
    {
        PaginaProduto paginaAtual = this.pilhaPaginas.firstElement();

        this.paineisProdutos[0].atualizarAtributosComPainelProduto(paginaAtual.getProduto1());
        this.paineisProdutos[1].atualizarAtributosComPainelProduto(paginaAtual.getProduto2());
        this.paineisProdutos[2].atualizarAtributosComPainelProduto(paginaAtual.getProduto3());
        this.paineisProdutos[3].atualizarAtributosComPainelProduto(paginaAtual.getProduto4());
        this.paineisProdutos[4].atualizarAtributosComPainelProduto(paginaAtual.getProduto5());
        this.paineisProdutos[5].atualizarAtributosComPainelProduto(paginaAtual.getProduto6());
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

        if (button.getId() == botao_produto.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto1().getIdProduto();
        }
        else if (button.getId() == botao_produto1.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto2().getIdProduto();
        }
        else if (button.getId() == botao_produto2.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto3().getIdProduto();
        }
        else if (button.getId() == botao_produto3.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto4().getIdProduto();
        }
        else if (button.getId() == botao_produto4.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto5().getIdProduto();
        }
        else if (button.getId() == botao_produto5.getId())
        {
            ControllerLogged.idProdutoAtual = this.pilhaPaginas.firstElement().getProduto6().getIdProduto();
        }
        
        this.mudarScene("ScreenExibirProduto.fxml");
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    private Button proxPagina;

    @FXML
    private Button antPagina;

    @FXML 
    private Text labelNome;

    @FXML
    private Text numeroPagina;

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

    @FXML
    private Button botao_produto3;

    @FXML
    private Button botao_produto4;

    @FXML
    private Button botao_produto5;

    /* 
     *  Paineis produto
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

    private PainelProdutoJAVAFX[] paineisProdutos;

    private int qtdTotalProdutos;
    private int marcadorProdutoAtual = 0;

    private Stack<PaginaProduto> pilhaPaginas;

    private ArrayList<Integer> produtos;
}
