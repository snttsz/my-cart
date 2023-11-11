package sistema.entitymodels;

public class ProdutoRoupa extends Produto
{
    

    public ProdutoRoupa(String tamanho, String cor, String material) 
    {
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    public ProdutoRoupa(int codigo, String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete, String tamanho, 
    String cor, String material) 
    {
        super(codigo, nome, preco, data_de_adicao, valorArrecadado, valorFrete);
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

    public String getCor() {
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


    private String tamanho;
    private String cor;
    private String material;
}
