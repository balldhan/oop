package com.example.sippdb.repository;

import com.example.sippdb.model.Pendaftaran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendaftaranRepository extends JpaRepository<Pendaftaran, Long> {
}