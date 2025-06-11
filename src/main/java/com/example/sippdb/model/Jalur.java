package com.example.sippdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jalur")
public class Jalur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nama;

    @Column(nullable = false)
    private String kuota;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getKuota() { return kuota; }
    public void setKuota(String kuota) { this.kuota = kuota; }
}
