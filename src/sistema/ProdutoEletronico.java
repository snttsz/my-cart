package sistema;

public class ProdutoEletronico extends Produto
{   

    public ProdutoEletronico(){};

    public ProdutoEletronico(int codigo, String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete) 
    {
        super(codigo, nome, preco, data_de_adicao, valorArrecadado, valorFrete);
    }

    int dimensoes;
}
