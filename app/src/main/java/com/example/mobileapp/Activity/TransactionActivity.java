package com.example.mobileapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Adapter.AdapterDataTiket;
import com.example.mobileapp.Model.DataTiketModel;
import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {

    private final List<DataTiketModel> list = new ArrayList<>();
    private AdapterDataTiket adapterDataTiket;
    private RecyclerView recyclerView;
    private String id_transaksi, id_wisata, id_user, tgl_transaksi,
            jumlah_tiket, total_harga, status, nama_wisata, no_hp, no_rekening;
    private String phone_number;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // session
        SessionManager sessionManager = new SessionManager(TransactionActivity.this);
        if (!sessionManager.isLogin()) {
            moveToLogin();
        }


//        SessionManager sessionManager = new SessionManager(TransactionActivity.this);
        String email = sessionManager.getUserData().get(SessionManager.getEMAIL());

        getDataTiket(email);
        // init
        recyclerView = findViewById(R.id.rvDataTiket);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        // bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_transaction);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_transaction:
                        break;

                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }


    private void getDataTiket(String email) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<DataTiketModel>> call = apiInterface.getDataTiket(email);
        call.enqueue(new Callback<List<DataTiketModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataTiketModel>> call, @NonNull Response<List<DataTiketModel>> response) {
                List<DataTiketModel> dataTiketModels = response.body();

                assert dataTiketModels != null;
                for (DataTiketModel dataTiketModel : dataTiketModels) {
                    id_transaksi = dataTiketModel.getId_transaksi();
                    id_wisata = dataTiketModel.getId_wisata();
                    id_user = dataTiketModel.getId_user();
                    tgl_transaksi = dataTiketModel.getTgl_transaksi();
                    jumlah_tiket = dataTiketModel.getJumlah_tiket();
                    total_harga = dataTiketModel.getTotal_harga();
                    status = dataTiketModel.getStatus();
                    no_hp = dataTiketModel.getNo_hp();
                    phone_number = dataTiketModel.getNo_hp();
                    nama_wisata = dataTiketModel.getNama_wisata();
                    no_rekening = dataTiketModel.getNo_rekening();

                    DataTiketModel model = new DataTiketModel(id_transaksi, no_hp, no_rekening, id_wisata, id_user,
                            tgl_transaksi, jumlah_tiket, total_harga, status, nama_wisata);
                    list.add(model);
                }

                adapterDataTiket = new AdapterDataTiket(list);
                recyclerView.setAdapter(adapterDataTiket);
            }

            @Override
            public void onFailure(@NonNull Call<List<DataTiketModel>> call, @NonNull Throwable t) {

            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(TransactionActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}