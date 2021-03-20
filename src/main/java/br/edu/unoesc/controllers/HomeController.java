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

import br.edu.unoesc.dto.CursoDto;
import br.edu.unoesc.dto.DisciplinaDto;
import br.edu.unoesc.models.Curso;
import br.edu.unoesc.models.Disciplina;
import br.edu.unoesc.repository.CursoRepository;
import br.edu.unoesc.repository.DisciplinaRepository;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("menu")
    public String home(){
        return "menu/home";
    }  
    
    
    /*------------------ Disciplinas -----------------------*/

    @GetMapping("disciplina/viewDisciplinas")
    public String viewDisciplina(Model model){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/viewDisciplinas";
    }
    @GetMapping("disciplina/cadastroDisciplina")
    public String disciplinaCadastro(DisciplinaDto disciplinaDto){
        return "disciplina/cadastroDisciplina";
    }

    @GetMapping("/disciplina/cadastroDisciplina/{id}")
    public String disciplinaEdicao(@PathVariable("id") Long id, Model model){
        Disciplina disciplina = disciplinaRepository.getOne(id); 
        model.addAttribute("disciplinaDto", disciplina.toDisciplinaDto());
        return "/disciplina/cadastroDisciplina";
    }    

    @GetMapping("/disciplina/delete/{id}")
    public String excluirDisciplina(@PathVariable("id") Long id){        
        disciplinaRepository.deleteById(id);           
        return "redirect:/home/disciplina/viewDisciplinas";
    }

    @PostMapping("/disciplina/novo")
    public String novaDisciplina(@Valid DisciplinaDto disciplinaDto, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar disciplina!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        redirectAttributes.addFlashAttribute(disciplinaDto);
        if(result.hasErrors()){
            return "/disciplina/cadastroDisciplina";    
        }
        Disciplina disciplina = disciplinaDto.toDisciplina();

        String operacao = disciplinaDto.getId() == null ? "inserida" : "alterada";
        redirectAttributes.addFlashAttribute("message", "Disciplina "+operacao+" com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        redirectAttributes.addFlashAttribute(disciplina.toDisciplinaDto());
        disciplinaRepository.save(disciplina);
        return "redirect:/home/disciplina/cadastroDisciplina";        
    }   


    /*---------- Cursos----------*/

    @GetMapping("curso/viewCursos")
    public String viewCursos(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "curso/viewCursos";
    }
    
    @GetMapping("curso/cadastroCurso")
    public String cursoCadastro(CursoDto cursoDto){
        return "curso/cadastroCurso";
    }    



    
}
