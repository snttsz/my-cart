package interfacegrafica.models;

import sistema.Loja;
import sistema.Produto;

public class PainelLoja 
{
    public PainelLoja(Loja loja, PainelProduto produto1, PainelProduto produto2, PainelProduto produto3)
    {
        this.idLoja = loja.getId();
        this.nomeDaLoja = loja.getNome();
        this.urlDaImagem = loja.getUrl_foto();
        this.produto1 = produto1;
        this.produto2 = produto2;
        this.produto3 = produto3;
    }

    public PainelLoja(int idLoja, String nomeDaLoja, String urlDaImagem, PainelProduto produto1, PainelProduto produto2, PainelProduto produto3) 
    {
        this.idLoja = idLoja;
        this.nomeDaLoja = nomeDaLoja;
        this.urlDaImagem = urlDaImagem;
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
    
    public String getNomeDaLoja() 
    {
        return nomeDaLoja;
    }
    
    public void setNomeDaLoja(String nomeDaLoja) 
    {
        this.nomeDaLoja = nomeDaLoja;
    }
    
    public String getUrlDaImagem() 
    {
        return urlDaImagem;
    }
    
    public void setUrlDaImagem(String urlDaImagem) 
    {
        this.urlDaImagem = urlDaImagem;
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

    private int idLoja;
    private String nomeDaLoja;
    private String urlDaImagem;
    private PainelProduto produto1;
    private PainelProduto produto2;
    private PainelProduto produto3;
}
