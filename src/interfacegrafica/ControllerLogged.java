package interfacegrafica;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.security.Key;
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

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == inicio.getId())
            {
                inicio.setOpacity(0);
            }
            else if (botaoSource.getId() == categorias.getId())
            {
                categorias.setOpacity(0);
            }
            else if (botaoSource.getId() == lojasCadastradas.getId())
            {
                lojasCadastradas.setOpacity(0);
            }
        }
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

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == inicio.getId())
            {
                inicio.setOpacity(0.17);
            }
            else if (botaoSource.getId() == categorias.getId())
            {
                categorias.setOpacity(0.17);
            }
            else if (botaoSource.getId() == lojasCadastradas.getId())
            {
                lojasCadastradas.setOpacity(0.17);
            }
        }
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

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == inicio.getId())
            {
                inicio.setOpacity(0.3);
            }
            else if (botaoSource.getId() == categorias.getId())
            {
                categorias.setOpacity(0.3);
            }
            else if (botaoSource.getId() == lojasCadastradas.getId())
            {
                lojasCadastradas.setOpacity(0.3);
            }
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

        if (pesquisarField.getText().isEmpty())
        {
           
            pesquisarField.setOpacity(0.65);
            pesquisarField.setPromptText("Pesquisar um produto");
            
            Font systemFont = Font.font(fontName, FontPosture.ITALIC, fontSize);
            pesquisarField.setFont(systemFont);
        
        }
        else
        {
            
            pesquisarField.setOpacity(1);
            
            Font systemFont = Font.font(fontName, FontPosture.REGULAR, fontSize);
            pesquisarField.setFont(systemFont);
        
        }
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

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


}
