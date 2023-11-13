package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Tag;
import utils.StringManager;

public class TagDAO extends DAO<Tag>
{

    @Override
    public void delete(Tag tag) 
    {
        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.delete(Tag.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }



    @Override
    public ArrayList<Tag> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Tag.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        /* 
         * Montando Tags
         */
        ArrayList<Tag> tags = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idTag = resultSet.getInt(Tag.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Tag.Coluna.NOME.getNomeColuna());

                Tag tag = new Tag(idTag, nome);

                tags.add(tag);
            }
            
            return tags;
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
    public Tag selectById(int id) 
    {
        /* 
         * Montando instrução
         */
        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Tag.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando Tag
         */
        try
        {

            if(resultSet != null)
            {
                int idTag = resultSet.getInt(Tag.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Tag.Coluna.NOME.getNomeColuna());

                Tag tag = new Tag(idTag, nome);

                return tag;
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

        String instrucao = SQLiteTableManager.insertTo(Tag.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }


    @Override
    public void updateString(Tag tag, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Tag tag, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Tag tag, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    
}
