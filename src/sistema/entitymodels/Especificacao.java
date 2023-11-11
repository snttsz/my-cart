package sistema.entitymodels;

public class Especificacao 
{
    public Especificacao(String nome, String valor) 
    {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getValor() 
    {
        return this.valor;
    }

    public void setValor(String valor) 
    {
        this.valor = valor;
    }
    
    private String nome;
    private String valor;
}
