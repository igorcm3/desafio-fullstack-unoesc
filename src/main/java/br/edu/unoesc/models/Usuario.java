package br.edu.unoesc.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.edu.unoesc.dto.UsuarioDto;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String cpf;
    private String senha;
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_perfil",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Perfil> perfil = new HashSet<>();    


    public Usuario(Long id, String codigo, String nome, String cpf, String senha, boolean enabled, Set<Perfil> perfil) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.enabled = enabled;
        this.perfil = perfil;
    }
    
    public Usuario(){}

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

    public UsuarioDto toUsuarioDto(){
        UsuarioDto userDto = new UsuarioDto();
        userDto.setCodigo(codigo);
        userDto.setCpf(cpf);
        userDto.setNome(nome);
        userDto.setSenha(senha);
        userDto.setId(id);
        return userDto;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set<Perfil> getPerfil() {
        return perfil;
    }
    public void setPerfil(Set<Perfil> perfil) {
        this.perfil = perfil;
    }

}
