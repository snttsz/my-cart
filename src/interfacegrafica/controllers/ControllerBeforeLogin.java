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
public abstract class ControllerBeforeLogin 
{
    /**
     * Função padrão para mudar a screen.
     * 
     * A função carregar um arquivo fxml e iniciar uma screen com as configurações
     * contidas no arquivo.
     * 
     * @param fxmlName
     * Nome do arquivo fxml que será carregado.
     */
    public void carregarNovaScene(String fxmlName, int idUsuario)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlName));
            Parent novoRoot = loader.load();
            
            String nomeDoUsuario = this.puxarNomeDoUsuario(idUsuario);
            
            Stage stage = (Stage) this.root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);
            
            stage.setTitle("MyCart - " + nomeDoUsuario);
            stage.setScene(novaScene);
            stage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void carregarNovaScene(String fxmlName)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlName));
            Parent novoRoot = loader.load();
            
            Stage stage = (Stage) this.root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);
            
            stage.setTitle("MyCart");
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
    protected String puxarNomeDoUsuario(int idUsuario)
    {
        // Chamar uma função integrada com o banco de dados para
        // puxar o nome do usuário que acabou de logar na conta
        // TODO: luis
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
}
