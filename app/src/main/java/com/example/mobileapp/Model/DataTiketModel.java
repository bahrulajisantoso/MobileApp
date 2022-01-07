package com.example.mobileapp.Model;

public class DataTiketModel {
    private String id_transaksi, id_wisata, no_rekening, id_user, tgl_transaksi, jumlah_tiket, total_harga, status, nama_wisata, no_hp;

    public DataTiketModel(String id_transaksi, String no_hp, String no_rekening, String id_wisata, String id_user, String tgl_transaksi, String jumlah_tiket, String total_harga, String status, String nama_wisata) {
        this.id_transaksi = id_transaksi;
        this.id_wisata = id_wisata;
        this.id_user = id_user;
        this.no_hp = no_hp;
        this.no_rekening = no_rekening;
        this.tgl_transaksi = tgl_transaksi;
        this.jumlah_tiket = jumlah_tiket;
        this.total_harga = total_harga;
        this.status = status;
        this.nama_wisata = nama_wisata;
    }

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

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public String getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(String jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }
}
