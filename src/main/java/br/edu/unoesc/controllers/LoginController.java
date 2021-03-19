package br.edu.unoesc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.dto.LoginDto;
import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login/sing-in")
    public String singIn(@Valid LoginDto loginDto, BindingResult result, RedirectAttributes redirectAttributes) {        
        if (result.hasErrors()) {
            return "login";
        }        
        String page = "";
        Usuario user  = userRepository.findByCpf(loginDto.getCPF());
        if (user != null) {
            
            if (user.getSenha().equals(loginDto.getSenha())) {
                // if (u.getRole().getName().equalsIgnoreCase("user")) {
                //     page = "userpage";
                // } else {
                //     page = "adminpage";
                // }
                page = "redirect:/home/menu";
            }
            else{
                redirectAttributes.addFlashAttribute("message", "Senha incorreta!");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute(loginDto);                
                page = "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "CPF não encontrado!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute(loginDto);             
            page = "redirect:/login";
        }
        return page;
    }

    @GetMapping("/")
    public String login_via_home() {
        return "redirect:/login";
    }
}
