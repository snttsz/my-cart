package sistema;

public class Especificacao 
{

    /* Construtores */

    public Especificacao(){};

    /* 
     * Construtor feito para montagem do objeto que está vindo do banco de dados (Possui ID)
     */
    public Especificacao(int id, String nome, String valor) 
    {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    /* 
     * Construtor feito para montagem do objeto que será enviado para o banco de dados ( Não possui ID, pois ele é gerado automaticamente no BD)
     */
    public Especificacao(String nome, String valor) 
    {
        this.nome = nome;
        this.valor = valor;
    }

    /* Funções gerais */
    public static void printarEspecificacao(Especificacao especificacao)
    {
        System.out.println("ID: " + especificacao.getId());
        System.out.println("Nome: " + especificacao.getNome());
        System.out.println("Valor: " + especificacao.getValor());
        System.out.println("\n");
    }

    /* Getters e Setters */

    public void setValores(String nome, String valor)
    {
        this.setNome(nome);
        this.setValor(valor);
    }

    public static String getNomeTabela()
    {
        return Especificacao.nomeTabela;
    }

    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
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

    public void setValor(String valor) 
    {
        this.valor = valor;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public enum Coluna
    {
        ID("idEspecificacao"),
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

    /* Atributos */
    
    private int id;
    private String nome;
    private String valor;
    private static final String nomeTabela = "Especificacao";

}
