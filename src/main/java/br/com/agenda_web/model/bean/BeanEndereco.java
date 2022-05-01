package br.com.agenda_web.model.bean;
/**
 *
 * @author gustavo
 */
public class BeanEndereco {
 
    private Long id;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private BeanUsuario usuario;

    public BeanEndereco(Long id, BeanUsuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }
    
	public BeanEndereco(Long id, String bairro, String cidade, String estado, String cep, String logradouro, String numero, String complemento) {
        this.id = id;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }
    
    

    public BeanEndereco(String bairro, String cidade, String estado, String cep, String logradouro, String numero, String complemento, BeanUsuario usuario) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.usuario = usuario;
    }

    public BeanEndereco(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanEndereco(Long id, String bairro, String cidade, String estado, String cep, String logradouro, String numero, String complemento, BeanUsuario usuario) {
        this.id = id;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanEndereco(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "BeanEndereco{" + "id=" + id + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", usuario=" + usuario + '}';
    }
}
