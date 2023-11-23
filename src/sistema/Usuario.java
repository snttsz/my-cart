package sistema;

import java.util.ArrayList;

public class Usuario {
    
    /* Contrutores */

    public Usuario(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Usuario(int id, String nome, String login, String senha, String email, String url_foto) 
    {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.url_foto = url_foto;
        this.produtos = new ArrayList<Produto>();
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Usuario(String nome, String login, String senha, String email, String url_foto) 
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.url_foto = url_foto;
        this.produtos = new ArrayList<Produto>();
    }

    /* Funções gerais */
    public static void printarUsuario(Usuario usuario)
    {
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome:" + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("Url_foto: " + usuario.getUrl_foto());
        System.out.println("\n");
    }

    /* Getters e Setters */

    public void setValores(String nome, String login, String senha, String email, ArrayList<Produto> produtos)
    {
        this.setNome(nome);
        this.setLogin(login);
        this.setSenha(senha);
        this.setEmail(email);
    }

    public static String getNomeTabela() 
    {
        return Usuario.nomeTabela;
    }

    public int getId() 
    {
        return this.id;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getLogin() 
    {
        return this.login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }

    public String getSenha() 
    {
        return this.senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }

    public String getEmail() 
    {
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public ArrayList<Produto> getProdutos() 
    {
        return this.produtos;
    }

    public String getUrl_foto() 
    {
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
    }

    public void setProdutos(ArrayList<Produto> produtos)
    {
        this.produtos = produtos;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idUsuario"),
        NOME("nome"),
        LOGIN("login"),
        SENHA("senha"),
        URL_FOTO("url_foto"),
        EMAIL("email");

        public final String nomeColuna;

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
    private String login;
    private String senha;
    private String email;
    private String url_foto;
    private static final String nomeTabela = "Usuario";
    private ArrayList<Produto> produtos;

}
