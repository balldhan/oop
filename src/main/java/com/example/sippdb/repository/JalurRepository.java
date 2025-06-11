package com.example.sippdb.repository;

import com.example.sippdb.model.Jalur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JalurRepository extends JpaRepository<Jalur, Long> {
    // Tidak perlu diubah
}
