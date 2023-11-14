package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Tag;
import utils.StringManager;


public class Tag_has_Produto extends DAOMTM<Tag, Produto>
{

    @Override
    public void delete(Tag tag, Produto produto) 
    {

        String condicao1 = StringManager.inserirIgualdade(Tag_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Tag_has_Produto.Coluna.IDTAG.getNomeColuna(), Integer.toString(tag.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void insert(Tag tag, Produto produto) 
    {
        /* 
        * TODO puxar o produto do BD, se existir prossegue, se não eu crio ele antes de inserir na tabela User_has_Produto
        * 
        * O usuário não precisa pasasr por isso, pois ele sempre terá sido criado anteriormente
        */
        ArrayList<Integer> valoresInteger = new ArrayList<Integer>();

        ArrayList<String> arrayColunas = new ArrayList<String>();

        /* 
        * Adicionando colunas
        */
        arrayColunas.add(Tag_has_Produto.Coluna.IDTAG.getNomeColuna());
        arrayColunas.add(Tag_has_Produto.Coluna.IDProduto.getNomeColuna());

        /* 
        * Montando valores
        */
        valoresInteger.add(tag.getId());
        valoresInteger.add(produto.getId());

        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);


        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresIntegerNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Tag_has_Produto.nomeTabela, colunas, valores);
        
        SQLiteConnectionManager.enviarQuery(instrucao);        
    }

    public ArrayList<Tag> selectTodasTagsDoProduto(Produto produto)
    {
        /* 
        * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Tag.getNomeTabela() + "." + Tag.Coluna.ID.getNomeColuna();
        String atributo2 = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDTAG.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDProduto.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(produto.getId());
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Tag.getNomeTabela(), Tag_has_Produto.nomeTabela, comparacaoAtributos, condicao);

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

    public ArrayList<Tag> selectTodasTagsDoProduto(int idProduto)
    {
        /* 
        * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Tag.getNomeTabela() + "." + Tag.Coluna.ID.getNomeColuna();
        String atributo2 = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDTAG.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDTAG.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(idProduto);
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Tag.getNomeTabela(), Tag_has_Produto.nomeTabela, comparacaoAtributos, condicao);

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

    public ArrayList<Produto> selectTodosProdutosDaTag(Tag tag)
    {
        /* 
         * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Produto.getNomeTabela() + "." + Produto.Coluna.ID.getNomeColuna();
        String atributo2 = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDProduto.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Tag_has_Produto.nomeTabela + "." + Tag_has_Produto.Coluna.IDTAG.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(tag.getId());
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Produto.getNomeTabela(), Tag_has_Produto.nomeTabela, comparacaoAtributos, condicao);

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
     * Enum com o nome das tabelas
     */
    public enum Coluna
    {
        IDTAG("Tag_idTag"),
        IDProduto("Produto_idProduto");

        private final String nomeColuna;

        Coluna(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }
    private static final String nomeTabela = "Tag_has_Produto";
    
}
