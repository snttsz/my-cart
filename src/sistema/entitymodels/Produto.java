package sistema.entitymodels;

import java.util.ArrayList;

public abstract class Produto 
{

    /* Construtores */

    public Produto(){};

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


    /* Atributos */

    private int codigo;
    private String nome;
    private double preco;
    private String link;
    private String url_foto;
    private String marca;
    private String data_de_adicao;
    private int prioridade;
    private double valorArrecadado;
    private double valorFrete; 
    private Categoria categoria;
    private ArrayList<Especificacao> especificacoes;
    private ArrayList<String> tags; 
}
