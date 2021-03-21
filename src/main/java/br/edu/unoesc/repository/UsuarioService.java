package br.edu.unoesc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.edu.unoesc.models.Usuario;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository userRepository;

    public Usuario getUsuarioLogado(){
        // Recuperando o usuario autenticado ALUNO, que será inserido na inscrição
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByCpf((((UserDetails)authentication.getPrincipal()).getUsername()));         
    }
}
