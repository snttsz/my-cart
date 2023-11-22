package interfacegrafica.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


/**
 * 
 * Classe responsável por definir as implementações da tela de login do usuário.
 * 
 * @author Glenda
 * 
 */
public class LoginController extends ControllerBeforeLogin
{
    /**
     * Função acionada quando o usuário clicar no botão "entrar"
     * 
     * A função irá checar se os dados inseridos nos campos de textos
     * são válidos para realizar um login.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void checarAutenticacao(ActionEvent action)
    {
        this.checarDadosInseridos();
    }

    /**
     * Função acionada quando o usuário estiver dentro dos campos
     * usuario ou senha e pressionar "enter"
     * 
     * A função irá checar se os dados inseridos nos campos de textos
     * são válidos para realizar um login.
     * 
     * @param key
     * Objeto KeyEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void entrarComEnter(KeyEvent key)
    {
        if (key.getCode() == KeyCode.ENTER)
        {
            this.checarDadosInseridos();
        }
    }

    public void recuperarSenha(ActionEvent event)
    {
        this.carregarNovaScene("ScreenRecuperarSenha.fxml", false);
    }

    /**
     * Função acionada quando o usuário clicar no botão "cadastrar"
     * 
     * A função irá mudar a screen para a equivalente ao arquivo fxml
     * que está armazenado as configurações da screen de cadastro
     * de usuário.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void cadastrarUsuario(ActionEvent action)
    {
        this.carregarNovaScene("ScreenCadastrarUsuario.fxml", false);
    }

    public void checarDadosInseridos()
    {
        String usuario = this.usuario.getText();
        String password = this.password.getText();

        // Aqui deve ter uma função integrada com o banco de dados
        // para checar se existe um usuario e uma senha cadastrados
        // com os dados acima. Caso tenha, retorna o id do usuario. Caso não, retorna 0.
        // O id do usuario vai servir pra puxar o nome dele na função
        // que o carregarNovaScene chama
        // TODO: luis

        this.idUsuario = 1;
        
        /* O empty tem que sair */
        if (this.idUsuario != 0 && !(usuario.isEmpty() || password.isEmpty()))
        {
            this.carregarNovaScene("ScreenLogged.fxml", true);
        }
        else
        {
            this.algoErrado.setOpacity(1);
        }
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
    *  Text
    */

    @FXML 
    private Text algoErrado;

    /* 
     *  Textfield
     */

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField password;
    
    /* 
     *  Botões
     */

    @FXML
    private Button entrar;

    @FXML 
    private Button cadastrar;

    @FXML
    private Button esqueceuSenha;
}
