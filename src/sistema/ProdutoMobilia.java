package sistema;

import java.util.ArrayList;

public class ProdutoMobilia extends Produto
{

    /* Construtores */

    public ProdutoMobilia(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoMobilia(int id,  String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, String material, String cor, double altura, double largura, double comprimento, int idUsuario, int idLoja)
    {
        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario,idLoja);
            
        this.material = material;
        this.cor = cor;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoMobilia(String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, String material, String cor, double altura, double largura, double comprimento, int idUsuario, int idLoja) 
    {

        super(descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario,idLoja);

        this.material = material;
        this.cor = cor;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    @Override
    public void inserirProdutoNoBD()
    {
        super.inserirProdutoNoBD();

        /* 
         * Adicionando atributos específicos da classe
         */
        this.atualizarAtributosEspecificos();
    }

    @Override
    public void updateProdutoBD()
    {
        super.updateProdutoBD();

        /* 
         * Atualizando atributos específicos da classe
         */
        this.atualizarAtributosEspecificos();
    }

    private void atualizarAtributosEspecificos()
    {
        this.produtoDao.updateString(this, ColunaProdutoMobilia.COR.getNomeColuna(), this.cor);
        this.produtoDao.updateString(this, ColunaProdutoMobilia.MATERIAL.getNomeColuna(), this.material);
        this.produtoDao.updateString(this, ColunaProdutoMobilia.ALTURA.getNomeColuna(), String.valueOf(this.altura));
        this.produtoDao.updateString(this, ColunaProdutoMobilia.LARGURA.getNomeColuna(), String.valueOf(this.largura));
        this.produtoDao.updateString(this, ColunaProdutoMobilia.COMPRIMENTO.getNomeColuna(), String.valueOf(this.comprimento));
    }
    
    /* Getters e Setters */

    public String getMaterial() 
    {
        return material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getCor() 
    {
        return cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public double getAltura() 
    {
        return altura;
    }

    public void setAltura(double altura) 
    {
        this.altura = altura;
    }

    public double getLargura() 
    {
        return largura;
    }

    public void setLargura(double largura) 
    {
        this.largura = largura;
    }

    public double getComprimento() 
    {
        return comprimento;
    }

    public void setComprimento(double comprimento) 
    {
        this.comprimento = comprimento;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum ColunaProdutoMobilia
    {
        MATERIAL("material"),
        COR("cor"),
        LARGURA("largura"),
        ALTURA("altura"),
        COMPRIMENTO("comprimento");

        private final String nomeColuna;

        ColunaProdutoMobilia(String nomeColuna)
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
    private double altura; 
    private double largura; 
    private double comprimento; 
}
