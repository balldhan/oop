package com.example.sippdb.repository;

import com.example.sippdb.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    // Tambahkan custom query jika diperlukan, misal:
    Siswa findByNisn(String nisn);
    Siswa findByEmail(String email);
}