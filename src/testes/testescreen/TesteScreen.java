package testes.testescreen;

import interfacegrafica.ScreenLogged;
import javafx.application.Application;
import javafx.stage.Stage;

public class TesteScreen extends Application
{
    @Override
    public void start(Stage stage) 
    {
        ScreenLogged minhaTela = new ScreenLogged("MyCart", "ScreenLogged.fxml", "Username");

        minhaTela.start(stage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
