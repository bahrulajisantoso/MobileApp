package com.example.mobileapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText tvNama, tvEmail, tvNik, tvNoHp, tvTglLahir, tvJenisKel, tvAlamat;
    String nama, email, nik, noHp, tglLahir, jenisKel, alamat;
    private ImageButton btnKembali ,btnPilihTglLahir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        // session
        SessionManager sessionManager = new SessionManager(EditActivity.this);
        if (sessionManager.isLogin() == false) {
            moveToLogin();
        }

        // bind
        tvNama = findViewById(R.id.eNama);
        tvEmail = findViewById(R.id.eEmail);
        //tvNik = findViewById(R.id.eNik);
        tvNoHp = findViewById(R.id.eNoHp);
        tvTglLahir = findViewById(R.id.eTglLahir);
        tvJenisKel = findViewById(R.id.eJenisKel);
        tvAlamat = findViewById(R.id.eAlamat);
        tvTglLahir= findViewById(R.id.eTglLahir);
        btnPilihTglLahir =(ImageButton) findViewById(R.id.btnPilihTglLahir);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        btnPilihTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tvTglLahir.setText(date);
                    }
                }, year, month, day
                );
                datePickerDialog.show();
            }
        });

        btnKembali = findViewById(R.id.btnKembaliEdit);
        btnKembali.setOnClickListener(this);

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


    }
    private void moveToLogin() {
        Intent intent = new Intent(EditActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnKembaliEdit:
                Intent kembali = new Intent(this, UserActivity.class);
                startActivity(kembali);
                break;
//            case R.id.kembali:
//                Intent kembali2 = new Intent(this, MainActivity.class);
//                startActivity(kembali2);
//                break;
        }
    }



}
