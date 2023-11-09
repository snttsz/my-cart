package testes.testesSQL;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;

public class TesteSQLite 
{
       public static void main(String args[])
    {
        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();

        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager(sqLiteConnectionManager);

        sqLiteTableManager.criarTabela();

    }
}
