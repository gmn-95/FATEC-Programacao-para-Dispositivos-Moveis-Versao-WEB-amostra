package br.com.agenda_web.model.bean;


/**
 *
 * @author gustavo
 */
public class BeanContato {
    
    private Long id;
    private BeanUsuario usuario;
    private String nome;
    private String telefone_fixo;
    private String celular;
    private String email;
    private String obs;
    
    public BeanContato() {
    }

    public BeanContato(Long id, BeanUsuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }
    
    public BeanContato(String nome) {
        this.nome = nome;
    }

    public BeanContato(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    

	public BeanContato(BeanUsuario usuario, String nome, String telefone_fixo, String celular, String email, String obs) {
        this.usuario = usuario;
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.email = email;
        this.obs = obs;
    }
    
    public BeanContato(Long id, String nome, String telefone_fixo, String celular, String email, String obs) {
        this.id = id;
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.email = email;
        this.obs = obs;
    }

    public BeanContato(Long id, BeanUsuario usuario, String nome, String telefone_fixo, String celular, String email, String obs) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.email = email;
        this.obs = obs;
    }

    public BeanContato(String nome, String telefone_fixo, String celular, String email, String obs) {
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.email = email;
        this.obs = obs;
    }

    public BeanContato(Long id) {
        this.id = id;
    }

    public BeanContato(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone_fixo() {
        return telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "BeanContato{" + "id=" + id + ", usuario=" + usuario + ", nome=" + nome + ", telefone_fixo=" + telefone_fixo + ", celular=" + celular + ", email=" + email + ", obs=" + obs + '}';
    }

}
