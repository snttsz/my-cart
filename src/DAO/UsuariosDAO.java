package DAO;

import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Usuario;
import utils.StringManager;

public class UsuariosDAO extends DAO<Usuario> 
{

    @Override
    public void delete(Usuario usuario) 
    {
        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.delete(usuario.getNomeTabela(), condicao);

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

        String instrucao = SQLiteTableManager.insertTo(usuario.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Usuario usuario, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Usuario usuario, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Usuario usuario, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public ArrayList<Usuario> SelectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario selectById(int id) {
        // TODO Auto-generated method stub
        return null;
    }






}
