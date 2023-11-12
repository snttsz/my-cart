package sistema.entitymodels;

import java.util.ArrayList;

public class ProdutoLivro extends Produto
{   

    /* Construtores */

    public ProdutoLivro(){};

    public ProdutoLivro(int codigo, String nome, double preco, String link, String url_foto, String marca,
            String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, Categoria categoria,
            ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String autor, String genero,
            int numeroPaginas, String editora, int anoPublicacao)
    {
        super(codigo, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.autor = autor;
        this.genero = genero;
        this.numeroPaginas = numeroPaginas;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    /* Getters e Setters */

    public String getAutor() 
    {
        return autor;
    }

    public void setAutor(String autor) 
    {
        this.autor = autor;
    }

    public String getGenero() 
    {
        return genero;
    }

    public void setGenero(String genero) 
    {
        this.genero = genero;
    }

    public int getNumeroPaginas() 
    {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) 
    {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditora() 
    {
        return editora;
    }

    public void setEditora(String editora) 
    {
        this.editora = editora;
    }

    public int getAnoPublicacao() 
    {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) 
    {
        this.anoPublicacao = anoPublicacao;
    }

    /* Atributos */

    private String autor;
    private String genero;
    private int numeroPaginas;
    private String editora;
    private int anoPublicacao;
}
