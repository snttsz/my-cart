package testes.testesSQL;


import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import entradaesaida.MontadorInstrucoes;
import sistema.ProdutoEletronico;


public class TesteSQLite 
{
       public static void main(String args[])
    {
        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();

        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager(sqLiteConnectionManager);

        ProdutoEletronico produto = new ProdutoEletronico(0, "Lixa de unha", 7.0, "2023-11-11", 3.0, 10.0);

        sqLiteConnectionManager.enviarQuery(MontadorInstrucoes.InserirProduto(produto));

        MontadorInstrucoes.setPrecoProduto(produto, 10.0);

        
    }
}
