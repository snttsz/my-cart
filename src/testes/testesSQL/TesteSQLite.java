package testes.testesSQL;

import java.util.ArrayList;

import DAO.EspecificacaoDAO;
import DAO.Especificacao_has_Produto;
import DAO.LojaDAO;
import DAO.ProdutoDAO;
import DAO.TagDAO;
import DAO.Tag_has_Produto;
import DAO.UsuariosDAO;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Especificacao;
import sistema.Loja;
import sistema.Produto;
import sistema.Tag;
import sistema.Usuario;
import utils.ParserArquivo;


public class TesteSQLite 
{

    public static void main(String args[])
    {
        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();

        TesteSQLite.povoarBanco();

        ProdutoDAO produtoDAO = new ProdutoDAO();

        /* PRODUTOS */
        System.out.println("\n");
        System.out.println("Testando todos os produtos");
        
        ArrayList<Produto> produtos = new ArrayList<>();
        

        produtos = produtoDAO.selectAll();

        for(Produto p : produtos)
        {
            Produto.printarProduto(p);
        }

        /* USUARIOS */
        System.out.println("\n");
        System.out.println("Testando todos os usuários");

        ArrayList<Usuario> usuarios = new ArrayList<>();

        UsuariosDAO usuarioDAO = new UsuariosDAO();

        usuarios = usuarioDAO.selectAll();

        for(Usuario u : usuarios)
        {
            Usuario.printarUsuario(u);
        }

        /* LOJAS */

        System.out.println("\n");
        System.out.println("Testando todas as lojas");
        
        ArrayList<Loja> lojas = new ArrayList<>();

        LojaDAO lojaDAO = new LojaDAO();

        lojas = lojaDAO.selectAll();

        for(Loja l : lojas)
        {
            Loja.printarLoja(l);
        }

        /* ESPECIFICACOES */

        System.out.println("\n");
        System.out.println("Testando todas as especificações");

        ArrayList<Especificacao> especificacoes = new ArrayList<>();

        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();

        especificacoes = especificacaoDAO.selectAll();

        for(Especificacao e : especificacoes)
        {
            Especificacao.printarEspecificacao(e);
        }

        /* TAGS */

        System.out.println("\n");
        System.out.println("Testando todas as tags");

        ArrayList<Tag> tags = new ArrayList<>();

        TagDAO tagDAO = new TagDAO();

        tags = tagDAO.selectAll();

        for(Tag t : tags)
        {
            Tag.printarTag(t);
        }

        /* Testando todos os produtos de cada usuário */
        System.out.println("\n");
        System.out.println("Testando produtos de cada usuário");

        ArrayList<Produto> produtoshas = new ArrayList<>();

        for(Usuario u: usuarios)
        {

            System.out.println("Usuário: " + usuarioDAO.selectById(u.getId()).getNome());
            produtoshas = produtoDAO.selectTodosProdutosDoUsuario(u.getId());
            for(Produto p : produtoshas)
            {
                Produto.printarProduto(p);
            }
        }

        /* Testando a tag de cada produto */
        System.out.println("\n");
        System.out.println("Testando a tag de cada produto");

        Tag_has_Produto tag_has_Produto = new Tag_has_Produto();
        ArrayList<Tag> tagsHas = new ArrayList<>();
        
        for(Produto p: produtos)
        {
            System.out.println("Produto: " + produtoDAO.selectById(p.getId()).getNome());
            tagsHas = tag_has_Produto.selectTodasTagsDoProduto(p.getId());
            for(Tag t : tagsHas)
            {
                Tag.printarTag(t);
            }
        }

        /* Testando produtos de cada tag */
        System.out.println("\n");
        System.out.println("Testando produtos de cada tag");

        for(Tag t : tags)
        {
            System.out.println("Tag: " + tagDAO.selectById(t.getId()).getNome());
            produtoshas = tag_has_Produto.selectTodosProdutosDaTag(t.getId());
            for(Produto p : produtoshas)
            {
                Produto.printarProduto(p);
            }
        }

        /* Testando especificacoes de cada produto */
        System.out.println("\n");
        System.out.println("Testando especificações de cada produto");

        Especificacao_has_Produto especificacao_has_Produto = new Especificacao_has_Produto();
        ArrayList<Especificacao> especificacoeshas = new ArrayList<>();

        for(Produto p : produtos)
        {
            System.out.println("Produto: " + produtoDAO.selectById(p.getId()).getNome());
            especificacoeshas = especificacao_has_Produto.selectTodasEspecificacoesDoProduto(p);
            for(Especificacao e : especificacoeshas)
            {
                Especificacao.printarEspecificacao(e);
            }
        }

        /* Testando produto de cada especificação - embora não seja o correto haver isso */
        System.out.println("\n");
        System.out.println("Testando especificações de cada produto");

        for(Especificacao e : especificacoes)
        {
            System.out.println("Especificação :" + especificacaoDAO.selectById(e.getId()).getNome());
            produtoshas = especificacao_has_Produto.selectTodosProdutosDaEspecificacao(e);
            for(Produto p: produtoshas)
            {
                Produto.printarProduto(p);
            }
        }

        /* Testando a contagem de produtos de cada usuário */
        System.out.println("\n");
        System.out.println("Testando a contagem de produtos de cada usuário");

        System.out.println("O usuário" + usuarioDAO.selectById(1000).getNome() + "possui: " + produtoDAO.contarProdutosCategorizadosDoUsuario(1000,Produto.Categorias.ELETRONICO.getCategoria()) + " produtos eletrônicos");

        
        /* Testando a contagem de tags de cada usuário */
        System.out.println("\n");
        System.out.println("Testando a contagem de tags de cada usuário");

        System.out.println("O usuário " + usuarioDAO.selectById(1).getNome() + " possui: " + tagDAO.contarTagsDoUsuario(1) + " tags");

        /* Testando a contagem de tags de um determinado produto */
        System.out.println("\n");
        System.out.println("Testando a contagem de tags de um produto específico");

        System.out.println("O produto " + produtoDAO.selectById(1).getNome() + " possui: " + tag_has_Produto.contarTagsDoProduto(1) + "tags");

        /* Testando a contagem de especificações de determinado produto */
        System.out.println("\n");
        System.out.println("Testando a contade de especificações de determinado produto");

        System.out.println("O produto " + produtoDAO.selectById(1).getNome() + " possui: " + especificacao_has_Produto.contarEspecificacoesDoProduto(1) + " especificações");

        /* Testando a contagem de todas as lojas de um usuário */
        System.out.println("\n");
        System.out.println("Testando a conatgem de lojas de um usuário");

        System.out.println("O usuário " + usuarioDAO.selectById(1).getNome() + " possui: " + lojaDAO.contarLojasDoUsuario(1) + " lojas cadastradas");

        /* Teste para contar todos os produtos cadastrados em uma loja */
        System.out.println("\n");
        System.out.println("Teste para contar todos os produtos cadastrados em uma loja");

        System.out.println("A loja " +  lojaDAO.selectById(1 ).getNome()+ " possui: " + lojaDAO.contarProdutosDaLoja(1) + " produtos cadastrados");

        /* Teste para contar todos os produtos cadastrados em uma loja */
        System.out.println("\n");
        System.out.println("Teste de todos os produtos cadastrados em uma loja");
         
        for(Loja l : lojas)
        {
            System.out.println("A loja: " + l.getNome() + " possui os seguintes produtos:");
            produtos = lojaDAO.selectTodosProdutosCadastradosNaLoja(l.getId());
            for(Produto p :produtos)
            {
                Produto.printarProduto(p);
            }
        }

        /* Testando lista uma quantidade x de produtos com base na data de adição */
        System.out.println("\n");
        System.out.println("Testando lista uma quantidade x de produtos com base na data de adição");

        produtos = produtoDAO.selectProdutosCadastradosRecentemente(3);

        System.out.println("Os seguintes produtos foram cadastrados recentemente: ");
        for(Produto p : produtos)
        {
            System.out.println("NOME: " + p.getNome() + " Data de adição: " + p.getData_de_adicao());
        }


    }

    public static void povoarBanco()
    {
        String scriptPath = "database/scriptPovoamento.txt";

        ArrayList<String> instrucoes = new ArrayList<String>();
        instrucoes = ParserArquivo.lerScriptSQL(scriptPath);
        
        for(String instrucao : instrucoes)
        {   
            SQLiteConnectionManager.enviarQuery(instrucao);
        }

    }

}

