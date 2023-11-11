package sistema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sistema.entitymodels.Usuario;

public class Usuarios extends DAO<Usuario> {

    @Override
    public Usuario getById(int id) {
        //to implement
        return null;
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario")) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                /* Colocar setters, exemplo: */
                /*
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                */
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public void insert(Usuario usuario) {
        //to implement
    }

    @Override
    public void update(Usuario usuario) {
        //to implement
    }

    @Override
    public void delete(int id) {
        //to implement
    }

}
