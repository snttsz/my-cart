package sistema;

import java.util.ArrayList;

public class ProdutoEletronico extends Produto
{   
    public ProdutoEletronico(double preco, String nome, int codigo, Categoria categoria, String marca) 
    {
        super(preco, nome, codigo, categoria);
        this.marca = marca;
        this.especificacoes = new ArrayList<Especificacao>();
    }

    public ProdutoEletronico(double preco, String nome, int codigo, Categoria categoria, int valorArrecadado, String marca) 
    {
        super(preco, nome, codigo, categoria, valorArrecadado);
        this.marca = marca;
        this.especificacoes = new ArrayList<Especificacao>();
    }

    public String getMarca() 
    {
        return this.marca;
    }

    public void setMarca(String marca) 
    {
        this.marca = marca;
    }

    // Isso pode ser usado na interface --> Se um usuário quiser adicionar uma especificação pro produto
    // ex: Memória Ram
    // Seria criado um objeto especificação (com nome da especificação e o valor)
    // ex: nome: capacidade / valor: 8GB
    // ex: nome: tipo / valor: DDR4
    // No banco de dados teria uma tabela com 0 ou muitas especificações onde a gente guardaria essas
    // informações, na hora de mostrar o produto seria só puxar elas pra esse vetor
    // É uma ideia inicial, pode ser desenvolvida ou descartada. A gente pode criar especificações 
    // padrões também, mas seria mais difícil pela variedade de produtos que podem existir.
    public ArrayList<Especificacao> getEspecificacoes() 
    {
        return especificacoes;
    }

    public void setEspecificacoes(ArrayList<Especificacao> especificacoes) 
    {
        this.especificacoes = especificacoes;
    }

    private String marca;
    private ArrayList<Especificacao> especificacoes;
}
