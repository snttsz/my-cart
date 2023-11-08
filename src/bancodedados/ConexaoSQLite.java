package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite 
{
    
    public ConexaoSQLite(){};

    public static boolean conectar()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:bancodedados/aplicacao.db";   
            
            Connection conexao = DriverManager.getConnection(url);

            return true;
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println(e.getMessage());

            return false;
        }

    } 
    
    
    public static boolean desconectar(Connection conexao)
    {
        try
        {
            if(!conexao.isClosed() == false)
            {
                conexao.close();
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }
}
