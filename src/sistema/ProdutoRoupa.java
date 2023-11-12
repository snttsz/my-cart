package sistema;

import java.util.ArrayList;

public class ProdutoRoupa extends Produto
{
    
    /* Construtores */

    public ProdutoRoupa(){};

    
        public ProdutoRoupa(int id, int disponibilidade, String descricao, String nome, double preco, String link,
            String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
            String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags,
            String tamanho, String cor, String material) 
        {
    
            super(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, especificacoes, tags);
            this.tamanho = tamanho;
            this.cor = cor;
            this.material = material;
            
        }

        


    /* Getters e Setters */



    public ProdutoRoupa(int disponibilidade, String descricao, String nome, double preco, String link,
        String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado,
        double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags,
        String tamanho, String cor, String material) 
    {
        super(disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade,
            valorArrecadado, valorFrete, categoria, especificacoes, tags);
            this.tamanho = tamanho;
            this.cor = cor;
            this.material = material;
    }


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

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        MATERIAL("material"),
        COR("cor"),
        TAMANHO("tamanho");

        private final String nomeColuna;

        Coluna(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }

    /* Atributos */

    private String tamanho; 
    private String cor;
    private String material; 

}
