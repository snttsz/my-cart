package interfacegrafica.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sistema.Usuario;

public class RecuperarSenhaController extends ControllerBeforeLogin
{
    public void voltarParaInicio(ActionEvent event)
    {
        this.carregarNovaScene("LoginScreen2.fxml", 0, root);
    }

    public void checarPermissaoAlterarSenha(ActionEvent event)
    {
        String usuario = this.login.getText();
        String nome = this.nome.getText();
        
        /* Verificando se o nome e login do usuário coincidem com algum usuário no banco */
        this.usuarioParaRecuperar = this.usuariosDAO.recuperarUsuario(usuario, nome);
        boolean result = false;
        if (this.usuarioParaRecuperar != null) 
        {
            result = true;
        }

        if (result)
        {
            this.retanguloResultado.setFill(Color.GREEN);
            this.textoResultado.setText("Os dados inseridos são válidos! Você poderá alterar sua senha abaixo:");

            // seta a opacidade dos textos para 1
            this.novaSenhaText.setOpacity(1);
            this.repetirSenhaText.setOpacity(1);
            
            // seta a opacidade dos botões para 1 e ativa o uso de cada um
            
            this.senha.setOpacity(1);
            this.senha.setDisable(false);

            this.confirmacaoSenha.setOpacity(1);
            this.confirmacaoSenha.setDisable(false);
            
            this.alterarSenha.setOpacity(1);
            this.alterarSenha.setDisable(false);
        }
        else
        {
            this.retanguloResultado.setFill(Color.RED);
            this.textoResultado.setText("Os dados inseridos são inválidos! Infelizmente, você não poderá modificar sua senha. Para mais informações, entre em contato com o e-mail kalvinalbuquerque5@gmail.com");
        }
    }

    public void alterarSenhaBD(ActionEvent action)
    {
        String novasenha = this.senha.getText();
        String confirmacaoSenha = this.confirmacaoSenha.getText();

        if (novasenha.equals(confirmacaoSenha) && !novasenha.isEmpty())
        {
            /* Alterando senha */
            usuariosDAO.updateSenha(this.usuarioParaRecuperar, confirmacaoSenha);
            
            // seta a opacidade dos textos para 1
            this.novaSenhaText.setOpacity(0.5);
            this.repetirSenhaText.setOpacity(0.5);
            
            /* 
             * Seta a opacidade dos botões para 1 e ativa o uso de cada um
             */

            this.senha.setOpacity(0.5);
            this.senha.setDisable(true);

            this.confirmacaoSenha.setOpacity(0.5);
            this.confirmacaoSenha.setDisable(true);
            
            this.alterarSenha.setOpacity(0.5);
            this.alterarSenha.setDisable(true);

            this.retanguloResultado.setFill(Color.GREEN);
            this.textoResultado.setText("Senha alterada com sucesso! Você já pode voltar para o início e logar novamente :)");
        }
        else
        {
            this.retanguloResultado.setFill(Color.RED);
            this.textoResultado.setText("As senhas precisam coincidir e não estarem vazias! Tente novamente.");
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
    private Button voltar;

    @FXML
    private Button checarValidade;

    @FXML
    private Button alterarSenha;

    /* 
     *  Textfields
     */

    @FXML
    private TextField login;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField senha;

    @FXML
    private PasswordField confirmacaoSenha;

    /* 
     *  Texto
     */

    @FXML
    private Text textoResultado;

    @FXML
    private Text novaSenhaText;

    @FXML
    private Text repetirSenhaText;

    /* 
     *  Figuras geométricas
     */

    @FXML
    private Rectangle retanguloResultado;

    /*
     * Atributos
     */

    private Usuario usuarioParaRecuperar = null;
}
