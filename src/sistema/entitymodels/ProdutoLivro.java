package sistema.entitymodels;

public class ProdutoLivro extends Produto
{   

    /* Construtores */

    public ProdutoLivro(){};

    public ProdutoLivro(int codigo, String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete) 
    {
        super(codigo, nome, preco, data_de_adicao, valorArrecadado, valorFrete);
    }

    /* Getters e Setters */

    public int getDimensoes() 
    {
        return dimensoes;
    }

    public void setDimensoes(int dimensoes) 
    {
        this.dimensoes = dimensoes;
    }

    /* Atributos */

    int dimensoes;
}
