package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Usuario;
import utils.StringManager;

public class UsuariosDAO extends DAO<Usuario> 
{

    @Override
    public void delete(Usuario usuario) 
    {
        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.delete(Usuario.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void insert(Usuario usuario) 
    {
       ArrayList<String> valoresString = new ArrayList<String>();

       ArrayList<String> arrayColunas = new ArrayList<String>();

       /* 
        * Adicionando colunas
        */
        arrayColunas.add(Usuario.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.EMAIL.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.LOGIN.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.SENHA.getNomeColuna());

        /* 
         * Montando valores
         */
        valoresString.add(usuario.getNome());
        valoresString.add(usuario.getEmail());
        valoresString.add(usuario.getLogin());
        valoresString.add(usuario.getSenha());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Usuario.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Usuario usuario, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Usuario usuario, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Usuario usuario, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public ArrayList<Usuario> selectAll() 
    {
        /* 
         * Montando instrucao
         */

        String instrucao = SQLiteTableManager.selectAll(Usuario.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try
        {
            while(resultSet.next())
            {
                int id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                
                Usuario usuario = new Usuario(id, nome, login, senha, email);

                usuarios.add(usuario);
            }

            return usuarios;
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
    public Usuario selectById(int id) 
    {
        /* 
         * Montando instrucao
         */

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        try
        {
            if(resultSet != null)
            {
                int idUsuario = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                
                Usuario usuario = new Usuario(idUsuario, nome, login, senha, email);

                return usuario;
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

}
