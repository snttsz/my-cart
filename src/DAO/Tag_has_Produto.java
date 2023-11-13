package DAO;

import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Tag;
import utils.StringManager;


public class Tag_has_Produto extends DAOMTM<sistema.Tag, Produto>
{

    @Override
    public void delete(Tag tag, Produto produto) 
    {

        String condicao1 = StringManager.inserirIgualdade(Tag_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Tag_has_Produto.Coluna.IDTAG.getNomeColuna(), Integer.toString(tag.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void insert(Tag tag, Produto produto) 
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
        arrayColunas.add(Tag_has_Produto.Coluna.IDTAG.getNomeColuna());
        arrayColunas.add(Tag_has_Produto.Coluna.IDProduto.getNomeColuna());

        /* 
        * Montando valores
        */
        valoresInteger.add(tag.getId());
        valoresInteger.add(produto.getId());

        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);


        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresIntegerNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Tag_has_Produto.nomeTabela, colunas, valores);
        
        SQLiteConnectionManager.enviarQuery(instrucao);        
    }

    /* 
     * Enum com o nome das tabelas
     */
    public enum Coluna
    {
        IDTAG("Tag_idTag"),
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
    private static final String nomeTabela = "Tag_has_Produto";
    
}
