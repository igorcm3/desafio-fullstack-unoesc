package br.edu.unoesc.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message="O CPF não pode estar vazio.")
    private String CPF;
    @NotBlank(message="A senha não pode estar vazia.")
    private String senha;
    
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
