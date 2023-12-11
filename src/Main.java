import interfacegrafica.screens.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) 
    {
        Screen minhaTela = new Screen("MyCart", "LoginScreen2.fxml");

        minhaTela.start(stage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
