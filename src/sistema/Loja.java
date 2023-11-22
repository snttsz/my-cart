package sistema;

import java.util.ArrayList;

import DAO.LojaDAO;

public class Loja 
{
    
    /* Construtores */

    public Loja(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Loja(int id, String nome, String url, String url_foto) 
    {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.url_foto = url_foto;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Loja(String nome, String url, String url_foto) 
    {
        this.nome = nome;
        this.url = url;
        this.url_foto = url_foto;


        lojaDAO.insert(this);
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
        lojaDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
        lojaDAO.updateInt(this, Coluna.ID.getNomeColuna(), this.id);
    }

    public String getUrl() 
    {
        return this.url;
    }
    
    public void setUrl(String url) 
    {
        this.url = url;
        lojaDAO.updateString(this, Coluna.URL.getNomeColuna(), this.url);
    }
    
    public String getUrl_foto() 
    {
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
        lojaDAO.updateString(this, Coluna.URL_FOTO.getNomeColuna(), this.url_foto);
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

    /* Funções */

    public static ArrayList<Loja> allLojas()
    {
        return lojaDAO.selectAll();
    }

    public ArrayList<Produto> produtosRelacionados()
    {
        return lojaDAO.selectTodosProdutosCadastradosNaLoja(this.getId());
    }

    public void delete()
    {
        // Deletando da tabela de loja
        lojaDAO.delete(this);
    }

    public void insert()
    {
        // Adicionando da tabela de tag
        lojaDAO.insert(this);
    }

    /* Atributos */
    private String nome;
    private String url;
    private String url_foto;
    private int id;
    private static LojaDAO lojaDAO = new LojaDAO();
    private static final String nomeTabela = "Loja";

}
