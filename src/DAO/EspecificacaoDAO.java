package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import utils.StringManager;

public class EspecificacaoDAO extends DAO<Especificacao>
{

    @Override
    public ArrayList<Especificacao> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Especificacao.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        /* 
         * Montando Especificacaos
         */
        ArrayList<Especificacao> especificacoes = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idEspecificacao = resultSet.getInt(Especificacao.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Especificacao.Coluna.NOME.getNomeColuna());
                String valor = resultSet.getString(Especificacao.Coluna.VALOR.getNomeColuna());

                Especificacao especificacao = new Especificacao(idEspecificacao, nome, valor);

                especificacoes.add(especificacao);
            }
            
            return especificacoes;
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
    public Especificacao selectById(int id) 
    {
        /* 
         * Montando instrução
         */
        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Especificacao.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando Especificacao
         */
        try
        {

            if(resultSet != null)
            {
                int idEspecificacao = resultSet.getInt(Especificacao.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Especificacao.Coluna.NOME.getNomeColuna());
                String valor = resultSet.getString(Especificacao.Coluna.VALOR.getNomeColuna());

                Especificacao Especificacao = new Especificacao(idEspecificacao, nome, valor);

                return Especificacao;
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

        String instrucao = SQLiteTableManager.insertTo(Especificacao.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void delete(Especificacao especificacao) 
    {
        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.delete(Especificacao.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Especificacao especificacao, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);
        
        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(Especificacao.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Especificacao especificacao, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(Especificacao.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Especificacao especificacao, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Especificacao.Coluna.ID.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String instrucao = SQLiteTableManager.update(Especificacao.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }
    
}
