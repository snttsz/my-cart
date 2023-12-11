package bancodedados;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 
 * Classe resposnsável por toda a parte de conexão com o banco de dados SQLite
 */
public class SQLiteConnectionManager 
{
    
    /* 
     * Construtores
     */
    public SQLiteConnectionManager(){};

    /* 
     * Métodos Gerais
                        */


    /* 
     * Método responsável por criar uma conexão com o banco de dados
     */
    public static void conectar()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:database/banco_sqlite.db";   

            SQLiteConnectionManager.conexao = DriverManager.getConnection(url);

            //System.out.println("Conectado com sucesso!");

        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Falha na conexão! " + e.getMessage());

        }

    } 
    
    /* 
     * Método responsável por desconectar a aplicação do banco de dados
     */
    public static void desconectar()
    {
        try
        {
            if(SQLiteConnectionManager.conexao != null)
            {
                if(!SQLiteConnectionManager.conexao.isClosed())
                {
                    SQLiteConnectionManager.conexao.close();
                }
            }

            if(SQLiteConnectionManager.statement != null)
            {
                if(!SQLiteConnectionManager.statement.isClosed())
                {
                    SQLiteConnectionManager.statement.close();
                }
            }

            if(SQLiteConnectionManager.resultSet != null)
            {
                if(!SQLiteConnectionManager.resultSet.isClosed())
                {
                    SQLiteConnectionManager.resultSet.close();
                }
            }
    
            //System.out.println("Desconectado com sucesso!");
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());

        }
    }

    /* 
     * Método responsável por criar e retornar um statement
     * Statement é uma instrução que você envia para o banco de dados para ser executada
     */
    private static Statement criarStatement()
    {
        try
        {
            return SQLiteConnectionManager.conexao.createStatement();

        }
        catch(SQLException e)
        {
            return null;
        }
    }

    /* 
     * Método responsável por enviar uma query para o banco de dados
     * 
     * IMPORTANTE lembrar que, por algum motivo o banco de dados só recebe um comando por vez.
     * Portanto, se for criar duas tabelas, primeiro mande um create table, depois mande o outro, jamais os dois de vez.
     */
    public static void enviarQuery(String instrucao)
    {
        try
        {
            SQLiteConnectionManager.conectar();

            Statement statement = SQLiteConnectionManager.criarStatement();

            statement.execute(instrucao);

            //System.out.println("O Query foi realizado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Erro no envio do Query: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }

    public static ResultSet receberQuery(String instrucao)
    {
        ResultSet resultSet = null;

        Statement statement = null;

        try
        {
            SQLiteConnectionManager.conectar();

            statement = criarStatement();

            resultSet = statement.executeQuery(instrucao);
            
            //System.out.println("O Query foi realizado com sucesso!");


        }
        catch(SQLException e)
        {
            System.out.println("Erro no recebimento da mensagem vinda do banco: " + e.getMessage());
        }

        return resultSet;
    }

    /* ATRIBUTOS */
    private static Connection conexao;
    private static Statement statement;
    private static ResultSet resultSet;



}



