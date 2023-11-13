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
    public boolean conectar()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:database/banco_sqlite.db";   

            conexao = DriverManager.getConnection(url);

            System.out.println("Conectado com sucesso!");

            return true;
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Falha na conexão! " + e.getMessage());

            return false;
        }

    } 
    
    /* 
     * Método responsável por desconectar a aplicação do banco de dados
     */
    public boolean desconectar()
    {
        try
        {
            if(conexao.isClosed() == false)
            {
                conexao.close();
            }
    
            System.out.println("Desconectado com sucesso!");
            
            return true;
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());

            return false;
        }
    }

    /* 
     * Método responsável por criar e retornar um statement
     * Statement é uma instrução que você envia para o banco de dados para ser executada
     */
    public Statement criarStatement()
    {
        try
        {
            return conexao.createStatement();

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
    public void enviarQuery(String instrucao)
    {

        boolean conectou = false;
        try
        {
            conectou = conectar();

            Statement statement = criarStatement();

            statement.execute(instrucao);

            System.out.println("O Query foi realizado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Erro no envio do Query: " + e.getMessage());
        }
        finally
        {
            if(conectou)
            {
                desconectar();
            }
        }
    }

    public ResultSet receberQuery(String instrucao)
    {
        ResultSet resultSet = null;

        Statement statement = null;

        boolean conectou = false;
        try
        {
            conectou = conectar();

            statement = criarStatement();

            resultSet = statement.executeQuery(instrucao);
            
            System.out.println("O Query foi realizado com sucesso!");


        }
        catch(SQLException e)
        {
            System.out.println("Erro no recebimento da mensagem vinda do banco: " + e.getMessage());
        }
        finally
        {
            //fecharStatement(statement);
        }

        return resultSet;
    }

    public void fecharResultSet(ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao fechar as conexões: " + e.getMessage());
        }
    }

      public void fecharStatement(Statement statement)
    {
        try
        {
            statement.close();
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao fechar as conexões: " + e.getMessage());
        }
    }

    /* GETTERS E SETTERS */
    public Connection getConexao() 
    {
        return conexao;
    }

    /* ATRIBUTOS */
    private static Connection conexao;



}



