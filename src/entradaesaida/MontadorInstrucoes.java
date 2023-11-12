package entradaesaida;

import java.util.ArrayList;

import bancodedados.SQLiteTableManager;
import sistema.Produto;

public class MontadorInstrucoes 
{
    
    MontadorInstrucoes(){};

    public static String montarProduto(Produto produto)
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

        return instrucao;
    }



}
