package DAO;

import java.util.ArrayList;
import java.util.List;

import bancodedados.SQLiteTableManager;
import sistema.Produto;
import utils.StringManager;

public class ProdutoDAO extends DAO<Produto> 
{

    @Override
    public Produto getById(int id) {
        //to implement
        return null;
    }

    @Override
    public List<Produto> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public ProdutoDAO(){};


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

        String instrucao = SQLiteTableManager.insertTo(produto.getNomeTabela(), colunas, valores);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void updateString(Produto produto, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        System.out.println(instrucao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);

    }

    @Override
    public void updateInt(Produto produto, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void updateDouble(Produto produto, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId()));

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        
        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }

    @Override
    public void delete(Produto produto) 
    {
        String condicao = StringManager.inserirIgualdade(Produto.Coluna.ID.getNomeColuna(), Integer.toString(produto.getId())); 

        String instrucao = SQLiteTableManager.delete(produto.getNomeTabela(), condicao);

        DAO.SQLiteConnectionManager.enviarQuery(instrucao);
    }

}