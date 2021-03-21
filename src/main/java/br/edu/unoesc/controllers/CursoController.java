package br.edu.unoesc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.models.Curso;
import br.edu.unoesc.models.Disciplina;
import br.edu.unoesc.models.Inscricao;
import br.edu.unoesc.repository.CursoRepository;
import br.edu.unoesc.repository.DisciplinaRepository;
import br.edu.unoesc.repository.InscricaoRepository;
import br.edu.unoesc.repository.UsuarioRepository;

@Controller
@RequestMapping("curso")
public class CursoController {


    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;    
    @Autowired
    InscricaoRepository inscricaoRepository;
        
    @GetMapping("cursos") 
    public String listarCursos(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "curso/cursos";
    }
    
    @GetMapping("/new")
    public String cursoCadastro(Model model){
        List<Disciplina> listaDisciplinas = disciplinaRepository.findAll();
        model.addAttribute("curso", new Curso());
        model.addAttribute("listaDisciplinas", listaDisciplinas);

        return "curso/curso_form";
    }    

    @PostMapping("/save")
    public String saveCurso(Curso curso, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar curso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");        
        String operacao = curso.getId() == null ? "inserido" : "alterado";
        redirectAttributes.addFlashAttribute("message", "Curso "+operacao+" com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
        cursoRepository.save(curso);     
        return "redirect:/curso/cursos";
    }

    @GetMapping("/edit/{id}")
    public String showEditCursoForm(@PathVariable("id") Long id, Model model){          
        Curso curso = cursoRepository.findById(id).get(); 
        List<Disciplina> listaDisciplinas = disciplinaRepository.findAll();
        model.addAttribute("curso", curso);
        model.addAttribute("listaDisciplinas", listaDisciplinas);
          
        return "/curso/curso_form";
    } 

    @GetMapping("/delete/{id}")
    public String deleteCurso(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){      

        
        List<Inscricao> listaInscricoesPorCurso = inscricaoRepository.findByIdCurso(id);

        // Se já existem inscritos, não deixa excuir o curso
        if(listaInscricoesPorCurso.size()> 0){
            redirectAttributes.addFlashAttribute("message", "Curso não pode ser excluido pois já existem inscrições no mesmo!");
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Curso excluido!");
            cursoRepository.deleteById(id);
        }
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");           
        return "redirect:/curso/cursos";
    }     
    
}
