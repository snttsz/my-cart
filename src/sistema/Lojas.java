package sistema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sistema.entitymodels.Loja;

public class Lojas extends DAO<Loja> {

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
    public void insert(Loja loja) {
        //to implement
    }

    @Override
    public void update(Loja loja) {
        //to implement
    }

    @Override
    public void delete(int id) {
        //to implement
    }

}
