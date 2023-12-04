package interfacegrafica.controllers;

import sistema.Tag;
import sistema.Especificacao;
import sistema.Loja;
import sistema.Produto;
import sistema.ProdutoEletronico;
import sistema.ProdutoFerramenta;
import sistema.ProdutoLivro;
import sistema.ProdutoMobilia;
import sistema.ProdutoModa;
import sistema.Produto.Categorias;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

import DAO.LojaDAO;
import DAO.ProdutoDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Classe responsável por definir as implementações para cadastrar um novo produto.
 * 
 * @author Glenda
 */
public class CadastrarProdutoController extends ControllerLogged
{   
    /**
     * Override do método padrão de inicialização da classe Controller do javafx.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        super.initialize(location, resources);

        Platform.runLater(() -> 
        {
            this.puxarCategorias();
            this.inicializarCamposCategoria();

            /* 
             * Se o modo de exibição for para editar o produto, insere os dados do produto no campo
             * para que sejam coletados e salvos quando o usuário solicitar.
             */
            if (ControllerLogged.editarProduto)
            {
                this.inserirDadosDoProduto();
            }
        });
    }

    /**
     * Construtor padrão da classe.
     */
    public CadastrarProdutoController()
    {
        this.tags = new ArrayList<Tag>();
        this.especificacoes = new ArrayList<Especificacao>();

        this.tagsText = new ArrayList<Text>();
        this.especificacoesText = new ArrayList<Text>();
    }

    /**
     * Função para inicializar os campos que estão associados à categoria do produto. A função
     * irá diminuir a opacidade e desabilitar os campos para que sejam habilitados de acordo
     * posteriormente.
     */
    private void inicializarCamposCategoria()
    {
        this.cor.setOpacity(0.5);
        this.cor.setDisable(true);

        this.tamanho.setOpacity(0.5);
        this.tamanho.setDisable(true);

        this.material.setOpacity(0.5);
        this.material.setDisable(true);

        this.altura.setOpacity(0.5);
        this.altura.setDisable(true);

        this.largura.setOpacity(0.5);
        this.largura.setDisable(true);

        this.comprimento.setOpacity(0.5);
        this.comprimento.setDisable(true);

        this.autor.setOpacity(0.5);
        this.autor.setDisable(true);

        this.genero.setOpacity(0.5);
        this.genero.setDisable(true);
    }

    /**
     * Função para adicionar uma tag no bloco de texto onde as tags estão sendo
     * exibidas e no array de tags que posteriormente será adicionado ao objeto
     * Produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void adicionarTagNoCampo(ActionEvent action)
    {
        /* Adicionando a tag no array de texto */
        Text tag = new Text(this.adicionarTagCampo.getText());

        if (!verificarSeTagJaExiste(this.adicionarTagCampo.getText()))
        {
            this.tagsText.add(tag);
    
            if (this.tagsText.size() > 7)
            {
                this.aumentarPainelEspecificacao();
            }
    
            /* Mostrando a especificação na tela de adicionar produtos, para que
             * fique visível ao usuário
             */
    
            tag.setText(tag.getText() + "\n");
            this.textFlowTag.getChildren().add(tag);

            this.adicionarTagCampo.clear();
        }
        else
        {
            this.abrirErroStage("Tag já cadastrada no produto!");
        }
    }

    /**
     * Função para adicionar uma especificação no bloco de texto onde as especificações 
     * estão sendo exibidas e no array de especificações que posteriormente será 
     * adicionado ao objeto Produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void adicionarEspecificacaoNoCampo(ActionEvent action)
    {
        /* Adicionando a especificação no array de texto */
        Text especificacao = new Text(this.especificacaoNome.getText() + " : " + this.especificacaoValor.getText());
        
        /* Checa se o nome da especificação já existe no produto antes de adicioná-la */
        if (!verificarSeEspecificacaoJaExiste(this.especificacaoNome.getText()))
        {
            this.especificacoesText.add(especificacao);
    
            if (this.especificacoesText.size() > 7)
            {
                this.aumentarPainelEspecificacao();
            }
    
            /* 
            * Mostrando a especificação na tela de adicionar produtos, para que
            * fique visível ao usuário
            */
    
            especificacao.setText(especificacao.getText() + "\n");
            this.textFlowEspecificacao.getChildren().add(especificacao);

            this.especificacaoNome.clear();
            this.especificacaoValor.clear();
        }
        else
        {
            this.abrirErroStage("Especificação já cadastrada no produto!");
        }
    }
    
    /**
     * Função para excluir uma tag no bloco de texto onde as tags estão sendo
     * exibidas e no array de tags que posteriormente será adicionado ao objeto
     * Produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void excluirTag(ActionEvent action)
    {
        String nomeTag = this.removerTagCampo.getText();

        /* Remover especificacao da lista */
        Iterator<Text> iterator = this.tagsText.iterator();

        while (iterator.hasNext()) 
        {
            Text tag = iterator.next();

            if (tag.getText().contains(nomeTag)) 
            {
                iterator.remove();
            }
        }

        /* Remover especificacao da tela */
        Iterator<Node> iteratorTela = this.textFlowTag.getChildren().iterator();

        while (iteratorTela.hasNext()) 
        {
            Node children = iteratorTela.next();

            if (children instanceof Text) {
                Text text = (Text) children;

                if (text.getText().contains(nomeTag)) 
                {
                    iteratorTela.remove();
                }
            }
        }

        this.removerTagCampo.clear();
    }

    /**
     * Função verificar se uma especificação ja existe no bloco de especificações
     * do produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public boolean verificarSeEspecificacaoJaExiste(String novaEspecificacaoNome)
    {
        boolean result = false;

        for (Text especificacaoText : this.especificacoesText)
        {
            if (especificacaoText.getText().contains(novaEspecificacaoNome))
            {
                result = true;
            }
        }

        return result;
    }

    /**
     * Função verificar se uma tag ja existe no bloco de tags
     * do produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public boolean verificarSeTagJaExiste(String novaTagNome)
    {
        boolean result = false;

        for (Text tagText : this.tagsText)
        {
            if (tagText.getText().contains(novaTagNome))
            {
                result = true;
            }
        }

        return result;
    }

    /**
     * Função para excluir uma especificação no bloco de texto onde as espeficiações estão sendo
     * exibidas e no array de especificações que posteriormente será adicionado ao objeto
     * Produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void excluirEspecificacao(ActionEvent action)
    {
        String nomeEspecificacao = this.removerEspecificacaoCampo.getText();

        /* Remover especificacao da lista */
        Iterator<Text> iterator = this.especificacoesText.iterator();

        while (iterator.hasNext()) 
        {
            Text especificacao = iterator.next();

            if (especificacao.getText().contains(nomeEspecificacao)) 
            {
                iterator.remove();
            }
        }

        /* Remover especificacao da tela */
        Iterator<Node> iteratorTela = this.textFlowEspecificacao.getChildren().iterator();

        while (iteratorTela.hasNext()) 
        {
            Node children = iteratorTela.next();

            if (children instanceof Text) {
                Text text = (Text) children;

                if (text.getText().contains(nomeEspecificacao)) 
                {
                    iteratorTela.remove();
                }
            }
        }

        this.removerEspecificacaoCampo.clear();
    }

    /**
     * Override da função da superclasse. Executa a função da superclasse e checa
     * se o usuário adicionou uma foto ao produto. Se sim e um botão do menu foi clicado,
     * significa que o usuário cancelou o cadastro do produto. A função irá chamar um método
     * para excluir a foto cadastrada pelo usuário.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @Override    
    public void botaoMenuClicked(MouseEvent mouse)
    {
        super.botaoMenuClicked(mouse);

        /* Excluindo foto do produto se já estiver salva */
        if (adicionouFotoProduto)
        {
            this.excluirFotoDoProduto();
        }
    }

    /**
     * Aumenta o painel de exibição de especificações.
     */
    public void aumentarPainelEspecificacao()
    {
        this.anchorEspecificacao.setPrefHeight(this.anchorEspecificacao.getHeight() + 5);
    }

    /**
     * Aumenta o painel de exibição de tags.
     */
    public void aumentarPainelTag()
    {
        this.anchorTag.setPrefHeight(this.anchorTag.getHeight() + 5);
    }

    /**
     * Cancela o cadastro do produto e checa se o usuário adicionou uma foto ao produto. A 
     * função irá chamar um método para excluir a foto cadastrada pelo usuário.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void voltarParaOInicio(ActionEvent action)
    {
        this.mudarScene("ScreenLogged.fxml");

        /* Excluindo foto do produto se já estiver salva */
        if (adicionouFotoProduto)
        {
            this.excluirFotoDoProduto();
        }
    }

    /**
     * Função para cadastrar uma foto para o produto.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void cadastrarFotoProduto(ActionEvent action)
    {
        String filePath = this.abrirFileChooser(action);

        String caminhoPastaDestino = "src/img/users";

        String nomeDaImagem = null;

        /* Gerando número random para que a imagem não seja repetida */
        Random random = new Random();
        int numeroIntervalo = random.nextInt(1, 3123123) + 1;

        try
        {
            nomeDaImagem = this.copiarImagem(filePath, caminhoPastaDestino, Controller.idUsuario, String.valueOf(numeroIntervalo));
            this.adicionouFotoProduto = true;

            String caminhoFinal = "../../img/users/" + nomeDaImagem;
            
            // Esperando o sistema terminar o processo de I/O
            while(true)
            {
                try
                {
                    Image image = new Image(getClass().getResource(caminhoFinal).toExternalForm());
                    this.fotoProdutoImg.setImage(image);
                    break;
                }
                catch (Exception e)
                {
    
                }
            }
        }
        catch(IOException e)
        {
            // e.printStackTrace();
        }
    }

    /**
     * Função para cadastrar o produto final no banco de dados, após todos
     * os campos serem devidamente preenchidos.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    public void cadastrarProdutoBD(ActionEvent action)
    {
        String nomeDoProduto = this.nomeDoProduto.getText();
        String linkDoProduto = this.linkDoProduto.getText();
        
        double valorArrecadado = Double.parseDouble(this.valorArrecadado.getText());
        double valorDoProduto = Double.parseDouble(this.valorDoProduto.getText());
        double valorDoFrete = Double.parseDouble(this.valorDoFrete.getText());

        for (Text especificacao : this.especificacoesText) 
        {
            int indexOf = especificacao.getText().lastIndexOf(":");

            String nomeEspecificacao = especificacao.getText().substring(0, indexOf - 1);
            String valorEspecificacao = especificacao.getText().substring(indexOf + 2, especificacao.getText().length());

            Especificacao novaEspecificacao = new Especificacao(nomeEspecificacao, valorEspecificacao);

            this.especificacoes.add(novaEspecificacao);
        }

        for (Text tag : this.tagsText) 
        {
            Tag novaTag = new Tag(tag.getText());

            this.tags.add(novaTag);
        }

        String categoria = this.categoriasSplitDown.getText();

        String cor = this.cor.getText();
        String material = this.material.getText();
        String autor = this.autor.getText();
        String genero = this.genero.getText();
        String descricao = this.descricao.getText();
        String tamanho = this.tamanho.getText();
        
        double altura = Double.parseDouble(this.altura.getText());
        double largura = Double.parseDouble(this.largura.getText());
        double comprimento = Double.parseDouble(this.comprimento.getText());

        String fotoDoProduto = this.fotoProdutoImg.getImage().getUrl();

        boolean canSendProductToDB = this.checarCamposObrigatorios();

        if (canSendProductToDB)
        {
            int idLoja = getIdLoja();

            if (categoria.equals(Produto.Categorias.ELETRONICO.getCategoria()))
            {
                ProdutoEletronico novoProduto = new ProdutoEletronico(descricao, nomeDoProduto, valorDoProduto, linkDoProduto, fotoDoProduto, valorArrecadado, valorDoFrete, categoria, this.especificacoes, this.tags, ControllerLogged.idUsuario, idLoja, cor, material);

                if (ControllerLogged.editarProduto)
                {
                    novoProduto.updateProdutoBD();
                }
                else
                {
                    novoProduto.inserirProdutoNoBD();
                }
            } 
            else if (categoria.equals(Produto.Categorias.FERRAMENTA.getCategoria()))
            {
                ProdutoFerramenta novoProduto = new ProdutoFerramenta(descricao, nomeDoProduto, valorDoProduto, linkDoProduto, fotoDoProduto, valorArrecadado, valorDoFrete, categoria, this.especificacoes, this.tags, ControllerLogged.idUsuario, idLoja, material, cor, altura, largura, comprimento);
                
                if (ControllerLogged.editarProduto)
                {
                    novoProduto.updateProdutoBD();
                }
                else
                {
                    novoProduto.inserirProdutoNoBD();
                }
            }
            else if (categoria.equals(Produto.Categorias.LIVRO.getCategoria()))
            {
                ProdutoLivro novoProduto = new ProdutoLivro(descricao, nomeDoProduto, valorDoProduto, linkDoProduto, fotoDoProduto, valorArrecadado, valorDoFrete, categoria, especificacoes, tags, autor, genero, idUsuario, idLoja);
                
                if (ControllerLogged.editarProduto)
                {
                    novoProduto.updateProdutoBD();
                }
                else
                {
                    novoProduto.inserirProdutoNoBD();
                }
            }
            else if (categoria.equals(Produto.Categorias.MOBILIA.getCategoria()))
            {
                ProdutoMobilia novoProduto = new ProdutoMobilia(descricao, nomeDoProduto, valorDoProduto, linkDoProduto, fotoDoProduto, valorArrecadado, valorDoFrete, categoria, especificacoes, tags, material, cor, altura, largura, comprimento, idUsuario, idLoja);
                
                if (ControllerLogged.editarProduto)
                {
                    novoProduto.updateProdutoBD();
                }
                else
                {
                    novoProduto.inserirProdutoNoBD();
                }
            }
            else if (categoria.equals(Produto.Categorias.ROUPA.getCategoria()))
            {
                ProdutoModa novoProduto = new ProdutoModa(descricao, nomeDoProduto, valorDoProduto, linkDoProduto, fotoDoProduto, valorArrecadado, valorDoFrete, categoria, this.especificacoes, this.tags, tamanho, cor, material, ControllerLogged.idUsuario, idLoja);
                
                if (ControllerLogged.editarProduto)
                {
                    novoProduto.updateProdutoBD();
                }
                else
                {
                    novoProduto.inserirProdutoNoBD();
                }
            }

            this.abrirErroStage("Produto adicionado com sucesso!");
            this.mudarScene("ScreenLogged.fxml");
        }
        else
        {
            this.abrirErroStage("Preencha os campos obrigatórios e tente novamente! \nCaso adicionar uma loja, não esqueça de verificá-la!");
        }
    }

    private int getIdLoja()
    {
        int result = 0;

        ArrayList<Integer> lojas = lojaDAO.selectLojaUsuario(ControllerLogged.idUsuario);

        for (Integer idLoja : lojas)
        {
            Loja loja = lojaDAO.selectById(idLoja);

            if (loja.getNome() == this.adicionarNaLojaCampo.getText())
            {
                result = loja.getId();
                break;
            }
        }

        return result;
    }

    /**
     * Função para puxar as categoriasc do usuário cadastradas no banco de dados.
     */
    public void puxarCategorias()
    {
        this.categoriasSplitDown.getItems().clear();
        
        Categorias[] categorias = Categorias.values();
        
        for (Categorias categoria : categorias)
        {
            MenuItem novaCategoria = new MenuItem(categoria.getCategoria());

            EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event)
                {
                    categoriasSplitDown.setText(categoria.getCategoria());
                    filtrarCamposPorCategoria();
                }
            };

            novaCategoria.setOnAction(eventHandler);

            this.categoriasSplitDown.getItems().add(novaCategoria);
        }
    }

    /**
     * Habilitar/Desabilitar campos a depender da categoria do produto em questão.
     */
    public void filtrarCamposPorCategoria()
    {
        this.inicializarCamposCategoria();
        
        String categoriaProduto = this.categoriasSplitDown.getText();

        if (categoriaProduto == Produto.Categorias.LIVRO.getCategoria())
        {
            this.autor.setOpacity(1);
            this.autor.setDisable(false);

            this.genero.setOpacity(1);
            this.genero.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.ROUPA.getCategoria() || categoriaProduto == Produto.Categorias.ACESSORIO.getCategoria() || categoriaProduto == Produto.Categorias.CALCADO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.tamanho.setOpacity(1);
            this.tamanho.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.ELETRONICO.getCategoria() || categoriaProduto == Produto.Categorias.ELETRODOMESTICO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);
        }
        else if (categoriaProduto == Produto.Categorias.MOBILIA.getCategoria() || categoriaProduto == Produto.Categorias.CASAEJARDIM.getCategoria() || categoriaProduto == Produto.Categorias.AUTOMOTIVO.getCategoria() || categoriaProduto == Produto.Categorias.FERRAMENTA.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.tamanho.setOpacity(1);
            this.tamanho.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);

            this.altura.setOpacity(1);
            this.altura.setDisable(false);

            this.largura.setOpacity(1);
            this.largura.setDisable(false);

            this.comprimento.setOpacity(1);
            this.comprimento.setDisable(false);
        }
    }
    
    /**
     * Função para checar se todos os campos obrigatórios estão preenchidos.
     */
    public boolean checarCamposObrigatorios()
    {
        boolean result = true;

        if (this.nomeDoProduto.getText().isEmpty())
        {
            result = false;
        }

        if (this.categoriasSplitDown.getText().equals("Selecione uma categoria"))
        {
            result = false;
        }

        Color corDoRetangulo = (Color) this.retanguloResultadoLoja.getFill();

        if (!this.adicionarNaLojaCampo.getText().isEmpty() && corDoRetangulo != Color.GREEN)
        {
            result = false;
        }

        return result;
    }

    /**
     * Função para excluir uma foto salva de um produto.
     */
    private void excluirFotoDoProduto()
    {
        int lastIndexOf = this.fotoProdutoImg.getImage().getUrl().lastIndexOf("/");
        String nomeDoArquivo = this.fotoProdutoImg.getImage().getUrl().substring(lastIndexOf + 1, this.fotoProdutoImg.getImage().getUrl().length());

        this.excluirImagem(nomeDoArquivo);
    }

    /**
     * Verificar se a loja existe no banco de dados.
     * 
     * @param action
     * Objeto ActionEvent com informações sobre o evento e entidade
     * que causou a chamada da função.
     */
    @FXML
    public void verificarSeLojaExiste(ActionEvent action)
    {
        String nomeDaLoja = this.adicionarNaLojaCampo.getText();

        boolean result = false;

        ArrayList<Loja> lojas = lojaDAO.selectLojaPorNome(nomeDaLoja, 1);
        
        for (Loja loja : lojas)
        {
            if (loja.getNome().equals(nomeDaLoja))
            {
                result = true;
            }
        }

        if (result)
        {
            this.retanguloResultadoLoja.setFill(Color.GREEN);
            this.textoResultadoLoja.setText("Encontramos a loja " + nomeDaLoja + "!");
        }   
        else
        {
            this.retanguloResultadoLoja.setFill(Color.RED);
            this.textoResultadoLoja.setText("A loja que você está procurando ainda não foi cadastrada por você :(");
        }
    }

    /**
     * Função para checar se foi adicionado um novo caractere no campo de associar uma loja ao produto.
     * A função irá reverter para nulo o objeto que define se a loja é válida ou não.
     */
    @FXML
    public void entradaCaractereCampoLoja(KeyEvent key)
    {
        this.retanguloResultadoLoja.setFill(Color.rgb(217, 219, 221));
        this.textoResultadoLoja.setText("");
    }

    /**
     * Função para inserir os dados de um produto já cadastrado.
     * 
     * Esta função será chamada apenas quando a tela estiver no modo de edição de produto, e não de criação.
     */
    private void inserirDadosDoProduto()
    {
        Produto produto = produtoDAO.selectById(ControllerLogged.idProdutoAtual);

        if (produto.getUrl_foto() != null)
        {
            Image imgProduto = new Image(produto.getUrl_foto());
            this.fotoProdutoImg.setImage(imgProduto);
        }

        this.nomeDoProduto.setText(produto.getNome());
        this.linkDoProduto.setText(produto.getLink());
        this.valorDoProduto.setText(String.valueOf(produto.getPreco()));
        this.valorArrecadado.setText(String.valueOf(produto.getValorArrecadado()));
        this.valorDoFrete.setText(String.valueOf(produto.getValorFrete()));

        if (produto.getEspecificacoes() != null)
        {
            for (Especificacao especificacao : produto.getEspecificacoes())
            {
                Text especificacaoText = new Text(especificacao.getNome() + " : " + especificacao.getValor());
    
                this.textFlowEspecificacao.getChildren().add(especificacaoText);
                this.especificacoesText.add(especificacaoText);
            }
        }

        if (produto.getTags() != null)
        {
            for (Tag tag : produto.getTags())
            {
                Text tagText = new Text(tag.getNome());
    
                this.textFlowTag.getChildren().add(tagText);
                this.tagsText.add(tagText);
            }
        }

        if (produto.getDescricao() != null)
        {
            this.descricao.setText(produto.getDescricao());
        }
        
        String nomeDaLoja;
        Loja loja = lojaDAO.selectById(produto.getIdLoja());
        if (loja != null)
        {
            nomeDaLoja = loja.getNome();
        }
        else
        {
            nomeDaLoja = null;
        }

        if (nomeDaLoja != null)
        {
            this.adicionarNaLojaCampo.setText(nomeDaLoja);
            this.retanguloResultadoLoja.setFill(Color.GREEN);
            this.textoResultadoLoja.setText("Produto cadastrado numa loja!");
        }

        /* Setando atributos relacionados à categoria do produto */
        String categoriaProduto = produto.getCategoria();

        this.categoriasSplitDown.setText(categoriaProduto);

        if (categoriaProduto == Produto.Categorias.LIVRO.getCategoria())
        {
            this.autor.setOpacity(1);
            this.autor.setDisable(false);
            this.genero.setOpacity(1);
            this.genero.setDisable(false);

            /* TODO: luis
             *  puxar do banco de dados
             * id do produto: ControllerLogged.idProdutoAtual
             */
            String autor;
            String genero;

            // this.genero.setText(genero);
            // this.autor.setText(autor);
        }
        else if (categoriaProduto == Produto.Categorias.ROUPA.getCategoria() || categoriaProduto == Produto.Categorias.ACESSORIO.getCategoria() || categoriaProduto == Produto.Categorias.CALCADO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.tamanho.setOpacity(1);
            this.tamanho.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);

            /* TODO: luis
             *  puxar do banco de dados
             * id do produto: ControllerLogged.idProdutoAtual
             */
            String cor;
            String tamanho;
            String material;

            // this.cor.setText(cor);
            // this.tamanho.setText(tamanho);
            // this.material.setText(material);
        }
        else if (categoriaProduto == Produto.Categorias.ELETRONICO.getCategoria() || categoriaProduto == Produto.Categorias.ELETRODOMESTICO.getCategoria())
        {
            this.cor.setOpacity(1);
            this.cor.setDisable(false);

            this.material.setOpacity(1);
            this.material.setDisable(false);

            /* TODO: luis
             *  puxar do banco de dados
             * id do produto: ControllerLogged.idProdutoAtual
             */
            String cor;
            String material;

            // this.cor.setText(cor);
            // this.material.setText(material);
        }
        else if (categoriaProduto == Produto.Categorias.MOBILIA.getCategoria() || categoriaProduto == Produto.Categorias.CASAEJARDIM.getCategoria() || categoriaProduto == Produto.Categorias.AUTOMOTIVO.getCategoria() || categoriaProduto == Produto.Categorias.FERRAMENTA.getCategoria())
        {
            this.cor.setOpacity(0.5);
            this.cor.setDisable(true);

            this.tamanho.setOpacity(0.5);
            this.tamanho.setDisable(true);

            this.material.setOpacity(0.5);
            this.material.setDisable(true);

            this.altura.setOpacity(0.5);
            this.altura.setDisable(true);

            this.largura.setOpacity(0.5);
            this.largura.setDisable(true);

            this.comprimento.setOpacity(0.5);
            this.comprimento.setDisable(true);

            /* TODO: luis
             *  puxar do banco de dados
             * id do produto: ControllerLogged.idProdutoAtual
             */
            String cor;
            String tamanho;
            String material;
            String altura;
            String largura;
            String comprimento;

            // this.cor.setText(cor);
            // this.tamanho.setText(tamanho);
            // this.material.setText(material);
            // this.altura.setText(altura);
            // this.largura.setText(largura);
            // this.comprimento.setText(comprimento);
        }

    }

    @FXML
    public void filterOnlyDigits(KeyEvent key)
    {
        Node source = (Node) key.getSource();
        TextField field = (TextField) source;

        if (!key.getCode().isDigitKey() && (key.getCode() != KeyCode.PERIOD || key.getCode() == KeyCode.PERIOD && field.getText().contains(".")) && !key.getCode().isArrowKey() && key.getCode() != KeyCode.BACK_SPACE)
        {

            field.clear();
            field.setText("0.0");
        }
    }

    /* 
     * 
     *      FXML ENTIDADES
     * 
     */

    @FXML
    protected Node root;

    /* 
     *  Textfields
     */
    

    @FXML
    private TextField nomeDoProduto;

    @FXML
    private TextField linkDoProduto;

    @FXML
    private TextField especificacaoNome;

    @FXML
    private TextField especificacaoValor;

    @FXML
    private TextField valorArrecadado;

    @FXML
    private TextField valorDoFrete;

    @FXML
    private TextField valorDoProduto;

    @FXML
    private TextField adicionarTagCampo;

    @FXML
    private TextField cor;

    @FXML
    private TextField tamanho;

    @FXML
    private TextField material;

    @FXML
    private TextField altura;

    @FXML
    private TextField largura;

    @FXML
    private TextField comprimento;

    @FXML
    private TextField autor;

    @FXML
    private TextField genero;

    @FXML
    private TextArea descricao;

    @FXML
    private TextField removerEspecificacaoCampo;

    @FXML
    private TextField removerTagCampo;

    @FXML
    private TextField adicionarNaLojaCampo;

    /* 
     *  Botões
     */

    @FXML
    private Button adicionarEspecificacao;

    @FXML
    private Button adicionarTag;

    @FXML
    private Button removerEspecificacao;    

    @FXML
    private Button removerTag;   

    @FXML
    private Button adicionarProdutoNaLoja;
    
    /* 
     *  Panes
     */

    @FXML 
    private AnchorPane anchorEspecificacao;

    @FXML 
    private AnchorPane anchorTag;

    /* 
     *  Texto
     */

    @FXML 
    private TextFlow textFlowEspecificacao;

    @FXML 
    private TextFlow textFlowTag;

    @FXML
    private Text textoResultadoLoja;

    /* 
     *  SplitDownButton
     */

    @FXML
    private SplitMenuButton categoriasSplitDown;

    /* 
     *  ImageView
     */

    @FXML 
    private ImageView fotoProdutoImg;

    /* 
     *  Figuras geométricas
     */

    @FXML
    private Rectangle retanguloResultadoLoja;

    /* 
     * 
     *      ATRIBUTOS INTERNOS
     * 
     */

    private ArrayList<Tag> tags;
    private ArrayList<Especificacao> especificacoes;

    private ArrayList<Text> especificacoesText;
    private ArrayList<Text> tagsText;

    private boolean adicionouFotoProduto;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private LojaDAO lojaDAO = new LojaDAO();
}
