package interfacegrafica.models;

import sistema.Produto;

public class PainelProduto
{   
    public PainelProduto (Produto produto)
    {
        this.nomeDoProduto = produto.getNome();
        this.idProduto = produto.getId();
        this.valorArrecadado = produto.getValorArrecadado();
        this.valorProduto = produto.getPreco() + produto.getValorFrete();
        this.urlImagem = produto.getUrl_foto();

        this.setarValoresString();
    }

    public PainelProduto(int idProduto, String nome, double valorArrecadado, double valorProduto, String urlImagem) 
    {
        this.idProduto = idProduto;
        this.valorArrecadado = valorArrecadado;
        this.valorProduto = valorProduto;
        this.urlImagem = urlImagem;
        this.nomeDoProduto = nome;

        this.setarValoresString();
    }

    private void setarValoresString()
    {
        this.valorArrecadadoString = "R$ " + String.valueOf(this.valorArrecadado);
        this.valorProdutoString = "R$ " + String.valueOf(this.valorProduto);
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

    public String getNomeDoProduto() 
    {
        return this.nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) 
    {
        this.nomeDoProduto = nomeDoProduto;
    }

    private int idProduto;
    private double valorArrecadado;
    private double valorProduto;
    private String urlImagem;
    private String nomeDoProduto;

    private String valorProdutoString;
    private String valorArrecadadoString;
}
