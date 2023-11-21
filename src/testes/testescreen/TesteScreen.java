package testes.testescreen;

import interfacegrafica.screens.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

public class TesteScreen extends Application
{
    @Override
    public void start(Stage stage) 
    {
        Screen minhaTela = new Screen("MyCart", "LoginScreen2.fxml");

        minhaTela.start(stage);

        // ScreenLogged minhaTela = new ScreenLogged("MyCart", "LoginScreen2.fxml", "Glendaaaaaaaaaaaa");

        // minhaTela.start(stage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
