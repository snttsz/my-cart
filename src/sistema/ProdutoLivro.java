package sistema;

import java.util.ArrayList;

public class ProdutoLivro extends Produto
{   

    /* Construtores */

    public ProdutoLivro(){};

    /* Getters e Setters */

    public ProdutoLivro(int id, int disponibilidade, String descricao, String nome, double preco, String link,
        String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String autor, String genero) 
    {
        super(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags);

        this.autor = autor;
        this.genero = genero;
    }

    
    public ProdutoLivro(int disponibilidade, String descricao, String nome, double preco, String link, String url_foto,
        String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete,
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, String autor,
        String genero) 
    {
        super(disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade,
        valorArrecadado, valorFrete, categoria, especificacoes, tags);
        this.autor = autor;
        this.genero = genero;
    }

    public String getAutor() 
    {
        return this.autor;
    }

    public void setAutor(String autor) 
    {
        this.autor = autor;
    }

    public String getGenero() 
    {
        return this.genero;
    }

    public void setGenero(String genero) 
    {
        this.genero = genero;
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

}
