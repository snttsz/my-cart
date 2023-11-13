package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Loja;
import utils.StringManager;

public class LojaDAO extends DAO<Loja> 
{

    @Override
    public ArrayList<Loja> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Loja.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        /* 
         * Montando lojas
         */
        ArrayList<Loja> lojas = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idLoja = resultSet.getInt(Loja.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Loja.Coluna.NOME.getNomeColuna());
                String url = resultSet.getString(Loja.Coluna.URL.getNomeColuna());

                Loja loja = new Loja(nome, url, idLoja);

                lojas.add(loja);
            }
            
            return lojas;
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
 
    }

    @Override
    public Loja selectById(int id) 
    {
        /* 
         * Montando instrução
         */
        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Loja.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando loja
         */
        try
        {

            if(resultSet != null)
            {
                int idLoja = resultSet.getInt(Loja.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Loja.Coluna.NOME.getNomeColuna());
                String url = resultSet.getString(Loja.Coluna.URL.getNomeColuna());

                Loja loja = new Loja(nome, url, idLoja);

                return loja;
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public void insert(Loja loja) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Loja.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Loja.Coluna.URL.getNomeColuna());


        /* 
         * Montando valores
         */
        valoresString.add(loja.getNome());
        valoresString.add(loja.getUrl());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Loja.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void delete(Loja loja) 
    {
        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.delete(Loja.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Loja loja, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Loja loja, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Loja loja, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

}
