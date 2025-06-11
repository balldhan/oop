package com.example.sippdb.controller;

import com.example.sippdb.model.Jurusan;
import com.example.sippdb.repository.JurusanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/jurusan")
public class JurusanApiController {

    @Autowired
    private JurusanRepository jurusanRepository;

    @GetMapping
    public List<Jurusan> getAll() {
        return jurusanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jurusan getById(@PathVariable Long id) {
        return jurusanRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Jurusan create(@RequestBody Jurusan jurusan) {
        if (jurusan.getNama() == null || jurusan.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (jurusan.getKuota() == null || jurusan.getKuota().trim().isEmpty()) {
            throw new IllegalArgumentException("Kuota tidak boleh kosong");
        }
        jurusan.setId(null);
        return jurusanRepository.save(jurusan);
    }

    @PutMapping("/{id}")
    public Jurusan update(@PathVariable Long id, @RequestBody Jurusan jurusan) {
        Jurusan existing = jurusanRepository.findById(id).orElse(null);
        if (existing == null) return null;
        if (jurusan.getNama() == null || jurusan.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (jurusan.getKuota() == null || jurusan.getKuota().trim().isEmpty()) {
            throw new IllegalArgumentException("Kuota tidak boleh kosong");
        }
        // Cek jika kuota hanya angka (opsional, jika ingin validasi angka)
        // if (!jurusan.getKuota().matches("\\d+")) {
        //     throw new IllegalArgumentException("Kuota harus berupa angka (string angka)");
        // }
        existing.setNama(jurusan.getNama());
        existing.setKuota(jurusan.getKuota());
        return jurusanRepository.saveAndFlush(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jurusanRepository.deleteById(id);
    }
}
