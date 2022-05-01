package br.com.agenda_web.model.bean;
/**
 *
 * @author gustavo
 */
public abstract class BeanPessoa {
    
    private Long id;
    private String nome;

    public BeanPessoa() {
    }

    public BeanPessoa(Long id) {
        this.id = id;
    }

    public BeanPessoa(String nome) {
        this.nome = nome;
    }

    public BeanPessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
