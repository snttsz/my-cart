package interfacegrafica;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class InitialScreenLogged extends Screen
{
    @FXML
    ScrollPane scrollInicio;

    @FXML
    AnchorPane initPane;

    public InitialScreenLogged(String tituloJanela, String fxmlname, String userName) 
    {
        super(tituloJanela + " - " + userName, fxmlname);
    }
}
