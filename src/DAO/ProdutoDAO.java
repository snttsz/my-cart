package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.DAOMTM.Especificacao_has_Produto;
import DAO.DAOMTM.Tag_has_Produto;
import DAO.EspecificacaoDAO;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.Tag;
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

    /* 
     * Método responsável por retornar um produto do banco com base no id dele
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

    /* 
     * Método responsável por retornar um ArrayList contendo todos os produtos do bando de dados
     */
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

    @Override
    public void insert(Produto produto) 
    {

        ArrayList<String> arrayColunas = new ArrayList<String>();
        /* 
         * Montando as colunas da tabela Produto
         */

        /* Importante seguir a ordem abaixo */
        arrayColunas.add(Produto.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Produto.Coluna.PRECO.getNomeColuna()); 
        arrayColunas.add(Produto.Coluna.LINK.getNomeColuna());
        arrayColunas.add(Produto.Coluna.URL_FOTO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.DESCRICAO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.VALOR_FRETE.getNomeColuna());
        arrayColunas.add(Produto.Coluna.CATEGORIA.getNomeColuna());
        arrayColunas.add("Loja_idLoja");
        arrayColunas.add(Produto.Coluna.IDUSUARIO.getNomeColuna());

        /* Montando string com a tabela, coluna e valores */
        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.formatarString(produto.getNome()) + ", " + 
        String.valueOf(produto.getPreco()) + ", " + StringManager.formatarString(produto.getLink()) +
        ", " + StringManager.formatarString(produto.getUrl_foto()) + ", " + 
        String.valueOf(produto.getValorArrecadado()) + ", " + StringManager.formatarString(produto.getDescricao()) +
        ", " + String.valueOf(produto.getValorFrete()) + ", " + 
        StringManager.formatarString(produto.getCategoria()) + ", " + 
        String.valueOf(produto.getIdLoja()) + ", " + String.valueOf(produto.getIdUsuario());

        String instrucao = SQLiteTableManager.insertTo(Produto.getNomeTabela(), colunas, valores);
        SQLiteConnectionManager.enviarQuery(instrucao);

        this.cadastrarTabelasAssociadas(this.selectProdutosCadastradosRecentemente(1).get(0).getId(), produto.getEspecificacoes(), produto.getTags());
    }

    public void cadastrarTabelasAssociadas(int idProduto, ArrayList<Especificacao> especificacoes, ArrayList<Tag> tags)
    {
        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
        TagDAO tagDao = new TagDAO();

        ArrayList<Integer> idEspecificacoes = this.cadastrarEspecificacoes(especificacoes);
        ArrayList<Integer> idTags = this.cadastrarTags(tags);

        for (int idEspecificacao : idEspecificacoes)
        {
            this.especificacao_has_Produto_DAO.insert(especificacaoDAO.selectById(idEspecificacao), this.selectById(idProduto));
        }

        for (int idTag : idTags)
        {
            this.tag_has_Produto_DAO.insert(tagDao.selectById(idTag), this.selectById(idProduto));
        }
    }

    public ArrayList<Integer> cadastrarEspecificacoes(ArrayList<Especificacao> especificacoes)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();

        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();

        for (Especificacao especificacao : especificacoes)
        {
            especificacaoDAO.insert(especificacao);
        }

        result = especificacaoDAO.selectEspecificacoesCadastradosRecentemente(especificacoes.size());

        return result;
    }

    public ArrayList<Integer> cadastrarTags(ArrayList<Tag> tags)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();

        TagDAO tagDAO = new TagDAO();

        for (Tag tag : tags)
        {
            tagDAO.insert(tag);
        }

        result = tagDAO.selectTagsCadastradosRecentemente(tags.size());

        return result;
    }

    @Override
    public void updateString(Produto produto, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.update(Produto.getNomeTabela(), colunas_valores, condicao);

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
        ArrayList<Especificacao> especificacoesRelacionadas = especificacao_has_Produto_DAO.selectTodasEspecificacoesDoProduto(produto.getId());
        ArrayList<Tag> tagsRelacionadas = tag_has_Produto_DAO.selectTodasTagsDoProduto(produto.getId());

        // Deletando da tabela tag_has_produto
        tagsRelacionadas.forEach(tag -> {
            tag_has_Produto_DAO.delete(tag, produto);
        });

        // Deletando da tabela especificacao_has_produto
        especificacoesRelacionadas.forEach(especificacao -> {
            especificacao_has_Produto_DAO.delete(especificacao, produto);
        });

        // Deletando da tabela de produto
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 
        String instrucao = SQLiteTableManager.delete(Produto.getNomeTabela(), condicao);
        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    /* 
     * Método responsável por receber um Map , sendo que a chave é o nome da coluna e o valor é o conteúdo daquela coluna
     * e instanciar um produto com base na categoria passada
     */
    public static Produto instanciarProduto(Map<String,String> produto, String categoria)
    {        
        int id = Integer.parseInt(produto.get(Produto.Coluna.ID.getNomeColuna()));
        int idUsuario = Integer.parseInt(produto.get(Produto.Coluna.IDUSUARIO.getNomeColuna()));
        int idLoja = Integer.parseInt(produto.get(Produto.Coluna.IDLOJA.getNomeColuna()));

        double preco = Double.parseDouble(produto.get(Produto.Coluna.PRECO.getNomeColuna()));
        double valorFrete = Double.parseDouble(produto.get(Produto.Coluna.VALOR_FRETE.getNomeColuna()));
        double valorArrecadado = Double.parseDouble(produto.get(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna()));

        String nome = produto.get(Produto.Coluna.NOME.getNomeColuna());
        String link = produto.get(Produto.Coluna.LINK.getNomeColuna());
        String url_foto = produto.get(Produto.Coluna.URL_FOTO.getNomeColuna());
        String descricao = produto.get(Produto.Coluna.DESCRICAO.getNomeColuna());

        // Especificacao_has_Produto especificacao_has_ProdutoDAO = new Especificacao_has_Produto();
        // ArrayList<Especificacao> especificacoes = this.especificacao_has_Produto_DAO.selectTodasEspecificacoesDoProduto(id);

        // Tag_has_Produto tag_has_Produto = new Tag_has_Produto();
        // ArrayList<Tag> tags = this.tag_has_Produto_DAO.selectTodasTagsDoProduto(id);

        ProdutoEletronico produtoEletronico = new ProdutoEletronico(id, descricao, nome, preco, link, url_foto, valorArrecadado, valorFrete, categoria, null, null, idUsuario, idLoja, null, null);
    
        return produtoEletronico;
    }

    /* 
     * Método responsável por receber um resultSet(informação vinda do banco de dados) e montar um produto
     */
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
                produto.put(Produto.Coluna.IDUSUARIO.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.IDUSUARIO.getNomeColuna())));
                produto.put(Produto.Coluna.IDLOJA.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.IDLOJA.getNomeColuna())));
    
                /* Strings */
                produto.put(Produto.Coluna.NOME.getNomeColuna(), resultSet.getString(Produto.Coluna.NOME.getNomeColuna()));
                produto.put(Produto.Coluna.DESCRICAO.getNomeColuna(), resultSet.getString(Produto.Coluna.DESCRICAO.getNomeColuna()));
                produto.put(Produto.Coluna.LINK.getNomeColuna(), resultSet.getString(Produto.Coluna.LINK.getNomeColuna()));
                produto.put(Produto.Coluna.URL_FOTO.getNomeColuna(), resultSet.getString(Produto.Coluna.URL_FOTO.getNomeColuna()));
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

    /* 
     * Método responsável por receber um resultSet(informação vinda do banco de dados) e montar um produto
     */
    public static Map<String, String> montarProdutoMap(ResultSet resultSet)
    {
         /* 
         * Montando produtos
         */
        Map<String,String> produto = new HashMap<>();
        try
        {

            if(resultSet.next())
            {
                /* Inteiros */
                produto.put(Produto.Coluna.ID.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.ID.getNomeColuna())));
                produto.put(Produto.Coluna.IDUSUARIO.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.IDUSUARIO.getNomeColuna())));
                produto.put(Produto.Coluna.IDLOJA.getNomeColuna(), Integer.toString(resultSet.getInt(Produto.Coluna.IDLOJA.getNomeColuna())));
    
    
                /* Strings */
                produto.put(Produto.Coluna.NOME.getNomeColuna(), resultSet.getString(Produto.Coluna.NOME.getNomeColuna()));
                produto.put(Produto.Coluna.DESCRICAO.getNomeColuna(), resultSet.getString(Produto.Coluna.DESCRICAO.getNomeColuna()));
                produto.put(Produto.Coluna.LINK.getNomeColuna(), resultSet.getString(Produto.Coluna.LINK.getNomeColuna()));
                produto.put(Produto.Coluna.URL_FOTO.getNomeColuna(), resultSet.getString(Produto.Coluna.URL_FOTO.getNomeColuna()));
                produto.put(Produto.Coluna.CATEGORIA.getNomeColuna(), resultSet.getString(Produto.Coluna.CATEGORIA.getNomeColuna()));
    
    
                /* Doubles */
                produto.put(Produto.Coluna.PRECO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.PRECO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_FRETE.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_FRETE.getNomeColuna())));

                return produto;
            }
        }
        catch(SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }

        return null;
    }

    /* 
     * Este método retorna um ArrayList contendo todos os produtos de um usuário passado por parâmetro
     */
    public ArrayList<Integer> selectTodosProdutosDoUsuario(int idUsuario)
    {
        /* 
         * Montando condição
         */
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.IDUSUARIO.getNomeColuna(), Integer.toString(idUsuario));

        String instrucao = SQLiteTableManager.select(Produto.getNomeTabela(), Produto.Coluna.ID.getNomeColuna(), condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> produtos = new ArrayList<Integer>();
        try
        {
            while(resultSet.next())
            {
                produtos.add(resultSet.getInt(Produto.Coluna.ID.getNomeColuna()));
            }

            return produtos;
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
     * Método responsável por contar todos os produtos de um usuário, com base no id do usario passado por parâmetro
     */
    public int contarProdutosDoUsuario(int idUsuario)
    {
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Produto.getNomeTabela() + "." + Produto.Coluna.IDUSUARIO.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(idUsuario); 
        String condicao =  StringManager.inserirIgualdade(valorEsquerdaIgualdade,valorDireitaIgualdade);

        String instrucao =SQLiteTableManager.count(Produto.getNomeTabela(), condicao, "*");

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        int resultado = 0;

        try
        {   
            if(resultSet.next())
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

    public ArrayList<Integer> selectProdutosCadastradosRecentementePorUser(int qtdProdutosCadastrados, int idUsuario)
    {
        /* SELECT * FROM Produto WHERE Produto.Usuario_idUsuario = Y ORDER BY idProduto DESC LIMIT X; */
        String qtd = Integer.toString(qtdProdutosCadastrados);

        // String instrucao = SQLiteTableManager.selectOrderByLimitDec(Produto.getNomeTabela(), Produto.Coluna.ID.getNomeColuna(), qtd);
        
        /* SELECT Produto.idProduto
            FROM Produto
            WHERE Produto.Usuario_idUsuario = X
            ORDER BY Produto.idProduto DESC LIMIT Y;
        */
        String instrucao = "SELECT Produto.idProduto FROM Produto WHERE Produto.Usuario_idUsuario = " + String.valueOf(idUsuario) + " ORDER BY Produto.idProduto DESC LIMIT " + qtd;

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> produtos = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                produtos.add(resultSet.getInt("idProduto"));
            }

            return produtos;
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
     * Método responsável por contar todos os produtos de determinada categoria de um usuário, cujo id 
     * é passado por parâmetro
     */
    public int contarProdutosCategorizadosDoUsuario(int idUsuario, String categoria)
    {
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Produto.getNomeTabela() + "." + Produto.Coluna.IDUSUARIO.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(idUsuario); 
        String condicao1 =  StringManager.inserirIgualdade(valorEsquerdaIgualdade,valorDireitaIgualdade);
        
        valorEsquerdaIgualdade = Produto.getNomeTabela() + "." + Produto.Coluna.CATEGORIA.getNomeColuna();
        valorDireitaIgualdade = StringManager.formatarString(categoria);
        String condicao2 = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao =SQLiteTableManager.count(Produto.getNomeTabela(), condicao, "*");

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        int resultado = 0;

        try
        {   
            if(resultSet != null)
            {
                resultado = resultSet.getInt(1);

                return resultado;
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

    public ArrayList<Integer> selectProdutosCategorizadosDoUsuario(int idUsuario, String categoria)
    {
        categoria = StringManager.formatarString(categoria);

        String instrucao = "SELECT Produto.idProduto FROM Produto WHERE Produto.Usuario_idUsuario = " + String.valueOf(idUsuario) + " AND Produto.categoria = " + categoria;

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> resultados = new ArrayList<Integer>();

        try
        {   
            if(resultSet != null)
            {
                resultados.add(resultSet.getInt("idProduto"));
            }

            return resultados;
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
     * Método responsável por retornar os x produtos cadastrados mais recentemente no banco de dados
     */
    public ArrayList<Produto> selectProdutosCadastradosRecentemente(int qtdProdutosCadastrados)
    {
     
        /* SELECT * FROM Produto ORDER BY idProduto DESC LIMIT X; */
        String qtd = Integer.toString(qtdProdutosCadastrados);

        String instrucao = SQLiteTableManager.selectOrderByLimitDec(Produto.getNomeTabela(), Produto.Coluna.ID.getNomeColuna(), qtd);
        
        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Produto> produtos = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                produtos.add(montarProduto(resultSet));
            }

            return produtos;
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
     * Método responsável por buscar produto pelo nome
     */
    public ArrayList<Integer> selectProdutosPorColuna(String coluna, String valorAtributo, int idUsuario)
    {
        valorAtributo = "'%" + valorAtributo + "%'";

        String instrucao = "SELECT Produto.idProduto FROM Produto WHERE " + coluna + " LIKE " + valorAtributo + " AND Produto.Usuario_idUsuario = " + String.valueOf(idUsuario);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Integer> produtos = new ArrayList<Integer>();
        try
        {
            while(resultSet.next())
            {
                produtos.add(resultSet.getInt(Produto.Coluna.ID.getNomeColuna()));
            }

            return produtos;
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
     * Método responsável por buscar produto pelo nome
     */
    public int countProdutosPorColuna(String coluna, String valorAtributo, int idUsuario)
    {
        valorAtributo = "'%" + valorAtributo + "%'";

        String instrucao = "SELECT COUNT(*) FROM Produto WHERE " + coluna + " LIKE " + valorAtributo + " AND Produto.Usuario_idUsuario = " + String.valueOf(idUsuario) + " GROUP BY Produto.idProduto";

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        int produtos = 0;
        try
        {
            while(resultSet.next())
            {
                produtos = resultSet.getInt(1);
            }

            return produtos;
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
     * Função de adicionar um produto
     */

    public void addProduto(Produto produto)
    {
        // Adicionando na tabela de produtos
        this.insert(produto);
        Produto produtoFromDB = this.selectProdutosCadastradosRecentemente(1).get(0);
        
        // Adicionando na tabela de tag_has_produto
        produto.getTags().forEach(tag -> {
            tag_has_Produto_DAO.insert(tag, produtoFromDB);
        });

        // Adicionando na tabela especificacao_hs_produto
        produto.getEspecificacoes().forEach(especificacao -> {
            especificacao_has_Produto_DAO.insert(especificacao, produtoFromDB);
        });
    }

    /* 
     * Função para pegar atributo x de um produto
     */
    public String getColunaDeProduto(String coluna, int idProduto)
    {
        String result = null;

        /* 
         * Montando condição
         */

        String instrucao = "SELECT " + coluna + " FROM Produto WHERE Produto.idProduto = " + String.valueOf(idProduto);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        try
        {
            while(resultSet.next())
            {
                result = resultSet.getString(coluna);
            }

            if (result.equals("null"))
            {
                return null;
            }

            return result;
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
     * Funções de update para cada atributo da classe produto
     */

    public void updateIdUsuario(Produto produto, int newIdUsuario)
    {
        this.updateInt(produto, Produto.Coluna.IDUSUARIO.getNomeColuna(), newIdUsuario);
    }

    public void updateIdLoja(Produto produto, int newIdLoja)
    {
        this.updateInt(produto, Produto.Coluna.IDLOJA.getNomeColuna(), newIdLoja);
    }

    public void updateDescricao(Produto produto, String newDescricao)
    {
        this.updateString(produto, Produto.Coluna.DESCRICAO.getNomeColuna(), newDescricao);
    }

    public void updateNome(Produto produto, String newNome)
    {
        this.updateString(produto, Produto.Coluna.NOME.getNomeColuna(), newNome);
    }

    public void updateLink(Produto produto, String newLink)
    {
        this.updateString(produto, Produto.Coluna.LINK.getNomeColuna(), newLink);
    }

    public void updateUrl_foto(Produto produto, String newUrl_foto)
    {
        this.updateString(produto, Produto.Coluna.URL_FOTO.getNomeColuna(), newUrl_foto);
    }

    public void updateCategoria(Produto produto, String newCategoria)
    {
        this.updateString(produto, Produto.Coluna.CATEGORIA.getNomeColuna(), newCategoria);
    }

    public void updatePreco(Produto produto, double newPreco)
    {
        this.updateDouble(produto, Produto.Coluna.PRECO.getNomeColuna(), newPreco);
    }

    public void updateValorArrecadado(Produto produto, double newValorArrecadado)
    {
        this.updateDouble(produto, Produto.Coluna.VALOR_ARRECADADO.getNomeColuna(), newValorArrecadado);
    }

    public void updateValorFrete(Produto produto, double newValorFrete)
    {
        this.updateDouble(produto, Produto.Coluna.VALOR_FRETE.getNomeColuna(), newValorFrete);
    }

    public void updateAll(Produto produtoAtual, Produto novoProduto)
    {
        updateValorArrecadado(produtoAtual, novoProduto.getValorArrecadado());
        updateValorFrete(produtoAtual, novoProduto.getValorFrete());
        updateIdUsuario(produtoAtual, novoProduto.getIdUsuario());
        updateDescricao(produtoAtual, novoProduto.getDescricao());
        updateCategoria(produtoAtual, novoProduto.getCategoria());
        updateUrl_foto(produtoAtual, novoProduto.getUrl_foto());
        updateIdLoja(produtoAtual, novoProduto.getIdLoja());
        updatePreco(produtoAtual, novoProduto.getPreco());
        updateNome(produtoAtual, novoProduto.getNome());
        updateLink(produtoAtual, novoProduto.getLink());
    }

    /*
     * Atributos
     */

    Especificacao_has_Produto especificacao_has_Produto_DAO = new Especificacao_has_Produto();
    Tag_has_Produto tag_has_Produto_DAO = new Tag_has_Produto();

}


