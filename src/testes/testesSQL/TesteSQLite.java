package testes.testesSQL;


import DAO.ProdutoDAO;
import DAO.Usuario_has_Produto;
import DAO.UsuariosDAO;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.Usuario;


public class TesteSQLite 
{
       public static void main(String args[])
    {
        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();

        Produto produto = new ProdutoEletronico(0, "é um bom produto", "PC", 1500.0, "www.vsf.com", "coloca no google caralho",
        "cachorro ", "2023-11-12", 2, 1000.0, 20, Produto.Categorias.ELETRONICO.getCategoria(), null, null);
        Usuario usuario = new Usuario("Kalvin", "teste", "123", "kalvin123@.com");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuariosDAO usuarioDAO = new UsuariosDAO();
        Usuario_has_Produto Usuario_has_Produto = new Usuario_has_Produto();

        Usuario_has_Produto.delete(usuario, produto);

        produtoDAO.insert(produto);
        usuarioDAO.insert(usuario);
 
    }
}
