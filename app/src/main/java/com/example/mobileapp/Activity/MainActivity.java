package com.example.mobileapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Adapter.AdapterDataWisata;
import com.example.mobileapp.Model.DataWisata;
import com.example.mobileapp.Model.ResponseWisata;
import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWisata;
    private RecyclerView.Adapter rvAdapater;
    private RecyclerView.LayoutManager rvLayoutManager;
    private List<DataWisata> listWisata = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // session
        SessionManager sessionManager = new SessionManager(MainActivity.this);
        if (sessionManager.isLogin() == false) {
            moveToLogin();
        }

        rvWisata = (RecyclerView) findViewById(R.id.rvWisata);
//        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        rvWisata.setLayoutManager(rvLayoutManager);
        readData();
    }

    public void readData() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<ResponseWisata> tampilDataWisata = apiInterface.getWisata();

        tampilDataWisata.enqueue(new Callback<ResponseWisata>() {
            @Override
            public void onResponse(Call<ResponseWisata> call, Response<ResponseWisata> response) {

                listWisata = response.body().getData();

                rvAdapater = new AdapterDataWisata(MainActivity.this, listWisata);
                rvWisata.setAdapter(rvAdapater);
                rvAdapater.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseWisata> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi sever " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        break;

                    case R.id.nav_transaction:
                        startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
