package testes.testesSQL;

import bancodedados.ConexaoSQLite;

public class TesteSQLite 
{
       public static void main(String args[])
    {
        ConexaoSQLite.conectar();
        System.out.println("teste");
    }
}
