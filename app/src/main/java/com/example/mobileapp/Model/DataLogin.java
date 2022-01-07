package com.example.mobileapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataLogin {

    @SerializedName("nik")
    private String nik;

    @SerializedName("password")
    private String password;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("nama_user")
    private String namaUser;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("email")
    private String email;

    @SerializedName("tgl_lahir")
    private String tglLahir;

    @SerializedName("alamat")
    private String alamat;

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNik() {
        return nik;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
}