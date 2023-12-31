package sistema;

public class Loja 
{
    /* Construtores */

    public Loja(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Loja(int id, String nome, String url, String url_foto, int idUsuario) 
    {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.url_foto = url_foto;
        this.idUsuario = idUsuario;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Loja(String nome, String url, String url_foto, int idUsuario) 
    {
        this.nome = nome;
        this.url = url;
        this.url_foto = url_foto;
        this.idUsuario = idUsuario;
    }

    /* Funções gerais */
    public static void printarLoja(Loja loja)
    {
        System.out.println("ID: " + loja.getId());
        System.out.println("Nome: " + loja.getNome());
        System.out.println("Url: " + loja.getUrl());
        System.out.println("Url_foto: " + loja.url_foto);
        System.out.println("\n");
    }
    /* Getters e Setters */

    public void setValores(String nome, String url)
    {
        this.setNome(nome);
        this.setUrl(url);
    }

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
    
    public String getUrl_foto() 
    {
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
    }

    public int getIdUsuario() 
    {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) 
    {
        this.idUsuario = idUsuario;
    }



    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idLoja"),
        NOME("nome"),
        URL("url"),
        URL_FOTO("url_foto");

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
    private String url_foto;
    private int id;
    private int idUsuario;
    private static final String nomeTabela = "Loja";

}
