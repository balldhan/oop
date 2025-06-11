package com.example.sippdb.repository;

import com.example.sippdb.model.Siswa;
import com.example.sippdb.model.Jalur;
import com.example.sippdb.model.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    boolean existsByNisn(String nisn);
    Siswa findByNisn(String nisn);
    long countByStatus(String status);
    long countByJalur(Jalur jalur);
    long countByJurusan(Jurusan jurusan);
}
