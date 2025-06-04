package com.example.sippdb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/fragment")
    public String adminDashboardFragment(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        // Tambahkan data statistik ke model jika perlu
        return "admin/fragments/dashboard :: content";
    }

    @GetMapping("/siswa/profil")
    public String siswaProfil(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "siswa/profil";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/siswa/dashboard")
    public String siswaDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "siswa/dashboard";
    }

    @GetMapping("/siswa/pendaftaran")
    public String siswaPendaftaran(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "siswa/pendaftaran";
    }

}