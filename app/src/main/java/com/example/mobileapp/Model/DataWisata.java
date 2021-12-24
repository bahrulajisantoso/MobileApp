package com.example.mobileapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataWisata {

    @SerializedName("harga_tiket")
    private double hargaTiket;

    @SerializedName("id_wisata")
    private int idWisata;

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

    public void setHargaTiket(double hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setIdWisata(int idWisata) {
        this.idWisata = idWisata;
    }

    public int getIdWisata() {
        return idWisata;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }
}