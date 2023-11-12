package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserArquivo 
{
    public static String lerArquivo(String caminhoArquivo)
    {
        StringBuilder arquivo = new StringBuilder();
        try
        {
            Scanner scanner = new Scanner(new File(caminhoArquivo));
            
            while(scanner.hasNextLine())
            {
                arquivo.append(scanner.nextLine());
            }

            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }

        System.out.println(arquivo);
        return arquivo.toString();
    }

    public static ArrayList<String> lerScriptSQL(String caminhoArquivo) 
    {
        ArrayList<String> instrucoes = new ArrayList<>();

        
        try
        {
            Scanner scanner = new Scanner(new File(caminhoArquivo));

            StringBuilder instrucaoAtual = new StringBuilder();

            
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                instrucaoAtual.append(line).append("\n");
                
                if (line.trim().endsWith(";")) 
                {
                    instrucoes.add(instrucaoAtual.toString().trim());
                    
                    instrucaoAtual.setLength(0); 
                }
            }

            scanner.close();
        }         
        catch(FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }


        return instrucoes;
    }
}    

