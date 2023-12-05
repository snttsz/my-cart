package interfacegrafica.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PainelProdutoJAVAFX 
{
    public PainelProdutoJAVAFX() {}

    public PainelProdutoJAVAFX(Pane paneProduto, Text nomeDoProduto, Text valorPrecoProduto, Text valorArrecadadoProduto, Text valorFaltamProduto, ImageView fotoProduto) 
    {
        this.paneProduto = paneProduto;
        this.nomeDoProduto = nomeDoProduto;
        this.valorPrecoProduto = valorPrecoProduto;
        this.valorArrecadadoProduto = valorArrecadadoProduto;
        this.valorFaltamProduto = valorFaltamProduto;
        this.fotoProduto = fotoProduto;

        this.produto = null;
    }

    public void atualizarAtributosComPainelProduto(PainelProduto painelProduto)
    {
        if (painelProduto == null)
        {
            this.paneProduto.setOpacity(0);
            this.paneProduto.setDisable(true);
            
            return;
        }

        this.produto = painelProduto;

        this.valorPrecoProduto.setText(painelProduto.getValorProdutoString());
        this.valorArrecadadoProduto.setText(painelProduto.getValorArrecadadoString());
        this.valorFaltamProduto.setText(painelProduto.getValorFaltamString());
        this.nomeDoProduto.setText(painelProduto.getNomeDoProduto());

        try
        {
            Image fotoProdutoImg = new Image(painelProduto.getUrlImagem());
            this.fotoProduto.setImage(fotoProdutoImg);
        }
        catch (IllegalArgumentException exception)
        {
            
        }
    }

    public Pane getPaneProduto() 
    {
        return this.paneProduto;
    }

    public Text getNomeDoProduto() 
    {
        return this.nomeDoProduto;
    }

    public void setNomeDoProduto(Text nomeDoProduto) 
    {
        this.nomeDoProduto = nomeDoProduto;
    }

    public void setPaneProduto(Pane paneProduto) 
    {
        this.paneProduto = paneProduto;
    }

    public Text getValorPrecoProduto() 
    {
        return this.valorPrecoProduto;
    }

    public void setValorPrecoProduto(Text valorPrecoProduto) 
    {
        this.valorPrecoProduto = valorPrecoProduto;
    }

    public Text getValorArrecadadoProduto() 
    {
        return this.valorArrecadadoProduto;
    }

    public void setValorArrecadadoProduto(Text valorArrecadadoProduto) 
    {
        this.valorArrecadadoProduto = valorArrecadadoProduto;
    }

    public Text getValorFaltamProduto() 
    {
        return this.valorFaltamProduto;
    }

    public void setValorFaltamProduto(Text valorFaltamProduto) 
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

    public PainelProduto getProduto() 
    {
        return produto;
    }

    public void setProduto(PainelProduto produto) 
    {
        this.produto = produto;
    }

    private Pane paneProduto;

    private Text nomeDoProduto;

    private Text valorPrecoProduto;

    private Text valorArrecadadoProduto;

    private Text valorFaltamProduto;

    private ImageView fotoProduto;

    PainelProduto produto;
}
