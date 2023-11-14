package utils;

import java.util.ArrayList;

/* Essa classe é responsável por montar e formatar strings para envio e recebimento de querys */
public class StringManager 
{

    /* 
     * CONSTRUTOR
     */
    StringManager(){};

    /* 
     * FUNÇÕES
    */


    /* 
     * Esse método recebe um array de dados que serão do tipo TEXT, VARCHAR, etc (Strings no caso), podendo ser tanto valores, como atributos
     * e irá concatenar eles em uma única string
     * 
     * Exemplo:
     * Strings[0] = "'Luis'"
     * Strings[1] = "'Glenda'"
     * Strings[2] = "'Kalvin'"
     * O retorno dessa função será = "'Luis', 'Glenda', 'Kalvin'"
     */
    public static String montarString(ArrayList<String> strings)
    {
        String texto = new String();
        for(int i = 0; i < strings.size()-1; i++)
        {
            texto =  texto + strings.get(i) + ", ";
        }

        texto = texto + strings.get(strings.size()-1);

        return texto;
    }

    /* 
     * Esse método é responsável por formtar uma string única adicionando aspas simples, como é pedido na sintaxe do SQLITE
     * Exemplo:
     * "Kalvin" -> "'Kalvin'"
     */
    public static String formatarString(String texto)
    {
        String stringFormatada = new String();
        stringFormatada = "'" + texto + "'";

        return stringFormatada;
    }

    /* 
     * Esse método é responsável por formtar um array de strings adicionando aspas simples, como é pedido na sintaxe do SQLITE
     * Exemplo: 
     * Texto[0] = "Kalvin" -> "'Kalvin'"
     * Texto[1] = "Helano" -> "'Helano'"
     */
    public static ArrayList<String> formatarString(ArrayList<String> strings)
    {
        ArrayList<String> stringsFormatadas = new ArrayList<String>();
        for(int i = 0; i < strings.size(); i++)
        {
            stringsFormatadas.add("'" + strings.get(i) + "'");
        }
        
        return stringsFormatadas;
    }

    /* 
     * Esse método é responsável por formatar um array de inteiros e transformá-los em string
     */
    public static ArrayList<String> formatarInt(ArrayList<Integer> inteiros)
    {
        ArrayList<String> inteirosFormatados = new ArrayList<String>();
        for(int i = 0; i < inteiros.size(); i++)
        {
            inteirosFormatados.add(Integer.toString(inteiros.get(i)));
        }
        
        return inteirosFormatados;
    }

    /* 
     * Esse método é responsável por formatar um array de doubles e transformá-los em string
     */
    public static ArrayList<String> formatarDouble(ArrayList<Double> doubles)
    {
        ArrayList<String> doublesFormatados = new ArrayList<String>();
        for(int i = 0; i < doubles.size(); i++)
        {
            doublesFormatados.add(Double.toString(doubles.get(i)));
        }
        
        return doublesFormatados;
    }
   

    /* 
     * Método responsável por receber um array de strings NÃO FORMATADAS e inserir vírgulas 
     */
    public static ArrayList<String> inserirVirgulas(ArrayList<String> strings)
    {
        ArrayList<String> stringsFormatadas = new ArrayList<String>();
        for(int i = 0; i < strings.size(); i++)
        {
            stringsFormatadas.add(strings.get(i) + ",");
        }

        return stringsFormatadas;

    }

    /* 
     * Método responsável por receber uma coluna e um valor e insere uma igualdade entre eles
     * 
     * Exemplo:
     * coluna = nome
     * novoValor = 'teste'
     * 
     * instrucao = nome = novoValor
     */
    public static String inserirIgualdade(String coluna, String valor)
    {
        String instrucao =
        coluna + " = " + valor;

        return instrucao;
    }

    /* 
     * Método responsável por receber um array de colunas e valores e insere uma igualdade entre eles
     */
    public static String inserirIgualdade(ArrayList<String> colunas, ArrayList<String> valores)
    {
        if(colunas.size() != valores.size())
        {
            System.out.println("Erro ao montar novos valores. A quantidade de colunas e novos valores são diferentes!");
            return null;
        }

        String instrucao = new String();
        for(int i = 0 ; i <colunas.size()-1; i++)
        {
            instrucao = instrucao + colunas.get(i) + " = " + valores.get(i) + ", ";
        }

        instrucao = instrucao + colunas.get(colunas.size()) + " = " + valores.get(valores.size());

        return instrucao;
    }

    /* 
     * Método responsável por receber duas condições e adicionar um AND entre eles
     */
    public static String inserirAnd(String condicao1, String condicao2)
    {
        String instrucao = condicao1 + " AND " + condicao2;

        return instrucao;
    }

    /* 
     * Método responsável por receber um array de condições e adicionar um AND entre elas
     */
    public static String inserirAnd(ArrayList<String> condicoes)
    {
        String instrucao = new String();
        for(int i = 0 ; i <condicoes.size()-1; i++)
        {
            instrucao = instrucao + condicoes.get(i) + " AND ";
        }

        instrucao = instrucao + condicoes.get(condicoes.size());

        return instrucao;
    }
    


}
