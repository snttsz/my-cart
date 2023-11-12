package DAO;

import java.sql.*;
import java.util.List;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;

public abstract class DAO<T> 
{
    
    /* 
     * Construtor default
     */
    public DAO(){};

    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    protected Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }


    /* 
     * Métodos CRUD (Create, Read, Update, Delete)
     */

    public abstract T getById(int id);

    public abstract List<T> getAll();

    public abstract void insert(T entity);

    public abstract void updateString(T entity, String coluna, String novoValor);

    public abstract void updateDouble(T entity, String coluna, double novoValor);

    public abstract void updateInt(T entity, String coluna, int novoValor);

    public abstract void delete(T entity);

    /* ATRIBUTOS */
    protected static final SQLiteConnectionManager SQLiteConnectionManager = SQLiteTableManager.getSqliteconnectionmanager();

}
