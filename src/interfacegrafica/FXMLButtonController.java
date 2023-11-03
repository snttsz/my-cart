package interfacegrafica;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLButtonController implements Initializable 
{
    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        // Initialization code can go here
    }

    @FXML
    public void buttonClicked(ActionEvent event) 
    {
        // label.setText("Button Clicked");
    }
}
