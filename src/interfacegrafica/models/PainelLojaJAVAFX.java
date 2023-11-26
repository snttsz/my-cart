package interfacegrafica.models;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PainelLojaJAVAFX 
{   
    public PainelLojaJAVAFX(AnchorPane paneLoja, Text nomeLoja, ImageView fotoLoja, Button maisProdutosLoja, PainelProdutoJAVAFX produto1, PainelProdutoJAVAFX produto2, PainelProdutoJAVAFX produto3) 
    {
        this.paneLoja = paneLoja;
        this.nomeLoja = nomeLoja;
        this.fotoLoja = fotoLoja;
        this.maisProdutosLoja = maisProdutosLoja;
        this.produto1 = produto1;
        this.produto2 = produto2;
        this.produto3 = produto3;
    }

    public void atualizarAtributosComPainelLoja(PainelLoja painelLoja)
    {
        if (painelLoja == null)
        {
            return;
        }

        this.nomeLoja.setText(painelLoja.getNomeDaLoja());
        
        Image imgLoja = new Image(painelLoja.getUrlDaImagem());
        this.fotoLoja.setImage(imgLoja);

        this.produto1.atualizarAtributosComPainelProduto(painelLoja.getProduto1());
        this.produto2.atualizarAtributosComPainelProduto(painelLoja.getProduto2());
        this.produto3.atualizarAtributosComPainelProduto(painelLoja.getProduto3());        
    }

    public AnchorPane getPaneLoja() 
    {
        return this.paneLoja;
    }

    public void setPaneLoja(AnchorPane paneLoja) 
    {
        this.paneLoja = paneLoja;
    }

    public Text getNomeLoja() 
    {
        return this.nomeLoja;
    }

    public void setNomeLoja(Text nomeLoja) 
    {
        this.nomeLoja = nomeLoja;
    }

    public ImageView getFotoLoja() 
    {
        return this.fotoLoja;
    }

    public void setFotoLoja(ImageView fotoLoja) 
    {
        this.fotoLoja = fotoLoja;
    }

    public Button getMaisProdutosLoja() 
    {
        return this.maisProdutosLoja;
    }

    public void setMaisProdutosLoja(Button maisProdutosLoja) 
    {
        this.maisProdutosLoja = maisProdutosLoja;
    }

    public PainelProdutoJAVAFX getProduto1() 
    {
        return this.produto1;
    }
    
    public void setProduto1(PainelProdutoJAVAFX produto1) 
    {
        this.produto1 = produto1;
    }

    public PainelProdutoJAVAFX getProduto2() 
    {
        return this.produto2;
    }

    public void setProduto2(PainelProdutoJAVAFX produto2) 
    {
        this.produto2 = produto2;
    }

    public PainelProdutoJAVAFX getProduto3() 
    {
        return this.produto3;
    }

    public void setProduto3(PainelProdutoJAVAFX produto3) 
    {
        this.produto3 = produto3;
    }

    private AnchorPane paneLoja;
    private Text nomeLoja;
    private ImageView fotoLoja;
    private Button maisProdutosLoja;

    private PainelProdutoJAVAFX produto1;
    private PainelProdutoJAVAFX produto2;
    private PainelProdutoJAVAFX produto3;
}
