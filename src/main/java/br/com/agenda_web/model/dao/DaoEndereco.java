package br.com.agenda_web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.util.ConexaoDb;

/**
 *
 * @author gustavo
 */
public class DaoEndereco {
    
    private Connection connection;
    private final ConexaoDb conexaoDb;

    public DaoEndereco() {
        conexaoDb = new ConexaoDb();
    }
    
    public BeanEndereco criarEndereco(BeanEndereco endereco){
        
        if(conexaoDb.conectar()){
            
            String sql = "INSERT INTO tb_endereco VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, endereco.getBairro());
                preparedStatement.setString(2, endereco.getCidade());
                preparedStatement.setString(3, endereco.getEstado());
                preparedStatement.setString(4, endereco.getCep());
                preparedStatement.setString(5, endereco.getLogradouro());
                preparedStatement.setString(6, endereco.getNumero());
                preparedStatement.setString(7, endereco.getComplemento());
                preparedStatement.setLong(8, endereco.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next()){
                        endereco.setId(resultSet.getLong(1));
                    }
                    
                    resultSet.close();
                }
                
                connection.commit();
                conexaoDb.desconectar();
                
                return endereco;
                
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
        conexaoDb.desconectar();
        return null;
    }

    public BeanEndereco atualizarEndereco(BeanEndereco endereco) {
        if(conexaoDb.conectar()){
            
            String sql = "UPDATE tb_endereco SET bairro = ?, cidade = ?, estado = ?, cep = ?,"
                    + " logradouro = ?, numero = ?, complemento = ? WHERE id = ? AND fk_id_usuario = ?";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, endereco.getBairro());
                preparedStatement.setString(2, endereco.getCidade());
                preparedStatement.setString(3, endereco.getEstado());
                preparedStatement.setString(4, endereco.getCep());
                preparedStatement.setString(5, endereco.getLogradouro());
                preparedStatement.setString(6, endereco.getNumero());
                preparedStatement.setString(7, endereco.getComplemento());
                preparedStatement.setLong(8, endereco.getId());
                preparedStatement.setLong(9, endereco.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return endereco;
                
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
    
    public BeanEndereco excluirTodosEnderecos(BeanEndereco endereco){
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_endereco WHERE fk_id_usuario = ?";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, endereco.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return endereco;
                
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

    public BeanEndereco excluirEndereco(BeanEndereco endereco) {
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_endereco WHERE id = ? AND fk_id_usuario = ?";
            
            try {
                
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, endereco.getId());
                preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return endereco;
                
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
    
    public List<BeanEndereco> listarEnderecos(BeanEndereco endereco, String tipoPesquisa){
        
        if(conexaoDb.conectar()){
            
            List<BeanEndereco> list = new ArrayList<>();
            
            String sql;
        
            try {
                connection = conexaoDb.getConnection();
                
                sql = "SELECT * FROM tb_endereco " +
                "WHERE fk_id_usuario = ? ORDER BY tb_endereco.id";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                switch(tipoPesquisa){
                    case "Todos" : 
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, endereco.getUsuario().getId_usuario());
                        break;
                    
                    case "Bairro":
                        sql = "SELECT * FROM tb_endereco WHERE bairro LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getBairro() + "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        
                        break;
                        
                    case "Cidade":
                        sql = "SELECT * FROM tb_endereco WHERE cidade LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getCidade() + "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                        
                    case "Estado":
                        sql = "SELECT * FROM tb_endereco WHERE estado LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getEstado()+ "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                        
                    case "Cep":
                        sql = "SELECT * FROM tb_endereco WHERE cep LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getCep() + "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                        
                    case "Logradouro":
                        sql = "SELECT * FROM tb_endereco WHERE logradouro LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getLogradouro()+ "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                        
                    case "Número":
                        sql = "SELECT * FROM tb_endereco WHERE numero LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getNumero()+ "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                    
                    case "Complemento":
                        sql = "SELECT * FROM tb_endereco WHERE complemento LIKE UPPER(?) "
                                + "AND fk_id_usuario = ? ORDER BY tb_endereco.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "%" + endereco.getComplemento()+ "%");
                        preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                        break;
                    
                }
                
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    String complemento = resultSet.getString("complemento");
                    
                    if(complemento == null || complemento.equals("null")){
                        complemento = " ";
                    }
                
                    endereco = new BeanEndereco(resultSet.getLong("id"), resultSet.getString("bairro"),
                            resultSet.getString("cidade"), resultSet.getString("estado"), 
                            resultSet.getString("cep"), resultSet.getString("logradouro"), 
                            resultSet.getString("numero"), complemento, new BeanUsuario(resultSet.getLong("fk_id_usuario")));
                    
                    list.add(endereco);
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
    
    public BeanEndereco buscarEndereco(BeanEndereco endereco){
        
        if(conexaoDb.conectar()){
            
            String sql = "select * from tb_endereco, tb_usuario where tb_endereco.id = ? "
                    + "and fk_id_usuario = ? and fk_id_usuario = tb_usuario.id";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, endereco.getId());
                preparedStatement.setLong(2, endereco.getUsuario().getId_usuario());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                    String complemento = resultSet.getString("complemento");
                    
                    if(complemento == null || complemento.equals("null")){
                        complemento = " ";
                    }
                    
                    endereco = new BeanEndereco(resultSet.getLong("id"), resultSet.getString("bairro"),
                            resultSet.getString("cidade"), resultSet.getString("estado"), 
                            resultSet.getString("cep"), resultSet.getString("logradouro"), 
                            resultSet.getString("numero"), complemento, new BeanUsuario(resultSet.getLong("fk_id_usuario")));
                    
                    connection.commit();
                    conexaoDb.desconectar();
                    resultSet.close();

                    return  endereco;
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
}
