// filepath: c:\laragon\www\sippdb\src\main\java\com\example\sippdb\repository\JurusanRepository.java
package com.example.sippdb.repository;

import com.example.sippdb.model.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JurusanRepository extends JpaRepository<Jurusan, Long> {
}