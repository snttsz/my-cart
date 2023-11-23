package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.DAOMTM.Especificacao_has_Produto;
import DAO.DAOMTM.Tag_has_Produto;
import bancodedados.SQLiteConnectionManager;
import bancodedados.SQLiteTableManager;
import sistema.Produto;
import sistema.Usuario;
import utils.StringManager;

public class UsuariosDAO extends DAO<Usuario> 
{

    @Override
    public void delete(Usuario usuario) 
    {
        // Deletando produtos relacionados com o usuário
        ArrayList<Produto> produtosRelacionados = produtoDAO.selectTodosProdutosDoUsuario(usuario.getId());
        produtosRelacionados.forEach(produto ->
        {
            produtoDAO.delete(produto);
        });

        // Deletando usuário da tabela de produtos
        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 
        String instrucao = SQLiteTableManager.delete(Usuario.getNomeTabela(), condicao);
        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void insert(Usuario usuario) 
    {
       ArrayList<String> valoresString = new ArrayList<String>();

       ArrayList<String> arrayColunas = new ArrayList<String>();

       /* 
        * Adicionando colunas
        */
        arrayColunas.add(Usuario.Coluna.NOME.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.EMAIL.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.LOGIN.getNomeColuna());
        arrayColunas.add(Usuario.Coluna.SENHA.getNomeColuna());

        /* 
         * Montando valores
         */
        valoresString.add(usuario.getNome());
        valoresString.add(usuario.getEmail());
        valoresString.add(usuario.getLogin());
        valoresString.add(usuario.getSenha());

        ArrayList<String> valoresStringNormalizados = StringManager.formatarString(valoresString);

        /* 
         * Montando instrução
         */

        String colunas = StringManager.montarString(arrayColunas);

        String valores = StringManager.montarString(valoresStringNormalizados);

        String instrucao = SQLiteTableManager.insertTo(Usuario.getNomeTabela(), colunas, valores);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public void updateString(Usuario usuario, String coluna, String novoValor)
    {
        novoValor = StringManager.formatarString(novoValor);

        String colunas_valores = StringManager.inserirIgualdade(coluna, novoValor);

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);

    }

    
    @Override
    public void updateInt(Usuario usuario, String coluna, int novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Integer.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
    }
    
    @Override
    public void updateDouble(Usuario usuario, String coluna, double novoValor) 
    {
        String colunas_valores = StringManager.inserirIgualdade(coluna, Double.toString(novoValor));

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(usuario.getId())); 

        String instrucao = SQLiteTableManager.update(Usuario.getNomeTabela(), colunas_valores, condicao);

        SQLiteConnectionManager.enviarQuery(instrucao);
        
    }

    @Override
    public ArrayList<Usuario> selectAll() 
    {
        /* 
         * Montando instrucao
         */

        String instrucao = SQLiteTableManager.selectAll(Usuario.getNomeTabela());

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try
        {
            while(resultSet.next())
            {
                int id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                
                Usuario usuario = new Usuario(id, nome, login, senha, email, url_foto);

                usuarios.add(usuario);
            }

            return usuarios;
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }

    @Override
    public Usuario selectById(int id)
    {
        /* 
         * Montando instrucao
         */

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.ID.getNomeColuna(), Integer.toString(id));
        
        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        try
        {
            if(resultSet != null)
            {
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                
                Usuario usuario = new Usuario(id, nome, login, senha, email, url_foto);

                return usuario;
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    public ArrayList<Usuario> selectUsuariosCadastradosRecentemente(int qtdUsuariosCadastrados)
    {
     
        /* SELECT * FROM Produto ORDER BY idProduto DESC LIMIT X; */
        String qtd = Integer.toString(qtdUsuariosCadastrados);

        String instrucao = SQLiteTableManager.selectOrderByLimitDec(Usuario.getNomeTabela(), Usuario.Coluna.ID.getNomeColuna(), qtd);
        
        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        try
        {
            while(resultSet.next())
            {
                int id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                
                Usuario usuario = new Usuario(id, nome, login, senha, email, url_foto);

                usuarios.add(usuario);
            }

            return usuarios;
        }
        catch(SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }

    public boolean verificarExistenciaLoginUsuario(String login)
    {
        
        // Verificando login
        login = StringManager.formatarString(login);

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.LOGIN.getNomeColuna(), login);

        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), Usuario.Coluna.LOGIN.getNomeColuna(), condicao);

        System.out.println(instrucao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        // Verificando a existência do usuário

        boolean result = true;

        try
        {
            if(!resultSet.next())
            {
                result = false;
            }

        }
        catch(SQLException e)
        {   
            e.printStackTrace();
        }

        SQLiteConnectionManager.desconectar();

        return result;
    }

    public boolean verificarExistenciaEmailUsuario(String email)
    {

        // Verificando login
        email = StringManager.formatarString(email);

        String condicao = StringManager.inserirIgualdade(Usuario.Coluna.EMAIL.getNomeColuna(), email);

        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), Usuario.Coluna.EMAIL.getNomeColuna(), condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);
        
        // Verificando a existência do usuário

        boolean result;

        if(resultSet != null)
        {
            result = true;
        }
        else
        {
            result = false;
        }

        SQLiteConnectionManager.desconectar();

        return result;
    }

    public int verificarVeracidadeDoUsuario(String login, String senha)
    {
        login = StringManager.formatarString(login);

        senha = StringManager.formatarString(senha);

        String condicao1 = StringManager.inserirIgualdade(Usuario.Coluna.LOGIN.getNomeColuna(), login);

        String condicao2 = StringManager.inserirIgualdade(Usuario.Coluna.SENHA.getNomeColuna(), senha);

        String condicao = StringManager.inserirAnd(condicao1, condicao2); 

        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), Usuario.Coluna.ID.getNomeColuna(), condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        int id = 0;
        
        try
        {
            if(resultSet != null)
            {
                id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
            }

        }
        catch(SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }

        return id;

    }

    public Usuario recuperarUsuario(String login, String nome)
    {
        login = StringManager.formatarString(login);

        nome = StringManager.formatarString(nome);

        String condicao1 = StringManager.inserirIgualdade(Usuario.Coluna.LOGIN.getNomeColuna(), login);

        String condicao2 = StringManager.inserirIgualdade(Usuario.Coluna.NOME.getNomeColuna(), nome);

        String condicao = StringManager.inserirAnd(condicao1, condicao2); 

        String instrucao = SQLiteTableManager.select(Usuario.getNomeTabela(), "*", condicao);

        ResultSet resultSet = SQLiteConnectionManager.receberQuery(instrucao);

        try
        {
            if(resultSet.next())
            {
                int id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome1 = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String login1 = resultSet.getString(Usuario.Coluna.LOGIN.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                
                Usuario usuario = new Usuario(id, nome1, login1, senha, email, url_foto);
                return usuario;
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao processar resultado do banco de dados", e);
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;

    }

    /*
     * Funções de update para cada atributo da classe Usuário
     */

    public void updateNome(Usuario usuario, String newNome)
    {
        this.updateString(usuario, Usuario.Coluna.NOME.getNomeColuna(), newNome);
    }

    public void updateLogin(Usuario usuario, String newLogin)
    {
        this.updateString(usuario, Usuario.Coluna.LOGIN.getNomeColuna(), newLogin);
    }

    public void updateSenha(Usuario usuario, String newSenha)
    {
        this.updateString(usuario, Usuario.Coluna.SENHA.getNomeColuna(), newSenha);
    }

    public void updateEmail(Usuario usuario, String newEmail)
    {
        this.updateString(usuario, Usuario.Coluna.EMAIL.getNomeColuna(), newEmail);
    }

    public void updateUrl_Foto(Usuario usuario, String newUrl_foto)
    {
        this.updateString(usuario, Usuario.Coluna.URL_FOTO.getNomeColuna(), newUrl_foto);
    }

    /*
     * Atributos
     */

    ProdutoDAO produtoDAO = new ProdutoDAO();
    Tag_has_Produto tag_has_Produto_DAO = new Tag_has_Produto();
    Especificacao_has_Produto especificacao_has_Produto_DAO = new Especificacao_has_Produto();
}
