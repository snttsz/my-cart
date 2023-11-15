package interfacegrafica.models;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

// Local libraries
import interfacegrafica.models.PainelProduto;

public class PainelLoja 
{
    public PainelLoja(int idLoja, AnchorPane paneLoja, Text nomeLoja, ImageView fotoLoja, Button maisProdutosLoja, PainelProduto produto1, PainelProduto produto2, PainelProduto produto3) 
    {
        this.idLoja = idLoja;
        this.nomeLoja = nomeLoja;
        this.fotoLoja = fotoLoja;
        this.maisProdutosLoja = maisProdutosLoja;
        
        this.produto1 = produto1;
        this.produto2 = produto2;
        this.produto3 = produto3;
    }

    public int getIdLoja() 
    {
        return idLoja;
    }
    
    public void setIdLoja(int idLoja) 
    {
        this.idLoja = idLoja;
    }
    
    public AnchorPane getPaneLoja() 
    {
        return paneLoja;
    }
    
    public void setPaneLoja(AnchorPane paneLoja) 
    {
        this.paneLoja = paneLoja;
    }
    
    public Text getNomeLoja() 
    {
        return nomeLoja;
    }
    
    public void setNomeLoja(Text nomeLoja) 
    {
        this.nomeLoja = nomeLoja;
    }
    
    public ImageView getFotoLoja() 
    {
        return fotoLoja;
    }
    
    public void setFotoLoja(ImageView fotoLoja) 
    {
        this.fotoLoja = fotoLoja;
    }
    
    public Button getMaisProdutosLoja() 
    {
        return maisProdutosLoja;
    }
    
    public void setMaisProdutosLoja(Button maisProdutosLoja) 
    {
        this.maisProdutosLoja = maisProdutosLoja;
    }

    public PainelProduto getProduto1() 
    {
        return produto1;
    }

    public void setProduto1(PainelProduto produto1) 
    {
        this.produto1 = produto1;
    }

    public PainelProduto getProduto2() 
    {
        return produto2;
    }

    public void setProduto2(PainelProduto produto2) 
    {
        this.produto2 = produto2;
    }

    public PainelProduto getProduto3() 
    {
        return produto3;
    }

    public void setProduto3(PainelProduto produto3) 
    {
        this.produto3 = produto3;
    }

    private PainelProduto produto1;
    private PainelProduto produto2;
    private PainelProduto produto3;

    private int idLoja;
    private AnchorPane paneLoja;
    private Text nomeLoja;
    private ImageView fotoLoja;
    private Button maisProdutosLoja;
}
