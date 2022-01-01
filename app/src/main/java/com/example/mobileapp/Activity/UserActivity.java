package com.example.mobileapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserActivity extends AppCompatActivity {

    private TextView tvNama, tvEmail, tvNik, tvNoHp, tvTglLahir, tvJenisKel, tvAlamat, tvEdit;
    private ImageButton btnLogout;
    private String nama, email, nik, noHp, tglLahir, jenisKel, alamat;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // session
        SessionManager sessionManager = new SessionManager(UserActivity.this);
        if (sessionManager.isLogin() == false) {
            moveToLogin();
        }

        // bind
        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);
        tvNik = findViewById(R.id.tvNik);
        tvNoHp = findViewById(R.id.tvNoHp);
        tvTglLahir = findViewById(R.id.tvTglLahir);
        tvJenisKel = findViewById(R.id.tvJenisKel);
        tvAlamat = findViewById(R.id.tvAlamat);

        btnLogout = findViewById(R.id.btnLogout);
        tvEdit = findViewById(R.id.tvEdit);

        nama = sessionManager.getUserData().get(SessionManager.getNAMA());
        email = sessionManager.getUserData().get(SessionManager.getEMAIL());
//        nik = sessionManager.getUserData().get(SessionManager.getNIK());
        noHp = sessionManager.getUserData().get(SessionManager.getNoHp());
        tglLahir = sessionManager.getUserData().get(SessionManager.getTanggalLahir());
        jenisKel = sessionManager.getUserData().get(SessionManager.getJenisKelamin());
        alamat = sessionManager.getUserData().get(SessionManager.getALAMAT());

        tvNama.setText(nama);
        tvEmail.setText(email);
//        tvNik.setText(nik);
        tvNoHp.setText(noHp);
        tvTglLahir.setText(tglLahir);
        tvJenisKel.setText(jenisKel);
        tvAlamat.setText(alamat);


        // logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sessionManager.logoutSession();
//                moveToLogin();
//                finish();

                new AlertDialog.Builder(UserActivity.this)
                        .setTitle("Apakah anda ingin keluar?")
                        .setMessage("Ingin keluar dari aplikasi ini?")
                        .setPositiveButton("TIDAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getActivity(), "Kamu Memilih YES", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(UserActivity.this, "Kamu Memilih TIDAK Ingin Keluar", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                                sessionManager.logoutSession();
                                moveToLogin();
                                finish();
                            }
                        }).show();
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, EditActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_account);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;

                    case R.id.nav_transaction:
                        startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_account:
                        return true;
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
