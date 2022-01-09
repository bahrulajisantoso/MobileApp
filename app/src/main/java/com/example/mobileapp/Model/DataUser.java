package com.example.mobileapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataUser {

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("nama_user")
    private String namaUser;

    @SerializedName("email")
    private String email;

    @SerializedName("no_hp")
    private String noHp;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
