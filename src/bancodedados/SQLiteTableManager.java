package bancodedados;

import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteTableManager
{
    public SQLiteTableManager(SQLiteConnectionManager conexaoSQLite)
    {
        this.conexaoSQLite = conexaoSQLite;
    }

    public void criarTabela()
    {
        String instrucao = 
        "CREATE TABLE IF NOT EXISTS Produto (" +
        "idProduto INTEGER NOT NULL UNIQUE," +
        "nome VARCHAR(45) NOT NULL," +
        "valor DOUBLE NOT NULL," +
        "url TEXT," +
        "PRIMARY KEY(idProduto AUTOINCREMENT)" +
        ");";

        boolean conectou = false;
        try
        {
            conectou = this.conexaoSQLite.conectar();

            Statement statement = this.conexaoSQLite.criarStatement();

            statement.execute(instrucao);

            System.out.println("Tabela criada com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Erro no statement");
        }
        finally
        {
            if(conectou)
            {
                this.conexaoSQLite.desconectar();
            }
        }
    }

    /* ATRIBUTOS */
    private final SQLiteConnectionManager conexaoSQLite;

}
