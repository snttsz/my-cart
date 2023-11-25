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
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
        String novoNomeArquivo = null;

        if (checarValidadeImagem(caminhoImagemOriginal))
        {
            Path origem = Paths.get(caminhoImagemOriginal);
            
            novoNomeArquivo = String.valueOf(idUsuario) + "_" + nomeUsuario + this.obterExtensaoDoArquivo(origem.getFileName().toString());
            
            Path destino = Paths.get(caminhoPastaDestino, novoNomeArquivo);
            
            /* copia o arquivo para a pasta de destino */
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
        }
        else
        {
            throw new IOException("Imagem maior que 4MB.");
        }

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

    /**
     * Função padrão mostrar uma janela de erro.
     * 
     * A função irá abrir uma nova janela mostrando uma mensagem de erro para o usuário.
     * 
     * @param mensagem
     * Mensagem de erro que será mostrada.
     */
    protected void abrirErroStage(String mensagem)
    {
        Stage erroStage = new Stage();

        erroStage.initModality(Modality.APPLICATION_MODAL);
        erroStage.setTitle("Erro");

        StackPane erroLayout = new StackPane();
        erroLayout.getChildren().add(new Label(mensagem));

        Scene erroScene = new Scene(erroLayout, 500, 100);
        erroStage.setScene(erroScene);

        erroStage.showAndWait();
    }   

    /**
     * Função para checar se o tamanho da imagem compreende o limite máximo.
     * 
     * @param caminhoDaImagem
     * Caminho para a imagem em questão.
     */
    protected boolean checarValidadeImagem(String caminhoDaImagem)
    {
        boolean result = true;

        try
        {
            Path source = Path.of(caminhoDaImagem);
    
            long tamanhoEmBytes = Files.size(source);
            long tamanhoEmMB = tamanhoEmBytes / (long) (Math.pow(2, 20));

            if (tamanhoEmMB > this.maxTamanhoImagemMB)
            {
                this.abrirErroStage("O tamanho da imagem não deve ser maior que 4MB!");
                
                result = false;
            }
        }
        catch (IOException e)
        {
            // e.printStackTrace();
        }

        return result;
    }

    public void exibirTextoDeAjuda(Button source, String texto)
    {
        Tooltip tooltip = new Tooltip(texto);

        Tooltip.install(source, tooltip);
    }

    /**
     * Função para cadastrar o caminho para a foto de perfil do usuário
     * no banco de dados.
     * 
     * @param caminhoParaImagem
     * Caminho relativo para a imagem de perfil do usuário.
     */
    public void setarFotoUsuarioNoBanco(String caminhoParaImagem)
    {
        usuariosDAO.updateUrl_Foto(usuariosDAO.selectById(Controller.idUsuario), caminhoParaImagem);
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

    // Constante com o tamanho máximo em MB que uma imagem pode conter
    protected final int maxTamanhoImagemMB = 4;
}
