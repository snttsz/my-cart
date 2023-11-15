package interfacegrafica.models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PainelProduto
{
    public PainelProduto(int idProduto, Pane paneProduto, Text valorPrecoProduto, Text valorArrecadadoProduto, Text valorFaltamProduto, ImageView fotoProduto) 
    {
        this.idProduto = idProduto;
        this.paneProduto = paneProduto;
        this.valorPrecoProduto = valorPrecoProduto;
        this.valorArrecadadoProduto = valorArrecadadoProduto;
        this.valorFaltamProduto = valorFaltamProduto;
        this.fotoProduto = fotoProduto;
    }

    public int getIdProduto() 
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto) 
    {
        this.idProduto = idProduto;
    }

    public Pane getProdutoLoja() 
    {
        return paneProduto;
    }

    public void setProdutoLoja2(Pane paneProduto) 
    {
        this.paneProduto = paneProduto;
    }

    public Text getValorPrecoProduto() 
    {
        return valorPrecoProduto;
    }

    public void setValorPrecoProduto2(Text valorPrecoProduto) 
    {
        this.valorPrecoProduto = valorPrecoProduto;
    }

    public Text getValorArrecadadoProduto() 
    {
        return valorArrecadadoProduto;
    }

    public void setValorArrecadadoProduto2(Text valorArrecadadoProduto) 
    {
        this.valorArrecadadoProduto = valorArrecadadoProduto;
    }

    public Text getValorFaltamProduto() 
    {
        return valorFaltamProduto;
    }

    public void setValorFaltamProduto2(Text valorFaltamProduto) 
    {
        this.valorFaltamProduto = valorFaltamProduto;
    }

    public ImageView getFotoProduto() 
    {
        return fotoProduto;
    }

    public void setFotoProduto(ImageView fotoProduto) 
    {
        this.fotoProduto = fotoProduto;
    }
    
    private int idProduto;
    private Pane paneProduto;
    private Text valorPrecoProduto;
    private Text valorArrecadadoProduto;
    private Text valorFaltamProduto;
    private ImageView fotoProduto;
}
