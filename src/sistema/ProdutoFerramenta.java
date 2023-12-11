package sistema;

import java.util.ArrayList;

public class ProdutoFerramenta extends Produto
{
    
    /* Construtores */

    public ProdutoFerramenta(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoFerramenta(int id, String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja, String material, String cor, double altura, double largura, double comprimento) 
    {
        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.material = material;
        this.cor = cor;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoFerramenta(String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja, String material, String cor, double altura, double largura, double comprimento)
    {
        super(descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
    
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.material = material;
        this.cor = cor;
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
        this.produtoDao.updateString(this, ColunaProdutoFerramenta.COR.getNomeColuna(), this.cor);
        this.produtoDao.updateString(this, ColunaProdutoFerramenta.MATERIAL.getNomeColuna(), this.material);
        this.produtoDao.updateString(this, ColunaProdutoFerramenta.ALTURA.getNomeColuna(), String.valueOf(this.altura));
        this.produtoDao.updateString(this, ColunaProdutoFerramenta.COMPRIMENTO.getNomeColuna(), String.valueOf(this.largura));
        this.produtoDao.updateString(this, ColunaProdutoFerramenta.LARGURA.getNomeColuna(), String.valueOf(this.comprimento));
    }

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

    private String material; 
    private String cor;
    private double altura; 
    private double largura; 
    private double comprimento; 

    public enum ColunaProdutoFerramenta
    {
        COR("cor"),
        MATERIAL("material"),
        ALTURA("altura"),
        LARGURA("largura"),
        COMPRIMENTO("comprimento");

        private final String nomeColuna;

        ColunaProdutoFerramenta(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }
}
