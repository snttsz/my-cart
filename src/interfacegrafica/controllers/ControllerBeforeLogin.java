package interfacegrafica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
}
