package br.edu.unoesc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.unoesc.dto.DisciplinaDto;
 
@Entity
public class Disciplina {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;

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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    } 

    public DisciplinaDto toDisciplinaDto(){
        DisciplinaDto disciplinaDto = new DisciplinaDto();
        disciplinaDto.setId(id);
        disciplinaDto.setCodigo(codigo);
        disciplinaDto.setNome(nome);
        return disciplinaDto;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
