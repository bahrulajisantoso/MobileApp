package com.example.mobileapp.API;

import com.example.mobileapp.Model.ResponseLogin;
import com.example.mobileapp.Model.ResponseRegister;
import com.example.mobileapp.Model.ResponseUser;
import com.example.mobileapp.Model.ResponseWisata;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestData {

    // tampil wisata
    @GET("read.php")
    Call<ResponseWisata> ardReadData();

    // tampil user
    @GET("user.php")
    Call<ResponseUser> ardUserData();

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> ardRegister(
            @Field("nama_user") String nama,
            @Field("email") String email,
            @Field("no_hp") String noHp,
            @Field("nik") String nik,
            @Field("jenis_kelamin") String jenisKelamin,
            @Field("tgl_lahir") String tglLahir,
            @Field("alamat") String alamat,
            @Field("password") String password
    );
}
