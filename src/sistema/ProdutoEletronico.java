package sistema;

import java.util.ArrayList;

public class ProdutoEletronico extends Produto
{

    /* Construtores */

    public ProdutoEletronico(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoEletronico(int id, String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja, String cor, String material) 
    {
        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        this.cor = cor;
        this.material = material;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoEletronico(String descricao, String nome, double preco, String link, String url_foto, double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja, String cor, String material) 
    {
        super(descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        this.cor = cor;
        this.material = material;
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
        this.produtoDao.updateString(this, ColunaProdutoEletronico.COR.getNomeColuna(), this.cor);
        this.produtoDao.updateString(this, ColunaProdutoEletronico.MATERIAL.getNomeColuna(), this.material);
    }

    public String getCor() 
    {
        return cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public String getMaterial() 
    {
        return material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
    }

    private String cor;
    private String material;

    private enum ColunaProdutoEletronico
    {
        COR("cor"),
        MATERIAL("material");

        private final String nomeColuna;

        ColunaProdutoEletronico(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }

}
