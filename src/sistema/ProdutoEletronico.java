package sistema;

import java.util.ArrayList;

public class ProdutoEletronico extends Produto
{

    /* Construtores */

    public ProdutoEletronico(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public ProdutoEletronico(int id, String descricao, String nome, double preco, String link,
    String url_foto, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja) 
    {
        super(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);
 
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public ProdutoEletronico( String descricao, String nome, double preco, String link,
    String url_foto, double valorArrecadado,
    double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags, int idUsuario, int idLoja) 
    {
        super(descricao, nome, preco, link, url_foto, 
        valorArrecadado, valorFrete, categoria, especificacoes, tags, idUsuario, idLoja);

        super.insert();
    }

}
