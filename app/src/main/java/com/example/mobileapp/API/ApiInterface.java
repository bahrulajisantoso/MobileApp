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

public interface ApiInterface {

    // tampil wisata
    @GET("wisata.php")
    Call<ResponseWisata> getWisata();

    // tampil user
//    @GET("user.php")
//    Call<ResponseUser> getUser();

    // login
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> getLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    // register
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> addUser(
            @Field("nama_user") String nama,
            @Field("email") String email,
            @Field("no_hp") String noHp,
//            @Field("nik") String nik,
            @Field("jenis_kelamin") String jenisKelamin,
            @Field("tgl_lahir") String tglLahir,
            @Field("alamat") String alamat,
            @Field("password") String password,
            @Field("konfirm_password") String konfirmPassword
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseRegister> updateUser(
            @Field("nama_user") String nama,
            @Field("email") String email,
            @Field("no_hp") String nomerhp,
            @Field("password") String password,
            @Field("alamat") String alamat,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jenis_kelamin") String jenis_kelamin
    );
}
