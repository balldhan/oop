package com.example.sippdb.controller;

import com.example.sippdb.model.Jalur;
import com.example.sippdb.repository.JalurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JalurController {

    @Autowired
    private JalurRepository jalurRepository;

    @GetMapping("/admin/jalur/fragment")
    public String adminJalurFragment(Model model) {
        model.addAttribute("listJalur", jalurRepository.findAll());
        return "admin/fragments/jalur :: content";
    }

    @PostMapping("/admin/jalur/tambah")
    public String tambahJalur(@ModelAttribute Jalur jalur) {
        jalurRepository.save(jalur);
        return "redirect:/admin/jalur/fragment";
    }

    @PostMapping("/admin/jalur/edit/{id}")
    public String editJalur(@PathVariable Long id, @ModelAttribute Jalur jalur) {
        jalur.setId(id);
        jalurRepository.save(jalur);
        return "redirect:/admin/jalur/fragment";
    }

    @PostMapping("/admin/jalur/hapus/{id}")
    public String hapusJalur(@PathVariable Long id) {
        jalurRepository.deleteById(id);
        return "redirect:/admin/jalur/fragment";
    }
}