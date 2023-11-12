package DAO;

import java.util.ArrayList;
import java.util.List;

import bancodedados.SQLiteTableManager;
import sistema.Loja;
import sistema.Tag;
import utils.StringManager;

public class TagDAO extends DAO<Tag>
{

    @Override
    public void delete(Tag tag) 
    {
        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.delete(tag.getNomeTabela(), condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public List<Tag> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tag getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Tag tag) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Tag.Coluna.NOME.getNomeColuna());


        /* 
        * Montando valores
        */
        valoresString.add(tag.getNome());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(tag.getNomeTabela(), colunas, valores);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }


    @Override
    public void updateString(Tag tag, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(tag.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Tag tag, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(tag.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Tag tag, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(tag.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    
}
