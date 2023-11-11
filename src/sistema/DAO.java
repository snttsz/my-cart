package sistema;

import java.sql.*;
import java.util.List;

public abstract class DAO<T> 
{
    
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Operações CRUD (Create, Read, Update, Delete)

    public abstract T getById(int id);

    public abstract List<T> getAll();

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(int id);
}
