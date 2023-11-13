package testes.testesSQL;


import java.util.ArrayList;

import DAO.ProdutoDAO;
import DAO.UsuariosDAO;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.Usuario;


public class TesteSQLite 
{
       public static void main(String args[])
    {
        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();

        Produto produto = new ProdutoEletronico(1, "é um bom produto", "teclado", 1500.0, "www.vsf.com", "coloca no google caralho",
        "cachorro ", "2023-11-12", 2, 1000.0, 20, Produto.Categorias.ELETRONICO.getCategoria(), null, null);
        Usuario usuario = new Usuario("Kalvin", "teste", "123", "kalvin123@.com");

        ProdutoDAO produtoDAO = new ProdutoDAO();

        ArrayList<Produto> teste = new ArrayList<>();

        teste =  produtoDAO.selectAll();

/* 
        UsuariosDAO usuariosDAO = new UsuariosDAO();

        ArrayList<Usuario> teste = new ArrayList<>();
 */
/*         teste =  usuariosDAO.selectAll();

        for(Usuario p : teste)
        {
            System.out.println(p.getId());
        } */


 
    }
}
