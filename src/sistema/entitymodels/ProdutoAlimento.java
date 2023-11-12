package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoAlimento extends Produto
{

    /* Construtores */

    public ProdutoAlimento(){};

    public ProdutoAlimento(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String tipo, String dataValidade, 
            String origem, float peso)
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.tipo = tipo;
        this.dataValidade = dataValidade;
        this.origem = origem;
        this.peso = peso;
    }

    /* Getters e Setters */

    public String getTipo() 
    {
        return tipo;
    }

    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }

    public String getDataValidade() 
    {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) 
    {
        this.dataValidade = dataValidade;
    }

    public String getOrigem() 
    {
        return origem;
    }

    public void setOrigem(String origem) 
    {
        this.origem = origem;
    }

    public float getPeso() 
    {
        return peso;
    }

    public void setPeso(float peso) 
    {
        this.peso = peso;
    }

    /* Atributos */
    
    private String tipo; // Exemplo: frutas, vegetais, carne, laticínios
    private String dataValidade;
    private String origem; // Exemplo: local, importado
    private float peso; // Em kg

}
