package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Loja;
import sistema.Produto;
import sistema.Usuario;
import utils.StringManager;

public class LojaDAO extends DAO<Loja> 
{

    @Override
    public ArrayList<Loja> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Loja.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        /* 
         * Montando lojas
         */
        ArrayList<Loja> lojas = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idLoja = resultSet.getInt(Loja.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Loja.Coluna.NOME.getNomeColuna());
                String url = resultSet.getString(Loja.Coluna.URL.getNomeColuna());
                String url_foto = resultSet.getString(Loja.Coluna.URL_FOTO.getNomeColuna());
                int idUsuario = resultSet.getInt("Usuario_idUsuario");


                Loja loja = new Loja(idLoja, nome, url, url_foto, idUsuario);

                lojas.add(loja);
            }
            
            return lojas;
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
    public Loja selectById(int id) 
    {
        /* 
         * Montando instrução
         */
        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Loja.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando loja
         */
        try
        {

            if(resultSet != null)
            {
                int idLoja = resultSet.getInt(Loja.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Loja.Coluna.NOME.getNomeColuna());
                String url = resultSet.getString(Loja.Coluna.URL.getNomeColuna());
                String url_foto = resultSet.getString(Loja.Coluna.URL_FOTO.getNomeColuna());
                int idUsuario = resultSet.getInt("Usuario_idUsuario");

                Loja loja = new Loja(idLoja, nome, url, url_foto, idUsuario);

                return loja;
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
    public void insert(Loja loja) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();
        ArrayList<Integer> valoresInt = new ArrayList<Integer>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Loja.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Loja.Coluna.URL.getNomeColuna());
        arrayColunas.add(Loja.Coluna.URL_FOTO.getNomeColuna());
        arrayColunas.add("Usuario_idUsuario");



        /* 
         * Montando valores
         */
        valoresString.add(loja.getNome());
        valoresString.add(loja.getUrl());
        valoresString.add(loja.getUrl_foto());
        
        valoresInt.add(loja.getIdUsuario());


        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);
        ArrayList<String> valorsIntNormalizados = StringManager.formatarInt(valoresInt);

        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados) + ", " + 
        StringManager.montarString(valorsIntNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Loja.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void delete(Loja loja) 
    {
        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.delete(Loja.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Loja loja, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Loja loja, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Loja loja, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.ID.getNomeColuna(), Integer.toString(loja.getId())); 

        String instrucao = SQLiteTableManager.update(Loja.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    /* 
     * Método responsável por retornar todos os produtos cadastrados em uma loja
     */
    public ArrayList<Produto> selectTodosProdutosCadastradosNaLoja(int idLoja)
    {
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.IDLOJA.getNomeColuna(), Integer.toString(idLoja));

        String instrucao = SQLiteTableManager.select(Produto.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando produtos
         */

         ArrayList<Produto> produtosMontados = new ArrayList<>();
         try
         {
             while (resultSet.next()) 
             {
                 produtosMontados.add(ProdutoDAO.montarProduto(resultSet));
             }
 
             return produtosMontados;
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
     * Método responsável por retornar todos os produtos cadastrados em uma loja, cadastrado por um determinado usuário
     */
    public ArrayList<Integer> selectTodosProdutosDaLojaUsuario(int idLoja, int idUsuario)
    {
        String condicao1 = StringManager.inserirIgualdade(Produto.Coluna.IDLOJA.getNomeColuna(), Integer.toString(idLoja));

        String condicao2 = StringManager.inserirIgualdade(Produto.Coluna.IDUSUARIO.getNomeColuna(), Integer.toString(idUsuario));

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        /* Select idProduto FROM Produto WHERE Loja_idLoja = x AND Usuario_idUsuario = y; */
        String instrucao = SQLiteTableManager.select(Produto.getNomeTabela(), Produto.Coluna.ID.getNomeColuna(), condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> idProdutos = new ArrayList<Integer>();

        try
        {
            while (resultSet.next()) 
            {
                idProdutos.add(resultSet.getInt(Produto.Coluna.ID.getNomeColuna()));
            }

            return idProdutos;
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

    public int contarLojas()
    {
        ArrayList<Loja> lojas = this.selectAll();

        return lojas.size();
    }

    /* 
     * Método responsável por contar todas as lojas cadastradas por um usuário, com base no id do usario passado por parâmetro
     */
    public int contarLojasDoUsuario(int idUsuario)
    {
        /* 
        * Motando condição
        */
        
        String condicao = StringManager.inserirIgualdade(Usuario.getNomeTabela() + "." + Usuario.Coluna.ID.getNomeColuna(), Integer.toString(idUsuario)); 

        /* 
        * Montando join
        */

        /* JOIN Produto ON Loja.idLoja = Produto.Loja_idLoja */
        String coluna1 = Loja.getNomeTabela() + "." + Loja.Coluna.ID.getNomeColuna();
        String coluna2 = Produto.getNomeTabela() + "." + Produto.Coluna.IDLOJA.getNomeColuna();
        String join1 = StringManager.formatarJoin(Produto.getNomeTabela(),coluna1 , coluna2);

        /* JOIN Usuario ON Produto.Usuario_idUsuario = Usuario.idUsuario */
        coluna1 = Produto.getNomeTabela() + "." + Produto.Coluna.IDUSUARIO.getNomeColuna();
        coluna2 = Usuario.getNomeTabela() + "." + Usuario.Coluna.ID.getNomeColuna();
        String join2 = StringManager.formatarJoin(Usuario.getNomeTabela(), coluna1, coluna2);

        String join = join1 + join2;

        String instrucao = SQLiteTableManager.count(Loja.getNomeTabela(), condicao, Loja.Coluna.ID.getNomeColuna(), join);

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

    /* 
     * Método responsável por contar quantos produtos estão cadastrados em uma loja, com base no id do usario passado por parâmetro
     */
    public int contarProdutosDaLoja(int idLoja)
    {
        /* 
        * Motando condição
        */
        
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.IDLOJA.getNomeColuna(), Integer.toString(idLoja)); 

        String instrucao = SQLiteTableManager.count(Produto.getNomeTabela(), condicao,  Produto.Coluna.ID.getNomeColuna());

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

    /* 
     * Método responsável por buscar lojas pelo nome
     */
    public ArrayList<Loja> selectLojaPorNome(String nomeLoja, int quantidadeRetornada)
    {

        nomeLoja = StringManager.formatarString(nomeLoja);

        String condicao = StringManager.inserirIgualdade(Loja.Coluna.NOME.getNomeColuna(), nomeLoja);

        /* SELECT * FROM Loja WHERE nome = 'nomeLoja' LIMIT X; */

        String instrucao = SQLiteTableManager.selectLimit(Loja.getNomeTabela(), "*", condicao, Integer.toString(quantidadeRetornada));

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando lojas
         */
        ArrayList<Loja> lojas = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idLoja = resultSet.getInt(Loja.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Loja.Coluna.NOME.getNomeColuna());
                String url = resultSet.getString(Loja.Coluna.URL.getNomeColuna());
                String url_foto = resultSet.getString(Loja.Coluna.URL_FOTO.getNomeColuna());
                int idUsuario = resultSet.getInt("Usuario_idUsuario");

                Loja loja = new Loja(idLoja, nome, url, url_foto, idUsuario);

                lojas.add(loja);
            }
            return lojas;
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


    /*
     * Funções de update para cada atributo da classe produto
     */

    public void updateNome(Loja loja, String newNome)
    {
        this.updateString(loja, Loja.Coluna.NOME.getNomeColuna(), newNome);
    }

    public void updateUrl(Loja loja, String newUrl)
    {
        this.updateString(loja, Loja.Coluna.URL.getNomeColuna(), newUrl);
    }

    public void updateUrl_foto(Loja loja, String newUrl_foto)
    {
        this.updateString(loja, Loja.Coluna.URL_FOTO.getNomeColuna(), newUrl_foto);
    }

    /* 
     * Método responsável por retornar todos os ID's das lojas cadastradas por um usuário
     */
    public ArrayList<Integer> selectLojaUsuario(int idUsuario)
    {
        String instrucao = "SELECT Loja.idLoja FROM Loja WHERE Loja.Usuario_idUsuario = " + String.valueOf(idUsuario);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> idLojas = new ArrayList<Integer>();

        try
        {
            while(resultSet.next())
            {
                idLojas.add(resultSet.getInt(Loja.Coluna.ID.getNomeColuna())); 
            }

            return idLojas;
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
}

