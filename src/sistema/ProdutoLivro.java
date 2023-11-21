package sistema;

import java.util.ArrayList;

import DAO.ProdutoDAO;

public class ProdutoLivro extends Produto
{   

    /* Construtores */

    public ProdutoLivro(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoLivro(int id, String descricao, String nome, double preco, String link,
        String url_foto, String data_de_adicao,  double valorArrecadado, double valorFrete, 
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String autor, String genero, int idUsuario, int idLoja) 
    {
        super(id,descricao, nome, preco, link, url_foto,  data_de_adicao, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags, idUsuario,idLoja);

        this.autor = autor;
        this.genero = genero;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoLivro(String descricao, String nome, double preco, String link, String url_foto,
         String data_de_adicao, double valorArrecadado, double valorFrete,
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String autor,
        String genero, int idUsuario, int idLoja) 
    {
        super(descricao, nome, preco, link, url_foto, data_de_adicao,
        valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario,idLoja);
        this.autor = autor;
        this.genero = genero;

        super.getProdutoDAO().insert(this);
    }

    /* Getters e Setters */

    public String getAutor() 
    {
        return this.autor;
    }

    public void setAutor(String autor) 
    {
        this.autor = autor;
        produtoDAO.updateString(this, Coluna.AUTOR.getNomeColuna(), this.autor);
    }

    public String getGenero() 
    {
        return this.genero;
    }

    public void setGenero(String genero) 
    {
        this.genero = genero;
        produtoDAO.updateString(this, Coluna.GENERO.getNomeColuna(), this.genero);
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        AUTOR("autor"),
        GENERO("genero");

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
    private String autor;
    private String genero;
    private ProdutoDAO produtoDAO = new ProdutoDAO();

}
