package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import sistema.Produto;
import utils.StringManager;

public class Especificacao_has_Produto  extends DAOMTM<Especificacao, Produto>
{

    @Override
    public void delete(Especificacao especificacao, Produto produto) 
    {

        String condicao1 = StringManager.inserirIgualdade(Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna(), Integer.toString(produto.getId())); 

        String condicao2 = StringManager.inserirIgualdade(Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna(), Integer.toString(especificacao.getId())); 

        String condicao = StringManager.inserirAnd(condicao1, condicao2);

        String instrucao = SQLiteTableManager.delete(nomeTabela, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void insert(Especificacao especificacao, Produto produto) 
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
        arrayColunas.add(Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna());
        arrayColunas.add(Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna());

        /* 
        * Montando valores
        */
        valoresInteger.add(especificacao.getId());
        valoresInteger.add(produto.getId());

        ArrayList<String> valoresIntegerNormalizados = StringManager.formatarInt(valoresInteger);


        /* 
        * Montando instrução
        */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresIntegerNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Especificacao_has_Produto.nomeTabela, colunas, valores);
        
        SQLiteConnectionManager.enviarQuery(instrucao);        
    }

    public ArrayList<Especificacao> selectTodasEspecificacoesDoProduto(Produto produto)
    {
        /* 
        * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Especificacao.getNomeTabela() + "." + Especificacao.Coluna.ID.getNomeColuna();
        String atributo2 = Especificacao_has_Produto.nomeTabela + "." + Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Especificacao_has_Produto.nomeTabela + "." + Especificacao_has_Produto.Coluna.IDESPECIFICACAO.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(produto.getId());
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Especificacao.getNomeTabela(), Especificacao_has_Produto.nomeTabela, comparacaoAtributos, condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        /* 
         * Montando Especificacoes
         */
        ArrayList<Especificacao> especificacoes = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int idEspecificacao = resultSet.getInt(Especificacao.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Especificacao.Coluna.NOME.getNomeColuna());
                String valor = resultSet.getString(Especificacao.Coluna.VALOR.getNomeColuna());

                Especificacao especificacao = new Especificacao(idEspecificacao,nome,valor);

                especificacoes.add(especificacao);
            }
            
            return especificacoes;
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

    public ArrayList<Produto> selectTodosProdutosDaEspecificacao(Especificacao especificacao)
    {
        /* 
         * Montando Comparação entre atributos de duas tabelas diferentes 
         */
        String atributo1 = Produto.getNomeTabela() + "." + Produto.Coluna.ID.getNomeColuna();
        String atributo2 = Especificacao_has_Produto.nomeTabela + "." + Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna();
        String comparacaoAtributos = StringManager.inserirIgualdade(atributo1, atributo2);
        
        /* 
         * Montando condição
         */
        String valorEsquerdaIgualdade = Especificacao_has_Produto.nomeTabela + "." + Especificacao_has_Produto.Coluna.IDProduto.getNomeColuna();
        String valorDireitaIgualdade = Integer.toString(especificacao.getId());
        String condicao = StringManager.inserirIgualdade(valorEsquerdaIgualdade, valorDireitaIgualdade);

        String instrucao = SQLiteTableManager.selectAllJoin(Produto.getNomeTabela(), Especificacao_has_Produto.nomeTabela, comparacaoAtributos, condicao);

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
        IDESPECIFICACAO("Especificacao_idEspecificacao"),
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
    private static final String nomeTabela = "Especificacao_has_Produto";
    
}

