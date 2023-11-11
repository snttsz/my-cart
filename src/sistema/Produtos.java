package sistema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sistema.entitymodels.Produto;

public class Produtos extends DAO<Produto> {

    @Override
    public Produto getById(int id) {
        //to implement
        return null;
    }

    @Override
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM produto")) {

            while (resultSet.next()) {
                Produto produto = new Produto(); // Produto é abstrato
                /* Colocar setters, exemplo: */
                /*
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                */
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public void insert(Produto produto) {
        //to implement
    }

    @Override
    public void update(Produto produto) {
        //to implement
    }

    @Override
    public void delete(int id) {
        //to implement
    }

}
