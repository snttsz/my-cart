package interfacegrafica.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * Classe responsável por definir as implementações padrões das telas de login e cadastro.
 * 
 * @author Glenda
 * 
 */
public abstract class ControllerBeforeLogin extends Controller
{
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
    public void carregarNovaScene(String fxmlName, boolean setarNomeUsuario)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlName));
            Parent novoRoot = loader.load();
            
            Stage stage = (Stage) this.root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);

            if (setarNomeUsuario)
            {
                String title = "MyCart - " + this.puxarNomeDoUsuario();
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
        // Chamar uma função integrada com o banco de dados para
        // puxar o nome do usuário que acabou de logar na conta
        // TODO: luis
        // variavel com o id = this.idUsuario
        String nome = "Glenda";

        return nome;
    }

    public void onButton(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.8);

        source.setCursor(Cursor.HAND);
    }

    public void offButton(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(1);
    }

    public void clickedButton(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.3);

        source.setCursor(Cursor.DEFAULT);
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
}
