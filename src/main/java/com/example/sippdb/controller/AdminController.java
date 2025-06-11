package com.example.sippdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboardAdmin() {
        return "admin_dashboard";
    }

    // ===== CRUD Data Siswa =====
    @GetMapping("/siswa")
    public String listSiswa(Model model) {
        // model.addAttribute("siswaList", siswaService.findAll());
        return "admin_siswa_list";
    }

    @GetMapping("/siswa/tambah")
    public String tambahSiswaForm(Model model) {
        // model.addAttribute("siswa", new Siswa());
        return "admin_siswa_form";
    }

    @PostMapping("/siswa/tambah")
    public String tambahSiswa(/* @ModelAttribute Siswa siswa */) {
        // siswaService.save(siswa);
        return "redirect:/admin/siswa";
    }

    @GetMapping("/siswa/edit/{id}")
    public String editSiswaForm(@PathVariable Long id, Model model) {
        // model.addAttribute("siswa", siswaService.findById(id));
        return "admin_siswa_form";
    }

    @PostMapping("/siswa/edit/{id}")
    public String editSiswa(@PathVariable Long id/*, @ModelAttribute Siswa siswa */) {
        // siswaService.update(id, siswa);
        return "redirect:/admin/siswa";
    }

    @PostMapping("/siswa/hapus/{id}")
    public String hapusSiswa(@PathVariable Long id) {
        // siswaService.delete(id);
        return "redirect:/admin/siswa";
    }

    // ===== CRUD Data Pendaftaran (contoh) =====
    @GetMapping("/pendaftaran")
    public String listPendaftaran(Model model) {
        // model.addAttribute("pendaftaranList", pendaftaranService.findAll());
        return "admin_pendaftaran_list";
    }

    @GetMapping("/pendaftaran/edit/{id}")
    public String editPendaftaranForm(@PathVariable Long id, Model model) {
        // model.addAttribute("pendaftaran", pendaftaranService.findById(id));
        return "admin_pendaftaran_form";
    }

    @PostMapping("/pendaftaran/edit/{id}")
    public String editPendaftaran(@PathVariable Long id/*, @ModelAttribute Pendaftaran pendaftaran */) {
        // pendaftaranService.update(id, pendaftaran);
        return "redirect:/admin/pendaftaran";
    }

    @PostMapping("/pendaftaran/hapus/{id}")
    public String hapusPendaftaran(@PathVariable Long id) {
        // pendaftaranService.delete(id);
        return "redirect:/admin/pendaftaran";
    }
}
