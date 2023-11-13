package sistema;


// Um produto pode ou não ter uma loja associada
// A gente pode fazer uma aba pra lojas também, e essas lojas vão ter produtos cadastrados
// E na hora de cadastrar um produto ele pode ser associado a uma loja já cadastrada
public class Loja 
{
    
    /* Construtores */

    public Loja(){};


    public Loja(String nome, String url, int id) 
    {
        this.nome = nome;
        this.url = url;
        this.id = id;
    }

    /* Getters e Setters */

    public static String getNomeTabela() 
    {
        return Loja.nomeTabela;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() 
    {
        return this.url;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idLoja"),
        NOME("nome"),
        URL("url");

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
    
    private String nome;
    private String url;
    private int id;
    private static final String nomeTabela = "Loja";

}
