package com.example.sippdb.model;

import jakarta.persistence.*;

@Entity
public class Pendaftaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siswa_id", nullable = false)
    private Siswa siswa;

    @ManyToOne
    @JoinColumn(name = "jurusan_id", nullable = false)
    private Jurusan jurusan;

    @ManyToOne
    @JoinColumn(name = "jalur_id", nullable = false)
    private Jalur jalur;

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Siswa getSiswa() {
        return siswa;
    }
    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }
    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }

    public Jalur getJalur() {
        return jalur;
    }
    public void setJalur(Jalur jalur) {
        this.jalur = jalur;
    }
}