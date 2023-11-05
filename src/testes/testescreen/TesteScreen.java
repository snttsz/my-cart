package testes.testescreen;

import interfacegrafica.InitialScreenLogged;
import javafx.application.Application;
import javafx.stage.Stage;

public class TesteScreen extends Application
{
    @Override
    public void start(Stage stage) 
    {
        InitialScreenLogged minhaTela = new InitialScreenLogged("MyCart", "InitialScreenLogged.fxml", "Username");

        minhaTela.start(stage);

    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
