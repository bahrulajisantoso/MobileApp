package com.example.mobileapp.Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseWisata {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private List<DataWisata> data;

    @SerializedName("kode")
    private int kode;

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setData(List<DataWisata> data) {
        this.data = data;
    }

    public List<DataWisata> getData() {
        return data;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public int getKode() {
        return kode;
    }
}