package DAO;

import java.util.ArrayList;

public abstract class DAO<T> 
{
    
    /* 
     * Construtor default
     */
    public DAO(){};

    /* 
     * Métodos CRUD (Create, Read, Update, Delete)
     */

    public abstract T selectById(int id);

    public abstract ArrayList<T> SelectAll();

    public abstract void insert(T entity);

    public abstract void updateString(T entity, String coluna, String novoValor);

    public abstract void updateDouble(T entity, String coluna, double novoValor);

    public abstract void updateInt(T entity, String coluna, int novoValor);

    public abstract void delete(T entity);

}
