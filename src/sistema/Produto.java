package sistema;

import java.util.ArrayList;

import DAO.ProdutoDAO;

public abstract class Produto 
{

    /* Construtores */

    public Produto(){};
    
    
    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Produto(int id, String descricao, String nome, double preco, String link,
    String url_foto, String data_de_adicao, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, int idUsuario, int idLoja) 
    {
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.link = link;
        this.url_foto = url_foto;
        this.data_de_adicao = data_de_adicao;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
        this.categoria = categoria;
        this.especificacoes = especificacoes;
        this.tags = tags;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLoja = idLoja;
    }
    
    
    /* 
    * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
    */
    public Produto(String descricao, String nome, double preco, String link,
    String url_foto, String data_de_adicao, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags, int idUsuario, int idLoja) 
    {
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.link = link;
        this.url_foto = url_foto;
        this.data_de_adicao = data_de_adicao;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
        this.categoria = categoria;
        this.especificacoes = especificacoes;
        this.tags = tags;
        this.idUsuario = idUsuario;
        this.idLoja = idLoja;
    }
    
    /* 
    * Funções gerais
    */
    public static void printarProduto(Produto produto)
    {
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: "+ produto.getNome());
        System.out.println("Categoria: " + produto.getCategoria());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Valor frete: " + produto.getValorFrete());
        System.out.println("Descricao: " + produto.getDescricao());
        System.out.println("Link: " + produto.getLink());
        System.out.println("Url_foto: " + produto.getUrl_foto());
        System.out.println("Data de adição: " + produto.getData_de_adicao());
        System.out.println("Valor arrecadado: " + produto.getValorArrecadado());
        System.out.println("\n");
    }
    /* Getters e setters */

    protected void setValores(String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete)
    {
        this.setNome(nome);
        this.setPreco(preco);
        this.setData_de_adicao(data_de_adicao);
        this.setValorArrecadado(valorArrecadado);
        this.setValorFrete(valorFrete);
    }
    
    protected void setValores(int disponibilidade, String descricao, String nome, double preco, String link,String url_foto, 
    String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, String categoria, 
    ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        this.setNome(nome);
        this.setPreco(preco);
        this.setLink(link);
        this.setUrl_foto(url_foto);
        this.setData_de_adicao(data_de_adicao);
        this.setValorArrecadado(valorArrecadado);
        this.setValorFrete(valorFrete);
        this.setCategoria(categoria);
        this.setEspecificacoes(especificacoes);
        this.setTags(tags);
        this.setDescricao(descricao);
    }
    
    public static String getNomeTabela() 
    {
        return Produto.nomeTabela;
    }

    public void setEspecificacoes(ArrayList<Especificacao> especificacoes)
    {
        ;
    }

    public int getIdUsuario() 
    {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) 
    {
        this.idUsuario = idUsuario;
    }
    
    public String getDescricao() 
    {
        return this.descricao;
    } 
    
    public void setDescricao(String descricao) 
    {
        this.descricao = descricao;
        produtoDAO.updateString(this, Coluna.DESCRICAO.getNomeColuna(), this.descricao);
    }

    public double getPreco() 
    {
        return this.preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
        produtoDAO.updateDouble(this, Coluna.PRECO.getNomeColuna(), this.preco);
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
        produtoDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public String getLink() 
    {
        return this.link;
    }

    public void setLink(String link) 
    {
        this.link = link;
        produtoDAO.updateString(this, Coluna.LINK.getNomeColuna(), this.link);
    }

    public String getUrl_foto() 
    {
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
        produtoDAO.updateString(this, Coluna.URL_FOTO.getNomeColuna(), this.url_foto);
    }

 
    public String getData_de_adicao() 
    {
        return this.data_de_adicao;
    }

    public void setData_de_adicao(String data_de_adicao) 
    {
        produtoDAO.updateString(this, Coluna.DATA_DE_ADICAO.getNomeColuna(), this.data_de_adicao);
    }


    public double getValorFrete() 
    {
        return this.valorFrete;
    }

    public void setValorFrete(double valorFrete) 
    {
        this.valorFrete = valorFrete;
        produtoDAO.updateDouble(this, Coluna.VALOR_FRETE.getNomeColuna(), this.valorFrete);
    }

    public int getIdLoja() 
    {
        return this.idLoja;
    }


    public void setIdLoja(int idLoja) 
    {
        this.idLoja = idLoja;
    }


    public String getCategoria() 
    {
        return this.categoria;
    }

    public void setCategoria(String categoria) 
    {
        this.categoria = categoria;
        produtoDAO.updateString(this, Coluna.CATEGORIA.getNomeColuna(), this.categoria);
    }

    public ArrayList<Especificacao> getEspecificacoes() 
    {
        return this.especificacoes;
    }

    public ArrayList<String> getTags() 
    {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags) 
    {
        /*
        */
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
        produtoDAO.updateInt(this, Coluna.ID.getNomeColuna(), this.id);
    }
    
    public double getValorArrecadado() 
    {
        return this.valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) 
    {
        this.valorArrecadado = valorArrecadado;
        produtoDAO.updateDouble(this, Coluna.VALOR_ARRECADADO.getNomeColuna(), this.valorArrecadado);
    }

    public ProdutoDAO getProdutoDAO() 
    {
        return Produto.produtoDAO;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idProduto"),
        NOME("nome"),
        PRECO("valor"),
        LINK("url"),
        DATA_DE_ADICAO("data_de_adicao"),
        URL_FOTO("url_foto"),
        VALOR_ARRECADADO("valor_arrecadado"),
        DESCRICAO("descricao"),
        CATEGORIA("categoria"),
        VALOR_FRETE("valor_frete"),
        IDUSUARIO("Usuario_idUsuario"),
        IDLOJA("Loja_idLoja");

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

    /* 
     * Enum com todas as possíveis categorias de um produto
     */
    public enum Categorias
    {
        ELETRONICO("Eletrônico"),
        ALIMENTICIO("Alimentício"),
        FERRAMENTA("Ferramenta"),
        LIVRO("Livro"),
        ROUPA("Roupa"),
        MOBILIA("Mobília");

        private final String categoria;

        Categorias(String categoria)
        {
            this.categoria = categoria;
        }

        public String getCategoria()
        {
            return this.categoria;
        }
    }

    /* Atributos */

    private int id;
    private int idUsuario;
    private String descricao;
    private String nome;
    private String link;
    private String url_foto;
    private String data_de_adicao;
    private String categoria;
    private double preco;
    private double valorArrecadado;
    private double valorFrete; 
    private int idLoja;
    private ArrayList<Especificacao> especificacoes;
    private ArrayList<String> tags;
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final String nomeTabela = "Produto";

}
