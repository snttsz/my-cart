package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoMobilia extends Produto
{

    /* Construtores */

    public ProdutoMobilia(){};

    public ProdutoMobilia(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String material, String cor, String estilo,
            double altura, double largura, double comprimento, double peso) 
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.material = material;
        this.cor = cor;
        this.estilo = estilo;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.peso = peso;
    }

    /* Getters e Setters */

    public String getMaterial() 
    {
        return material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getCor() 
    {
        return cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public String getEstilo() 
    {
        return estilo;
    }

    public void setEstilo(String estilo) 
    {
        this.estilo = estilo;
    }

    public double getAltura() 
    {
        return altura;
    }

    public void setAltura(double altura) 
    {
        this.altura = altura;
    }

    public double getLargura() 
    {
        return largura;
    }

    public void setLargura(double largura) 
    {
        this.largura = largura;
    }

    public double getComprimento() 
    {
        return comprimento;
    }

    public void setComprimento(double comprimento) 
    {
        this.comprimento = comprimento;
    }

    public double getPeso() 
    {
        return peso;
    }

    public void setPeso(double peso) 
    {
        this.peso = peso;
    }

    /* Atributos */
    
    private String material; 
    private String cor;
    private String estilo; // Exemplo: moderno, clássico, rústico
    private double altura; // Em metros
    private double largura; // Em metros
    private double comprimento; // Em metros
    private double peso; // Em kg;

}
