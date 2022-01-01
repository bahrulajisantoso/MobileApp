package com.example.mobileapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.R;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvNama, tvEmail, tvNik, tvNoHp, tvTglLahir, tvJenisKel, tvAlamat;
    String nama, email, nik, noHp, tglLahir, jenisKel, alamat;
    Button simpan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
    }

    @Override
    public void onClick(View view) {

    }
}
