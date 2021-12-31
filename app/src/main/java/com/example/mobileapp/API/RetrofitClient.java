package com.example.mobileapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2/Web-Service/";
    private static final String IMAGE_URL = "http://10.0.2.2/Arah_Kita/Arah%20Kita%20WEB/img/wisata/";
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

    public static String getImageUrl() {
        return IMAGE_URL;
    }
}
