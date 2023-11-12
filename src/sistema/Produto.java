package sistema;

import java.util.ArrayList;

public abstract class Produto 
{

    /* Construtores */

    Produto(){};

    public Produto(int codigo, String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete)  
    {
        this.setValores(codigo, nome, preco, data_de_adicao, valorArrecadado, valorFrete);
        this.especificacoes = new ArrayList<Especificacao>();
        this.tags = new ArrayList<String>();
    }

    /* Getters e setters */

    protected void setValores(int codigo, String nome, double preco, String data_de_adicao, double valorArrecadado, double valorFrete)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.data_de_adicao = data_de_adicao;
        this.valorArrecadado = valorArrecadado;
        this.valorFrete = valorFrete;
    }
    
    protected void setValores(int codigo, String nome, double preco, String link, String url_foto, String marca, String data_de_adicao, int prioridade, 
    double valorArrecadado, double valorFrete, Categoria categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        this.codigo = codigo;
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

    public double getPreco() 
    {
        return this.preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public String getLink() 
    {
        return this.link;
    }

    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getUrl_foto() 
    {
        return this.url_foto;
    }

    public void setUrl_foto(String url_foto) 
    {
        this.url_foto = url_foto;
    }

    public String getMarca() 
    {
        return this.marca;
    }

    public void setMarca(String marca) 
    {
        this.marca = marca;
    }

    public String getData_de_adicao() 
    {
        return this.data_de_adicao;
    }

    public void setData_de_adicao(String data_de_adicao) 
    {
        this.data_de_adicao = data_de_adicao;
    }

    public int getPrioridade() 
    {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) 
    {
        this.prioridade = prioridade;
    }

    public double getValorFrete() 
    {
        return this.valorFrete;
    }

    public void setValorFrete(double valorFrete) 
    {
        this.valorFrete = valorFrete;
    }

    public Categoria getCategoria() 
    {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) 
    {
        this.categoria = categoria;
    }

    public ArrayList<Especificacao> getEspecificacoes() 
    {
        return this.especificacoes;
    }

    public void setEspecificacoes(ArrayList<Especificacao> especificacoes) 
    {
        this.especificacoes = especificacoes;
    }

    public ArrayList<String> getTags() 
    {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags) 
    {
        this.tags = tags;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public int getCodigo() 
    {
        return this.codigo;
    }

    public void setCodigo(int codigo) 
    {
        this.codigo = codigo;
    }
    
    public double getValorArrecadado() 
    {
        return this.valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) 
    {
        this.valorArrecadado = valorArrecadado;
    }

    public String getDescricao() 
    {
        return this.descricao;
    }

    public void setDescricao(String descricao) 
    {
        this.descricao = descricao;
    }
    
    public int getDisponibilidade() 
    {
        return this.disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) 
    {
        this.disponibilidade = disponibilidade;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum ColunaProduto
    {
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
        VALOR_FRETE("valor_frete");
        
        private final String nomeColuna;
        
        ColunaProduto(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }
        
        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }

    /* Atributos */
    
    private int codigo;
    private String nome;
    private String descricao;
    private double preco;
    private String link;
    private String url_foto;
    private String marca;
    private String data_de_adicao;
    private int prioridade;
    private int disponibilidade;
    private double valorArrecadado;
    private double valorFrete; 
    private Categoria categoria;
    private ArrayList<Especificacao> especificacoes;
    private ArrayList<String> tags;
}
