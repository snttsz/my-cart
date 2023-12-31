package interfacegrafica.models;

import sistema.Produto;

public class PaginaProduto 
{
    public PaginaProduto(Produto[] produtos)
    {
        if (produtos == null)
        {
            return;
        }

        if (produtos[0] != null)
        {
            this.produto1 = new PainelProduto(produtos[0]);
        }

        if (produtos[1] != null)
        {
            this.produto2 = new PainelProduto(produtos[1]);
        }

        if (produtos[2] != null)
        {
            this.produto3 = new PainelProduto(produtos[2]);
        }

        if (produtos[3] != null)
        {
            this.produto4 = new PainelProduto(produtos[3]);
        }   

        if (produtos[4] != null)
        {
            this.produto5 = new PainelProduto(produtos[4]);
        }

        if (produtos[5] != null)
        {
            this.produto6 = new PainelProduto(produtos[5]);
        }
        
    }

    public PainelProduto getProduto1() 
    {
        return this.produto1;
    }

    public void setProduto1(PainelProduto produto1) 
    {
        this.produto1 = produto1;
    }

    public PainelProduto getProduto2() 
    {
        return this.produto2;
    }

    public void setProduto2(PainelProduto produto2) 
    {
        this.produto2 = produto2;
    }

    public PainelProduto getProduto3() 
    {
        return this.produto3;
    }

    public void setProduto3(PainelProduto produto3) 
    {
        this.produto3 = produto3;
    }

    public PainelProduto getProduto4() 
    {
        return this.produto4;
    }

    public void setProduto4(PainelProduto produto4) 
    {
        this.produto4 = produto4;
    }

    public PainelProduto getProduto5() 
    {
        return this.produto5;
    }

    public void setProduto5(PainelProduto produto5) 
    {
        this.produto5 = produto5;
    }

    public PainelProduto getProduto6() 
    {
        return this.produto6;
    }

    public void setProduto6(PainelProduto produto6) 
    {
        this.produto6 = produto6;
    }

    PainelProduto produto1 = null;
    PainelProduto produto2 = null;
    PainelProduto produto3 = null;
    PainelProduto produto4 = null;
    PainelProduto produto5 = null;
    PainelProduto produto6 = null;
}
