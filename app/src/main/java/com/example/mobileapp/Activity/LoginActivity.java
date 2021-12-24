package com.example.mobileapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapp.API.ApiRequestData;
import com.example.mobileapp.API.RetroServer;
import com.example.mobileapp.Model.DataLogin;
import com.example.mobileapp.Model.ResponseLogin;
import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText etEmail, etPassword;
    Button btnLogin;
    String email, password;
    TextView tvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // bind
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        // button login
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(LoginActivity.this);


        // text view register
        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(LoginActivity.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if (email.trim().equals("")) {
                    etEmail.setError("Email kosong");
                } else if (password.trim().equals("")) {
                    etPassword.setError("Password Kosong");
                } else {
                    login();
                }
                break;

            case R.id.tvRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    // fungsi login
    private void login() {

        ApiRequestData apiRequestData = RetroServer.getClient().create(ApiRequestData.class);
        Call<ResponseLogin> responseLoginCall = apiRequestData.ardLogin(email, password);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getKode() == 200) {

                    // session
                    SessionManager sessionManager = new SessionManager(LoginActivity.this);
                    DataLogin dataLogin = response.body().getData();
                    sessionManager.createLoginSession(dataLogin);

                    Toast.makeText(LoginActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}