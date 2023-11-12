package DAO;

import java.util.ArrayList;

import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import sistema.Produto;
import utils.StringManager;

public class Especificacao_has_Produto  extends DAOMTM<Especificacao, Produto>
{

    @Override
    public void delete(Especificacao especificacao, Produto produto) 
    {

        String condicao1 = StringManager.inserirIgualdade(Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void insert(Especificacao especificacao, Produto produto) 
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
        arrayColunas.add(Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna());
        arrayColunas.add(Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna());

        /* 
        * Montando valores
        */
        valoresInteger.add(especificacao.getId());
        valoresInteger.add(produto.getId());

        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);


        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresIntegerNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Especificacao_has_Produto.nomeTabela, colunas, valores);
        
        DAO.SQLiteConnectionManager.enviarQuery(instrucao);        
    }

    /* 
     * Enum com o nome das tabelas
     */
    public enum Coluna
    {
        IDESPECIFICACAO("Especificacao_idEspecificacao"),
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
    private static final String nomeTabela = "Especificacao_has_Produto";
    
}

