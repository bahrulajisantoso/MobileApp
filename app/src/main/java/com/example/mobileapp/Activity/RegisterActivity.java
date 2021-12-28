package com.example.mobileapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Model.ResponseRegister;
import com.example.mobileapp.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNama, etEmail, etNoHp, etNik, etJeniKel, etTglLahir, etAlamat, etPassword, etKonfirmPassword;
    Button btnRegister, btnPilihTglLahir;
    TextView tvLogin;
    String nama, email, noHp, nik, jenisKelamin, tglLahir, alamat, password, konfirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // bind
        btnPilihTglLahir = (Button) findViewById(R.id.btnPilihTglLahir);
        etTglLahir = (EditText) findViewById(R.id.etTglLahir);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        btnPilihTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etTglLahir.setText(date);
                    }
                }, year, month, day
                );
                datePickerDialog.show();
            }
        });
//        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                String date = day + "/" + month + "/" + year;
//                etTglLahir.setText(date);
//            }
//        };

//
//        etTglLahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        String date = day + "/" + month + "/" + year;
//                        etTglLahir.setText(date);
//                    }
//                }, year, month, day
//                );
//                datePickerDialog.show();
//            }
//        });

        etNama = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNoHp = findViewById(R.id.etNoHp);
//        etNik = findViewById(R.id.etNik);
        etJeniKel = findViewById(R.id.etJenisKel);
//        etTglLahir = findViewById(R.id.tvTglLahir);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);
        etKonfirmPassword = findViewById(R.id.etKonfirmPassword);

        // button register
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(RegisterActivity.this);

        // text view login
        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(RegisterActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                noHp = etNoHp.getText().toString();
//                nik = etNik.getText().toString();
                jenisKelamin = etJeniKel.getText().toString();
                tglLahir = etTglLahir.getText().toString();
                alamat = etAlamat.getText().toString();
                password = etPassword.getText().toString();
                konfirmPassword = etKonfirmPassword.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("Nama harus diisi");
                } else if (email.trim().equals("")) {
                    etEmail.setError("Email harus diisi");
                } else if (noHp.trim().equals("")) {
                    etNoHp.setError("No handphone harus diisi");
//                } else if (nik.trim().equals("")) {
//                    etNik.setError("NIK harus diisi");
                } else if (jenisKelamin.trim().equals("")) {
                    etJeniKel.setError("Jenis kelamin harus diisi");
                } else if (tglLahir.trim().equals("")) {
                    etTglLahir.setError("Tanggal lahir harus diisi");
                } else if (alamat.trim().equals("")) {
                    etAlamat.setError("Alamat harus diisi");
                } else if (password.trim().equals("")) {
                    etPassword.setError("Password harus diisi");
                } else if (konfirmPassword.trim().equals("")) {
                    etKonfirmPassword.setError("konfirmasi password harus diisi");
//                } else if (!konfirmPassword.trim().equals(password)) {
//                    etKonfirmPassword.setError("Konfirmasi password tidak sesuai");
                } else {
                    register(nama, email, noHp, nik, jenisKelamin, tglLahir, alamat, password, konfirmPassword);
                }
                break;

            case R.id.tvLogin:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    // fungsi register
    private void register(String nama, String email, String noHp, String nik, String jenisKelamin, String tglLahir, String alamat, String password, String konfirmPassword) {

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<ResponseRegister> responseRegisterCall = apiInterface.addUser(nama, email, noHp, nik, jenisKelamin, tglLahir, alamat, password, konfirmPassword);
        responseRegisterCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getKode() == 1) {
                    Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
