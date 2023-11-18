package sistema;

import java.util.ArrayList;

import DAO.TagDAO;
import DAO.Tag_has_Produto;

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
        this.insert();
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
        tagDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
        tagDAO.updateInt(this, Coluna.ID.getNomeColuna(), this.id);
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

    /* Funções */

    public static ArrayList<Tag> allTags()
    {
        return tagDAO.selectAll();
    }

    public ArrayList<Produto> produtosRelacionados()
    {
        return tag_has_produto_DAO.selectTodosProdutosDaTag(this.getId());
    }

    public void delete()
    {
        // Deletando da tabela de tag_has_produto
        ArrayList<Produto> produtos = this.produtosRelacionados();
        for (Produto produto : produtos) 
        {
            tag_has_produto_DAO.delete(this, produto);
        }

        // Deletando da tabela de tag
        tagDAO.delete(this);
    }

    public void insert()
    {
        // Adicionando da tabela de tag
        tagDAO.insert(this);
    }

    /* Atributos */

    private int id;
    private String nome;
    private static TagDAO tagDAO = new TagDAO();
    private static Tag_has_Produto tag_has_produto_DAO = new Tag_has_Produto();
    private static final String nomeTabela = "Tag";
}
