package sistema;

import java.util.ArrayList;

public class ProdutoEletronico extends Produto
{

    /* Construtores */

    public ProdutoEletronico(){};

    public ProdutoEletronico(int id, int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado, double valorFrete, 
    String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        super(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
        valorFrete, categoria, especificacoes, tags);
 
    }

    public ProdutoEletronico(int disponibilidade, String descricao, String nome, double preco, String link,
    String url_foto, String marca, String data_de_adicao, int prioridade, double valorArrecadado,
    double valorFrete, String categoria, ArrayList<Especificacao> especificacoes, ArrayList<String> tags) 
    {
        super(disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade,
        valorArrecadado, valorFrete, categoria, especificacoes, tags);
    }

}
