package interfacegrafica.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class Controller 
{
    /**
     * Função responsável por abrir o navegador de arquivos para que o usuário
     * selecione uma imagem.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public String abrirFileChooser(ActionEvent event)
    {
        FileChooser filechooser = new FileChooser();
        String filePath = null;

        filechooser.setTitle("Escolha uma foto");
        filechooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg"),
            new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*")
        );

        File selectedFile = filechooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());

        if (selectedFile != null)
        {
            filePath = selectedFile.getAbsolutePath();
        }

        return filePath;
    }

    /**
     * Função para copiar uma imagem num diretório qualquer para o diretório do projeto.
     * 
     * @param caminhoImagemOriginal
     * O caminho absoluto da imagem original
     * 
     * @param caminhoPastaDestino
     * O caminho relativo para a pasta de destino
     */
    public String copiarImagem(String caminhoImagemOriginal, String caminhoPastaDestino, int idUsuario, String nomeUsuario) throws IOException
    {
        Path origem = Paths.get(caminhoImagemOriginal);
        
        String novoNomeArquivo = String.valueOf(idUsuario) + "_" + nomeUsuario + "_" + "userprofile" + this.obterExtensaoDoArquivo(origem.getFileName().toString());
        
        Path destino = Paths.get(caminhoPastaDestino, novoNomeArquivo);
        
        /* copia o arquivo para a pasta de destino */
        Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

        return novoNomeArquivo;
    }

    /** 
     * Função para retornar a extensão de um arquivo.
     * A função retornará a extensão acompanhada do ".". Ex: .png, .jpg...
     * 
     * @param nomeDoArquivo
     * Nome do arquivo que a extensão será retornada.
     */
    public String obterExtensaoDoArquivo(String nomeDoArquivo)
    {
        int ultimoPonto = nomeDoArquivo.lastIndexOf(".");

        return nomeDoArquivo.substring(ultimoPonto);
    }
}
