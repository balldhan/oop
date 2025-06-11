package com.example.sippdb.controller;

import com.example.sippdb.model.Role;
import com.example.sippdb.model.User;
import com.example.sippdb.repository.RoleRepository;
import com.example.sippdb.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Simpan username ke session agar bisa diakses di controller lain
            session.setAttribute("username", user.getUsername());
            if (user.getRole() != null && user.getRole().getName().name().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin/dashboard";
            } else if (user.getRole() != null && user.getRole().getName().name().equalsIgnoreCase("SISWA")) {
                return "redirect:/siswa/dashboard";
            } else {
                return "redirect:/dashboard";
            }
        }
        model.addAttribute("error", "Username atau password salah");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username sudah digunakan");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Role siswaRole = roleRepository.findByName(Role.RoleName.SISWA);
        user.setRole(siswaRole);
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }
}
