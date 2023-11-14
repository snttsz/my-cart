package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Usuario;
import utils.StringManager;

public class Usuario_has_Produto extends DAOMTM<Usuario, Produto> 
{
    /* 
     * Construtores
     */
    public Usuario_has_Produto(){};

    @Override
    public void insert(Usuario usuario, Produto produto) 
    {   
        
        /* 
         * TODO VER SE ISSO É REALMENTE NECESSARIO
         */
        /* 
         * Verificando se o produto já existe no banco de dados
         */
/*         ProdutoDAO produtoDAO = new ProdutoDAO();
        if((produtoDAO.selectById(produto.getId()) != null))
        {
            System.out.println("Erro na tentativa de inserir o produto: O produto já existe");
            return;
        }
 */
        ArrayList<Integer> valoresInteger = new ArrayList<Integer>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Usuario_has_Produto.Coluna.IDUsuario.getNomeColuna());
        arrayColunas.add(Usuario_has_Produto.Coluna.IDProduto.getNomeColuna());

        /* 
         * Montando valores
         */
        valoresInteger.add(usuario.getId());
        valoresInteger.add(produto.getId());

        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);


        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresIntegerNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Usuario_has_Produto.nomeTabela, colunas, valores);
        
        SQLiteConnectionManager.enviarQuery(instrucao);
    }

        
    @Override
    public void delete(Usuario usuario, Produto produto) 
    {
        String condicao1 = StringManager.inserirIgualdade(Usuario_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Usuario_has_Produto.Coluna.IDUsuario.getNomeColuna(), Integer.toString(usuario.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    public ArrayList<Produto> selectTodosProdutosDoUsuario(int idUsuario)
    {
        /* 
         * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Produto.getNomeTabela() + "." + Produto.Coluna.ID.getNomeColuna();
        String atributo2 = Usuario_has_Produto.nomeTabela + "." + Usuario_has_Produto.Coluna.IDProduto.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Usuario_has_Produto.nomeTabela + "." + Usuario_has_Produto.Coluna.IDUsuario.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(idUsuario);
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Produto.getNomeTabela(), Usuario_has_Produto.nomeTabela, comparacaoAtributos, condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

         /* 
         * Montando produtos
         */

         ArrayList<Produto> produtosMontados = new ArrayList<>();
         try
         {
             while (resultSet.next()) 
             {
                 produtosMontados.add(ProdutoDAO.montarProduto(resultSet));
             }
 
             return produtosMontados;
         }
         catch(SQLException e) 
         {
             e.printStackTrace(); 
             throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
         }
         finally
         {
             SQLiteConnectionManager.desconectar();
         }
    }   


    /* 
     * Enum com o nome das tabelas
     */
    public enum Coluna
    {
        IDUsuario("Usuario_idUsuario"),
        IDProduto("Produto_idProduto");

        private final String nomeColuna;

        Coluna(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }
    private static final String nomeTabela = "Usuario_has_Produto";
    
}