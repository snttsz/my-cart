package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.ProdutoAlimento;
import sistema.ProdutoEletronico;
import sistema.ProdutoFerramenta;
import sistema.ProdutoLivro;
import sistema.ProdutoMobilia;
import sistema.ProdutoRoupa;
import utils.StringManager;

public class ProdutoDAO extends DAO<Produto> 
{

    /* 
     * Construtores
     */
    public ProdutoDAO(){};

    /* 
     * Métodos gerais
     */

    @Override
    public Produto selectById(int id)
    {

        /* 
         * Montando instrucao
         */

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Produto.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando produto
         */
        if(resultSet != null)
        {
            Produto produto = montarProduto(resultSet);

            SQLiteConnectionManager.desconectar();

            return produto;
        }

        SQLiteConnectionManager.desconectar();
        
        return null;
    }

    @Override
    public ArrayList<Produto> selectAll() 
    {
        /* 
         * Montando instrução
         */

        String instrucao = SQLiteTableManager.selectAll(Produto.getNomeTabela());

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

    public void insert(Produto produto) 
    {
        ArrayList<String> valoresString = new ArrayList<String>();
        ArrayList<Integer> valoresInteger = new ArrayList<Integer>();
        ArrayList<Double> valoresDouble = new ArrayList<Double>();

        ArrayList<String> arrayColunas = new ArrayList<String>();
        /* 
         * Montando as colunas da tabela Produto
         */

        /* Importante seguir a ordem abaixo */
        arrayColunas.add(Produto.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Produto.Coluna.LINK.getNomeColuna());
        arrayColunas.add(Produto.Coluna.MARCA.getNomeColuna());
        arrayColunas.add(Produto.Coluna.DESCRICAO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.URL_FOTO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.DATA_DE_ADICAO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.CATEGORIA.getNomeColuna());
        /* loja */
        arrayColunas.add(Produto.Coluna.PRIORIDADE.getNomeColuna());
        arrayColunas.add(Produto.Coluna.DISPONIBILIDADE.getNomeColuna());
        arrayColunas.add(Produto.Coluna.PRECO.getNomeColuna()); 
        arrayColunas.add(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.VALOR_FRETE.getNomeColuna());

        /* 
         * Montando os valores referente a cada coluna
         */
        /* Valores do tipo TEXT */
        valoresString.add(produto.getNome());
        valoresString.add(produto.getLink());
        valoresString.add(produto.getMarca());
        valoresString.add(produto.getDescricao());
        valoresString.add(produto.getUrl_foto());
        valoresString.add(produto.getData_de_adicao());
        valoresString.add(produto.getCategoria());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* Valores do tipo Integer */
        valoresInteger.add(produto.getPrioridade());
        valoresInteger.add(produto.getDisponibilidade());
        
        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);

        /* Valores do tipo Double */
        valoresDouble.add(produto.getPreco());
        valoresDouble.add(produto.getValorArrecadado());
        valoresDouble.add(produto.getValorFrete());

        ArrayList<String> valoresDoubleNormalizados = StringManager.formatarDouble(valoresDouble);

        /* Montando string com a tabela, coluna e valores */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = 
        StringManager.montarString(valoresStringNormalizados) + ", " + 
        StringManager.montarString(valoresIntegerNormalizados) + ", " +
        StringManager.montarString(valoresDoubleNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Produto.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void updateString(Produto produto, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.update(Produto.getNomeTabela(), colunas_valores, condicao);

        System.out.println(instrucao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void updateInt(Produto produto, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.update(Produto.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void updateDouble(Produto produto, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId()));

        String instrucao = SQLiteTableManager.update(Produto.getNomeTabela(), colunas_valores, condicao);

        
        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void delete(Produto produto) 
    {
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.delete(Produto.getNomeTabela(), condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    public static Produto instanciarProduto(Map<String,String> produto, String categoria)
    {
        int id = Integer.parseInt(produto.get(Produto.Coluna.ID.getNomeColuna()));
        int prioridade = Integer.parseInt(produto.get(Produto.Coluna.PRIORIDADE.getNomeColuna()));
        int disponibilidade = Integer.parseInt(produto.get(Produto.Coluna.DISPONIBILIDADE.getNomeColuna()));

        double preco = Double.parseDouble(produto.get(Produto.Coluna.PRECO.getNomeColuna()));
        double valorFrete = Double.parseDouble(produto.get(Produto.Coluna.VALOR_FRETE.getNomeColuna()));
        double valorArrecadado = Double.parseDouble(produto.get(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna()));

        String nome = produto.get(Produto.Coluna.NOME.getNomeColuna());
        String link = produto.get(Produto.Coluna.LINK.getNomeColuna());
        String marca = produto.get(Produto.Coluna.MARCA.getNomeColuna());
        String data_de_adicao = produto.get(Produto.Coluna.DATA_DE_ADICAO.getNomeColuna());
        String url_foto = produto.get(Produto.Coluna.URL_FOTO.getNomeColuna());
        String descricao = produto.get(Produto.Coluna.DESCRICAO.getNomeColuna());

        if(categoria.equals(Produto.Categorias.ELETRONICO.getCategoria()))
        {
            ProdutoEletronico produtoEletronico = new ProdutoEletronico(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null);
            
            return produtoEletronico;
        }
        else if(categoria.equals(Produto.Categorias.ALIMENTICIO.getCategoria()))
        {
            ProdutoAlimento produtoAlimento = new ProdutoAlimento(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null);

            return produtoAlimento;

        }
        else if(categoria.equals(Produto.Categorias.FERRAMENTA.getCategoria()))
        {
            ProdutoFerramenta produtoFerramenta = new ProdutoFerramenta(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null);

            return produtoFerramenta;
        }
        else if(categoria.equals(Produto.Categorias.LIVRO.getCategoria()))
        {
            String autor = produto.get(ProdutoLivro.Coluna.AUTOR.getNomeColuna());
            String genero = produto.get(ProdutoLivro.Coluna.GENERO.getNomeColuna());

            ProdutoLivro produtoLivro = new ProdutoLivro(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null, autor, genero);
            
            return produtoLivro;
        }
        else if(categoria.equals(Produto.Categorias.MOBILIA.getCategoria()))
        {
                
             String material = produto.get(ProdutoMobilia.Coluna.MATERIAL.getNomeColuna()); 
             String cor = produto.get(ProdutoMobilia.Coluna.COR.getNomeColuna());

             double altura = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.ALTURA.getNomeColuna()));
             double largura = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.LARGURA.getNomeColuna())); 
             double comprimento = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.COMPRIMENTO.getNomeColuna()));

             ProdutoMobilia produtoMobilia = new ProdutoMobilia(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null, material, cor, altura, largura, comprimento );

            return produtoMobilia;
        }
        else if(categoria.equals(Produto.Categorias.ROUPA.getCategoria()))
        {
            String tamanho = produto.get(ProdutoRoupa.Coluna.TAMANHO.getNomeColuna()); 
            String cor = produto.get(ProdutoRoupa.Coluna.COR.getNomeColuna());
            String material = produto.get(ProdutoRoupa.Coluna.MATERIAL.getNomeColuna());

            ProdutoRoupa produtoRoupa = new ProdutoRoupa(id, disponibilidade, descricao, nome, preco, link, url_foto, marca, data_de_adicao, prioridade, valorArrecadado, 
            valorFrete, categoria, null, null, tamanho, cor, material);

            return produtoRoupa;
        }
        else
        {
            System.out.println("Categoria inexistente!");
            
            return null;
        }
    }

    public static Produto montarProduto(ResultSet resultSet)
    {
         /* 
         * Montando produtos
         */
        Map<String,String> produto = new HashMap<>();
        try
        {

            if(resultSet != null)
            {
                /* Inteiros */
                produto.put(Produto.Coluna.ID.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.ID.getNomeColuna())));
                produto.put(Produto.Coluna.DISPONIBILIDADE.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.DISPONIBILIDADE.getNomeColuna())));
                produto.put(Produto.Coluna.PRIORIDADE.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.PRIORIDADE.getNomeColuna())));
    
    
                /* Strings */
                produto.put(Produto.Coluna.NOME.getNomeColuna(), resultSet.getString(Produto.Coluna.NOME.getNomeColuna()));
                produto.put(Produto.Coluna.DESCRICAO.getNomeColuna(), resultSet.getString(Produto.Coluna.DESCRICAO.getNomeColuna()));
                produto.put(Produto.Coluna.LINK.getNomeColuna(), resultSet.getString(Produto.Coluna.LINK.getNomeColuna()));
                produto.put(Produto.Coluna.URL_FOTO.getNomeColuna(), resultSet.getString(Produto.Coluna.URL_FOTO.getNomeColuna()));
                produto.put(Produto.Coluna.MARCA.getNomeColuna(), resultSet.getString(Produto.Coluna.MARCA.getNomeColuna()));
                produto.put(Produto.Coluna.DATA_DE_ADICAO.getNomeColuna(), resultSet.getString(Produto.Coluna.DATA_DE_ADICAO.getNomeColuna()));
                produto.put(Produto.Coluna.CATEGORIA.getNomeColuna(), resultSet.getString(Produto.Coluna.CATEGORIA.getNomeColuna()));
    
    
                /* Doubles */
                produto.put(Produto.Coluna.PRECO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.PRECO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_FRETE.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_FRETE.getNomeColuna())));

                String categoria = resultSet.getString(Produto.Coluna.CATEGORIA.getNomeColuna());

                return instanciarProduto(produto, categoria);
    
            }
        }
        catch(SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }

        return null;
    }
}
