package entradaesaida;

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
     * Método responsável por receber uma coluna e um novo valor para formar poder ser inserido no SET
     * 
     * Exemplo:
     * coluna = nome
     * novoValor = 'teste'
     * 
     * instrucao = nome = novoValor
     */
    public static String montarNovoValor(String coluna, String novoValor)
    {
        String instrucao =
        coluna + " = " + novoValor;

        return instrucao;
    }

    /* 
     * Método responsável por receber um array de colunas e novos valores e formatá-los para serem inseridos no SET
     */
    public static String montarNovoValor(ArrayList<String> colunas, ArrayList<String> novosValores)
    {
        if(colunas.size() != novosValores.size())
        {
            System.out.println("Erro ao montar novos valores. A quantidade de colunas e novos valores são diferentes!");
            return null;
        }

        String instrucao = new String();
        for(int i = 0 ; i <colunas.size()-1; i++)
        {
            instrucao = instrucao + colunas.get(i) + " = " + novosValores.get(i) + ", ";
        }

        instrucao = instrucao + colunas.get(colunas.size()) + " = " + novosValores.get(novosValores.size());

        return instrucao;
    }

}
