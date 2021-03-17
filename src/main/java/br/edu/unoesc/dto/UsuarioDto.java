package br.edu.unoesc.dto;

import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import br.edu.unoesc.models.Usuario;
public class UsuarioDto {

    private Long id;
    
    @NotBlank(message= "O código é obrigatório")
    private String codigo;
    @NotBlank(message= "O nome é obrigatório")
    private String nome;
    @NotBlank(message= "O CPF é obrigatório")
    private String cpf;
    @NotBlank(message= "A senha é obrigatória")
    private String senha;
    
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Usuario toUsuario() {
        Usuario user = new Usuario();
        user.setCodigo(codigo);
        user.setCpf(cpf);
        user.setNome(nome);
        user.setSenha(senha);
        user.setId(id);
        return user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }    
    
}
