package DAO;

import java.util.ArrayList;

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
         * TODO puxar o produto do BD, se existir prossegue, se não eu crio ele antes de inserir na tabela User_has_Produto
         * 
         * O usuário não precisa pasasr por isso, pois ele sempre terá sido criado anteriormente
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
        
        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }

        
    @Override
    public void delete(Usuario usuario, Produto produto) 
    {
        String condicao1 = StringManager.inserirIgualdade(Usuario_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Usuario_has_Produto.Coluna.IDUsuario.getNomeColuna(), Integer.toString(usuario.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        System.out.println(instrucao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
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