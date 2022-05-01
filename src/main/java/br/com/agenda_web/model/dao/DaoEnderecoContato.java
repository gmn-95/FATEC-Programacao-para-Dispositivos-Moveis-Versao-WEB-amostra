package br.com.agenda_web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanEnderecoContato;
import br.com.agenda_web.util.ConexaoDb;

/**
 *
 * @author gustavo
 */
public class DaoEnderecoContato {
    
    private Connection connection;
    private final ConexaoDb conexaoDb;

    public DaoEnderecoContato() {
        conexaoDb = new ConexaoDb();
    }
    
    public BeanEnderecoContato criarEnderecoContato(BeanEnderecoContato enderecoContato){
        if(conexaoDb.conectar()){
            
            String sql = "INSERT INTO tb_endereco_do_contato VALUES (null, ?, ?, ?, ?)";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, enderecoContato.getEndereco().getId());
                preparedStatement.setLong(2, enderecoContato.getContato().getId());
                preparedStatement.setString(3, enderecoContato.getObs());
                preparedStatement.setLong(4, enderecoContato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next()){
                        enderecoContato.setId(resultSet.getLong(1));
                    }
                    
                    resultSet.close();
                }
                
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecoContato;
                
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
    
    
    public List<BeanEnderecoContato> listarEnderecoContato(BeanEnderecoContato enderecoContato, String tipoPesquisa){
        List<BeanEnderecoContato> enderecosContatos = new ArrayList<>();
        
        String sql;
        
        if(conexaoDb.conectar()){
            
            try {
                
                connection = conexaoDb.getConnection();
                
                sql = "SELECT end_cont.id, end_cont.fk_id_contato, cont.nome, end_cont.fk_id_endereco," +
                                "ende.bairro, ende.cidade, ende.estado, ende.cep, " +
                                "ende.logradouro, ende.numero, ende.complemento, end_cont.obs " +
                                "FROM tb_endereco_do_contato AS end_cont " +
                                "INNER JOIN tb_contato AS cont ON end_cont.fk_id_contato = cont.id " +
                                "INNER JOIN tb_endereco AS ende ON end_cont.fk_id_endereco = ende.id " +
                                "WHERE end_cont.fk_id_usuario = ? ORDER BY end_cont.id";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 
                switch (tipoPesquisa) {
                    case "Todos":
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                        break;
                        
                    case "Id endereco":
                        sql = "SELECT end_cont.id, end_cont.fk_id_contato, cont.nome, end_cont.fk_id_endereco," +
                                "ende.bairro, ende.cidade, ende.estado, ende.cep, " +
                                "ende.logradouro, ende.numero, ende.complemento, end_cont.obs " +
                                "FROM tb_endereco_do_contato AS end_cont " +
                                "INNER JOIN tb_contato AS cont ON end_cont.fk_id_contato = cont.id " +
                                "INNER JOIN tb_endereco AS ende ON end_cont.fk_id_endereco = ende.id " +
                                "WHERE end_cont.fk_id_usuario = ? AND end_cont.fk_id_endereco = ? ORDER BY end_cont.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                        preparedStatement.setLong(2, enderecoContato.getEndereco().getId());
                        break;
                        
                    case "Id contato":
                        sql = "SELECT end_cont.id, end_cont.fk_id_contato, cont.nome, end_cont.fk_id_endereco," +
                                "ende.bairro, ende.cidade, ende.estado, ende.cep, " +
                                "ende.logradouro, ende.numero, ende.complemento, end_cont.obs " +
                                "FROM tb_endereco_do_contato AS end_cont " +
                                "INNER JOIN tb_contato AS cont ON end_cont.fk_id_contato = cont.id " +
                                "INNER JOIN tb_endereco AS ende ON end_cont.fk_id_endereco = ende.id " +
                                "WHERE end_cont.fk_id_usuario = ? AND end_cont.fk_id_contato = ? ORDER BY end_cont.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                        preparedStatement.setLong(2, enderecoContato.getContato().getId());
                        break;
                        
                    case "Obs":
                        sql = "SELECT end_cont.id, end_cont.fk_id_contato, cont.nome, end_cont.fk_id_endereco," +
                                "ende.bairro, ende.cidade, ende.estado, ende.cep, " +
                                "ende.logradouro, ende.numero, ende.complemento, end_cont.obs " +
                                "FROM tb_endereco_do_contato AS end_cont " +
                                "INNER JOIN tb_contato AS cont ON end_cont.fk_id_contato = cont.id " +
                                "INNER JOIN tb_endereco AS ende ON end_cont.fk_id_endereco = ende.id " +
                                "WHERE end_cont.fk_id_usuario = ? AND end_cont.obs LIKE UPPER(?) ORDER BY end_cont.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                        preparedStatement.setString(2, "%" + enderecoContato.getObs() + "%");
                        break;
                    
                    default:
                        break;
                }
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    BeanContato beanContato = new BeanContato(resultSet.getLong("fk_id_contato"), resultSet.getString("nome"));
                    BeanEndereco beanEndereco = new BeanEndereco(resultSet.getLong("fk_id_endereco"), 
                            resultSet.getString("bairro"), resultSet.getString("cidade"), 
                            resultSet.getString("estado"), resultSet.getString("cep"), 
                            resultSet.getString("logradouro"), resultSet.getString("numero"), 
                            resultSet.getString("complemento"));
                    
                    BeanEnderecoContato beanEnderecoContato = new BeanEnderecoContato(resultSet.getLong("id"), 
                            beanEndereco, beanContato, resultSet.getString("obs"));
                    
                    enderecosContatos.add(beanEnderecoContato);
                }
                
                resultSet.close();
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecosContatos;
                
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

    
    public BeanEnderecoContato buscarEnderecoContato(BeanEnderecoContato enderecoContato){
        if(conexaoDb.conectar()){
            
            String sql = "SELECT end_cont.id, end_cont.fk_id_contato, cont.nome, end_cont.fk_id_endereco," +
                                "ende.bairro, ende.cidade, ende.estado, ende.cep, " +
                                "ende.logradouro, ende.numero, ende.complemento, end_cont.obs " +
                                "FROM tb_endereco_do_contato AS end_cont " +
                                "INNER JOIN tb_contato AS cont ON end_cont.fk_id_contato = cont.id " +
                                "INNER JOIN tb_endereco AS ende ON end_cont.fk_id_endereco = ende.id " +
                                "WHERE end_cont.fk_id_usuario = ? AND end_cont.id = ? ORDER BY end_cont.id";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                preparedStatement.setLong(2, enderecoContato.getId());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                    BeanContato beanContato = new BeanContato(resultSet.getLong("fk_id_contato"), resultSet.getString("nome"));
                    BeanEndereco beanEndereco = new BeanEndereco(resultSet.getLong("fk_id_endereco"), 
                            resultSet.getString("bairro"), resultSet.getString("cidade"), 
                            resultSet.getString("estado"), resultSet.getString("cep"), 
                            resultSet.getString("logradouro"), resultSet.getString("numero"), 
                            resultSet.getString("complemento"));
                    
                    enderecoContato = new BeanEnderecoContato(resultSet.getLong("id"), 
                            beanEndereco, beanContato, resultSet.getString("obs"));
                }
                
                resultSet.close();
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecoContato;
                
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

    
    public BeanEnderecoContato atualizarEnderecoContato(BeanEnderecoContato enderecoContato) {
        if(conexaoDb.conectar()){
            
            String sql = "UPDATE tb_endereco_do_contato SET fk_id_endereco = ?, fk_id_contato = ?, obs = ? "
                    + "WHERE id = ? AND fk_id_usuario = ?";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, enderecoContato.getEndereco().getId());
                preparedStatement.setLong(2, enderecoContato.getContato().getId());
                preparedStatement.setString(3, enderecoContato.getObs());
                preparedStatement.setLong(4, enderecoContato.getId());
                preparedStatement.setLong(5, enderecoContato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecoContato;
                
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
    
    public BeanEnderecoContato excluirTodosEnderecoContato(BeanEnderecoContato enderecoContato) {
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_endereco_do_contato WHERE fk_id_usuario = ? ";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, enderecoContato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecoContato;
                
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
    
    
    public BeanEnderecoContato excluirEnderecoContato(BeanEnderecoContato enderecoContato) {
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_endereco_do_contato WHERE id = ? AND fk_id_usuario = ? ";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, enderecoContato.getId());
                preparedStatement.setLong(2, enderecoContato.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return enderecoContato;
                
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
