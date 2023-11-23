package interfacegrafica.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.UsuariosDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Classe responsável por definir as implementações padrões das telas de login e cadastro.
 * 
 * @author Glenda
 * 
 */
public abstract class ControllerBeforeLogin extends Controller implements Initializable
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

            if (title.contains("_"))
            {
                int indexOf = title.lastIndexOf("_");

                this.idUsuario = Integer.valueOf(title.substring(indexOf + 1, title.length()));
                title = title.substring(0, indexOf);
            }

            stage.setTitle(title);
        });
    }

    /**
     * Função padrão para mudar a screen.
     * 
     * A função irá carregar um arquivo fxml e iniciar uma screen com as configurações
     * contidas no arquivo.
     * 
     * @param fxmlName
     * Nome do arquivo fxml que será carregado.
     * 
     * @param idUsuario
     * ID do usuário que será direcionado para a próxima scene.
     */
    public void carregarNovaScene(String fxmlName, int idUsuario)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlName));
            Parent novoRoot = loader.load();
            
            Stage stage = (Stage) this.root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);

            if (idUsuario != 0)
            {
                String title = "MyCart - " + this.puxarNomeDoUsuario() + "_" + String.valueOf(idUsuario);
                stage.setTitle(title);
            }
            
            stage.setScene(novaScene);
            stage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Função para setar o nome do usuário, puxando a informação do banco 
     * de dados.
     * 
     * O nome do usuário irá aparecer no título da janela e será importante
     * para que screens posteriores tenham o acesso ao dado.
     */
    protected String puxarNomeDoUsuario()
    {
        String nome = usuariosDAO.selectById(this.idUsuario).getNome();

        return nome;
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    protected Node root;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    // Constante com o caminho pra pasta resources, onde está os arquivos .fxml
    protected final String pathResources = "../../resources/";

    // ID do usuário
    protected int idUsuario;
    protected UsuariosDAO usuariosDAO = new UsuariosDAO();
}
