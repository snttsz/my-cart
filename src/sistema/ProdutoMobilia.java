package sistema;

import java.util.ArrayList;

import DAO.ProdutoDAO;

public class ProdutoMobilia extends Produto
{

    /* Construtores */

    public ProdutoMobilia(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoMobilia(int id, int disponibilidade, String descricao, String nome, double preco, String link,
        String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags,
        String material, String cor, double altura, double largura, double comprimento)
    {
        super(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade,
        valorArrecadado, valorFrete, categoria, especificacoes, tags);
            
        this.material = material;
        this.cor = cor;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoMobilia(int disponibilidade, String descricao, String nome, double preco, String link,
        String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado,
        double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags,
        String material, String cor, double altura, double largura, double comprimento) 
    {

        super(disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade,
        valorArrecadado, valorFrete, categoria, especificacoes, tags);

        this.material = material;
        this.cor = cor;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;

        super.insert(this);
    }
    
    /* Getters e Setters */


    public String getMaterial() 
    {
        return material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
        produtoDAO.updateString(this, Coluna.MATERIAL.getNomeColuna(), this.material);
    }

    public String getCor() 
    {
        return cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
        produtoDAO.updateString(this, Coluna.COR.getNomeColuna(), this.cor);
    }

    public double getAltura() 
    {
        return altura;
    }

    public void setAltura(double altura) 
    {
        this.altura = altura;
        produtoDAO.updateDouble(this, Coluna.ALTURA.getNomeColuna(), this.altura);
    }

    public double getLargura() 
    {
        return largura;
    }

    public void setLargura(double largura) 
    {
        this.largura = largura;
        produtoDAO.updateDouble(this, Coluna.LARGURA.getNomeColuna(), this.largura);
    }

    public double getComprimento() 
    {
        return comprimento;
    }

    public void setComprimento(double comprimento) 
    {
        this.comprimento = comprimento;
        produtoDAO.updateDouble(this, Coluna.COMPRIMENTO.getNomeColuna(), this.comprimento);
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        MATERIAL("material"),
        COR("cor"),
        LARGURA("largura"),
        ALTURA("altura"),
        COMPRIMENTO("comprimento");

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
    
    private String material; 
    private String cor;
    private double altura; // Em metros
    private double largura; // Em metros
    private double comprimento; // Em metros
    private ProdutoDAO produtoDAO = new ProdutoDAO();

}
