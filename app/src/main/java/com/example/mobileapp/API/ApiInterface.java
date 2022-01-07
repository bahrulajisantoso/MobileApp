package com.example.mobileapp.API;

import com.example.mobileapp.Model.DataTiketModel;
import com.example.mobileapp.Model.ResponseLogin;
import com.example.mobileapp.Model.ResponseRegister;
import com.example.mobileapp.Model.ResponseTransaction;
import com.example.mobileapp.Model.ResponseUser;
import com.example.mobileapp.Model.ResponseWisata;
import com.example.mobileapp.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


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

    // getDataUser
    @FormUrlEncoded
    @POST("datauser.php")
    Call<UserModel> getDataUserByID(
            @Field("email") String email);


    // tampil wisata
    @GET("wisata.php")
    Call<ResponseWisata> getWisata();

    // update user
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseRegister> updateUser(
            @Field("nama_user") String nama,
            @Field("email") String email,
            @Field("no_hp") String nomerhp,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("alamat") String alamat,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("transaksi.php")
    Call<ResponseTransaction> addNewTransaction(
            @Field("id_wisata") String id_wisata,
            @Field("id_user") String id_user,
            @Field("tgl_transaksi") String tgl_transaksi,
            @Field("jumlah_tiket") String jumlah_tiket,
            @Field("total_harga") String total_harga
    );

    @FormUrlEncoded
    @POST("getNoRekening.php")
    Call<String> getNoHP(@Field("id_wisata") String id_wisata);

    @FormUrlEncoded
    @POST("datatiketbyid.php")
    Call<List<DataTiketModel>> getDataTiket(
            @Field("email") String email);

    @GET("jumlahwisata.php")
    Call<String> getJumlahWisata();

    @FormUrlEncoded
    @POST("updatejumlahtiket.php")
    Call<ResponseTransaction> updateJumlahTiket(
            @Field("id_wisata") String id_wisata,
            @Field("jumlah_tiket") int jumlah_tiket
    );
}
