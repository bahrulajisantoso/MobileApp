package com.example.mobileapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserActivity extends AppCompatActivity {

    public static String nama;
    public static String email;
    public static String noHp;
    public String tglLahir;
    public String jenisKel;
    public String alamat;
    public String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // session
        SessionManager sessionManager = new SessionManager(UserActivity.this);
        if (!sessionManager.isLogin()) {
            moveToLogin();
        }

        // bind
        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvNoHp = findViewById(R.id.tvNoHp);
        TextView tvTglLahir = findViewById(R.id.tvTglLahir);
        TextView tvJenisKel = findViewById(R.id.tvJenisKel);
        TextView tvAlamat = findViewById(R.id.tvAlamat);

        ImageButton btnLogout = findViewById(R.id.btnLogout);
        TextView tvEdit = findViewById(R.id.tvEdit);

        nama = sessionManager.getUserData().get(SessionManager.getNAMA());
        email = sessionManager.getUserData().get(SessionManager.getEMAIL());
        noHp = sessionManager.getUserData().get(SessionManager.getNoHp());
        tglLahir = sessionManager.getUserData().get(SessionManager.getTanggalLahir());
        jenisKel = sessionManager.getUserData().get(SessionManager.getJenisKelamin());
        alamat = sessionManager.getUserData().get(SessionManager.getALAMAT());
        id_user = sessionManager.getUserData().get(SessionManager.getIDUser());

        tvNama.setText(nama);
        tvEmail.setText(email);
        tvNoHp.setText(noHp);
        tvTglLahir.setText(tglLahir);
        tvJenisKel.setText(jenisKel);
        tvAlamat.setText(alamat);


        // logout
        btnLogout.setOnClickListener(view -> new AlertDialog.Builder(UserActivity.this)
                .setTitle("Apakah anda ingin keluar?")
                .setMessage("Ingin keluar dari aplikasi ini?")
                .setPositiveButton("TIDAK", (dialog, which) -> dialog.cancel())
                .setNegativeButton("YA", (dialog, which) -> {
                    dialog.cancel();
                    sessionManager.logoutSession();
                    moveToLogin();
                    finish();
                }).show());

        tvEdit.setOnClickListener(view -> {
            Intent intent = new Intent(UserActivity.this, EditActivity.class);
            startActivity(intent);
            finish();
        });

        // bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_user);

//        bottomNavigationView.setOnItemSelectedListener(item -> {

//            int itemId = item.getItemId();
//            if (itemId == R.id.nav_home) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                overridePendingTransition(0, 0);
//                return true;
//            } else if (itemId == R.id.nav_transaction) {
//                startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
//                overridePendingTransition(0, 0);
//                return true;
//            } else {
//                return itemId == R.id.nav_user;
//            }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_transaction:
                        startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_user:
                        break;
                }
                return false;
            }
        });
    }


    private void moveToLogin() {
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
