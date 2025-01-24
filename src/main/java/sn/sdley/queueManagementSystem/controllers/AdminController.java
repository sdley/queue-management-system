package sn.sdley.queueManagementSystem.controllers;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.Admin;
import sn.sdley.queueManagementSystem.service.AdminService;
import sn.sdley.queueManagementSystem.service.FileAttenteService;

import java.util.List;

// AdminController.java
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String listAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
//        model.addAttribute("adminsList", admins);

//            System.out.println("\nListe des admins [objet]: \n" + admins);
        model.addAttribute("admins", admins);

//        System.out.println("id Admin : "+ admins.get(0).getId());

        // print admins in the console using for each loop in json format
//        for (Admin admin : admins) {
//            System.out.println("id Admin : "+admin.getId());
//            System.out.println("Nom Admin : "+admin.getNom());
//            System.out.println("Prenom Admin : "+admin.getPrenom());
//        }


        return "admin"; // retourne la vue admin.jsp correspondant a la liste des admins
    }

    @GetMapping("/add")
    public String newAdmin(Model model) {
        model.addAttribute("admin", new Admin());
//        System.out.println("New admin: " + new Admin());
        return "add-admin"; // formulaire d'ajout d'un admin
    }

    @PostMapping("/add")
    public String addAdmin(@ModelAttribute Admin admin) {
        adminService.createAdmin(admin);
//        System.out.println("New admin cree avec succes: " + admin);
        return "redirect:/admin"; // redirige vers la liste des admins
    }

    // edit admin
    @GetMapping("/edit/{id}")
    public String editAdmin(@PathVariable Long id, Model model) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);

        return "edit-admin"; // formulaire de modification d'un admin

    }

    // delete admin
    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
//        adminService.deleteAdmin(id);
        System.out.println("\nDelete id: " + id);
        return "redirect:/admin"; // redirige vers la liste des admins
    }

}
