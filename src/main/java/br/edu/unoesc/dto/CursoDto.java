package br.edu.unoesc.dto;

import javax.validation.constraints.NotBlank;

public class CursoDto {

    private Long id;
    @NotBlank(message="O código é obrigatório")
    private String codigo;
    @NotBlank(message="O nome é obrigatório")
    private String nome;
    @NotBlank(message="A quantidade de vagas é obrigatória")
    private Integer vagas;



    
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
    public Integer getVagas() {
        return vagas;
    }
    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }    
}
