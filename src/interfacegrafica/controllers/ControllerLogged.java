package interfacegrafica.controllers;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe abstrata responsável por definir as implementações padrões 
 * disponíveis na tela principal quando um usuário está logado.
 * 
 * @author Glenda
 */
public abstract class ControllerLogged implements Initializable
{
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        Platform.runLater(() -> 
        {
            Stage stage = (Stage) this.root.getScene().getWindow();
            String title = stage.getTitle();

            int indiceDoHifen = title.lastIndexOf("-");

            String username = title.substring(indiceDoHifen + 1).trim();

            if (username.length() > 13)
            {
                username = username.substring(0, 9) + "...";
            }

            this.username.setText(username);
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
        }
    }

    @FXML
    public void cursorOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setCursor(Cursor.HAND);
    }

    @FXML
    public void cursorNormal(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setCursor(Cursor.DEFAULT);
    }

    protected void carregarNovaScene(String fxmlname)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlname));
            Parent novoRoot = loader.load();
            
            Stage stage = (Stage) this.root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);

            stage.setScene(novaScene);
            stage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    protected void mudarScene(String fxmlName)
    {
        this.carregarNovaScene(fxmlName);
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
    *    root
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
    protected Button perfilUsuario;

    /* 
     *    Input texto
     */

    @FXML 
    protected TextField pesquisarField;

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
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    // Constante com o caminho pra pasta resources, onde está os arquivos .fxml
    private final String pathResources = "../../resources/";

}
