package interfacegrafica.models;

public class PainelProduto
{   
    public PainelProduto(int idProduto, double valorArrecadado, double valorProduto, String urlImagem) 
    {
        this.idProduto = idProduto;
        this.valorArrecadado = valorArrecadado;
        this.valorProduto = valorProduto;
        this.urlImagem = urlImagem;
    }

    public int getIdProduto() 
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto) 
    {
        this.idProduto = idProduto;
    }
    
    public double getValorProduto() 
    {
        return valorProduto;
    }
    
    public void setValorProduto(double valorProduto) 
    {
        this.valorProduto = valorProduto;
    }
    
    public double getValorArrecadado() 
    {
        return valorArrecadado;
    }
    
    public void setValorArrecadado(double valorArrecadado) 
    {
        this.valorArrecadado = valorArrecadado;
    }
    
    public String getValorProdutoString() 
    {
        return valorProdutoString;
    }
    
    public void setValorProdutoString(String valorProdutoString) 
    {
        this.valorProdutoString = valorProdutoString;
    }
    
    public String getValorArrecadadoString() 
    {
        return valorArrecadadoString;
    }
    
    public void setValorArrecadadoString(String valorArrecadadoString) 
    {
        this.valorArrecadadoString = valorArrecadadoString;
    }
    
    public String getUrlImagem() 
    {
        return urlImagem;
    }
    
    public void setUrlImagem(String urlImagem) 
    {
        this.urlImagem = urlImagem;
    }

    public String getValorFaltamString()
    {
        double faltam = this.valorProduto - this.valorArrecadado;

        String faltamString = String.valueOf(faltam);
        String result = "R$ ";
        
        if (faltam < 0)
        {
            result += "0.0";
        }
        else
        {
            result += faltamString;
        }

        return result;
    }

    private int idProduto;
    private double valorArrecadado;
    private double valorProduto;
    private String urlImagem;

    private String valorProdutoString;
    private String valorArrecadadoString;
}
