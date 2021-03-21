package br.edu.unoesc.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("report")
public class ReportsController {
    
    @Autowired
    ReportService reportService;

    @GetMapping("/disciplina/{format}")
    public String generatedReport(@PathVariable String format, Model model, RedirectAttributes redirectAttributes) throws FileNotFoundException, JRException{
        String path = reportService.exportReport(format);
        redirectAttributes.addFlashAttribute("messageDoc", path);
        redirectAttributes.addFlashAttribute("alertClass", "alert-primary");
        return "redirect:/disciplina/disciplinas";
    }



}
