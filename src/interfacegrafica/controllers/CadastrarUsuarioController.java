package interfacegrafica.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/**
 * 
 * Classe responsável por definir as implementações da tela de cadastro de usuário.
 * 
 * @author Glenda
 * 
 */
public class CadastrarUsuarioController extends ControllerBeforeLogin
{   
    /**
     * Função acionada quando o usuário clicar no botão "voltar".
     * 
     * A função irá carregar o arquivo fxml equilavente à screen de início.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void voltarParaInicio(ActionEvent action)
    {
        this.carregarNovaScene("LoginScreen2.fxml");
    }

    /**
     * Função acionada quando o usuário clicar no botão "cadastrar".
     * 
     * A função será responsável por invocar os métodos que irão
     * cadastrar o usuário no banco de dados.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void cadastrarUsuario(ActionEvent action)
    {
        String nomeDoUsuario = this.nome.getText();
        String usuario = this.usuario.getText();
        String senha = this.senha.getText();
        String confirmacaoSenha = this.confirmacaoSenha.getText();

        boolean senhaOk = true;
        boolean userOk = true;

        if (usuario.contains(" ") || usuario.isEmpty())
        {
            this.alertaErro.setOpacity(1);
            userOk = false;
        }

        if (!senha.equals(confirmacaoSenha) || senha.isEmpty())
        {
            this.retanguloSenha.setFill(Color.RED);
            this.senhaResultado.setText("Senhas não coincidem!");
            this.alertaErro.setOpacity(1);
            senhaOk = false;
        }
        else
        {
            this.retanguloSenha.setFill(Color.GREEN);
            this.senhaResultado.setText("Senha válida");
        }

        if (userOk && senhaOk)
        {
            // aqui chamar a função que irá cadastrar o usário
            
            /* Entrando na tela de usuário logado */
            this.carregarNovaScene("ScreenLogged.fxml");
        }
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
     *  Botões
     */

    @FXML
    private Button cadastrar;

    @FXML
    private Button voltar;

    /* 
     *  TextFields
     */

    @FXML
    private TextField nome;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private PasswordField confirmacaoSenha;

    /* 
     *  Texto
     */

    @FXML 
    private Text alertaErro;

    @FXML 
    private Text senhaResultado;

    /* 
     *  Figuras Geometricas
     */

    @FXML
    private Rectangle retanguloSenha;
}
