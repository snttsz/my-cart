package interfacegrafica.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

        if (source.getId() == adicionarProduto.getId())
        {   
            this.mudarScene("ScreenAdicionarProduto.fxml");
        }   
        else if (source.getId() == adicionarLoja.getId())
        {   
            this.mudarScene("ScreenCadastrarLoja.fxml");
        }
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    protected Node root;

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
