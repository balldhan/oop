package com.example.sippdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "siswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nisn;

    @Column(nullable = false)
    private String namaLengkap;

    @Column(nullable = false)
    private String asalSekolah;

    @Column(nullable = false)
    private String jenisKelamin;

    @Column(nullable = false)
    private String tempatLahir;

    @Column(nullable = false)
    private String tanggalLahir;

    @Column(nullable = false)
    private String alamat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jalur_id", referencedColumnName = "id", nullable = false)
    private Jalur jalur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id", nullable = false)
    private Jurusan jurusan;

    @Column
    private String dokumen; // nama file dokumen

    @Column
    private String status; // MENUNGGU, DITERIMA, DITOLAK

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNisn() { return nisn; }
    public void setNisn(String nisn) { this.nisn = nisn; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getAsalSekolah() { return asalSekolah; }
    public void setAsalSekolah(String asalSekolah) { this.asalSekolah = asalSekolah; }
    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    public String getTempatLahir() { return tempatLahir; }
    public void setTempatLahir(String tempatLahir) { this.tempatLahir = tempatLahir; }
    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public Jalur getJalur() { return jalur; }
    public void setJalur(Jalur jalur) { this.jalur = jalur; }
    public Jurusan getJurusan() { return jurusan; }
    public void setJurusan(Jurusan jurusan) { this.jurusan = jurusan; }
    public String getDokumen() { return dokumen; }
    public void setDokumen(String dokumen) { this.dokumen = dokumen; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

// Tidak wajib ada field username di model Siswa
// Cukup gunakan field nisn sebagai penghubung dengan User (username == nisn)
// Jika ingin relasi eksplisit, bisa tambahkan relasi ke User, tapi tidak wajib
