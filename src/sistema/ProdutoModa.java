package sistema;
import java.util.ArrayList;

public class ProdutoModa extends Produto
{
    
    /* Construtores */

    public ProdutoModa(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoModa(int id, String descricao, String nome, double preco, String link, String url_foto,  double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, String tamanho, String cor, String material, int idUsuario, int idLoja) 
    {
        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
        
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoModa( String descricao, String nome, double preco, String link, String url_foto,  double valorArrecadado, double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, String tamanho, String cor, String material, int idUsuario, int idLoja) 
    {
        super(descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);

        this.tamanho = tamanho;
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
        this.produtoDao.updateString(this, ColunaProdutoModa.COR.getNomeColuna(), this.cor);
        this.produtoDao.updateString(this, ColunaProdutoModa.MATERIAL.getNomeColuna(), this.material);
        this.produtoDao.updateString(this, ColunaProdutoModa.TAMANHO.getNomeColuna(), this.tamanho);
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
        this.produtoDao.updateString(this, ColunaProdutoModa.COR.getNomeColuna(), this.cor);
        this.produtoDao.updateString(this, ColunaProdutoModa.MATERIAL.getNomeColuna(), this.material);
        this.produtoDao.updateString(this, ColunaProdutoModa.TAMANHO.getNomeColuna(), this.tamanho);
    }

    /* Getters e Setters */

    public String getTamanho() 
    {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) 
    {
        this.tamanho = tamanho;
    }

    public String getCor() 
    {
        return this.cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public String getMaterial() 
    {
        return this.material;
    }

    public void setMaterial(String material) 
    {
        this.material = material;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum ColunaProdutoModa
    {
        MATERIAL("material"),
        COR("cor"),
        TAMANHO("tamanho");

        private final String nomeColuna;

        ColunaProdutoModa(String nomeColuna)
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
}
