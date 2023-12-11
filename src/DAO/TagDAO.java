package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOMTM.Tag_has_Produto;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Tag;
import sistema.Usuario;
import utils.StringManager;

public class TagDAO extends DAO<Tag>
{

    @Override
    public void delete(Tag tag) 
    {
        // Deletando elementos da tabela has
        ArrayList<Produto> produtosRelacionados = tag_has_Produto_DAO.selectTodosProdutosDaTag(tag.getId());
        produtosRelacionados.forEach(produto -> {
            tag_has_Produto_DAO.delete(tag, produto);
        });

        // Deletando tag da tabela de tags
        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 
        String instrucao = SQLiteTableManager.delete(Tag.getNomeTabela(), condicao);
        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }


    @Override
    public ArrayList<Tag> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Tag.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        /* 
         * Montando Tags
         */
        ArrayList<Tag> tags = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idTag = resultSet.getInt(Tag.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Tag.Coluna.NOME.getNomeColuna());

                Tag tag = new Tag(idTag, nome);

                tags.add(tag);
            }
            
            return tags;
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
    public Tag selectById(int id) 
    {
        /* 
         * Montando instrução
         */
        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Tag.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando Tag
         */
        try
        {

            if(resultSet != null)
            {
                int idTag = resultSet.getInt(Tag.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Tag.Coluna.NOME.getNomeColuna());

                Tag tag = new Tag(idTag, nome);

                return tag;
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
    public void insert(Tag tag) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Tag.Coluna.NOME.getNomeColuna());


        /* 
        * Montando valores
        */
        valoresString.add(tag.getNome());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Tag.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }


    @Override
    public void updateString(Tag tag, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Tag tag, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Tag tag, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Tag.Coluna.ID.getNomeColuna(), Integer.toString(tag.getId())); 

        String instrucao = SQLiteTableManager.update(Tag.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

     /* 
      * Método responsável por contar quantas tags um usuário possui, atavés do ID do usuário passado por parâmetro
    */
    public int contarTagsDoUsuario(int idUsuario)
    {
        /* 
         * Motando condição
         */
        
        String condicao = StringManager.inserirIgualdade(Usuario.getNomeTabela() + "." + Usuario.Coluna.ID.getNomeColuna(), Integer.toString(idUsuario)); 

        /* 
         * Montando join
         */

        /* 
         * JOIN Produto ON Tag_has_Produto.Produto_idProduto = Produto.idProduto
         */
        String coluna1 = Tag_has_Produto.getNomeTabela() + "." + Tag_has_Produto.Coluna.IDProduto.getNomeColuna();
        String coluna2 = Produto.getNomeTabela() + "." + Produto.Coluna.ID.getNomeColuna();
        String join1 = StringManager.formatarJoin(Produto.getNomeTabela(),coluna1 , coluna2);

        /* 
         * JOIN Usuario ON Produto.Usuario_idUsuario = Usuario.idUsuario
         */
        coluna1 = Produto.getNomeTabela() + "." + Produto.Coluna.IDUSUARIO.getNomeColuna();
        coluna2 = Usuario.getNomeTabela() + "." + Usuario.Coluna.ID.getNomeColuna();
        String join2 = StringManager.formatarJoin(Usuario.getNomeTabela(), coluna1, coluna2);

        String join = join1 + " " + join2;
        String instrucao = SQLiteTableManager.count(Tag_has_Produto.getNomeTabela(), condicao,  Tag_has_Produto.Coluna.IDTAG.getNomeColuna(), join);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        int resultado = 0;

        try
        {
            if(resultSet != null)
            {
                resultado = resultSet.getInt(1);
            }

            return resultado;
        }
        catch(SQLException e) 
         {
             e.printStackTrace(); 
             throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
         }
         finally
         {
             SQLiteConnectionManager.desconectar();
         }

    }

    public ArrayList<Integer> selectTagsCadastradosRecentemente(int qtdProdutosCadastrados)
    {
        /* SELECT * FROM Produto WHERE Produto.Usuario_idUsuario = Y ORDER BY idProduto DESC LIMIT X; */
        String qtd = Integer.toString(qtdProdutosCadastrados);

        // String instrucao = SQLiteTableManager.selectOrderByLimitDec(Produto.getNomeTabela(), Produto.Coluna.ID.getNomeColuna(), qtd);
        
        /* SELECT Produto.idProduto
            FROM Produto
            WHERE Produto.Usuario_idUsuario = X
            ORDER BY Produto.idProduto DESC LIMIT Y;
        */
        String instrucao = "SELECT Tag.idTag FROM Tag ORDER BY Tag.idTag DESC LIMIT " + String.valueOf(qtdProdutosCadastrados);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> tags = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                tags.add(resultSet.getInt("idTag"));
            }

            return tags;
        }
        catch(SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }

    /*
     * Funções de update para cada atributo da classe Tag
     */

    public void updateNome(Tag tag, String newNome)
    {
        this.updateString(tag, Tag.Coluna.NOME.getNomeColuna(), newNome);
    }


    /*
     * Atributos
     */

    Tag_has_Produto tag_has_Produto_DAO = new Tag_has_Produto();
    
}
