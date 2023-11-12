package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bancodedados.SQLiteTableManager;
import sistema.Loja;
import utils.StringManager;

public class LojaDAO extends DAO<Loja> 
{

    @Override
    public Loja getById(int id) {
        //to implement
        return null;
    }

    @Override
    public List<Loja> getAll() {
        List<Loja> lojas = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM loja")) {

            while (resultSet.next()) {
                Loja loja = new Loja();
                /* Colocar setters, exemplo: */
                /*
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                */
                lojas.add(loja);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lojas;
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

        String instrucao = SQLiteTableManager.insertTo(loja.getNomeTabela(), colunas, valores);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void delete(Loja loja) 
    {
        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.delete(loja.getNomeTabela(), condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Loja loja, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(loja.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Loja loja, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(loja.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Loja loja, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(loja.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
        
    }



}
