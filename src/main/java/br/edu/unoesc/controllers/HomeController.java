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

import br.edu.unoesc.models.Curso;
import br.edu.unoesc.models.Disciplina;
import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.CursoRepository;
import br.edu.unoesc.repository.DisciplinaRepository;
import br.edu.unoesc.repository.UsuarioRepository;
import br.edu.unoesc.repository.UsuarioService;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;    

    @GetMapping("menu")
    public String home(){
        return "menu/home";
    }  
    
    
    /*------------------ Disciplinas -----------------------*/

    @GetMapping("disciplina/viewDisciplinas")
    public String viewDisciplina(Model model){
        Usuario userLogado = usuarioService.getUsuarioLogado();
        List<Disciplina> disciplinas;
        if(userLogado.isProfessor()){
            disciplinas = disciplinaRepository.findByIdProfessor(userLogado.getId());
        }else{
            // Se não é professor, então é admin e tem acesso a todos.
            disciplinas = disciplinaRepository.findAll();
        }
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/viewDisciplinas";
    }
    @GetMapping("disciplina/cadastroDisciplina")
    public String showDisciplinaForm(Model model){
        //  Perfil = 2 -> Professor
        List<Usuario> listaProfessores = usuarioRepository.findAll();
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("listaProfessores", listaProfessores);
        return "disciplina/cadastroDisciplina";
    }

    @GetMapping("/disciplina/cadastroDisciplina/{id}")
    public String disciplinaEdicao(@PathVariable("id") Long id, Model model){
        Disciplina disciplina = disciplinaRepository.findById(id).get(); 
        model.addAttribute("disciplina", disciplina);
        return "/disciplina/cadastroDisciplina";
    }    

    @GetMapping("/disciplina/delete/{id}")
    public String excluirDisciplina(@PathVariable("id") Long id){        
        disciplinaRepository.deleteById(id);           
        return "redirect:/home/disciplina/viewDisciplinas";
    }

    @PostMapping("/disciplina/novo")
    public String novaDisciplina(@Valid Disciplina disciplina, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar disciplina!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        redirectAttributes.addFlashAttribute(disciplina);
        if(result.hasErrors()){
            return "/disciplina/cadastroDisciplina";    
        }

        String operacao = disciplina.getId() == null ? "inserida" : "alterada";
        redirectAttributes.addFlashAttribute("message", "Disciplina "+operacao+" com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        redirectAttributes.addFlashAttribute(disciplina);
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
    
    @GetMapping("curso/new")
    public String cursoCadastro(Model model){
        List<Disciplina> listaDisciplinas = disciplinaRepository.findAll();
        model.addAttribute("curso", new Curso());
        model.addAttribute("listaDisciplinas", listaDisciplinas);

        return "curso/cadastroCurso";
    }    

    @PostMapping("curso/save")
    public String saveCurso(Curso curso){
        cursoRepository.save(curso);
        return "redirect:/home/curso/viewCursos";
    }

    @GetMapping("curso/edit/{id}")
    public String showEditCursoForm(@PathVariable("id") Long id, Model model){
        Curso curso = cursoRepository.findById(id).get(); 
        List<Disciplina> listaDisciplinas = disciplinaRepository.findAll();
        model.addAttribute("curso", curso);
        model.addAttribute("listaDisciplinas", listaDisciplinas);
        return "/curso/cadastroCurso";
    } 

    @GetMapping("/curso/delete/{id}")
    public String deleteCurso(@PathVariable("id") Long id){        
        cursoRepository.deleteById(id);           
        return "redirect:/home/curso/viewCursos";
    }    





    
}
