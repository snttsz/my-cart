package interfacegrafica.controllers;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sistema.Produto;
import sistema.Usuario;
import sistema.Produto.Categorias;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.LojaDAO;
import DAO.ProdutoDAO;

/**
 * Classe abstrata responsável por definir as implementações padrões 
 * disponíveis na tela principal quando um usuário está logado.
 * 
 * @author Glenda
 */
public abstract class ControllerLogged extends Controller implements Initializable
{
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        // Exemplo de titulo: My Cart - NomeDoUsuario_idDoUsuario
        Platform.runLater(() -> 
        {
            Stage stage = (Stage) this.root.getScene().getWindow();
            String title = stage.getTitle();

            /* Pegando índice do "-" */
            int indiceDoHifen = title.lastIndexOf("-");
            
            /* Removendo o conteúdo antes do "-" */
            String username = title.substring(indiceDoHifen + 1).trim();

            /* Setando o título da janela com o nome do usuário */
            stage.setTitle("MyCart - " + username);
            
            username = this.tratarNomeDoUsuario(username);

            /* Setando o nome de usuário na interface */
            this.username.setText(username);
            
            /* 
             * Setando a foto do usuário na interface
             */

            /* Puxando foto do banco de dados */
            String caminhoFoto = this.usuariosDAO.selectById(Controller.idUsuario).getUrl_foto();

            /* Mostrando foto na interface */
            Image image = new Image(getClass().getResource(caminhoFoto).toExternalForm());
            this.fotoUsuario.setImage(image);

            /* Adicionando categorias no menu */
            this.carregarCategorias();
        });
    }

    /**
     * Função acionada quando o mouse está fora de um botão do menu.
     * A função irá setar a opacidade do botão para 0 (tornando-o invisível).
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void botaoMenuMouseOff(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();
        Button botaoSource = (Button) source;
        
        botaoSource.setOpacity(0.0);
    }

    /**
     * Função acionada quando o mouse está sobre um botão do menu.
     * 
     * A função irá setar a opacidade do botão para 0.17. Isso fará
     * com que a cor do botão ganhe um efeito de "brilho", para indicar
     * que o mouse está sobre o botão e o clique naquele espaço acionará
     * uma função do programa.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void botaoMenuMouseOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();
        Button botaoSource = (Button) source;

        botaoSource.setOpacity(0.17);
        
        /*  
         * Seta o cursor para "HAND" (mãozinha)
        */
        this.cursorOn(mouse);
    }

    /**
     * Função acionada quando um botão do menu é clicado.
     * 
     * A função irá setar a opacidade do botão para 0.3. Isso fará
     * com que a cor do botão ganhe um efeito de "brilho", para indicar
     * que o botão foi acionado e uma função será executada.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void botaoMenuClicked(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();
        Button botaoSource = (Button) source;

        botaoSource.setOpacity(0.3);

        /*  
        * Seta o cursor pra "DEFAULT" (ponteiro)
        */
        this.cursorNormal(mouse);

        /*  
        * Checa se o botão clicado foi "Categorias" para exibir o menu lateral
        */
        if (botaoSource.getId() == this.categorias.getId())
        {
            if (this.categoriasMenu.isShowing())
            {
                this.categoriasMenu.hide();
            }
            else
            {
                this.categoriasMenu.show();
            }
            
            this.setaInicio.setOpacity(0);
            this.setaLojasCadastradas.setOpacity(0);
        }
        /* 
         * Checa se o botão clicado foi "lojas cadastradas" para exibir 
         * a tela de lojas cadastradas.
         */
        else if (botaoSource.getId() == this.lojasCadastradas.getId())
        {
            ArrayList<Integer> lojasCadastradas = this.lojaDAO.selectLojaUsuario(Controller.idUsuario);

            if (lojasCadastradas.size() == 0)
            {
                this.abrirErroStage("Não há lojas cadastradas ainda!");
            }
            else
            {
                this.mudarScene("ScreenLojasCadastradas.fxml");
            }
        }
        /* 
         * Checa se o botão clicado foi "inicio" para exibir a tela inicial.
         */
        else if (botaoSource.getId() == this.inicio.getId())
        {
            this.mudarScene("ScreenLogged.fxml");;
        }
    }

    /**
     * Função acionada quando o campo de pesquisar é focado e uma tecla é digitada.
     * A função irá gerenciar ações quando teclas são digitadas no campo ou quando
     * o campo é tirado de foco.
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML 
    public void opacityPesquisaField(KeyEvent key)
    {
        String fontName = "System";
        double fontSize = 17.0;

        /* 
         * Se o campo de pesquisa estiver em branco, restaura o prompt text pro 
         * default.
         */
        if (this.pesquisarField.getText().isEmpty())
        {
           
            this.pesquisarField.setOpacity(0.65);
            this.pesquisarField.setPromptText("Pesquisar um produto");
            
            Font systemFont = Font.font(fontName, FontPosture.ITALIC, fontSize);
            this.pesquisarField.setFont(systemFont);
        
        }
        /* 
         * Caso alguma tecla for digitada, define as configurações de estilo e fonte do
         * texto.
         */
        else
        {
            this.pesquisarField.setOpacity(1);
            
            Font systemFont = Font.font(fontName, FontPosture.REGULAR, fontSize);
            this.pesquisarField.setFont(systemFont);

            if (key.getCode() == KeyCode.ENTER)
            {
                this.pesquisarProdutoPorNome(this.pesquisarField.getText());
            }
        }
    }

    private void pesquisarProdutoPorNome(String nomeDoProduto)
    {
        ArrayList<Integer> produtos = this.produtoDAO.selectProdutosPorColuna("Produto." + Produto.Coluna.NOME.getNomeColuna(), nomeDoProduto, ControllerLogged.idUsuario);
        
        if (produtos.size() > 0)
        {
            labelPaginaProdutoAtual = labelsPaginasProduto.NOME.getNomeLabel();
            valorLabelAtual = this.pesquisarField.getText();

            this.mudarScene("ScreenExibirProdutos.fxml");
        }
        else
        {
            this.abrirErroStage("Nenhum produto foi cadastrado com esse nome!");
        }

    }

    /**
     * Função para mudar o cursor do mouse para a "mãozinha"
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void cursorOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setCursor(Cursor.HAND);
    }

    /**
     * Função para mudar o cursor do mouse para default
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void cursorNormal(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setCursor(Cursor.DEFAULT);
    }

    /**
     * Função para mudar a scene da janela.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    protected void mudarScene(String fxmlName)
    {
        this.carregarNovaScene(fxmlName, true, root);
    }

    /**
     * Função acionada quando o usuário clica no botão para sair da conta.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void sairDaConta(ActionEvent action)
    {
        this.carregarNovaScene("LoginScreen2.fxml", false, root);
    }

    /**
     * Função acionada quando o usuário move o mouse pelo Node onde está
     * a foto de perfil.
     * 
     * A função exibirá um texto abaixo do cursor indicando que ao clicar no local,
     * sera possível modificar a foto de perfil.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void exibirTextoTrocarFotoUsuario(MouseEvent mouse)
    {
        this.exibirTextoDeAjuda(this.trocarFotoUsuario, "Alterar sua foto");
    }

    /**
     * Função acionada quando o usuário move o mouse pelo Node onde está
     * o nome de usuário.
     * 
     * A função exibirá um texto abaixo do cursor indicando que ao clicar no local,
     * sera possível modificar seu nome.
     * 
     * @param mouse
     * Objeto MouseEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void exibirTextoTrocarNomeUsuario(MouseEvent mouse)
    {
        this.exibirTextoDeAjuda(this.trocarNomeUsuario, "Alterar seu nome");
    }

    /**
     * Função acionada quando o usuário clica em sua foto de perfil.
     * 
     * A função irá abrir o explorador de arquivos para que o usuário
     * escolha uma nova imagem de perfil.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void alterarFotoUsuario(ActionEvent action)
    {
        String filepath = this.abrirFileChooser(action);

        if (filepath == null)
        {
            return;
        }

        String caminhoPastaDestino = "src/img/users/";

        String nomeDaImagem = null;

        try
        {
            nomeDaImagem = this.copiarImagem(filepath, caminhoPastaDestino, Controller.idUsuario, this.puxarNomeDoUsuario());
            
            String caminhoFinal = "../../img/users/" + nomeDaImagem;
    
            while(true)
            {
                try
                {
                    Image image = new Image(getClass().getResource(caminhoFinal).toExternalForm());
                    this.fotoUsuario.setImage(image);
                    break;
                }
                catch (Exception e)
                {
    
                }
            }
    
            /* Cadastrar a foto no banco de dados */
            this.setarFotoUsuarioNoBanco(caminhoFinal);
        }
        catch (IOException e)
        {
            // e.printStackTrace();
        }
    }

    /**
     * Função acionada quando o usuário clica no prórpio nome na área
     * de perfil do usuário.
     * 
     */
    @FXML
    protected void mostrarCampoTrocarNomeUsuario()
    {
        this.trocarNomeUsuario.toBack();
        
        this.novoNomeUsuario.toFront();
        this.novoNomeUsuario.setOpacity(1);
        this.novoNomeUsuario.requestFocus();
    }

    /**
     * Função acionada quando o usuário digita algo com o textfield trocarNomeUsuario
     * em foco. 
     * 
     * A função verifica se a tecla pressionada é enter para confirmar que o usuário
     * terminou de inserir o novo nome e chama os métodos corretos para fazer as alterações
     * no banco de dados.
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void checarEnterTrocaNomeUsuario(KeyEvent key)
    {
        if (key.getCode() == KeyCode.ENTER)
        {
            this.trocarNomeUsuario.toFront();

            this.novoNomeUsuario.setOpacity(0);
            this.novoNomeUsuario.toBack();

            if (!novoNomeUsuario.getText().isEmpty())
            {
                this.alterarNomeDoUsuarioNoBD();
            }
        }
    }   

    /**
     * Função acionada quando o usuário move o mouse para fora do textfield
     * para definir um novo nome de usuário
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    protected void cancelarTrocaNome(MouseEvent mouse)
    {
        this.trocarNomeUsuario.toFront();

        this.novoNomeUsuario.setOpacity(0);
        this.novoNomeUsuario.toBack();
    }

    /**
     * Função para alterar o nome do usuário no banco de dados.
     */
    protected void alterarNomeDoUsuarioNoBD()
    {
        String novoNome = this.novoNomeUsuario.getText();

        novoNome = this.tratarNomeDoUsuario(novoNome);
        
        this.username.setText(novoNome);

        Usuario usuario = this.usuariosDAO.selectById(ControllerLogged.idUsuario);

        this.usuariosDAO.updateNome(usuario, novoNome);
    }

    /**
     * Função para implementar as definições corretas no nome do usuário
     * para exibi-lo na tela de perfil.
     * 
     * @param username
     * Nome do usuário
     * 
     * @return
     * String com o nome do usuário tratado corretamente.
     */
    protected String tratarNomeDoUsuario(String username)
    {
        /* 
        * Caso o nome do usuário seja composto ou haja um sobrenome,
        * mostra apenas o primeiro nome.
        */
        if (username.contains(" "))
        {
            int indexOf = username.indexOf(" ");

            username = username.substring(0, indexOf);
        }

        /* Se o nome contém mais que 13 letras, encurta substituindo com "..." */
        if (username.length() > 13)
        {
            username = username.substring(0, 9) + "...";
        }

        return username;
    }

    private void carregarCategorias()
    {
        this.categoriasMenu.getItems().clear();
        
        Categorias[] categorias = Categorias.values();
        
        for (Categorias categoria : categorias)
        {
            MenuItem novaCategoria = new MenuItem(categoria.getCategoria());

            EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event)
                {
                    categoriasMenu.setText(categoria.getCategoria());
                    
                    labelPaginaProdutoAtual = labelsPaginasProduto.CATEGORIA.getNomeLabel();
                    valorLabelAtual = categoria.getCategoria();
                    
                    int qtdTotalProdutos = produtoDAO.contarProdutosCategorizadosDoUsuario(ControllerLogged.idUsuario, ControllerLogged.valorLabelAtual);
                    
                    if (qtdTotalProdutos > 0)
                    {
                        mudarScene("ScreenExibirProdutos.fxml");
                    }
                    else
                    {
                        abrirErroStage("Não há produtos cadastrados nesta categoria!");
                    }
                }
            };

            novaCategoria.setOnAction(eventHandler);

            this.categoriasMenu.getItems().add(novaCategoria);
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
     *    Botões
     */

    @FXML
    protected Button inicio;

    @FXML
    protected Button categorias;

    @FXML
    protected Button lojasCadastradas;

    @FXML
    protected Button pesquisar;
    
    @FXML
    protected Button logomarcaInicio;
    
    @FXML
    protected Button trocarUsuario;

    @FXML
    protected Button trocarFotoUsuario;

    @FXML
    protected Button trocarNomeUsuario;

    /* 
     *    Input texto
     */

    @FXML 
    protected TextField pesquisarField;

    @FXML
    protected TextField novoNomeUsuario;

    /* 
     *    Figuras geométricas
     */

    @FXML
    protected Polygon setaInicio;

    @FXML
    protected Polygon setaCategorias;

    @FXML
    public Polygon setaLojasCadastradas;

    /* 
     *    Menu
     */

    @FXML 
    protected SplitMenuButton categoriasMenu;

    /* 
     *    Panes
     */

    @FXML
    protected ScrollPane scrollPane;

    @FXML
    // Pane inside scrollPane
    protected AnchorPane mainPane;

    /* 
     *    Texto
     */

    @FXML
    protected Text username;

    /* 
     *   ImageView
     */

    @FXML
    protected ImageView fotoUsuario;

    /* 
     * 
     *      Atributos internos
     * 
     */

    protected static int idProdutoAtual;
    protected static boolean editarProduto;
    
    protected static String labelPaginaProdutoAtual;
    protected static String valorLabelAtual;

    public enum labelsPaginasProduto
    {
        TAG("Tag"),
        ESPECIFICACAO("Especificacao"),
        LOJA("Loja"),
        TODOS_OS_PRODUTOS("*"),
        CATEGORIA("Categoria"),
        NOME("Nome");

        private final String nomeLabel;

        labelsPaginasProduto(String nomeLabel)
        {
            this.nomeLabel = nomeLabel;
        }

        public String getNomeLabel()
        {
            return this.nomeLabel;
        }
    }

    protected LojaDAO lojaDAO = new LojaDAO();
    protected ProdutoDAO produtoDAO = new ProdutoDAO();
}
