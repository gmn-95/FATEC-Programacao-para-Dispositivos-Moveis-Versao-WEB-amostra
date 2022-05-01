package br.com.agenda_web.model.bean;


import java.util.Date;

/**
 *
 * @author gustavo
 */
public class BeanAgendamento {
    		
    private Long id;
    private BeanContato contato;
    private BeanUsuario usuario;
    private Date data_agendada;
    private Date hora_agendada;
    private String descricao;
    private String conteudo;

    public BeanAgendamento() {
    }

    public BeanAgendamento(Long id, BeanUsuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }
    
    public BeanAgendamento(BeanUsuario usuario) {
        this.usuario = usuario;
    }
    
    public BeanAgendamento(Long id) {
        this.id = id;
    }

    public BeanAgendamento(Long id, BeanContato contato, BeanUsuario usuario, Date data_agendada, Date hora_agendada, String descricao, String conteudo) {
        this.id = id;
        this.contato = contato;
        this.usuario = usuario;
        this.data_agendada = data_agendada;
        this.hora_agendada = hora_agendada;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }

    public BeanAgendamento(BeanContato contato, BeanUsuario usuario, Date data_agendada, Date hora_agendada, String descricao, String conteudo) {
        this.contato = contato;
        this.usuario = usuario;
        this.data_agendada = data_agendada;
        this.hora_agendada = hora_agendada;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }

    public BeanAgendamento(Date data_agendada, Date hora_agendada, String descricao, String conteudo) {
        this.data_agendada = data_agendada;
        this.hora_agendada = hora_agendada;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeanContato getContato() {
        return contato;
    }

    public void setContato(BeanContato contato) {
        this.contato = contato;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public Date getData_agendada() {
        return data_agendada;
    }

    public void setData_agendada(Date data_agendada) {
        this.data_agendada = data_agendada;
    }

    public Date getHora_agendada() {
        return hora_agendada;
    }

    public void setHora_agendada(Date hora_agendada) {
        this.hora_agendada = hora_agendada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "BeanAgendamento{" + "id=" + id + ", contato=" + contato + ", usuario=" + usuario + ", \ndata_agendada=" + data_agendada + ", hora_agendada=" + hora_agendada + ", descricao=" + descricao + ", \nconteudo=" + conteudo + '}';
    }
}
