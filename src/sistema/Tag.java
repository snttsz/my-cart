package sistema;

public class Tag 
{
    /* Construtores */
    
    public Tag(){};

    public Tag(String nome, int id) 
    {
        this.nome = nome;
        this.id = id;
    }
    
    /* Getters e Setters */
    public String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public static String getNomeTabela() 
    {
        return Tag.nomeTabela;
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idTag"),
        NOME("nome");
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

    /* ATRIBUTOS */
    private String nome;
    private int id;
    private static final String nomeTabela = "Tag";
}
