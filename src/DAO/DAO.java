package DAO;

import java.util.ArrayList;

/* 
 * Classe abstrata Data Access Object (Objeto de Acesso a Dados)
 * Serve como intermediária entre a aplicação java e o banco de dados.
 * 
 * Todos os metódos especificados aqui serão implementados em todas as tabelas simples do banco de dados
 */
public abstract class DAO<T> 
{
    
    /* 
     * Construtor default
     */
    public DAO(){};

    /* 
     * Métodos CRUD (Create, Read, Update, Delete)
     */

    /* 
     * Método responsável por puxar do banco de dados um elemento através do ID
     */
    public abstract T selectById(int id);

    /* 
     * Método responsável por puxar do banco de dados todos os elementos de um tabela 
     */
    public abstract ArrayList<T> selectAll();

    /* 
     * Método responsável por inserir um elemento, em uma determinada tabela no banco de dados
     */
    public abstract void insert(T entity);


    /* 
     * Método responsável por setar um elemento do tipo VARCHAR, TEXT, etc (Isto é, do tipo String) 
     * em uma determinada tabela no banco de dados
     */
    public abstract void updateString(T entity, String coluna, String novoValor);

    /* 
     * Método responsável por setar um elemento do tipo real, ou double em uma determinarda tabela no banco de dados
     */
    public abstract void updateDouble(T entity, String coluna, double novoValor);

    /* 
     * Método responsável por setar um elemento do tipo inteiro em uma determinarda tabela no banco de dados
     */
    public abstract void updateInt(T entity, String coluna, int novoValor);
    
    /* 
     * Método responsável por deletar um elemento em uma determinada tabela no banco de dados
     */
    public abstract void delete(T entity);

}
