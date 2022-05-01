package br.com.agenda_web.model.bean;
/**
 *
 * @author gustavo
 */
public class BeanEnderecoContato {
    
    private Long id;
    private BeanEndereco endereco;
    private BeanContato contato;
    private String obs;
    private BeanUsuario usuario;

    public BeanEnderecoContato(Long id) {
        this.id = id;
    }

    public BeanEnderecoContato(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanEnderecoContato(BeanEndereco endereco, BeanUsuario usuario) {
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public BeanEnderecoContato(BeanContato contato, BeanUsuario usuario) {
        this.contato = contato;
        this.usuario = usuario;
    }
    
    
    public BeanEnderecoContato(Long id, BeanEndereco endereco, BeanContato contato, String obs) {
        this.id = id;
        this.endereco = endereco;
        this.contato = contato;
        this.obs = obs;
    }

    public BeanEnderecoContato(Long id, BeanEndereco endereco, BeanContato contato, String obs, BeanUsuario usuario) {
        this.id = id;
        this.endereco = endereco;
        this.contato = contato;
        this.obs = obs;
        this.usuario = usuario;
    }

    public BeanEnderecoContato(BeanEndereco endereco, BeanContato contato, String obs, BeanUsuario usuario) {
        this.endereco = endereco;
        this.contato = contato;
        this.obs = obs;
        this.usuario = usuario;
    }

    public BeanEnderecoContato(Long id, BeanUsuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeanEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(BeanEndereco endereco) {
        this.endereco = endereco;
    }

    public BeanContato getContato() {
        return contato;
    }

    public void setContato(BeanContato contato) {
        this.contato = contato;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "BeanEnderecoContato{" + "id=" + id + ", endereco=" + endereco + ", contato=" + contato + ", obs=" + obs + ", usuario=" + usuario + '}';
    }
}
