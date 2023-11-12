package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoRoupa extends Produto
{
    
    /* Construtores */

    public ProdutoRoupa(){};

    public ProdutoRoupa(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String tamanho, String cor,
            String material, String estilo) 
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
        this.estilo = estilo;
    }

    /* Getters e Setters */

    public String getTamanho() 
    {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) 
    {
        this.tamanho = tamanho;
    }

    public String getCor() 
    {
        return this.cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public String getMaterial() 
    {
        return this.material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getEstilo() 
    {
        return estilo;
    }

    public void setEstilo(String estilo) 
    {
        this.estilo = estilo;
    }

    /* Atributos */

    private String tamanho; // G, P, M, GG ....
    private String cor;
    private String material; // Exemplo: nilon, algodão ...
    private String estilo; // Exemplo: casual, esportivo, formal ...

}
