package sistema;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DAO.Especificacao_has_Produto;
import DAO.ProdutoDAO;
import DAO.Tag_has_Produto;

public abstract class Produto 
{

    /* Construtores */

    public Produto(){};
    
    
    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Produto(int id, int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja) 
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
        this.idUsuario = idUsuario;
        this.idLoja = idLoja;
    }
    
    
    /* 
    * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
    */
    public Produto(int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, int prioridade, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja) 
    {
        this.disponibilidade = disponibilidade;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.link = link;
        this.url_foto = url_foto;
        this.marca = marca;
        this.prioridade = prioridade;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
        this.categoria = categoria;
        this.especificacoes = especificacoes;
        this.tags = tags;
        this.idUsuario = idUsuario;
        this.idLoja = idLoja;

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.data_de_adicao = localDateTime.format(formatter);
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
    String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, String categoria) 
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
        this.setDisponibilidade(disponibilidade);
        this.setDescricao(descricao);
    }
    
    public static String getNomeTabela() 
    {
        return Produto.nomeTabela;
    }

    public int getIdUsuario() 
    {
        this.idUsuario = produtoDAO.selectById(this.id).idUsuario;
        return this.idUsuario;
    }
    
    public String getDescricao() 
    {
        this.descricao = produtoDAO.selectById(this.id).descricao;
        return this.descricao;
    } 
    
    public void setDescricao(String descricao) 
    {
        this.descricao = descricao;
        produtoDAO.updateString(this, Coluna.DESCRICAO.getNomeColuna(), this.descricao);
    }

    public int getDisponibilidade() 
    {
        this.disponibilidade = produtoDAO.selectById(this.id).disponibilidade;
        return this.disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) 
    {
        this.disponibilidade = disponibilidade;
        produtoDAO.updateInt(this, Coluna.DISPONIBILIDADE.getNomeColuna(), this.disponibilidade);
    }

    public double getPreco() 
    {
        this.preco = produtoDAO.selectById(this.id).preco;
        return this.preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
        produtoDAO.updateDouble(this, Coluna.PRECO.getNomeColuna(), this.preco);
    }

    public String getNome() 
    {
        this.nome = produtoDAO.selectById(this.id).nome;
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
        produtoDAO.updateString(this, Coluna.NOME.getNomeColuna(), this.nome);
    }

    public String getLink() 
    {
        this.link = produtoDAO.selectById(this.id).link;
        return this.link;
    }

    public void setLink(String link) 
    {
        this.link = link;
        produtoDAO.updateString(this, Coluna.LINK.getNomeColuna(), this.link);
    }

    public String getUrl_foto() 
    {
        this.url_foto = produtoDAO.selectById(this.id).url_foto;
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
        produtoDAO.updateString(this, Coluna.URL_FOTO.getNomeColuna(), this.url_foto);
    }

    public String getMarca() 
    {
        this.marca = produtoDAO.selectById(this.id).marca;
        return this.marca;
    }

    public void setMarca(String marca) 
    {
        this.marca = marca;
        produtoDAO.updateString(this, Coluna.MARCA.getNomeColuna(), this.marca);
    }

    public String getData_de_adicao() 
    {
        this.data_de_adicao = produtoDAO.selectById(this.id).data_de_adicao;
        return this.data_de_adicao;
    }

    public void setData_de_adicao(String data_de_adicao) 
    {
        this.data_de_adicao = data_de_adicao;
        produtoDAO.updateString(this, Coluna.DATA_DE_ADICAO.getNomeColuna(), this.data_de_adicao);
    }

    public int getPrioridade() 
    {
        this.prioridade = produtoDAO.selectById(this.id).prioridade;
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) 
    {
        this.prioridade = prioridade;
        produtoDAO.updateInt(this, Coluna.PRIORIDADE.getNomeColuna(), this.prioridade);
    }

    public double getValorFrete() 
    {
        this.valorFrete = produtoDAO.selectById(this.id).valorFrete;
        return this.valorFrete;
    }

    public void setValorFrete(double valorFrete) 
    {
        this.valorFrete = valorFrete;
        produtoDAO.updateDouble(this, Coluna.VALOR_FRETE.getNomeColuna(), this.valorFrete);
    }

    public int getIdLoja() 
    {
        this.idLoja = produtoDAO.selectById(this.id).idLoja;
        return this.idLoja;
    }


    public void setIdLoja(int idLoja) 
    {
        this.idLoja = idLoja;
        produtoDAO.updateInt(this, Coluna.IDLOJA.getNomeColuna(), this.idLoja);
    }


    public String getCategoria() 
    {
        this.categoria = produtoDAO.selectById(this.id).categoria;
        return this.categoria;
    }

    public void setCategoria(String categoria) 
    {
        this.categoria = categoria;
        produtoDAO.updateString(this, Coluna.CATEGORIA.getNomeColuna(), this.categoria);
    }

    public ArrayList<Especificacao> getEspecificacoes() 
    {
        this.especificacoes = especificacao_has_Produto_DAO.selectTodasEspecificacoesDoProduto(this.getId());
        return this.especificacoes;
    }

    public ArrayList<Tag> getTags() 
    {
        this.tags = tag_has_Produto_DAO.selectTodasTagsDoProduto(this.getId());
        return this.tags;
    }

    public int getId() 
    {
        this.id = produtoDAO.selectById(this.id).id;
        return this.id;
    }
    
    public double getValorArrecadado() 
    {
        this.valorArrecadado = produtoDAO.selectById(this.id).valorArrecadado;
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

    /* Funções */

    public static ArrayList<Produto> allProdutos()
    {
        return produtoDAO.selectAll();
    }
    
    public static ArrayList<Produto> produtosRecentes(int qntDeProdutos)
    {
        return produtoDAO.selectProdutosCadastradosRecentemente(qntDeProdutos);
    }

    public void delete()
    {
        // Deletando da tabela de especificacao_has_produto
        ArrayList<Especificacao> especificacoes = this.getEspecificacoes();
        for (Especificacao especificacao : especificacoes) 
        {
            especificacao_has_Produto_DAO.delete(especificacao, this);
        }

        // Deletando da tabela de tag_has_produto
        ArrayList<Tag> tags = this.getTags();
        for (Tag tag : tags) 
        {
            tag_has_Produto_DAO.delete(tag, this);
        }

        // Deletando da tabela de produto
        produtoDAO.delete(this);
    }

    public void insert()
    {
        // Adicionando da tabela de especificacao_has_produto
        for (Especificacao especificacao : this.especificacoes) 
        {
            especificacao_has_Produto_DAO.insert(especificacao, this);
        }

        // Adicionando da tabela de tag_has_produto
        for (Tag tag : this.tags) 
        {
            tag_has_Produto_DAO.insert(tag, this);
        }

        // Adicionando da tabela de produto
        produtoDAO.insert(this);
    }

    /* Atributos */

    private int id;
    private int disponibilidade;
    private int prioridade;
    private int idUsuario;
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
    private int idLoja;
    private ArrayList<Especificacao> especificacoes;
    private ArrayList<Tag> tags;
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Especificacao_has_Produto especificacao_has_Produto_DAO = new Especificacao_has_Produto();
    private static Tag_has_Produto tag_has_Produto_DAO = new Tag_has_Produto();
    private static final String nomeTabela = "Produto";

}
