package interfacegrafica.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class InitialScreenLoggedController extends ControllerLogged
{
    @FXML
    public void botaoAddMouseOff(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.0);
    }

    @FXML
    public void botaoAddMouseOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.17);

        this.cursorOn(mouse);
    }

    @FXML
    public void botaoAddClicked(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.3);

        this.cursorNormal(mouse);
    }

    @FXML
    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        Node botao = (Node) mouse.getSource();

        if (botao.getId() == this.lojasCadastradas.getId())
        {
            this.mudarSceneLojasCadastradas();
        }
        else if (botao.getId() == this.categorias.getId())
        {
            this.mudarSceneCategorias();
        }
        else if (botao.getId() == this.inicio.getId())
        {
            this.mudarSceneInicio();
        }
    }

    @Override
    public void mudarSceneInicio()
    {
        this.carregarNovaScene("ScreenLogged.fxml");
    }

    @Override
    public void mudarSceneCategorias()
    {   

    }

    @Override 
    public void mudarSceneLojasCadastradas()
    {
        this.carregarNovaScene("ScreenLojasCadastradas.fxml");
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
     *    Botões
     */


    @FXML
    private Button adicionarProduto;

    @FXML
    private Button adicionarLoja;

    /* 
     *    Texto
     */

    @FXML
    private Text listaVazia2Inicio;

    @FXML
    private Text listaVaziaInicio;

    /* 
     *    Imagens
     */

    @FXML
    private ImageView adicionadosRecentementeImg;

    @FXML
    private ImageView quaseLaImg;
}
