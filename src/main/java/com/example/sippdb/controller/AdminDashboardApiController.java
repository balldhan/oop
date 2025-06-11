package com.example.sippdb.controller;

import com.example.sippdb.repository.SiswaRepository;
import com.example.sippdb.repository.JalurRepository;
import com.example.sippdb.repository.JurusanRepository;

import com.example.sippdb.model.Jalur;
import com.example.sippdb.model.Jurusan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/dashboard")
public class AdminDashboardApiController {

    @Autowired
    private SiswaRepository siswaRepository;
    @Autowired
    private JalurRepository jalurRepository;
    @Autowired
    private JurusanRepository jurusanRepository;

    @GetMapping
    public Map<String, Object> dashboard() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalSiswa", siswaRepository.count());
        // Ubah penjumlahan kuota ke String (karena field kuota sekarang String)
        int totalKuotaJalur = jalurRepository.findAll().stream()
            .mapToInt(j -> {
                try { return Integer.parseInt(j.getKuota()); } catch (Exception e) { return 0; }
            }).sum();
        int totalKuotaJurusan = jurusanRepository.findAll().stream()
            .mapToInt(j -> {
                try { return Integer.parseInt(j.getKuota()); } catch (Exception e) { return 0; }
            }).sum();
        result.put("kuotaJalur", totalKuotaJalur);
        result.put("kuotaJurusan", totalKuotaJurusan);
        result.put("statusDiterima", siswaRepository.countByStatus("DITERIMA"));
        result.put("statusDitolak", siswaRepository.countByStatus("DITOLAK"));
        result.put("statusMenunggu", siswaRepository.countByStatus("MENUNGGU"));

        // Jalur chart
        List<Jalur> jalurList = jalurRepository.findAll();
        List<String> jalurLabels = jalurList.stream().map(Jalur::getNama).collect(Collectors.toList());
        List<Long> jalurData = jalurList.stream()
                .map(jalur -> siswaRepository.countByJalur(jalur))
                .collect(Collectors.toList());
        result.put("jalurLabels", jalurLabels);
        result.put("jalurData", jalurData);

        // Jurusan chart
        List<Jurusan> jurusanList = jurusanRepository.findAll();
        List<String> jurusanLabels = jurusanList.stream().map(Jurusan::getNama).collect(Collectors.toList());
        List<Long> jurusanData = jurusanList.stream()
                .map(jurusan -> siswaRepository.countByJurusan(jurusan))
                .collect(Collectors.toList());
        result.put("jurusanLabels", jurusanLabels);
        result.put("jurusanData", jurusanData);

        return result;
    }
}
