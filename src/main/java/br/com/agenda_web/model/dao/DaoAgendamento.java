package br.com.agenda_web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda_web.model.bean.BeanAgendamento;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.util.ConexaoDb;


/**
 *
 * @author gustavo
 */
public class DaoAgendamento {
    
    private Connection connection;
    private final ConexaoDb conexaoDb;

    public DaoAgendamento() {
        conexaoDb = new ConexaoDb();
    }
    
    public BeanAgendamento criarAgendamento(BeanAgendamento agendamento){
        
        if(conexaoDb.conectar()){
            
            String sql = "INSERT INTO tb_agendamento (fk_id_contato, fk_id_usuario, "
                    + "data_agendada, hora_agendada, descricao, conteudo) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                preparedStatement.setLong(1, agendamento.getContato().getId());
                preparedStatement.setLong(2, agendamento.getUsuario().getId_usuario());
                preparedStatement.setDate(3, new java.sql.Date(agendamento.getData_agendada().getTime()));
                preparedStatement.setTime(4, new java.sql.Time(agendamento.getHora_agendada().getTime()));
                preparedStatement.setString(5, agendamento.getDescricao());
                preparedStatement.setString(6, agendamento.getConteudo());
                
                preparedStatement.executeUpdate();
                
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next()){
                        agendamento.setId(resultSet.getLong(1));
                    }
                    
                    resultSet.close();
                }
                
                connection.commit();
                connection.close();
                
                return agendamento;
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
    
    public List<BeanAgendamento> listarAgendamento(BeanAgendamento agendamento, String tipoPesquisa){
        
        List<BeanAgendamento> agendamentos = new ArrayList<>();

        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
        String sql;
        
        if(conexaoDb.conectar()){
            
            try {
                
                connection = conexaoDb.getConnection();
                
                sql = "select age.id, age.fk_id_contato, age.data_agendada, age.hora_agendada, age.descricao, " +
                                "age.conteudo, cont.nome " +
                                "from tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? ORDER BY age.data_agendada";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 
                switch (tipoPesquisa) {
                    case "Todos":
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        break;
                        
                    case "Hora":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and age.hora_agendada = ? ORDER BY age.hora_agendada";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setTime(2, new java.sql.Time(agendamento.getHora_agendada().getTime()));
                        break;
                        
                    case "Data":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and age.data_agendada = ? ORDER BY age.data_agendada";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setDate(2, new java.sql.Date(agendamento.getData_agendada().getTime()));
                        break;
                    case "Descricao":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and age.descricao LIKE UPPER(?) ORDER BY age.descricao";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setString(2, "%" + agendamento.getDescricao() + "%");
                        break;
                    case "Conteudo":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and age.conteudo LIKE UPPER(?) ORDER BY age.conteudo";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setString(2, "%" + agendamento.getConteudo() + "%");
                        break;
                    case "Contato":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and cont.nome LIKE UPPER(?) ORDER BY cont.nome";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setString(2, "%" + agendamento.getContato().getNome() + "%");
                        break;
                    case "Id contato":
                        sql = "select age.id, age.fk_id_contato, age.data_agendada, " +
                                "age.hora_agendada, age.descricao, age.conteudo, cont.nome FROM tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where usu.id = ? and cont.id = ? ORDER BY cont.id";
                        
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                        preparedStatement.setLong(2, agendamento.getContato().getId());
                        break;
                    default:
                        break;
                }
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    BeanContato beanContato = new BeanContato(resultSet.getLong("fk_id_contato"), resultSet.getString("nome"));
                    
                    BeanAgendamento agendamento1 = new BeanAgendamento();
                    agendamento1.setId(resultSet.getLong("id"));
                    agendamento1.setContato(beanContato);
                    agendamento1.setDescricao(resultSet.getString("descricao"));
                    agendamento1.setConteudo(resultSet.getString("conteudo"));
                    agendamento1.setHora_agendada(horaFormat.parse(resultSet.getTime("hora_agendada").toString()));
                    agendamento1.setData_agendada(resultSet.getDate("data_agendada"));    
                    
                    agendamentos.add(agendamento1);
                }
                
                resultSet.close();
                connection.commit();
                conexaoDb.desconectar();
                
                return agendamentos;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }
    
    public BeanAgendamento atualizarAgendamento(BeanAgendamento agendamento){
        
        if(conexaoDb.conectar()){
            
            String sql = "UPDATE tb_agendamento SET data_agendada = ?, hora_agendada = ?, fk_id_contato = ?, "
                    + "descricao = ?, conteudo = ? WHERE id = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDate(1, new java.sql.Date(agendamento.getData_agendada().getTime()));
                preparedStatement.setTime(2, new java.sql.Time(agendamento.getHora_agendada().getTime()));
                preparedStatement.setLong(3, agendamento.getContato().getId());
                preparedStatement.setString(4, agendamento.getDescricao());
                preparedStatement.setString(5, agendamento.getConteudo());
                preparedStatement.setLong(6, agendamento.getId());
                
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return agendamento;
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
    
    public BeanAgendamento excluirTodosAgendamentos(BeanAgendamento agendamento){
        if(conexaoDb.conectar()){
            
            String sql = "DELETE FROM tb_agendamento WHERE fk_id_usuario = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, agendamento.getUsuario().getId_usuario());
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return agendamento;
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
    
    public BeanAgendamento excluirAgendamento(BeanAgendamento agendamento){
         if(conexaoDb.conectar()){
            
            String sql = "delete from tb_agendamento where id = ? AND fk_id_usuario = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, agendamento.getId());
                preparedStatement.setLong(2, agendamento.getUsuario().getId_usuario());
                preparedStatement.executeUpdate();
                
                connection.commit();
                conexaoDb.desconectar();
                
                return agendamento;
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

    public BeanAgendamento buscarAgendamento(BeanAgendamento agendamento) {
        
        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
        
        if(conexaoDb.conectar()){
            
            String sql = "select age.id, age.fk_id_contato, age.data_agendada, age.hora_agendada, age.descricao, " +
                                "age.conteudo, cont.nome " +
                                "from tb_agendamento AS age " +
                                "inner join tb_usuario AS usu on age.fk_id_usuario = usu.id " +
                                "inner join tb_contato AS cont on age.fk_id_contato = cont.id " +
                                "where age.id = ? and usu.id = ?";
            
            try {
                connection = conexaoDb.getConnection();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, agendamento.getId());
                preparedStatement.setLong(2, agendamento.getUsuario().getId_usuario());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                    BeanContato beanContato = new BeanContato(resultSet.getLong("fk_id_contato"), resultSet.getString("nome"));
                    agendamento = new BeanAgendamento();
                    agendamento.setId(resultSet.getLong("id"));
                    agendamento.setContato(beanContato);
                    agendamento.setDescricao(resultSet.getString("descricao"));
                    agendamento.setConteudo(resultSet.getString("conteudo"));
                    agendamento.setHora_agendada(horaFormat.parse(resultSet.getTime("hora_agendada").toString()));
                    agendamento.setData_agendada(resultSet.getDate("data_agendada")); 
                }
                
                connection.commit();
                conexaoDb.desconectar();
                
                return agendamento;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
            finally{
                conexaoDb.desconectar();
            }
        }
        return null;
    }  
}
