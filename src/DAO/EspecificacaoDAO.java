package DAO;

import java.util.ArrayList;
import java.util.List;

import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import utils.StringManager;

public class EspecificacaoDAO extends DAO<Especificacao>
{

    @Override
    public List<Especificacao> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Especificacao getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Especificacao especificacao) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Especificacao.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Especificacao.Coluna.VALOR.getNomeColuna());


        /* 
         * Montando valores
         */
        valoresString.add(especificacao.getNome());
        valoresString.add(especificacao.getValor());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(especificacao.getNomeTabela(), colunas, valores);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void delete(Especificacao especificacao) 
    {
        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.delete(especificacao.getNomeTabela(), condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Especificacao especificacao, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);
        
        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(especificacao.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Especificacao especificacao, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(especificacao.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Especificacao especificacao, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(especificacao.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }
    
}
