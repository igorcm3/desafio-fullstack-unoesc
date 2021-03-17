package br.edu.unoesc.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/cadastro/{id}")
    public String user_cad_edicao(@PathVariable("id") Long id, Model model){
        Usuario user = userRepository.getOne(id); 
        model.addAttribute("usuarioDto", user.toUsuarioDto());
        return "/usuario/cadastro";
    }

    @PostMapping("/novo")
    public String novo(@Valid UsuarioDto userDto, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar usuário!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        redirectAttributes.addFlashAttribute(userDto);
        if(result.hasErrors()){
            return "usuario/cadastro";    
        }
        if (userDto.getId() == null) {
            redirectAttributes.addFlashAttribute("message", "Usuário salvo com sucesso!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            Usuario user = userDto.toUsuario();
            userRepository.save(user);

            redirectAttributes.addFlashAttribute(user.toUsuarioDto());
            return "redirect:/usuario/cadastro";
        }else {
            redirectAttributes.addFlashAttribute("message", "Usuário editado com sucesso!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            Usuario user = userDto.toUsuario();
            userRepository.save(user);
            redirectAttributes.addFlashAttribute(user.toUsuarioDto());
            return "redirect:/usuario/cadastro";            
        }

        
    }

    @GetMapping("viewUsuarios")
    public String viewUsuarios(Model model){
        List<Usuario> usuarios = userRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario/viewUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String excluirUsuario(@PathVariable("id") Long id){        
        userRepository.deleteById(id);           
        return "redirect:/usuario/viewUsuarios";
    }

}
