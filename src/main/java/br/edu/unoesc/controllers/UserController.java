package br.edu.unoesc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.dto.UsuarioDto;
import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.UsuarioRepository;

@Controller
@RequestMapping("usuario")
public class UserController {

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping("cadastro")
    public String user_cad(UsuarioDto userDto){

        return "usuario/cadastro";
    }
    @PostMapping("novo")
    public String novo(@Valid UsuarioDto userDto, BindingResult result, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar usuário!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        redirectAttributes.addFlashAttribute(userDto);

        if(result.hasErrors()){
            return "usuario/cadastro";    
        }
        if (result.hasErrors()) {
            return "redirect:/suggest-event";
        }
        redirectAttributes.addFlashAttribute("message", "Usuário salvo com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        Usuario user = userDto.toUsuario();
        userRepository.save(user);
        return "redirect:/usuario/cadastro";
    }
}
