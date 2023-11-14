package bancodedados;

import java.util.ArrayList;

import utils.ParserArquivo;

/* 
* Classe responsável por montar todos os comandos referentes ao banco de dados SQLite
*/
public class SQLiteTableManager
{
    
    /* 
    * Construtores
    */
    public SQLiteTableManager()
    {
        inicializarBanco();
    }
    
    /* 
    * FUNÇÕES DO SQLITE
    */
    
    /* 
    * Método responsável por inicializar o banco de dados.
    * Ele lê o script de criação de tabelas que está em um txt e enviar comandos de Create Table um a um para criar as tabelas,
    * caso elas não estejam criadas.
    *
    * Embora esse método não gera um comando, acho que essa classe é o melhor local para colocá-la. 
    */
    public void inicializarBanco()
    {

        this.scriptPath = "database/script_banco_de_dados.txt";

        ArrayList<String> instrucoes = new ArrayList<String>();
        instrucoes = ParserArquivo.lerScriptSQL(scriptPath);
        
        for(String instrucao : instrucoes)
        {   
            SQLiteConnectionManager.enviarQuery(instrucao);
        }


    }

     /* 
      * Esse método retorna um comando para fazer insert com a tabela, colunas e valores passados por parâmetro
    */
    public static String insertTo(String tabela, String colunas, String valores)
    {
        String instrucao = new String();
        instrucao =
        "INSERT INTO "+ tabela +"(" + colunas +") " +
        "VALUES "+ "(" + valores + ")" + ";";

        return instrucao;
    }

    /* 
     * Esse método irá retornar um comando para dar um select * na tabela passada por parâmetro
     */
    public static String selectAll(String tabela)
    {
        String instrucao =
        "SELECT * FROM " + tabela + ";";
        
        return instrucao;
    }

    /* 
     * Método responsável por retornar um comando para dar um UPDATE em uma tabela , em campos especificados por uma condicao, passados por parâmetro
     */
    public static String update(String tabela, String colunas_novosValores, String condicao)
    {
        String instrucao = 
        "UPDATE " + tabela + " SET " + colunas_novosValores + " WHERE " + condicao + ";";

        return instrucao;
    }

    /* 
     * Método responsável por retornar um comando para dar DELETE em uma linha de uma tabela passados por parâmetro
     */
    public static String delete(String tabela, String condicao)
    {
        String instrucao=
        "DELETE FROM " + tabela + " WHERE " + condicao + ";";

        return instrucao;
    }

    /*
     * Esse método seleciona atributos específicos de uma tabela
     */
    public static String select(String tabela, String atributos)
    {
        String instrucao = new String();
        instrucao = 
        "SELECT " + atributos + " FROM " + tabela + ";";

        return instrucao;
    }

    /* 
     * Esse método utiliza o where para selecionar atributos com uma condição
     */
    public static String select(String tabela, String atributos, String condicao)
    {
        String instrucao = new String();
        instrucao = 
        "SELECT " + atributos + " FROM " + tabela + " WHERE " + condicao + ";";

        return instrucao;
    }

    /* 
     * Método que utiliza o Join para mesclar tabelas e seleciona todos os dados a partir dessa tabela com uma condição
     */
    public static String selectAllJoin(String tabelaDeRetorno, String tabelaMesclada, String comparacaoDeAtributos,  String condicao)
    {
        String instrucao =
        "SELECT " + tabelaDeRetorno + ".* " + " FROM " + tabelaDeRetorno + " JOIN " + tabelaMesclada +
        " ON " + comparacaoDeAtributos + " WHERE " + condicao + ";";

        return instrucao;
    }
    
    /* ATRIBUTOS */
    private String scriptPath;
    

}
