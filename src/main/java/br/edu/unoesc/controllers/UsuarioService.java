package br.edu.unoesc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.UsuarioRepository;
import br.edu.unoesc.security.MyUserDetails;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository userRepository;

    public Usuario getUsuarioLogado(){
        // Recuperando o usuario autenticado ALUNO, que será inserido na inscrição
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByCpf((((UserDetails)authentication.getPrincipal()).getUsername()));         
    }

    public boolean userIsAnonymous(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
            return (userRepository.findByCpf(user.getUsername())).getId() <= 0; 
        }catch(Exception e){
            return true;
        }
    }
}
