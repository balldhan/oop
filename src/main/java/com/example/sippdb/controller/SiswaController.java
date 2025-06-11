package com.example.sippdb.controller;

import com.example.sippdb.model.Jalur;
import com.example.sippdb.model.Jurusan;
import com.example.sippdb.model.Siswa;
import com.example.sippdb.repository.JalurRepository;
import com.example.sippdb.repository.JurusanRepository;
import com.example.sippdb.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SiswaController {

    @Autowired
    private SiswaRepository siswaRepository;
    @Autowired
    private JalurRepository jalurRepository;
    @Autowired
    private JurusanRepository jurusanRepository;

    @GetMapping("/siswa/dashboard")
    public String dashboardSiswa(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        model.addAttribute("username", authentication.getName());
        return "siswa_dashboard";
    }

    @GetMapping("/siswa/profil")
    public String profil(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        Siswa siswa = siswaRepository.findByNisn(username);
        model.addAttribute("siswa", siswa);
        return "siswa_profil";
    }

    @GetMapping("/siswa/pendaftaran")
    public String pendaftaran(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        Siswa siswa = siswaRepository.findByNisn(username);
        if (siswa != null) {
            // Sudah pernah daftar, redirect ke status
            return "redirect:/siswa/status";
        }
        model.addAttribute("jalurList", jalurRepository.findAll());
        model.addAttribute("jurusanList", jurusanRepository.findAll());
        return "siswa_pendaftaran";
    }

    @PostMapping("/siswa/pendaftaran")
    public String prosesPendaftaran(
            Authentication authentication,
            @RequestParam String namaLengkap,
            @RequestParam String asalSekolah,
            @RequestParam String jenisKelamin,
            @RequestParam String tempatLahir,
            @RequestParam String tanggalLahir,
            @RequestParam String alamat,
            @RequestParam Long jalur,
            @RequestParam Long jurusan,
            @RequestParam(required = false) MultipartFile dokumen,
            Model model
    ) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        if (siswaRepository.findByNisn(username) != null) {
            return "redirect:/siswa/status";
        }
        try {
            Siswa siswa = new Siswa();
            siswa.setNisn(username);
            siswa.setNamaLengkap(namaLengkap);
            siswa.setAsalSekolah(asalSekolah);
            siswa.setJenisKelamin(jenisKelamin);
            siswa.setTempatLahir(tempatLahir);
            siswa.setTanggalLahir(tanggalLahir);
            siswa.setAlamat(alamat);
            Jalur jalurObj = jalurRepository.findById(jalur).orElse(null);
            Jurusan jurusanObj = jurusanRepository.findById(jurusan).orElse(null);
            if (jalurObj == null || jurusanObj == null) {
                model.addAttribute("error", "Jalur atau jurusan tidak ditemukan.");
                model.addAttribute("jalurList", jalurRepository.findAll());
                model.addAttribute("jurusanList", jurusanRepository.findAll());
                return "siswa_pendaftaran";
            }
            siswa.setJalur(jalurObj);
            siswa.setJurusan(jurusanObj);
            siswa.setStatus("MENUNGGU");
            if (dokumen != null && !dokumen.isEmpty()) {
                siswa.setDokumen(dokumen.getOriginalFilename());
            }
            siswaRepository.save(siswa);

            model.addAttribute("success", "Pendaftaran berhasil!");
            return "redirect:/siswa/status";
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan: " + e.getMessage());
            model.addAttribute("jalurList", jalurRepository.findAll());
            model.addAttribute("jurusanList", jurusanRepository.findAll());
            return "siswa_pendaftaran";
        }
    }

    @GetMapping("/siswa/status")
    public String status(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        Siswa siswa = siswaRepository.findByNisn(username);
        model.addAttribute("siswa", siswa);
        return "siswa_status";
    }
}
