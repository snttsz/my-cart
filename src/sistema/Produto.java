package sistema;

public abstract class Produto 
{

    public Produto(double preco, String nome, int codigo, Categoria categoria) 
    {
        this.setValores(preco, nome, codigo, categoria);
        this.valorArrecadado = 0;
    }

    public Produto(double preco, String nome, int codigo, Categoria categoria, int valorArrecadado) 
    {
        this.setValores(valorArrecadado, nome, valorArrecadado, categoria);
        this.valorArrecadado = valorArrecadado;
    }

    public double getPreco() 
    {
        return this.preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public int getCodigo() 
    {
        return this.codigo;
    }

    public void setCodigo(int codigo) 
    {
        this.codigo = codigo;
    }
    
    public double getValorArrecadado() 
    {
        return valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) 
    {
        this.valorArrecadado = valorArrecadado;
    }

    protected void setValores(double preco, String nome, int codigo, Categoria categoria)
    {
        this.preco = preco;
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
    }

    private double preco;
    private String nome;
    private int codigo;
    private double valorArrecadado;
    private String link; // Gerar get & set e adicionar no construtor
    private String descricao; // Gerar get & set
    private Categoria categoria; // Gerar get & set
    private double valorFrete; // Gerar get & set e adicionar no construtor
    private String tag; // Produto pode ter uma tag, e aí a gente pode ter um filtro que mostre os produtos dql tag
}
