package br.edu.unoesc.models;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Inscricao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    @OneToOne
    private Usuario Usuario;

    @OneToMany
    private Set<Curso> cursos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
        
}
