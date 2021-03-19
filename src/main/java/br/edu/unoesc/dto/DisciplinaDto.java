package br.edu.unoesc.dto;

import javax.validation.constraints.NotBlank;

import br.edu.unoesc.models.Disciplina;

public class DisciplinaDto {

    private Long id;
    
    @NotBlank(message= "O código é obrigatório")
    private String codigo;
    @NotBlank(message= "O nome é obrigatório")
    private String nome;

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

    public Disciplina toDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setCodigo(codigo);
        disciplina.setNome(nome);

        return disciplina;
    }
    
    
}
