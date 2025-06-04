package com.example.sippdb.controller;

import com.example.sippdb.model.Pendaftaran;
import com.example.sippdb.repository.PendaftaranRepository;
import com.example.sippdb.repository.SiswaRepository;
import com.example.sippdb.repository.JurusanRepository;
import com.example.sippdb.repository.JalurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PendaftaranController {

    @Autowired
    private PendaftaranRepository pendaftaranRepository;
    @Autowired
    private SiswaRepository siswaRepository;
    @Autowired
    private JurusanRepository jurusanRepository;
    @Autowired
    private JalurRepository jalurRepository;

    @GetMapping("/admin/pendaftaran/fragment")
    public String adminPendaftaranFragment(Model model) {
        model.addAttribute("listPendaftaran", pendaftaranRepository.findAll());
        model.addAttribute("listSiswa", siswaRepository.findAll());
        model.addAttribute("listJurusan", jurusanRepository.findAll());
        model.addAttribute("listJalur", jalurRepository.findAll());
        return "admin/fragments/pendaftaran :: content";
    }

    @PostMapping("/admin/pendaftaran/tambah")
    public String tambahPendaftaran(@RequestParam Long siswaId,
                                    @RequestParam Long jurusanId,
                                    @RequestParam Long jalurId) {
        Pendaftaran pendaftaran = new Pendaftaran();
        pendaftaran.setSiswa(siswaRepository.findById(siswaId).orElse(null));
        pendaftaran.setJurusan(jurusanRepository.findById(jurusanId).orElse(null));
        pendaftaran.setJalur(jalurRepository.findById(jalurId).orElse(null));
        pendaftaranRepository.save(pendaftaran);
        return "redirect:/admin/pendaftaran/fragment";
    }

    @PostMapping("/admin/pendaftaran/hapus/{id}")
    public String hapusPendaftaran(@PathVariable Long id) {
        pendaftaranRepository.deleteById(id);
        return "redirect:/admin/pendaftaran/fragment";
    }
}