package entradaesaida;

import java.util.ArrayList;

import bancodedados.SQLiteTableManager;
import sistema.Produto;

public class MontadorInstrucoes 
{
    
    MontadorInstrucoes(){};

    public static String InserirProduto(Produto produto)
    {

        ArrayList<String> valoresString = new ArrayList<String>();
        ArrayList<Integer> valoresInteger = new ArrayList<Integer>();
        ArrayList<Double> valoresDouble = new ArrayList<Double>();

        ArrayList<String> arrayColunas = new ArrayList<String>();
        /* 
         * Montando as colunas da tabela Produto
         */

        /* Importante seguir a ordem abaixo */
        arrayColunas.add(Produto.ColunaProduto.NOME.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.LINK.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.MARCA.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.DESCRICAO.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.URL_FOTO.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.DATA_DE_ADICAO.getNomeColuna());
        /* loja */
        arrayColunas.add(Produto.ColunaProduto.PRIORIDADE.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.DISPONIBILIDADE.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.PRECO.getNomeColuna()); 
        arrayColunas.add(Produto.ColunaProduto.VALOR_ARRECADADO.getNomeColuna());
        arrayColunas.add(Produto.ColunaProduto.VALOR_FRETE.getNomeColuna());

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

        String instrucao = SQLiteTableManager.insertTo("Produto", colunas, valores);

        System.out.println(instrucao);

        return instrucao;
    }

    public static String setNomeProduto(Produto produto, String novoNome)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.NOME.getNomeColuna(), novoNome);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setPrecoProduto(Produto produto, double novoPreco)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.PRECO.getNomeColuna(), Double.toString(novoPreco));

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setLinkProduto(Produto produto, String novoLink)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.LINK.getNomeColuna(), novoLink);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setMarcaProduto(Produto produto, String novaMarca)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.MARCA.getNomeColuna(), novaMarca);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setDataDeAdicaoProduto(Produto produto, String novaDataDeAdicao)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.DATA_DE_ADICAO.getNomeColuna(), novaDataDeAdicao);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setDisponibilidadeProduto(Produto produto, int novaDisponibilidade)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.DISPONIBILIDADE.getNomeColuna(), Integer.toString(novaDisponibilidade));

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }
    
    public static String setPrioridadeProduto(Produto produto, int novaPrioridade)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.PRIORIDADE.getNomeColuna(), Integer.toString(novaPrioridade));

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setUrlFotoProduto(Produto produto, String novaUrlFoto)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.URL_FOTO.getNomeColuna(), novaUrlFoto);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }


    public static String setValorArrecadadoProduto(Produto produto, double novoValorArrecadado)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.VALOR_ARRECADADO.getNomeColuna(), Double.toString(novoValorArrecadado));

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setDescricaoProduto(Produto produto, String novaDescricao)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.DESCRICAO.getNomeColuna(), novaDescricao);

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String setValorFrete(Produto produto, double novoValorFrete)
    {
        String colunas_valores = StringManager.inserirIgualdade(Produto.ColunaProduto.VALOR_FRETE.getNomeColuna(), Double.toString(novoValorFrete));

        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.update(produto.getNomeTabela(), colunas_valores, condicao);

        return instrucao;
    }

    public static String deleteProduto(Produto produto)
    {
        String condicao = StringManager.inserirIgualdade(Produto.ColunaProduto.ID.getNomeColuna(), Integer.toString(produto.getCodigo())); 

        String instrucao = SQLiteTableManager.delete(produto.getNomeTabela(), condicao);

        return instrucao;
    }
                

}
