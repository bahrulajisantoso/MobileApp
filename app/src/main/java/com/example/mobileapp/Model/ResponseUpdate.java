package com.example.mobileapp.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdate {


    public class ResponseRegister{

        @SerializedName("pesan")
        private String pesan;

        @SerializedName("kode")
        private int kode;

        public void setPesan(String pesan){
            this.pesan = pesan;
        }

        public String getPesan(){
            return pesan;
        }

        public void setKode(int kode){
            this.kode = kode;
        }

        public int getKode(){
            return kode;
        }
    }
}
