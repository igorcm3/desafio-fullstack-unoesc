package br.edu.unoesc.controllers;

import java.util.ArrayList;
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

import br.edu.unoesc.models.Disciplina;
import br.edu.unoesc.models.Usuario;
import br.edu.unoesc.repository.DisciplinaRepository;
import br.edu.unoesc.repository.UsuarioRepository;

@Controller
@RequestMapping("disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/disciplinas")
    public String listarDisciplina(Model model) {
        Usuario userLogado = usuarioService.getUsuarioLogado();
        List<Disciplina> disciplinas;
        if (userLogado.isProfessor()) {
            disciplinas = disciplinaRepository.findByIdProfessor(userLogado.getId());
        } else {
            // Se não é professor, então é admin e tem acesso a todos.
            disciplinas = disciplinaRepository.findAll();
        }
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/disciplinas";
    }

    @GetMapping("new")
    public String showDisciplinaForm(Model model) {
        // Perfil = 2 -> Professor
        List<Usuario> listaUsers = usuarioRepository.findAll();
        List<Usuario> listaProfessores = new ArrayList<>();
        // TODO: Implementar filtro para listar professores direto pela consulta
        for (Usuario user : listaUsers) {
            if (user.isProfessor()) {
                listaProfessores.add(user);
            }
        }
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("listaProfessores", listaProfessores);
        return "disciplina/disciplina_form";
    }

    @GetMapping("/edit/{id}")
    public String disciplinaEdicao(@PathVariable("id") Long id, Model model) {
        Disciplina disciplina = disciplinaRepository.findById(id).get();
        List<Usuario> listaUsers = usuarioRepository.findAll();
        List<Usuario> listaProfessores = new ArrayList<>();
        // TODO: Implementar filtro para listar professores direto pela consulta
        for (Usuario user : listaUsers) {
            if (user.isProfessor()) {
                listaProfessores.add(user);
            }
        }
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("listaProfessores", listaProfessores);

        return "/disciplina/disciplina_form";
    }

    @GetMapping("/delete/{id}")
    public String excluirDisciplina(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Disciplina excluida!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        disciplinaRepository.deleteById(id);
        return "redirect:/disciplina/disciplinas";
    }

    @PostMapping("/save")
    public String novaDisciplina(@Valid Disciplina disciplina, BindingResult result,
            RedirectAttributes redirectAttributes, Model model) {
        redirectAttributes.addFlashAttribute("message", "Erro ao salvar disciplina!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        redirectAttributes.addFlashAttribute(disciplina);
        if (result.hasErrors()) {
            List<Usuario> listaUsers = usuarioRepository.findAll();
            List<Usuario> listaProfessores = new ArrayList<>();
            // TODO: Implementar filtro para listar professores direto pela consulta
            for (Usuario user : listaUsers) {
                if (user.isProfessor()) {
                    listaProfessores.add(user);
                }
            }
            model.addAttribute("listaProfessores", listaProfessores);
            model.addAttribute("disciplina", disciplina);
            return "/disciplina/disciplina_form";
        }
        String operacao = disciplina.getId() == null ? "inserida" : "alterada";
        redirectAttributes.addFlashAttribute("message", "Disciplina " + operacao + " com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        redirectAttributes.addFlashAttribute(disciplina);
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplina/disciplinas";
    }

}
