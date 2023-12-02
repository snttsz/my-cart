package interfacegrafica.controllers;

import java.io.IOException;
import java.util.Random;

import DAO.LojaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sistema.Loja;

/**
 * 
 * Classe responsável por definir as implementações da tela de login do usuário.
 * 
 * @author Glenda
 * 
 */
public class CadastrarLojaController extends ControllerLogged
{
    /**
     * Função acionada quando o usuário clicar no botão "Cancelar"
     * 
     * A função irá voltar para a tela de início.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void voltarParaOInicio(ActionEvent action)
    {
        if (this.adicionouFotoLoja)
        {
            this.excluirFotoDaLoja();
        }

        this.mudarScene("ScreenLogged.fxml");
    }

    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        if (this.adicionouFotoLoja)
        {
            this.excluirFotoDaLoja();
        }

        super.botaoMenuClicked(mouse);
    }

    /**
     * Função acionada quando o usuário clicar no botão "cadastrar loja"
     * 
     * A função irá cadastrar a loja no banco de dados.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void cadastrarLojaBD(ActionEvent action)
    {
        String nomeDaLoja = this.nomeDaLoja.getText();
        String linkDaLoja = this.linkDaLoja.getText();
        String caminhoFotoLoja = this.fotoLoja.getImage().getUrl();

        Loja novaLoja = new Loja(nomeDaLoja, linkDaLoja, caminhoFotoLoja);
        lojaDAO.insert(novaLoja);
    }

    /**
     * Função acionada quando o usuário clica no botão de adicionar foto.
     * 
     * A função irá abrir um file explorer, copiar a imagem selecionada para
     * uma pasta local do projeto e exibi-la na tela para o usuário.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void cadastrarFotoLoja(ActionEvent action)
    {
        String filePath = this.abrirFileChooser(action);

        String caminhoPastaDestino = "src/img/users";

        String nomeDaImagem = null;

        /* Gerando número random para que a imagem não seja repetida */
        Random random = new Random();
        int numeroIntervalo = random.nextInt(1, 3123123) + 1;

        try
        {
            nomeDaImagem = this.copiarImagem(filePath, caminhoPastaDestino, Controller.idUsuario, String.valueOf(numeroIntervalo));
            this.adicionouFotoLoja = true;

            String caminhoFinal = "../../img/users/" + nomeDaImagem;
            
            // Esperando o sistema terminar o processo de I/O
            while(true)
            {
                try
                {
                    Image image = new Image(getClass().getResource(caminhoFinal).toExternalForm());
                    this.fotoLoja.setImage(image);
                    break;
                }
                catch (Exception e)
                {
    
                }
            }
        }
        catch(IOException e)
        {
            // e.printStackTrace();
        }
    }

    /**
     * Função para remover a foto do usuário da pasta local quando o mesmo adiciona uma foto à loja
     * e cancela a operação de cadastro.
     */
    private void excluirFotoDaLoja()
    {
        int lastIndexOf = this.fotoLoja.getImage().getUrl().lastIndexOf("/");
        String nomeDoArquivo = this.fotoLoja.getImage().getUrl().substring(lastIndexOf + 1, this.fotoLoja.getImage().getUrl().length());

        this.excluirImagem(nomeDoArquivo);
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    private Node root;

    /* 
     *  Botões
     */

    @FXML
    private Button adicionarFotoLoja;

    /* 
     *  Textfields
     */

    @FXML
    private TextField nomeDaLoja;

    @FXML
    private TextField linkDaLoja;

    /* 
     *  Imageview
     */

    @FXML
    private ImageView fotoLoja;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */
    
    private boolean adicionouFotoLoja;
    private LojaDAO lojaDAO = new LojaDAO();
    
}
