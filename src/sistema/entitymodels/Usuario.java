package sistema.entitymodels;

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

    public int getId() {
        return id;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }

    public String getSenha() 
    {
        return senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public ArrayList<Produto> getProdutos() 
    {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) 
    {
        this.produtos = produtos;
    }

    /* Atributos */

    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private ArrayList<Produto> produtos;
}
