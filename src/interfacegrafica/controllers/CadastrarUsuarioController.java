package interfacegrafica.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        this.carregarNovaScene("LoginScreen2.fxml", false);
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
    public void checarDadosUsuario(ActionEvent action)
    {
        String nomeDoUsuario = this.nome.getText();
        String usuario = this.usuario.getText();
        String senha = this.senha.getText();
        String confirmacaoSenha = this.confirmacaoSenha.getText();

        boolean senhaOk = true;
        boolean userOk = true;

        /* Checando se o nome de usuário contem espaços ou está vazio */
        if ((usuario.contains(" ") || usuario.isEmpty()) || nomeDoUsuario.isEmpty())
        {
            this.alertaErro.setOpacity(1);
            this.alertaAbaixoUsuario.setText("*Não utilize espaços.");
            userOk = false;
        }

        /* Checando se as senhas informadas são iguais */
        if (!senha.equals(confirmacaoSenha) || senha.isEmpty())
        {
            this.retanguloSenha.setFill(Color.RED);
            this.senhaResultado.setText("As senhas precisam coincidir e não estarem vazias!");
            this.alertaErro.setOpacity(1);
            senhaOk = false;
        }
        else
        {
            this.retanguloSenha.setFill(Color.GREEN);
            this.senhaResultado.setText("Senha válida");
        }

        /* 
         * Se tudo estiver corretamente inserido, então chama as funções
         * do banco de dados para checar a validade dos dados.
         */
        if (userOk && senhaOk)
        {
            // TODO: luis (variaveis criadas no inicio da função)
            
            // chamar função pra checar se o nome de usuario já existe no banco de dados
            boolean doesUserExistis = false;

            if (doesUserExistis)
            {
                this.alertaErro.setOpacity(1);
                this.alertaAbaixoUsuario.setText("*Usuário já existe, tente outro nome de usuário!");
            }
            else
            {
                // aqui chamar a função que irá cadastrar o usário
                // setar isso aqui com o id do usuário criado:
                // TODO: luis
                this.idUsuario = 1;
    
                /* Entrando na tela de usuário logado */
                this.carregarNovaScene("ScreenCadastrarUsuarioFoto.fxml", true);
            }
        }
    }

    /**
     * Função acionada quando o botão "continuar" é clicado na tela de adicionar
     * foto do novo usuário.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void continuarLogin(ActionEvent action)
    {
        this.carregarNovaScene("ScreenLogged.fxml", false);
    }

    /**
     * Função acionada quando o botão para adicionar foto do usuário é
     * clicado.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void cadastrarFotoUsuario(ActionEvent action)
    {
        String filepath = this.abrirFileChooser(action);

        String caminhoPastaDestino = "src/img/users/";

        String nomeDaImagem = null;

        try
        {
            nomeDaImagem = this.copiarImagem(filepath, caminhoPastaDestino, this.idUsuario, this.puxarNomeDoUsuario());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }    

        String caminhoFinal = "../../img/users/" + nomeDaImagem;

        Image image = new Image(getClass().getResource(caminhoFinal).toExternalForm());
        this.fotoUsuario.setImage(image);

        /* Cadastrar a foto no banco de dados */
        this.setarFotoUsuarioNoBanco(caminhoFinal);
    }

    /**
     * Função para cadastrar o caminho para a foto de perfil do usuário
     * no banco de dados.
     * 
     * @param caminhoParaImagem
     * Caminho relativo para a imagem de perfil do usuário.
     */
    public void setarFotoUsuarioNoBanco(String caminhoParaImagem)
    {
        /* 
         * TODO: luis
         * setar a foto do usuario no banco de dados
         * variavel com o id do usuario: this.userId
         */
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
    private Button continuar;

    @FXML
    private Button voltar;

    @FXML
    private Button continuarParaLogin;

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

    @FXML
    private Text alertaAbaixoUsuario;

    /* 
     *  Figuras Geometricas
     */

    @FXML
    private Rectangle retanguloSenha;

    /* 
     *  ImageView
     */

    @FXML
    private ImageView fotoUsuario;
}
