package interfacegrafica.controllers;

import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CadastrarLojaController extends ControllerLogged
{
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

    @FXML
    public void cadastrarLojaBD(ActionEvent action)
    {
        String nomeDaLoja = this.nomeDaLoja.getText();
        String linkDaLoja = this.linkDaLoja.getText();
        String caminhoFotoLoja = this.fotoLoja.getImage().getUrl();

        /* 
         * TODO: luis
         */
    }

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
    
}
