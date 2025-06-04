package com.example.sippdb.model;

import jakarta.persistence.*;

@Entity
public class Jurusan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String namaJurusan;

    @Column(nullable = false)
    private Integer kuota;

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }
    public void setNamaJurusan(String namaJurusan) {
        this.namaJurusan = namaJurusan;
    }

    public Integer getKuota() {
        return kuota;
    }
    public void setKuota(Integer kuota) {
        this.kuota = kuota;
    }
}