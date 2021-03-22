package br.edu.unoesc.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import br.edu.unoesc.models.Perfil;
import br.edu.unoesc.models.Usuario;
public class UsuarioDto {

    private Long id;
    
    @NotBlank(message= "O código é obrigatório")
    private String codigo;
    @NotBlank(message= "O nome é obrigatório")
    private String nome;
    @CPF
    private String cpf;
    @NotBlank(message= "A senha é obrigatória")
    private String senha;

    private Set<PerfilDto> perfilsDto;
    
    private PerfilDto perfilDto;

    private int id_perfil;
    
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

    Set<Perfil>prepararSetPerfils(){
        Set<Perfil> perfils = new HashSet<Perfil>();
        // for(PerfilDto perfil : perfilsDto){
        //     perfils.add(perfil.toPerfil());
        // }
        perfils.add(perfilDto.toPerfil());
        return perfils;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Set<PerfilDto> getPerfilsDto() {
        return perfilsDto;
    }
    public void setPerfilsDto(Set<PerfilDto> perfils) {
        this.perfilsDto = perfils;
        for (PerfilDto p : perfilsDto){
            this.id_perfil = (int) (long) p.getId();
        }         
    }
    public PerfilDto getPerfilDto() {
        return perfilDto;
    }
    public void setPerfilDto(PerfilDto perfilDto) {
        this.perfilDto = perfilDto;
    }
    public int getId_perfil() {
        return id_perfil;
    }
    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
   
    
}
