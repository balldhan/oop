package com.example.sippdb.controller;

import com.example.sippdb.model.Siswa;
import com.example.sippdb.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SiswaController {

    @Autowired
    private SiswaRepository siswaRepository;

    @GetMapping("/admin/siswa/fragment")
    public String adminSiswaFragment(Model model) {
        model.addAttribute("listSiswa", siswaRepository.findAll());
        return "admin/fragments/siswa :: content";
    }

    @PostMapping("/admin/siswa/tambah")
    public String tambahSiswa(@ModelAttribute Siswa siswa) {
        siswaRepository.save(siswa);
        return "redirect:/admin/siswa/fragment";
    }

    @PostMapping("/admin/siswa/edit/{id}")
    public String editSiswa(@PathVariable Long id, @ModelAttribute Siswa siswa) {
        siswa.setId(id);
        siswaRepository.save(siswa);
        return "redirect:/admin/siswa/fragment";
    }

    @PostMapping("/admin/siswa/hapus/{id}")
    public String hapusSiswa(@PathVariable Long id) {
        siswaRepository.deleteById(id);
        return "redirect:/admin/siswa/fragment";
    }
}