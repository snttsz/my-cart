package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoEletronico extends Produto
{

    /* Construtores */

    public ProdutoEletronico(){};

    public ProdutoEletronico(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String modelo,
            String voltagem, boolean possuiBateria, float duracaoBateria) 
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.modelo = modelo;
        this.voltagem = voltagem;
        this.possuiBateria = possuiBateria;
        this.duracaoBateria = duracaoBateria;
    }

    /* Getters e Setters */

    public String getModelo() 
    {
        return modelo;
    }

    public void setModelo(String modelo) 
    {
        this.modelo = modelo;
    }

    public String getVoltagem() 
    {
        return voltagem;
    }

    public void setVoltagem(String voltagem) 
    {
        this.voltagem = voltagem;
    }

    public boolean isPossuiBateria() 
    {
        return possuiBateria;
    }

    public void setPossuiBateria(boolean possuiBateria) 
    {
        this.possuiBateria = possuiBateria;
    }

    public double getDuracaoBateria() 
    {
        return duracaoBateria;
    }

    public void setDuracaoBateria(double duracaoBateria) 
    {
        this.duracaoBateria = duracaoBateria;
    }

    /* Atributos */
    
    private String modelo;
    private String voltagem;
    private boolean possuiBateria;
    private double duracaoBateria; // Em horas
}
