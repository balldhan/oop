package com.example.sippdb.model;

import jakarta.persistence.*;

@Entity
public class Jalur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String namaJalur;

    @Column(nullable = false)
    private Integer kuota;

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaJalur() {
        return namaJalur;
    }
    public void setNamaJalur(String namaJalur) {
        this.namaJalur = namaJalur;
    }

    public Integer getKuota() {
        return kuota;
    }
    public void setKuota(Integer kuota) {
        this.kuota = kuota;
    }
}