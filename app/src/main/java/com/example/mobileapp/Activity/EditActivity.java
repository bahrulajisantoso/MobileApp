package com.example.mobileapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Model.ResponseRegister;
import com.example.mobileapp.Model.ResponseUpdateUser;
import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etEmail, etNoHp, etTglLahir, etJenisKel, etAlamat;
    private String idUser, nama, email, noHp, tglLahir, jenisKel, alamat;
    private ImageButton btnKembali, btnPilihTglLahir;
    private Button btnEditUser;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        // session
        sessionManager = new SessionManager(EditActivity.this);
        if (!sessionManager.isLogin()) {
            moveToLogin();
        }

        // bind
        etNama = findViewById(R.id.eNama);
        etEmail = findViewById(R.id.eEmail);
        etNoHp = findViewById(R.id.eNoHp);
        etTglLahir = findViewById(R.id.eTglLahir);
        etJenisKel = findViewById(R.id.eJenisKel);
        etAlamat = findViewById(R.id.eAlamat);
        etTglLahir = findViewById(R.id.eTglLahir);

        btnPilihTglLahir = (ImageButton) findViewById(R.id.btnPilihTglLahir);
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
                        etTglLahir.setText(date);
                    }
                }, year, month, day
                );
                datePickerDialog.show();
            }
        });

        btnEditUser = findViewById(R.id.btnEditUser);
        btnEditUser.setOnClickListener(EditActivity.this);

        btnKembali = findViewById(R.id.btnKembaliEdit);
        btnKembali.setOnClickListener(EditActivity.this);

        idUser = sessionManager.getUserData().get(SessionManager.getIDUser());
        nama = sessionManager.getUserData().get(SessionManager.getNAMA());
        email = sessionManager.getUserData().get(SessionManager.getEMAIL());
        noHp = sessionManager.getUserData().get(SessionManager.getNoHp());
        tglLahir = sessionManager.getUserData().get(SessionManager.getTanggalLahir());
        jenisKel = sessionManager.getUserData().get(SessionManager.getJenisKelamin());
        alamat = sessionManager.getUserData().get(SessionManager.getALAMAT());

        etNama.setText(nama);
        etEmail.setText(email);
        etNoHp.setText(noHp);
        etTglLahir.setText(tglLahir);
        etJenisKel.setText(jenisKel);
        etAlamat.setText(alamat);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditUser:

                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("Apakah anda ingin mengubah data?")
                        .setMessage("Ingin mengubah data profil anda?")
                        .setPositiveButton("TIDAK", (dialog, which) -> dialog.cancel())
                        .setNegativeButton("YA", (dialog, which) -> {
                            dialog.cancel();

                            nama = etNama.getText().toString().toLowerCase();
                            email = etEmail.getText().toString().toLowerCase();
                            noHp = etNoHp.getText().toString();
                            tglLahir = etTglLahir.getText().toString();
                            jenisKel = etJenisKel.getText().toString().toLowerCase();
                            alamat = etAlamat.getText().toString().toLowerCase();

                            if (nama.trim().equals("Nama harus diisi")) {
                            } else if (nama.trim().length() > 40) {
                                etJenisKel.setError("Max 40 karakter");

                            } else if (email.trim().equals("")) {
                                etEmail.setError("Email harus diisi");
                            } else if (email.trim().length() > 30) {
                                etEmail.setError("Max 30 karakter");
                            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                etEmail.setError("Masukkan email yang valid");

                            } else if (noHp.trim().equals("")) {
                                etNoHp.setError("No handphone harus diisi");
                            } else if (noHp.trim().length() < 10 || noHp.trim().length() > 13) {
                                etNoHp.setError("No handphone tidak valid");
                            } else if (!Patterns.PHONE.matcher(noHp).matches()) {
                                etNoHp.setError("No handphone tidak valid");

                            } else if (tglLahir.trim().equals("")) {
                                etTglLahir.setError("Tanggal lahir harus diisi");

                            } else if (jenisKel.trim().equals("")) {
                                etTglLahir.setError("Jenis kelamin harus diisi");

                            } else if (alamat.trim().equals("")) {
                                etTglLahir.setError("Alamat harus diisi");
                            } else if (alamat.trim().length() > 90) {
                                etTglLahir.setError("Max 90 karakter");
                            } else {
                                UpdateUser(idUser, nama, email, noHp, tglLahir, jenisKel, alamat);
                            }
                        }).show();
                break;
            case R.id.btnKembaliEdit:
                Intent kembali = new Intent(this, UserActivity.class);
                startActivity(kembali);
                finish();
                break;
        }
    }

    private void UpdateUser(String idUser, String nama, String email, String noHp, String tglLahir, String jenisKel, String alamat) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
//        Call<ResponseUpdateUser> responseUpdateUserCall = apiInterface.updateUser(idUser, nama, email, noHp, tglLahir, jenisKel, alamat);
        Call<ResponseUpdateUser> responseUpdateUserCall = apiInterface.updateUser(idUser, nama, email, noHp, tglLahir, jenisKel, alamat);
        responseUpdateUserCall.enqueue(new Callback<ResponseUpdateUser>() {
            @Override
            public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getKode() == 1) {
                    Toast.makeText(EditActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    sessionManager.logoutSession();
                    moveToLogin();
                    finish();

                } else {
                    Toast.makeText(EditActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateUser> call, Throwable t) {
                Toast.makeText(EditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(EditActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
