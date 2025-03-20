package sn.sdley.queueManagementSystem.controllers;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.sdley.queueManagementSystem.dto.QueueInfo;
import sn.sdley.queueManagementSystem.model.Admin;
import sn.sdley.queueManagementSystem.model.FileAttente;
import sn.sdley.queueManagementSystem.service.AdminService;
import sn.sdley.queueManagementSystem.service.FileAttenteService;
import sn.sdley.queueManagementSystem.service.TicketService;

import java.util.List;

// AdminController.java
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FileAttenteService fileAttenteService;

    @GetMapping
    public String listAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);

        return "admin"; // retourne la vue admin.jsp correspondant a la liste des admins
    }

    @GetMapping("/add")
    public String newAdmin(Model model) {
        model.addAttribute("admin", new Admin());

        return "add-admin"; // formulaire d'ajout d'un admin
    }

    @PostMapping("/add")
    public String addAdmin(@ModelAttribute Admin admin) {
        adminService.createAdmin(admin);

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

    @GetMapping("/dashboard")
    public String getAdminDashboard(Model model) {
        List<QueueInfo> queues = ticketService.getQueuesOverview();
        model.addAttribute("queues", queues);
        return "admin-dashboard";
    }


}
