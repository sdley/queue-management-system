package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.sdley.queueManagementSystem.model.FileAttente;
import sn.sdley.queueManagementSystem.service.FileAttenteService;

import java.util.List;

@Controller
@RequestMapping("/fileattente")
public class FileAttenteController {

    @Autowired
    private FileAttenteService fileAttenteService;

    @GetMapping
    public String listFileAttente(Model model){
        model.addAttribute("filesAttente", fileAttenteService.getAllFilesAttente());
        return "fileAttente"; // retourne fileAttente.jsp
    }

    // logique a revoir
    @GetMapping("/add")
    public String addFileAttente(Model model){
        model.addAttribute("fileAttente", new FileAttente());
        return "addFileAttente"; // retourne addFileAttente.jsp
    }

    @PostMapping("/add")
    public String addFileAttente(FileAttente fileAttente){
        fileAttenteService.createFileAttente(fileAttente);
        return "redirect:/fileattente"; // Redirige vers la liste des files d'attente
    }

//    @GetMapping("/delete")
}
