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

    public void botaoAddProdutoMouseOff(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0);
    }

    public void botaoAddProdutoMouseOn(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0.17);
    }

    public void botaoAddProdutoClicked(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0.3);
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
