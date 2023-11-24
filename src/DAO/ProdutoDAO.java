package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.DAOMTM.Especificacao_has_Produto;
import DAO.DAOMTM.Tag_has_Produto;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.ProdutoFerramenta;
import sistema.ProdutoLivro;
import sistema.ProdutoMobilia;
import sistema.ProdutoModa;
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
        arrayColunas.add(Produto.Coluna.DESCRICAO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.URL_FOTO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.CATEGORIA.getNomeColuna());
        /* loja */
        arrayColunas.add(Produto.Coluna.PRECO.getNomeColuna()); 
        arrayColunas.add(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna());
        arrayColunas.add(Produto.Coluna.VALOR_FRETE.getNomeColuna());
        arrayColunas.add(Produto.Coluna.IDUSUARIO.getNomeColuna());

        /* 
         * Montando os valores referente a cada coluna
         */
        /* Valores do tipo TEXT */
        valoresString.add(produto.getNome());
        valoresString.add(produto.getLink());
        valoresString.add(produto.getDescricao());
        valoresString.add(produto.getUrl_foto());
        valoresString.add(produto.getCategoria());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* Valores do tipo Integer */
        valoresInteger.add(produto.getIdUsuario());
        
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

        if(categoria.equals(Produto.Categorias.ELETRONICO.getCategoria()) || categoria.equals(Produto.Categorias.ELETRODOMESTICO.getCategoria()))
        {
            ProdutoEletronico produtoEletronico = new ProdutoEletronico(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
            valorFrete, categoria, null, null, idUsuario, idLoja);
            
            return produtoEletronico;
        }
        else if(categoria.equals(Produto.Categorias.FERRAMENTA.getCategoria()))
        {
            ProdutoFerramenta produtoFerramenta = new ProdutoFerramenta(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
            valorFrete, categoria, null, null, idUsuario, idLoja);

            return produtoFerramenta;
        }
        else if(categoria.equals(Produto.Categorias.LIVRO.getCategoria()))
        {
            String autor = produto.get(ProdutoLivro.Coluna.AUTOR.getNomeColuna());
            String genero = produto.get(ProdutoLivro.Coluna.GENERO.getNomeColuna());

            ProdutoLivro produtoLivro = new ProdutoLivro(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
            valorFrete, categoria, null, null, autor, genero, idUsuario, idLoja);
            
            return produtoLivro;
        }
        else if(categoria.equals(Produto.Categorias.MOBILIA.getCategoria()) || categoria.equals(Produto.Categorias.CASAEJARDIM.getCategoria()) || categoria.equals(Produto.Categorias.AUTOMOTIVO.getCategoria()))
        {
                
            String material = produto.get(ProdutoMobilia.Coluna.MATERIAL.getNomeColuna()); 
            String cor = produto.get(ProdutoMobilia.Coluna.COR.getNomeColuna());
            double altura = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.ALTURA.getNomeColuna()));
            double largura = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.LARGURA.getNomeColuna())); 
            double comprimento = Double.parseDouble(produto.get(ProdutoMobilia.Coluna.COMPRIMENTO.getNomeColuna()));


            ProdutoMobilia produtoMobilia = new ProdutoMobilia(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
            valorFrete, categoria, null, null, material, cor, altura, largura, comprimento, idUsuario, idLoja );

            return produtoMobilia;
        }
        else if(categoria.equals(Produto.Categorias.ROUPA.getCategoria()) || categoria.equals(Produto.Categorias.ACESSORIO.getCategoria()) || categoria.equals(Produto.Categorias.CALCADO.getCategoria()))
        {
            String tamanho = produto.get(ProdutoModa.Coluna.TAMANHO.getNomeColuna()); 
            String cor = produto.get(ProdutoModa.Coluna.COR.getNomeColuna());
            String material = produto.get(ProdutoModa.Coluna.MATERIAL.getNomeColuna());

            ProdutoModa produtoRoupa = new ProdutoModa(id, descricao, nome, preco, link, url_foto, valorArrecadado, 
            valorFrete, categoria, null, null, tamanho, cor, material, idUsuario, idLoja);

            return produtoRoupa;
        }
        else
        {
            System.out.println("Categoria inexistente!");
            
            return null;
        }
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
                produto.put(ProdutoLivro.Coluna.AUTOR.getNomeColuna(), resultSet.getString(ProdutoLivro.Coluna.AUTOR.getNomeColuna()));
                produto.put(ProdutoLivro.Coluna.GENERO.getNomeColuna(), resultSet.getString(ProdutoLivro.Coluna.GENERO.getNomeColuna()));
                produto.put(ProdutoMobilia.Coluna.MATERIAL.getNomeColuna(), resultSet.getString(ProdutoMobilia.Coluna.MATERIAL.getNomeColuna()));       
                produto.put(ProdutoMobilia.Coluna.COR.getNomeColuna(), resultSet.getString(ProdutoMobilia.Coluna.COR.getNomeColuna()));
    
    
                /* Doubles */
                produto.put(Produto.Coluna.PRECO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.PRECO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_ARRECADADO.getNomeColuna())));
                produto.put(Produto.Coluna.VALOR_FRETE.getNomeColuna(), Double.toString(resultSet.getDouble(Produto.Coluna.VALOR_FRETE.getNomeColuna())));
                produto.put(ProdutoMobilia.Coluna.ALTURA.getNomeColuna(),Double.toString(resultSet.getDouble(ProdutoMobilia.Coluna.ALTURA.getNomeColuna())));
                produto.put(ProdutoMobilia.Coluna.LARGURA.getNomeColuna(),Double.toString(resultSet.getDouble(ProdutoMobilia.Coluna.LARGURA.getNomeColuna())));
                produto.put(ProdutoMobilia.Coluna.COMPRIMENTO.getNomeColuna(),Double.toString(resultSet.getDouble(ProdutoMobilia.Coluna.COMPRIMENTO.getNomeColuna())));

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
     * Este método retorna um ArrayList contendo todos os produtos de um usuário passado por parâmetro
     */
    public ArrayList<Produto> selectTodosProdutosDoUsuario(int idUsuario)
    {
        /* 
         * Montando condição
         */
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.IDUSUARIO.getNomeColuna(), Integer.toString(idUsuario));

        String instrucao = SQLiteTableManager.select(Produto.getNomeTabela(), "*", condicao);

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
    public ArrayList<Produto> selectProdutosPorNome(String nome, int quantidadeRetornada)
    {
        nome = StringManager.formatarString(nome);
        
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.NOME.getNomeColuna(), nome);

        /* SELECT * FROM Produto WHERE nome = 'nome' LIMIT X; */

        String instrucao = SQLiteTableManager.selectLimit(Produto.getNomeTabela(), "*", condicao, Integer.toString(quantidadeRetornada));

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

    /*
     * Atributos
     */

    Especificacao_has_Produto especificacao_has_Produto_DAO = new Especificacao_has_Produto();
    Tag_has_Produto tag_has_Produto_DAO = new Tag_has_Produto();

}


