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

        ProdutoEletronico produto = new ProdutoEletronico(0, "PC", 5000.0, "2023-11-11", 3000.0, 200.0);

        sqLiteConnectionManager.enviarQuery(MontadorInstrucoes.montarProduto(produto));

    }
}
