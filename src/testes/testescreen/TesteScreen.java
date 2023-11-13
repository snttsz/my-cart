package testes.testescreen;

import interfacegrafica.screens.ScreenLogged;
import javafx.application.Application;
import javafx.stage.Stage;

public class TesteScreen extends Application
{
    @Override
    public void start(Stage stage) 
    {
        ScreenLogged minhaTela = new ScreenLogged("MyCart", "ScreenLogged.fxml", "Bigodao");

        minhaTela.start(stage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
