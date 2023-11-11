package sistema.entitymodels;

// É uma ajuda pra interface ter um objeto categoria, já é mais fácil pra puxar os dados
// A gente pode desenvolver essa classe também, se pensarmos em mais coisas pra adicionar 
// Mas também pode ser descartada
public class Categoria 
{
    public Categoria(String nome, String descricao) 
    {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getDescricao() 
    {
        return descricao;
    }

    public void setDescricao(String descricao) 
    {
        this.descricao = descricao;
    }

    private String nome;
    private String descricao;
}
