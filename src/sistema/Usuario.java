package sistema;

import java.util.ArrayList;

import DAO.UsuariosDAO;

public class Usuario {
    
    /* Contrutores */

    public Usuario(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Usuario(int id, String nome, String login, String senha, String email) 
    {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.produtos = new ArrayList<Produto>();
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Usuario(String nome, String login, String senha, String email) 
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.produtos = new ArrayList<Produto>();

        usuarioDAO.insert(this);
    }

    /* Funções gerais */
    public static void printarUsuario(Usuario usuario)
    {
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome:" + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("\n");
    }

    /* Getters e Setters */

    public void setValores(String nome, String login, String senha, String email, ArrayList<Produto> produtos)
    {
        this.setNome(nome);
        this.setLogin(login);
        this.setSenha(senha);
        this.setEmail(email);
        this.setProdutos(produtos);
    }

    public static String getNomeTabela() 
    {
        return Usuario.nomeTabela;
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
        usuarioDAO.updateInt(this, Coluna.ID.getNomeColuna(), this.id);
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
        usuarioDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public String getLogin() 
    {
        return this.login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
        usuarioDAO.updateString(this, Coluna.LOGIN.getNomeColuna(), this.login);
    }

    public String getSenha() 
    {
        return this.senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
        usuarioDAO.updateString(this, Coluna.SENHA.getNomeColuna(), this.senha);
    }

    public String getEmail() 
    {
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
        usuarioDAO.updateString(this, Coluna.EMAIL.getNomeColuna(), this.email);
    }

    public ArrayList<Produto> getProdutos() 
    {
        return this.produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) 
    {
        this.produtos = produtos;
        this.produtos.forEach(produto -> {
            //Produtos
        });
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

    /* Funções */

    public void getProdutoById(int idProduto)
    {
        
    }
    
    /* Atributos */

    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private UsuariosDAO usuarioDAO = new UsuariosDAO();
    private static final String nomeTabela = "Usuario";
    private ArrayList<Produto> produtos;

}
