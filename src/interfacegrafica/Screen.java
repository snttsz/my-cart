package interfacegrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Classe abstrata responsável por definir as implementações padrões da janela
 * de uma interface gráfica quando um usuário está logado.
 * 
 * @author Glenda
 * 
 */
public abstract class Screen extends Application
{
    /**
     * Construtor padrão da classe Screen.
     * 
     * @param tituloJanela 
     * Título que a janela apresentará.
     * 
     * @param fxmlname 
     * Nome do arquivo .fxml equivalente à classe.
     */
    public Screen(String tituloJanela, String fxmlname)
    {
        super();
        this.tituloJanela = tituloJanela;
        this.fxmlname = fxmlname;
    }

    @Override
    public void start(Stage stage) 
    {
        this.stage = stage;

        try
        {   
            Parent root = FXMLLoader.load(getClass().getResource(pathResources + fxmlname));
            Scene scene = new Scene(root, larguraJanela, alturaJanela);
            
            stage.setResizable(false);
            stage.setTitle(this.tituloJanela);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return Largura da janela em pixels.
     */
    public double getLarguraJanela() 
    {
        return this.larguraJanela;
    }

    /**
     * @return Altura da janela em pixels.
     */
    public double getAlturaJanela() 
    {
        return this.alturaJanela;
    }

    /**
     * @return Título que a janela apresentará.
     */
    public String getTituloJanela() 
    {
        return this.tituloJanela;
    }

    /**
     * @param tituloJanela 
     * Título que a janela apresentará.
     */
    public void setTituloJanela(String tituloJanela) 
    {
        this.tituloJanela = tituloJanela;
    }

    /**
     * @return Nome do arquivo .fxml equivalente à classe.
     */
    public String getFxmlname() 
    {
        return fxmlname;
    }

    /**
     * @param fxmlname 
     * Nome do arquivo .fxml equivalente à classe.
     */
    public void setFxmlname(String fxmlname) 
    {
        this.fxmlname = fxmlname;
    }

    /**
     * Função responsável por fechar a janela. Deve ser chamada somente após a
     * definição da variável stage.
     */
    public void closeScreen()
    {
        try
        {
            stage.close();
        }
        catch (Exception e)
        {
            System.out.println("Ocorreu um erro ao fechar a janela. Verifique se a variável stage foi inicializada corretamente antes de utilizar esta função.");
            e.printStackTrace();
        }
    }

    protected Stage stage;
    protected String tituloJanela;
    
    // Constantes com as definições de resolução da janela
    protected final double larguraJanela = 1096;
    protected final double alturaJanela = 575;

    // Constante com o caminho pra pasta resources, onde está os arquivos .fxml
    protected final String pathResources = "../resources/";

    // Nome do arquivo .fxml equivalente à classe
    protected String fxmlname;
}
