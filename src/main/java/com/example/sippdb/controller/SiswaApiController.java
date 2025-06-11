package com.example.sippdb.controller;

import com.example.sippdb.model.Siswa;
import com.example.sippdb.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/siswa")
public class SiswaApiController {

    @Autowired
    private SiswaRepository siswaRepository;

    @GetMapping
    public List<Siswa> getAll() {
        return siswaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Siswa getById(@PathVariable Long id) {
        return siswaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Siswa create(@RequestBody Siswa siswa) {
        if (siswa.getNamaLengkap() == null || siswa.getNisn() == null ||
            siswa.getAsalSekolah() == null || siswa.getJenisKelamin() == null ||
            siswa.getTempatLahir() == null || siswa.getTanggalLahir() == null ||
            siswa.getAlamat() == null || siswa.getStatus() == null ||
            siswa.getJalur() == null || siswa.getJalur().getId() == null ||
            siswa.getJurusan() == null || siswa.getJurusan().getId() == null) {
            throw new IllegalArgumentException("Semua field wajib diisi dan relasi jalur/jurusan tidak boleh kosong");
        }
        return siswaRepository.save(siswa);
    }

    @PutMapping("/{id}")
    public Siswa update(@PathVariable Long id, @RequestBody Siswa siswa) {
        Siswa existing = siswaRepository.findById(id).orElse(null);
        if (existing == null) return null;
        if (siswa.getNamaLengkap() == null || siswa.getNisn() == null ||
            siswa.getAsalSekolah() == null || siswa.getJenisKelamin() == null ||
            siswa.getTempatLahir() == null || siswa.getTanggalLahir() == null ||
            siswa.getAlamat() == null || siswa.getStatus() == null ||
            siswa.getJalur() == null || siswa.getJalur().getId() == null ||
            siswa.getJurusan() == null || siswa.getJurusan().getId() == null) {
            throw new IllegalArgumentException("Semua field wajib diisi dan relasi jalur/jurusan tidak boleh kosong");
        }
        // Pastikan ID pada entity tidak berubah
        existing.setNamaLengkap(siswa.getNamaLengkap());
        existing.setNisn(siswa.getNisn());
        existing.setAsalSekolah(siswa.getAsalSekolah());
        existing.setJenisKelamin(siswa.getJenisKelamin());
        existing.setTempatLahir(siswa.getTempatLahir());
        existing.setTanggalLahir(siswa.getTanggalLahir());
        existing.setAlamat(siswa.getAlamat());
        existing.setStatus(siswa.getStatus());
        existing.setJalur(siswa.getJalur());
        existing.setJurusan(siswa.getJurusan());
        // Jangan setId pada existing, biarkan ID tetap
        return siswaRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        siswaRepository.deleteById(id);
    }
}
