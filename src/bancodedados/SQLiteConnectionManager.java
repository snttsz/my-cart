package bancodedados;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnectionManager 
{
    
    public SQLiteConnectionManager(){};

    public boolean conectar()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/bancodedados/banco_sqlite.db";   
            
            this.conexao = DriverManager.getConnection(url);

            System.out.println("Conectado com sucesso!");

            return true;
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Falha na conexão! " + e.getMessage());

            return false;
        }

    } 
    
    
    public  boolean desconectar()
    {
        try
        {
            if(this.conexao.isClosed() == false)
            {
                this.conexao.close();
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
     * Statement é uma instrução que você envia para o banco de dados para ser executada
     */
    public Statement criarStatement()
    {
        try
        {
            return this.conexao.createStatement();

        }
        catch(SQLException e)
        {
            return null;
        }
    }

    /* GETTERS E SETTERS */
    public Connection getConexao() 
    {
        return this.conexao;
    }

    /* ATRIBUTOS */
    private Connection conexao;



}



