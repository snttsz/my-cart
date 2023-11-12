package sistema;

import java.util.ArrayList;

public class Usuario {
    
    /* Contrutores */

    public Usuario(){};

    public Usuario(int id, String nome, String login, String senha, String email) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.produtos = new ArrayList<Produto>();
    }

    public Usuario(String nome, String login, String senha, String email) 
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.produtos = new ArrayList<Produto>();
    }

    /* Getters e Setters */

    public void setValores(int id, String nome, String login, String senha, String email, ArrayList<Produto> produtos)
    {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.produtos = produtos;
    }

    public void setId(int idUsuario) {
        this.id = idUsuario;
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

    public void setProdutos(ArrayList<Produto> produtos) 
    {
        this.produtos = produtos;
    }

    public String getNomeTabela() 
    {
        return Usuario.nomeTabela;
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
        EMAIL("email"),;

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
    private static final String nomeTabela = "Usuario";
    private ArrayList<Produto> produtos;

}
