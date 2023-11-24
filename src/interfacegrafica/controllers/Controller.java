package interfacegrafica.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import DAO.UsuariosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Classe abstrata responsável por definir as implementações padrões 
 * disponíveis em todas as telas.
 * 
 * @author Glenda
 */
public abstract class Controller 
{
    /**
     * Função responsável por abrir o navegador de arquivos para que o usuário
     * selecione uma imagem.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public String abrirFileChooser(ActionEvent event)
    {
        FileChooser filechooser = new FileChooser();
        String filePath = null;

        filechooser.setTitle("Escolha uma foto");
        filechooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*")
        );

        File selectedFile = filechooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());

        if (selectedFile != null)
        {
            filePath = selectedFile.getAbsolutePath();
        }

        return filePath;
    }

    /**
     * Função para copiar uma imagem num diretório qualquer para o diretório do projeto.
     * 
     * @param caminhoImagemOriginal
     * O caminho absoluto da imagem original
     * 
     * @param caminhoPastaDestino
     * O caminho relativo para a pasta de destino
     */
    public String copiarImagem(String caminhoImagemOriginal, String caminhoPastaDestino, int idUsuario, String nomeUsuario) throws IOException
    {
        // TODO: não copiar imagem maior que 3 mb
        Path origem = Paths.get(caminhoImagemOriginal);
        
        String novoNomeArquivo = String.valueOf(idUsuario) + "_" + nomeUsuario + this.obterExtensaoDoArquivo(origem.getFileName().toString());
        
        Path destino = Paths.get(caminhoPastaDestino, novoNomeArquivo);
        
        /* copia o arquivo para a pasta de destino */
        Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

        return novoNomeArquivo;
    }

    /** 
     * Função para retornar a extensão de um arquivo.
     * A função retornará a extensão acompanhada do ".". Ex: .png, .jpg...
     * 
     * @param nomeDoArquivo
     * Nome do arquivo que a extensão será retornada.
     */
    public String obterExtensaoDoArquivo(String nomeDoArquivo)
    {
        int ultimoPonto = nomeDoArquivo.lastIndexOf(".");

        return nomeDoArquivo.substring(ultimoPonto);
    }

    public void onButton(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.9);

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

        source.setOpacity(0.6);

        source.setCursor(Cursor.DEFAULT);
    }

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
    public void carregarNovaScene(String fxmlName, boolean showUserName, Node root)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathResources + fxmlName));
            Parent novoRoot = loader.load();
            
            Stage stage = (Stage) root.getScene().getWindow();
            Scene novaScene = new Scene(novoRoot);

            /* Caso um usuário estiver logado */
            if (showUserName)
            {
                // String title = "MyCart - " + this.puxarNomeDoUsuario() + "_" + String.valueOf(idUsuario);
                String title = "MyCart - " + this.puxarNomeDoUsuario();
                stage.setTitle(title);
            }
            /* Caso não haja usuário logado */
            else
            {
                stage.setTitle("MyCart");
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
        String nome = usuariosDAO.selectById(Controller.idUsuario).getNome();

        return nome;
    }

    /* 
     * 
     *      Atributos internos
     * 
     */

    // id do usuário que está logado
    protected static int idUsuario;

    // DAO do usuário
    protected UsuariosDAO usuariosDAO = new UsuariosDAO();

    // Constante com o caminho pra pasta resources, onde está os arquivos .fxml
    protected final String pathResources = "../../resources/";
}
