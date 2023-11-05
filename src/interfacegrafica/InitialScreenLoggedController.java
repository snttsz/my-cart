package interfacegrafica;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class InitialScreenLoggedController extends ControllerLogged
{
    // TODO: verificar se é possível setar manualmente algo dentro dessa classe
    public String getUserName() 
    {
        return this.userName;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    @Override
    public void botaoMenuMouseOff(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == adicionarProduto.getId())
            {
                adicionarProduto.setOpacity(0.0);
            }
            else
            {
                super.botaoMenuMouseOff(mouse);
            }
        }
    }

    @Override
    public void botaoMenuMouseOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == adicionarProduto.getId())
            {
                adicionarProduto.setOpacity(0.17);
            }
            else
            {
                super.botaoMenuMouseOn(mouse);
            }
        }
    }

    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        if (source instanceof Button)
        {
            Button botaoSource = (Button) source;

            if (botaoSource.getId() == adicionarProduto.getId())
            {
                adicionarProduto.setOpacity(0.3);
            }
            else
            {
                super.botaoMenuClicked(mouse);
            }
        }
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

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    private String userName;
}
