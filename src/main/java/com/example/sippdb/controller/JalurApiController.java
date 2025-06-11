package com.example.sippdb.controller;

import com.example.sippdb.model.Jalur;
import com.example.sippdb.repository.JalurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/jalur")
public class JalurApiController {

    @Autowired
    private JalurRepository jalurRepository;

    @GetMapping
    public List<Jalur> getAll() {
        return jalurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jalur getById(@PathVariable Long id) {
        return jalurRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Jalur create(@RequestBody Jalur jalur) {
        if (jalur.getNama() == null || jalur.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (jalur.getKuota() == null || jalur.getKuota().trim().isEmpty()) {
            throw new IllegalArgumentException("Kuota tidak boleh kosong");
        }
        jalur.setId(null); // pastikan create tidak membawa id
        return jalurRepository.save(jalur);
    }

    @PutMapping("/{id}")
    public Jalur update(@PathVariable Long id, @RequestBody Jalur jalur) {
        Jalur existing = jalurRepository.findById(id).orElse(null);
        if (existing == null) return null;
        if (jalur.getNama() == null || jalur.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (jalur.getKuota() == null || jalur.getKuota().trim().isEmpty()) {
            throw new IllegalArgumentException("Kuota tidak boleh kosong");
        }
        // Cek jika kuota hanya angka (opsional, jika ingin validasi angka)
        // if (!jalur.getKuota().matches("\\d+")) {
        //     throw new IllegalArgumentException("Kuota harus berupa angka (string angka)");
        // }
        existing.setNama(jalur.getNama());
        existing.setKuota(jalur.getKuota());
        return jalurRepository.saveAndFlush(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jalurRepository.deleteById(id);
    }
}
