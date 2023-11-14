package interfacegrafica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LojasCadastradasController extends ControllerLogged
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        super.initialize(location, resources);
        
        // Aguarda até que o processo de inicialização seja finalizado para executar
        // o comando.
        Platform.runLater(() -> 
        {
            // TODO: usar pilhas para gerenciar as páginas
            // TODO: mover esse bloco de código pra uma função separada
            /* 
             * Setando a altura do pane de acordo com a quantidade de lojas.
             * 
             * Sendo que são disponibilizadas 3 lojas por página, a cada
             * página será verificado a quantidade de lojas para determinar
             * qual será a altura do pane e não deixar espaços em branco
             * desproporcionais na página.
             */
            int quantidadeDeLojas = 2;

            double alturaPane = this.mainPane.getHeight();
            if (quantidadeDeLojas > 1)
            {
                // Criando uma nova loja teste

                Pane testePane = clonarPaneDefaultProduto(
                    "img/memoria-ram-test.png",
                    "TESTE",
                    123.05,
                    100
                );
                testePane.setLayoutX(this.defaultProdutoLoja.getLayoutX() + paneProdutoDiferencaX);
                testePane.setLayoutY(this.defaultProdutoLoja.getLayoutY());


                double defaultLojaPaneHeight = this.defaultLojaPane.getHeight();
                alturaPane += (defaultLojaPaneHeight * quantidadeDeLojas); 
                alturaPane += (this.diferencaPanesLojasY * quantidadeDeLojas);

                this.mainPane.getChildren().add(testePane);
            }

            this.mainPane.setPrefHeight(alturaPane);
            // =======================
        }
        );
    }

    @FXML
    public void botaoAddMouseOff(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.0);
    }

    @FXML
    public void botaoAddMouseOn(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.17);

        this.cursorOn(mouse);
    }

    @FXML
    public void botaoAddClicked(MouseEvent mouse)
    {
        Node source = (Node) mouse.getSource();

        source.setOpacity(0.3);

        this.cursorNormal(mouse);
    }

    @FXML
    @Override
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        Node botao = (Node) mouse.getSource();

        if (botao.getId() == this.lojasCadastradas.getId())
        {
            this.mudarSceneLojasCadastradas();
        }
        else if (botao.getId() == this.categorias.getId())
        {
            this.mudarSceneCategorias();
        }
        else if (botao.getId() == this.inicio.getId())
        {
            this.mudarSceneInicio();
        }
    }

    
    @Override
    public void mudarSceneInicio()
    {
        this.carregarNovaScene("ScreenLogged.fxml");
    }

    @Override
    public void mudarSceneCategorias()
    {   
        
    }

    @Override 
    public void mudarSceneLojasCadastradas()
    {
        this.carregarNovaScene("ScreenLojasCadastradas.fxml");
    }
    
    // TODO: inves de criar um novo painel, deixar os painéis prontos e só editar os atributos
    private Pane clonarPaneDefaultProduto(String imagePath, String novoNomeDoProduto, double valorProduto, double novoArrecadado)
    {
        Pane result = new Pane();

        result.setStyle(this.defaultProdutoLoja.getStyle());
        result.setPrefSize(this.defaultProdutoLoja.getWidth(), this.defaultProdutoLoja.getHeight());

        /* 
         * Deep copy do texto "valor arrecadado"
         */
        Text arrecadado = new Text();
        arrecadado.setStyle(this.arrecadadoPaneDefault.getStyle());
        arrecadado.setLayoutX(this.arrecadadoPaneDefault.getLayoutX());
        arrecadado.setLayoutY(this.arrecadadoPaneDefault.getLayoutY());

        /* 
         * Deep copy do texto "preço"
         */
        Text preco = new Text();
        preco.setStyle(this.precoProdutoPaneDefault.getStyle());
        preco.setLayoutX(this.precoProdutoPaneDefault.getLayoutX());
        preco.setLayoutY(this.precoProdutoPaneDefault.getLayoutY());

        /* 
         * Deep copy do texto com nome do produto
         */
        Text nome = new Text();
        nome.setStyle(this.nomeProdutoPaneDefault.getStyle());
        nome.setLayoutX(this.nomeProdutoPaneDefault.getLayoutX());
        nome.setLayoutY(this.nomeProdutoPaneDefault.getLayoutY());
        nome.setText(novoNomeDoProduto);

        /* 
         * Deep copy do texto "faltam"
         */
        Text faltam = new Text();
        faltam.setStyle(this.faltamPaneDefault.getStyle());
        faltam.setLayoutX(this.faltamPaneDefault.getLayoutX());
        faltam.setLayoutY(this.faltamPaneDefault.getLayoutY());

        /* 
         * Deep copy do texto do preço de "faltam"
         */
        Text precoFaltam = new Text();
        precoFaltam.setStyle(this.precoFaltam.getStyle());
        precoFaltam.setLayoutX(this.precoFaltam.getLayoutX());
        precoFaltam.setLayoutY(this.precoFaltam.getLayoutY());
        
        double diferenca = valorProduto - novoArrecadado;

        if (diferenca < 0)
        {
            precoFaltam.setText("R$ 0,0");
        }
        else
        {
            precoFaltam.setText("R$ " + String.valueOf(diferenca));
        }

        /* 
         * Deep copy do texto do preço de "arrecadado"
         */
        Text precoArrecadado = new Text();
        precoArrecadado.setStyle(this.precoArrecadado.getStyle());
        precoArrecadado.setLayoutX(this.precoArrecadado.getLayoutX());
        precoArrecadado.setLayoutY(this.precoArrecadado.getLayoutY());
        precoArrecadado.setText("R$ " + String.valueOf(novoArrecadado));

        /* 
         * Deep copy do texto do preço de "arrecadado"
         */
        Text precoProduto = new Text();
        precoProduto.setStyle(this.precoProduto.getStyle());
        precoProduto.setLayoutX(this.precoProduto.getLayoutX());
        precoProduto.setLayoutY(this.precoProduto.getLayoutY());
        precoProduto.setText("R$ " + String.valueOf(valorProduto));

        /* 
         * Deep copy das propriedades da imagem do produto
         */
        ImageView fotoPane = new ImageView();
        // Image image = new Image(imagePath);
        // fotoPane.setImage(image);
        fotoPane.setStyle(this.fotoPaneDefault.getStyle());
        fotoPane.setLayoutX(this.fotoPaneDefault.getLayoutX());
        fotoPane.setLayoutY(this.fotoPaneDefault.getLayoutY());
        
        
        /* 
         * Adicionando os nodes dentro do Pane
         */
        result.getChildren().add(arrecadado);
        result.getChildren().add(preco);
        result.getChildren().add(nome);
        result.getChildren().add(faltam);
        result.getChildren().add(fotoPane);
        result.getChildren().add(precoFaltam);
        result.getChildren().add(precoArrecadado);
        result.getChildren().add(precoProduto);

        return result;
    }
    
    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    /* 
     *    Botões
     */

    @FXML
    private Button adicionarProduto;

    @FXML
    private Button adicionarLoja;

    /* 
     *    Panes
     */

    @FXML
    private AnchorPane defaultLojaPane;

    @FXML
    private Pane defaultProdutoLoja;

    /* 
     *    Texto
     */

    @FXML
    private Text arrecadadoPaneDefault;

    @FXML
    private Text faltamPaneDefault;

    @FXML
    private Text precoProdutoPaneDefault;

    @FXML
    private Text nomeProdutoPaneDefault;

    @FXML 
    private Text precoProduto;

    @FXML 
    private Text precoArrecadado;

    @FXML 
    private Text precoFaltam;

    /* 
     *    Imagens
     */

    @FXML
    private ImageView fotoPaneDefault;

    /* 
     * 
     *      ATRIBUTOS INTERNOS  
     * 
     */

    // Constante com o espaço em pixels que separa um produto do outro
    // na visualização do pane da loja.
    private final double paneProdutoDiferencaX = 213;

    // Constante com o espaço em pixels que separa um Pane de uma loja 
    // de outro.
    private final double diferencaPanesLojasY = 50;
}
