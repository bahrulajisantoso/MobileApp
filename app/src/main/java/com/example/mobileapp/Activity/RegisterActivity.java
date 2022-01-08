package com.example.mobileapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Model.ResponseRegister;
import com.example.mobileapp.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama, etEmail, etNoHp, etNik, etJeniKel, etTglLahir, etAlamat, etPassword, etKonfirmPassword;
    private Button btnRegister;
    private ImageButton btnPilihTglLahir;
    private TextView tvLogin;
    private Spinner spinJenisKel;
    private String nama, email, noHp, nik, jenisKelamin, tglLahir, alamat, password, konfirmPassword, kodeNegara;
//    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}$";
    //                    "(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$";
//    private static final Pattern patternPass = Pattern.compile(PASSWORD_PATTERN);
//    private Matcher matcherPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // bind
        etNama = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNoHp = findViewById(R.id.etNoHp);
//        etNik = findViewById(R.id.etNik);

        btnPilihTglLahir = (ImageButton) findViewById(R.id.btnPilihTglLahir);
        etTglLahir = (EditText) findViewById(R.id.etTglLahir);
        etTglLahir.setEnabled(false);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        btnPilihTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        etJeniKel = findViewById(R.id.etJenisKel);
        etJeniKel.setEnabled(false);

        spinJenisKel = findViewById(R.id.spinJenisKel);
        final String[] jenisKel = getResources().getStringArray(R.array.jenis_kelamin);

//        Spinner spinnerJenisKel = (Spinner) findViewById(R.id.spinnerJenisKel);
        spinJenisKel = (Spinner) findViewById(R.id.spinJenisKel);

        ArrayAdapter<CharSequence> adapterJenisKel = new ArrayAdapter<CharSequence>(RegisterActivity.this, android.R.layout.simple_spinner_item, jenisKel);
        adapterJenisKel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenisKel.setAdapter(adapterJenisKel);

        spinJenisKel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jenisKelamin = adapterView.getItemAtPosition(i).toString();
                etJeniKel.setText(jenisKelamin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                nama = etNama.getText().toString().toLowerCase();
                email = etEmail.getText().toString().toLowerCase();

                kodeNegara = "+62";
                noHp = kodeNegara + etNoHp.getText().toString();
//                nik = etNik.getText().toString();
//                jenisKelamin = etJeniKel.getText().toString();
                tglLahir = etTglLahir.getText().toString();
                alamat = etAlamat.getText().toString().toLowerCase();

                password = etPassword.getText().toString();
                // validasi password
//                matcherPass = patternPass.matcher(password);
//                isValidPassword(password);

                konfirmPassword = etKonfirmPassword.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("Nama harus diisi");
                } else if (nama.trim().length() > 40) {
                    etNama.setError("Max 40 karakter");

                } else if (email.trim().equals("")) {
                    etEmail.setError("Email harus diisi");
                } else if (email.trim().length() > 30) {
                    etEmail.setError("Max 30 karakter");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Masukkan email yang valid");

                } else if (noHp.trim().equals("+62")) {
                    etNoHp.setError("No handphone harus diisi");
                } else if (noHp.trim().length() < 13 || noHp.trim().length() > 16) {
                    etNoHp.setError("No handphone tidak valid");
                } else if (!Patterns.PHONE.matcher(noHp).matches()) {
                    etNoHp.setError("No handphone tidak valid");
//                } else if (nik.trim().equals("")) {
//                    etNik.setError("NIK harus diisi");
//                } else if (jenisKelamin.trim().equals("")) {
//                    etJeniKel.setError("Jenis kelamin harus diisi");

                } else if (tglLahir.trim().equals("")) {
                    etTglLahir.setError("Tanggal lahir harus diisi");

                } else if (jenisKelamin.trim().equals("Pilih")) {
                    TextView errTv = (TextView) spinJenisKel.getSelectedView();
                    errTv.setError("Jenis kelamin harus diisi");

                } else if (alamat.trim().equals("")) {
                    etAlamat.setError("Alamat harus diisi");
                } else if (alamat.trim().length() > 90) {
                    etAlamat.setError("Max 90 karakter");


                } else if (password.trim().equals("")) {
                    etPassword.setError("Password harus diisi");
                } else if (password.trim().length() < 8 || password.trim().length() > 16) {
                    etPassword.setError("Password min 8 karakter max 16 karakter");
                } else if (!isValidPassword(password)) {
                    etPassword.setError("Password harus berisi kombinasi huruf besar, huruf kecil, angka, dan tanpa spasi");

                } else if (konfirmPassword.trim().equals("")) {
                    etKonfirmPassword.setError("konfirmasi password harus diisi");
                } else if (!konfirmPassword.trim().equals(password)) {
                    etKonfirmPassword.setError("Konfirmasi password tidak sesuai");
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
        Call<ResponseRegister> responseRegisterCall = apiInterface.addUser(nama, email, noHp, jenisKelamin, tglLahir, alamat, password, konfirmPassword);
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

    private static boolean isValidPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])"
//                "(?=.*[A-Z])"
//                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,16}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
//        if (password == null) {
//            return false;
//        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}
