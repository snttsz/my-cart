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
    public Produto(int id, int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        this.disponibilidade = disponibilidade;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.link = link;
        this.url_foto = url_foto;
        this.marca = marca;
        this.data_de_adicao = data_de_adicao;
        this.prioridade = prioridade;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
        this.categoria = categoria;
        this.especificacoes = especificacoes;
        this.tags = tags;
        this.id = id;
    }

        
    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Produto(int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        this.disponibilidade = disponibilidade;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.link = link;
        this.url_foto = url_foto;
        this.marca = marca;
        this.data_de_adicao = data_de_adicao;
        this.prioridade = prioridade;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
        this.categoria = categoria;
        this.especificacoes = especificacoes;
        this.tags = tags;
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
        System.out.println("Disponibilidade: " + produto.getDisponibilidade());
        System.out.println("Prioridade: " + produto.getPrioridade());
        System.out.println("Marca: " + produto.getMarca());
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
        this.setMarca(marca);
        this.setPrioridade(prioridade);
        this.setData_de_adicao(data_de_adicao);
        this.setValorArrecadado(valorArrecadado);
        this.setValorFrete(valorFrete);
        this.setCategoria(categoria);
        this.setEspecificacoes(especificacoes);
        this.setTags(tags);
        this.setDisponibilidade(disponibilidade);
        this.setDescricao(descricao);
    }

    public static String getNomeTabela() 
    {
        return Produto.nomeTabela;
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

    public int getDisponibilidade() 
    {
        return this.disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) 
    {
        this.disponibilidade = disponibilidade;
        produtoDAO.updateInt(this, Coluna.DISPONIBILIDADE.getNomeColuna(), this.disponibilidade);
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

    public String getMarca() 
    {
        return this.marca;
    }

    public void setMarca(String marca) 
    {
        this.marca = marca;
        produtoDAO.updateString(this, Coluna.MARCA.getNomeColuna(), this.marca);
    }

    public String getData_de_adicao() 
    {
        return this.data_de_adicao;
    }

    public void setData_de_adicao(String data_de_adicao) 
    {
        produtoDAO.updateString(this, Coluna.DATA_DE_ADICAO.getNomeColuna(), this.data_de_adicao);
    }

    public int getPrioridade() 
    {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) 
    {
        this.prioridade = prioridade;
        produtoDAO.updateInt(this, Coluna.PRIORIDADE.getNomeColuna(), this.prioridade);
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

    public void setEspecificacoes(ArrayList<Especificacao> especificacoes) 
    {
        /*
        ArrayList<Especificacao> especificacoes_antigas = this.especificacoes;

        especificacoes_antigas.forEach(especificacao -> {
            especificacao.deletar();
        });

        this.especificacoes = especificacoes;

        this.especificacoes.forEach(especificacao -> {
            especificacao.adicionar();
        });
        */
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
        MARCA("marca"),
        DATA_DE_ADICAO("data_de_adicao"),
        DISPONIBILIDADE("disponibilidade"),
        PRIORIDADE("prioridade"),
        URL_FOTO("url_foto"),
        VALOR_ARRECADADO("valor_arrecadado"),
        DESCRICAO("descricao"),
        CATEGORIA("categoria"),
        VALOR_FRETE("valor_frete"),
        IDUSUARIO("Usuario_idUsuario");

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
        MOBILIA("Mobília"),
        ROUPA("Roupa");

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
    private int disponibilidade;
    private int prioridade;
    private String descricao;
    private String nome;
    private String link;
    private String url_foto;
    private String marca;
    private String data_de_adicao;
    private String categoria;
    private double preco;
    private double valorArrecadado;
    private double valorFrete; 
    private ArrayList<Especificacao> especificacoes;
    private ArrayList<String> tags;
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final String nomeTabela = "Produto";

}
