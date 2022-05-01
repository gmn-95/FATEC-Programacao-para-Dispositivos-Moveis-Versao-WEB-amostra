package br.com.agenda_web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.util.ConexaoDb;
/**
 *
 * @author gustavo
 */
public class DaoContato {
    
    private Connection connection;
    private final ConexaoDb conexaoDb;

    public DaoContato() {
        conexaoDb = new ConexaoDb();
    }
    
    public BeanContato criarContato(BeanContato contato) {
        if(conexaoDb.conectar()){
            
            String sql = "INSERT INTO tb_contato VALUES (null, ?, ?, ?, ?, ?, ?)";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, contato.getUsuario().getId_usuario());
                preparedStatement.setString(2, contato.getNome());
                preparedStatement.setString(3, contato.getTelefone_fixo());
                preparedStatement.setString(4, contato.getCelular());
                preparedStatement.setString(5, contato.getEmail());
                preparedStatement.setString(6, contato.getObs());
                
                preparedStatement.executeUpdate();
                
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next()){
                        contato.setId(resultSet.getLong(1));
                    }
                    
                    resultSet.close();
                }
                
                connection.commit();
                conexaoDb.desconectar();
                
                return contato;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }
    
    public List<BeanContato> listarContatos(BeanContato contato, String tipoPesquisa){
        
        if(conexaoDb.conectar()){
            
            List<BeanContato> list = new ArrayList<>();
            
            String sql;
        
            try {
                connection = conexaoDb.getConnection();
                
                sql = "SELECT * FROM tb_contato " +
                "WHERE fk_id_usuario = ? ORDER BY tb_contato.id";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                switch(tipoPesquisa){
                    case "Todos" : 
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, contato.getUsuario().getId_usuario());
                        break;
                    
                    case "Nome":
                        sql = "SELECT * FROM tb_contato WHERE nome LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_contato.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + contato.getNome() + "%");
                        preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                        
                        break;
                        
                    case "Telefone":
                        sql = "SELECT * FROM tb_contato WHERE telefone_fixo LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_contato.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + contato.getTelefone_fixo() + "%");
                        preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                        break;
                        
                    case "Celular":
                        sql = "SELECT * FROM tb_contato WHERE celular LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_contato.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + contato.getCelular() + "%");
                        preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                        break;
                        
                    case "Email":
                        sql = "SELECT * FROM tb_contato WHERE email LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_contato.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + contato.getEmail() + "%");
                        preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                        break;
                        
                    case "Obs":
                        sql = "SELECT * FROM tb_contato WHERE obs LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_contato.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + contato.getObs()+ "%");
                        preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                        break;
                    
                }
                
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    String obs = resultSet.getString("obs");
                    
                    if(obs == null || obs.equals("null")){
                        obs = " ";
                    }
                    contato = new BeanContato(resultSet.getLong("id"), 
                            new BeanUsuario(resultSet.getLong("fk_id_usuario")), 
                            resultSet.getString("nome"), resultSet.getString("telefone_fixo"), 
                            resultSet.getString("celular"), resultSet.getString("email"), 
                            obs);
                    
                    list.add(contato);
                }
                
                connection.commit();
                conexaoDb.desconectar();
                resultSet.close();
                
                return list;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
        
    }
    
    public BeanContato buscarContato(BeanContato contato){
        
        if(conexaoDb.conectar()){
            
            String sql = "select * from tb_contato, tb_usuario where tb_contato.id = ? "
                    + "and fk_id_usuario = ? and fk_id_usuario = tb_usuario.id";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, contato.getId());
                preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                    String obs = resultSet.getString("obs");
                    
                    if(obs == null || obs.equals("null")){
                        obs = " ";
                    }
                    
                    contato = new BeanContato(resultSet.getLong("id"), 
                            new BeanUsuario(resultSet.getLong("fk_id_usuario")), 
                            resultSet.getString("nome"), resultSet.getString("telefone_fixo"), 
                            resultSet.getString("celular"), resultSet.getString("email"), 
                            obs);
                    
                    connection.commit();
                    conexaoDb.desconectar();
                    resultSet.close();

                    return  contato;
                }
                
                return null;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }

    public BeanContato atualizarContato(BeanContato contato){
        
        if(conexaoDb.conectar()){
            
            String sql = "UPDATE tb_contato SET nome = ?, telefone_fixo = ?, celular = ?, " +
            "email = ?, obs = ? WHERE id = ? and fk_id_usuario = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, contato.getNome());
                preparedStatement.setString(2, contato.getTelefone_fixo());
                preparedStatement.setString(3, contato.getCelular());
                preparedStatement.setString(4, contato.getEmail());
                preparedStatement.setString(5, contato.getObs());
                preparedStatement.setLong(6, contato.getId());
                preparedStatement.setLong(7, contato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return contato;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }
    
    public BeanContato excluirTodosContatos(BeanContato contato) {
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_contato WHERE fk_id_usuario = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, contato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();

                return  contato;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }

    public BeanContato excluirContato(BeanContato contato) {
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_contato WHERE id = ? AND fk_id_usuario = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, contato.getId());
                preparedStatement.setLong(2, contato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();

                return  contato;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }
}
