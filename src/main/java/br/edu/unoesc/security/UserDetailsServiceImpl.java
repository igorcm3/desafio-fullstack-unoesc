package br.edu.unoesc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario user = userRepository.findByCpf(cpf);

        if (user == null) {
            throw new UsernameNotFoundException("CPF não encontrado.");
        }

        return new MyUserDetails(user);
    }

}
