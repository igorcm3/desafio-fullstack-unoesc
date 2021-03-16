package br.edu.unoesc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String login(Model model) {
        // List<Pedido> pedirdos = repo.findAll();
        // model.addAttribute("pedidos", pedidos);
        return "login";
    }  
    
	@GetMapping("/")
    public String login_via_home(Model model) {
        // List<Pedido> pedirdos = repo.findAll();
        // model.addAttribute("pedidos", pedidos);
        return "login";
    }     
}
