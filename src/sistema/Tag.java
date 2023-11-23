package sistema;

public class Tag 
{
    /* Construtores */
    
    public Tag(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Tag(int id, String nome) 
    {
        this.id = id;
        this.nome = nome;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Tag(String nome) 
    {
        this.nome = nome;
    }
    
    /* Funções gerais */
    public static void printarTag(Tag tag)
    {
        System.out.println("ID: " + tag.getId());
        System.out.println("Nome: " + tag.getNome());
        System.out.println("\n");
    }
    /* Getters e Setters */

    public static String getNomeTabela() 
    {
        return Tag.nomeTabela;
    }

    public String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome) 
    {
        this.nome = nome;
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

    /* Atributos */

    private int id;
    private String nome;
    private static final String nomeTabela = "Tag";
}
