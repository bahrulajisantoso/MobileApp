package com.example.mobileapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

  //  private static final String IP_ADDRES = "192.168.1.5";
       private static final String IP_ADDRES = "192.168.0.102";
    private static final String BASE_URL = "http://" + IP_ADDRES + "/Web-Service/";
    public static final String TRANSACTION = "http://" + IP_ADDRES + "/Web-Service/transaksi.php";
    public static final String UPDATE_JUMLAH_TIKET = "http://" + IP_ADDRES + "/Web-Service/updatejumlahtiket.php";
    public static final String IMAGE_URL = "http://" + IP_ADDRES + "/Arah_Kita/Arah_Kita_Web/img/wisata/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
