package sistema.entitymodels;


// Um produto pode ou não ter uma loja associada
// A gente pode fazer uma aba pra lojas também, e essas lojas vão ter produtos cadastrados
// E na hora de cadastrar um produto ele pode ser associado a uma loja já cadastrada
public class Loja 
{
    
    /* Construtores */

    public Loja(){};

    public Loja(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    /* Getters e Setters */
    
    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    /* Atributos */
    
    private String nome;
    private String url;
}
