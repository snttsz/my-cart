package interfacegrafica.controllers;

import interfacegrafica.ControllerLogged;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class InitialScreenLoggedController extends ControllerLogged
{
    // TODO: ver como pegar o titulo da janela para atribuir como nome de usuário

    @FXML
    public void botaoAddProdutoMouseOff(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0);
    }

    @FXML
    public void botaoAddProdutoMouseOn(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0.17);
    }

    @FXML
    public void botaoAddProdutoClicked(MouseEvent mouse)
    {
        adicionarProduto.setOpacity(0.3);
    }

    @FXML
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        Node botao = (Node) mouse.getSource();

        if (botao.getId() == lojasCadastradas.getId())
        {
            this.adicionadosRecentementeImg.setOpacity(0);
            this.quaseLaImg.setOpacity(0);

            if (this.listaVazia2Inicio.getOpacity() == 1)
            {
                this.listaVazia2Inicio.setOpacity(0);
            }
            if (this.listaVaziaInicio.getOpacity() == 1)
            {
                this.listaVaziaInicio.setOpacity(0);
            }
        }
        else if (botao.getId() == categorias.getId())
        {

        }
        else if (botao.getId() == inicio.getId())
        {
            this.adicionadosRecentementeImg.setOpacity(1);
            this.quaseLaImg.setOpacity(1);
            this.listaVazia2Inicio.setOpacity(1);
            this.listaVaziaInicio.setOpacity(1);
        }
    }

    @Override
    public void mudarSceneCategorias()
    {

    }

    @Override 
    public void mudarSceneLojasCadastradas()
    {

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
