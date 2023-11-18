package sistema;

import java.util.ArrayList;

import DAO.UsuariosDAO;
import DAO.ProdutoDAO;

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

        this.insert();
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
    }

    public static String getNomeTabela() 
    {
        return Usuario.nomeTabela;
    }

    public int getId() 
    {
        this.id = usuarioDAO.selectById(this.id).id;
        return this.id;
    }

    public String getNome() 
    {
        this.nome = usuarioDAO.selectById(this.id).nome;
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
        usuarioDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public String getLogin() 
    {
        this.login = usuarioDAO.selectById(this.id).login;
        return this.login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
        usuarioDAO.updateString(this, Coluna.LOGIN.getNomeColuna(), this.login);
    }

    public String getSenha() 
    {
        this.senha = usuarioDAO.selectById(this.id).senha;
        return this.senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
        usuarioDAO.updateString(this, Coluna.SENHA.getNomeColuna(), this.senha);
    }

    public String getEmail() 
    {
        this.nome = usuarioDAO.selectById(this.id).email;
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
        usuarioDAO.updateString(this, Coluna.EMAIL.getNomeColuna(), this.email);
    }

    public ArrayList<Produto> getProdutos() 
    {
        this.produtos = produtosDAO.selectTodosProdutosDoUsuario(this.id);
        return this.produtos;
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

    public static ArrayList<Usuario> allUsuarios()
    {
        return usuarioDAO.selectAll();
    }

    public int qntDeProdutosCadastrados()
    {
        return produtosDAO.contarProdutosDoUsuario(this.getId());
    }

    public int qntDeProdutosCategorizadosCadastrados(String categoria)
    {
        return produtosDAO.contarProdutosCategorizadosDoUsuario(this.getId(), categoria);
    }

    public void delete()
    {
        // Deletando produtos do usuario
        ArrayList<Produto> produtos = getProdutos();
        for (Produto produto : produtos) 
        {
            produto.delete();
        }

        // Deletando usuario
        usuarioDAO.delete(this);
    }

    public void insert()
    {
        // Adicionando usuario
        usuarioDAO.insert(this);
    }

    /* Atributos */

    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private static UsuariosDAO usuarioDAO = new UsuariosDAO();
    private static ProdutoDAO produtosDAO = new ProdutoDAO();
    private static final String nomeTabela = "Usuario";
    private ArrayList<Produto> produtos;

}
