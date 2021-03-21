package br.edu.unoesc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.unoesc.models.Curso;
import br.edu.unoesc.models.Inscricao;
import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.CursoRepository;
import br.edu.unoesc.repository.InscricaoRepository;
import br.edu.unoesc.repository.UsuarioService;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {
    
    @Autowired
    InscricaoRepository inscricaoRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("/inscricoes")
    public String exibirInscricao(Model model){

        Usuario userLogado = usuarioService.getUsuarioLogado();
        if (userLogado == null){
            model.addAttribute("inscricao", new Inscricao());
            return "inscricao/inscricoes";
        }
        Inscricao inscricao = inscricaoRepository.findByUsuario(userLogado);
        if(inscricao == null){
            model.addAttribute("inscricao", new Inscricao());
            return "inscricao/inscricoes";            
        }

        // Se já existe inscrição, alimenta os models
        // para listar os detalhes
        model.addAttribute("inscricao", inscricao);
        model.addAttribute("curso", inscricao.getCurso());
        model.addAttribute("usuario", inscricao.getUsuario());
        model.addAttribute("disciplinas", inscricao.getCurso().getDisciplinas());

        return "inscricao/inscricoes";
    }

    @GetMapping("/new")
    public String showInscricaoForm(Model model){       
        List<Curso> listaCursos = cursoRepository.findAll();
        model.addAttribute("inscricao", new Inscricao());
        model.addAttribute("listaCursos", listaCursos);
        return "inscricao/inscricao_form";
    }      

    @PostMapping("/save")
    public String saveInscricao(Inscricao inscricao, Model model){
        Usuario userLogado = usuarioService.getUsuarioLogado();
        inscricao.setUsuario(userLogado);
        inscricaoRepository.save(inscricao);
        model.addAttribute("inscricao", inscricao);
        return "redirect:/inscricao/inscricoes";
    }    
    
    @GetMapping("edit/{id}")
    public String showEditInscricaoForm(@PathVariable("id") Long id, Model model){
        List<Curso> listaCursos = cursoRepository.findAll();
        Inscricao inscricao = inscricaoRepository.findById(id).get();
        model.addAttribute("inscricao", inscricao);
        model.addAttribute("listaCursos", listaCursos);
        return "inscricao/inscricao_form";
    }     
}
