package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoFerramenta extends Produto
{

    /* Construtores */

    public ProdutoFerramenta(){};

    public ProdutoFerramenta(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, boolean eletrica,
            boolean recarregavel, double potencia, boolean portatil) 
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.eletrica = eletrica;
        this.recarregavel = recarregavel;
        this.potencia = potencia;
        this.portatil = portatil;
    }

    /* Getters e Setters */

    public boolean isEletrica() 
    {
        return eletrica;
    }

    public void setEletrica(boolean eletrica) 
    {
        this.eletrica = eletrica;
    }

    public boolean isRecarregavel() 
    {
        return recarregavel;
    }

    public void setRecarregavel(boolean recarregavel) 
    {
        this.recarregavel = recarregavel;
    }

    public double getPotencia() 
    {
        return potencia;
    }

    public void setPotencia(double potencia) 
    {
        this.potencia = potencia;
    }

    public boolean isPortatil() 
    {
        return portatil;
    }

    public void setPortatil(boolean portatil) 
    {
        this.portatil = portatil;
    }

    /* Atributos */
    
    private boolean eletrica; // Indica se a ferramenta é elétrica
    private boolean recarregavel; // Indica se a ferramenta é recarregável
    private double potencia; // Em watts ou outra unidade de medida relevante
    private boolean portatil; // Indica se a ferramenta é portátil

}
