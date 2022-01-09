package com.example.mobileapp.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormTransaction extends AppCompatActivity {

    private String nama_wisata;
    private TextView txt_total_harga;
    private TextView txt_desc_jumlah_tiket;
    private TextView txt_tgl_transaksi;
    private SimpleDateFormat simpleDateFormat;
    private double harga_tiket = 0;

    // form credetial
    private String id_wisata;
    private String id_user;
    private String nama_user, email, no_hp;
    private String tgl_transaksi;
    private int jumlah_tiket = 0;
    private int total_harga = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_transaction);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        ImageView iv_img_detail_wisata = findViewById(R.id.iv_img_detail_wisata);
        TextView txt_detail_nama_wisata = findViewById(R.id.txt_detail_nama_wisata);
        TextView txt_harga_tiket = findViewById(R.id.txt_harga_tiket);
        TextView txt_nama_user = findViewById(R.id.txt_nama_user);
        TextView txt_email_user = findViewById(R.id.txt_email_user);
        TextView txt_nohp_user = findViewById(R.id.txt_nohp_user);
        txt_tgl_transaksi = findViewById(R.id.txt_tgl_transaksi);
        txt_desc_jumlah_tiket = findViewById(R.id.txt_desc_jumlah_tiket);
        Button btn_tgl_transaksi = findViewById(R.id.btn_tgl_transaksi);
        ImageButton btnKembali = findViewById(R.id.btnKembali);
        txt_total_harga = findViewById(R.id.txt_total_harga);
        TextView txt_jumlah_tiket = findViewById(R.id.txt_jumlah_tiket);
        Button btn_konfirmasi_pembayaran = findViewById(R.id.btn_konfirmasi_pembayaran);

        btn_tgl_transaksi.setOnClickListener(v -> showDatePickerDialog(txt_tgl_transaksi));
        btnKembali.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        // set data user
        txt_total_harga.setText("Rp. -");


        if (getIntent() != null) {
            String img_detail_wisata = getIntent().getStringExtra("img_wisata");
            nama_wisata = getIntent().getStringExtra("nama_wisata");
            Glide.with(getApplicationContext()).load(RetrofitClient.IMAGE_URL + img_detail_wisata).into(iv_img_detail_wisata);
            txt_detail_nama_wisata.setText(nama_wisata);
            id_wisata = String.valueOf(getIntent().getIntExtra("id_wisata", 0));
            id_user = getIntent().getStringExtra("id_user");
            nama_user = getIntent().getStringExtra("nama_user");
            email = getIntent().getStringExtra("email");
            no_hp = getIntent().getStringExtra("no_hp");

            txt_nama_user.setText(nama_user);
            txt_email_user.setText(email);
            txt_nohp_user.setText(no_hp);
            harga_tiket = Double.parseDouble(getIntent().getStringExtra("harga"));
            txt_desc_jumlah_tiket.setText(nama_wisata + " x " + jumlah_tiket);
            txt_harga_tiket.setText("Rp." + harga_tiket);
        }

        txt_jumlah_tiket.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    jumlah_tiket = Integer.parseInt(s.toString());
                    total_harga = (int) (jumlah_tiket * harga_tiket);
                    txt_total_harga.setText("Rp. " + total_harga);
                    txt_desc_jumlah_tiket.setText(nama_wisata + " x " + jumlah_tiket);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

        btn_konfirmasi_pembayaran.setOnClickListener(v -> {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, RetrofitClient.TRANSACTION,
                    response -> {
                        if (jumlah_tiket != 0) {
                            updateJumlahTiket(id_wisata, String.valueOf(jumlah_tiket));
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), "Jumlah tiket min 1 tiket max 100 tiket", Toast.LENGTH_SHORT).show()) {

                @NonNull
                @Override
                protected Map<String, String> getParams() {
                    if (jumlah_tiket == 0 || jumlah_tiket > 100) {
                        Toast.makeText(getApplicationContext(), "Jumlah tiket min 1 tiket max 100 tiket", Toast.LENGTH_SHORT).show();
                    }
                    tgl_transaksi = txt_tgl_transaksi.getText().toString();
                    Map<String, String> map = new HashMap<>();
                    map.put("id_wisata", id_wisata);
                    map.put("id_user", id_user);
                    map.put("tgl_transaksi", tgl_transaksi);
                    map.put("jumlah_tiket", String.valueOf(jumlah_tiket));
                    map.put("total_harga", String.valueOf(total_harga));
                    map.put("status", "belum dibayar");
                    return map;
                }
            };
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        });
    }

    private void showDatePickerDialog(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year, month, dayOfMonth);
            textView.setText(simpleDateFormat.format(calendar1.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void updateJumlahTiket(String id_wisata, String jumlah_tiket) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RetrofitClient.UPDATE_JUMLAH_TIKET,
                response -> {
                    Toast.makeText(getApplicationContext(), "Transaction success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
                },
                error -> Toast.makeText(getApplicationContext(), "Transaction Failed " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("id_wisata", id_wisata);
                map.put("jumlah_tiket", jumlah_tiket);
                return map;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}