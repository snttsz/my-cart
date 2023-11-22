package sistema;

import java.util.ArrayList;

import DAO.ProdutoDAO;

public class ProdutoRoupa extends Produto
{
    
    /* Construtores */

    public ProdutoRoupa(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoRoupa(int id, String descricao, String nome, double preco, String link,
        String url_foto,  double valorArrecadado, double valorFrete, 
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags,
        String tamanho, String cor, String material, int idUsuario, int idLoja) 
    {

        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
        
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoRoupa( String descricao, String nome, double preco, String link,
        String url_foto,  double valorArrecadado, double valorFrete, 
        String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags,
        String tamanho, String cor, String material, int idUsuario, int idLoja) 
    {

        super( descricao, nome, preco, link, url_foto, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
        
        super.insert();
    }

    /* Getters e Setters */

    public String getTamanho() 
    {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) 
    {
        this.tamanho = tamanho;
        produtoDAO.updateString(this, Coluna.TAMANHO.getNomeColuna(), this.tamanho);
    }

    public String getCor() 
    {
        return this.cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
        produtoDAO.updateString(this, Coluna.COR.getNomeColuna(), this.cor);
    }

    public String getMaterial() 
    {
        return this.material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
        produtoDAO.updateString(this, Coluna.MATERIAL.getNomeColuna(), this.material);
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        MATERIAL("material"),
        COR("cor"),
        TAMANHO("tamanho");

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

    private String tamanho; 
    private String cor;
    private String material;
    private ProdutoDAO produtoDAO = new ProdutoDAO();

}
