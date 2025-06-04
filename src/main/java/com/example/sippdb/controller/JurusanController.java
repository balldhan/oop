package com.example.sippdb.controller;

import com.example.sippdb.model.Jurusan;
import com.example.sippdb.repository.JurusanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JurusanController {

    @Autowired
    private JurusanRepository jurusanRepository;

    @GetMapping("/admin/jurusan/fragment")
    public String adminJurusanFragment(Model model) {
        model.addAttribute("listJurusan", jurusanRepository.findAll());
        return "admin/fragments/jurusan :: content";
    }

    @PostMapping("/admin/jurusan/tambah")
    public String tambahJurusan(@ModelAttribute Jurusan jurusan) {
        // Pastikan Jurusan memiliki field kuota dan setter-nya
        jurusanRepository.save(jurusan);
        return "redirect:/admin/jurusan/fragment";
    }

    @PostMapping("/admin/jurusan/edit/{id}")
    public String editJurusan(@PathVariable Long id, @ModelAttribute Jurusan jurusan) {
        jurusan.setId(id);
        // Field kuota otomatis terisi dari form jika entity benar
        jurusanRepository.save(jurusan);
        return "redirect:/admin/jurusan/fragment";
    }

    @PostMapping("/admin/jurusan/hapus/{id}")
    public String hapusJurusan(@PathVariable Long id) {
        jurusanRepository.deleteById(id);
        return "redirect:/admin/jurusan/fragment";
    }
}