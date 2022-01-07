package com.example.mobileapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataWisata {

    @SerializedName("harga_tiket")
    private double hargaTiket;

    @SerializedName("id_wisata")
    private int idWisata;

    @SerializedName("no_rekening")
    private String no_rekening;

    @SerializedName("no_hp")
    private String no_hp;

    public String getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(String no_rekening) {
        this.no_rekening = no_rekening;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("nama_wisata")
    private String namaWisata;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("jumlah_kuota")
    private int jumlah_kuota;

    public int getJumlah_kuota() {
        return jumlah_kuota;
    }

    public void setJumlah_kuota(int jumlah_kuota) {
        this.jumlah_kuota = jumlah_kuota;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public int getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(int idWisata) {
        this.idWisata = idWisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}