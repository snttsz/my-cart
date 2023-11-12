package sistema;

public class Especificacao 
{
    public Especificacao(String nome, String valor) 
    {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getValor() 
    {
        return this.valor;
    }

    public int getId() 
    {
        return this.id;
    }

    public String getNomeTabela()
    {
        return Especificacao.nomeTabela;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public void setValor(String valor) 
    {
        this.valor = valor;
    }

        /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idProduto"),
        NOME("nome"),
        VALOR("valor");

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
    
    private int id;
    private String nome;
    private String valor;
    private static final String nomeTabela = "Especificacao";

}
