package com.example.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.R;

public class DetailWisata extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNamaWisata, tvKategori, tvLokasi, tvHargaTiket, tvDeskripsi, kembali;
    private String gambarWisata, namaWisata, kategori, lokasi, hargaTiket, deskripsi;
    private ImageView ivGambarWisata;
    private ImageButton btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        ivGambarWisata = findViewById(R.id.ivGambarDetail);
        tvNamaWisata = findViewById(R.id.tvNamaWisataDetail);
        tvKategori = findViewById(R.id.tvKategoriDetail);
        tvLokasi = findViewById(R.id.tvLokasiDetail);
        tvHargaTiket = findViewById(R.id.tvHargaTiketDetail);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        btnKembali = findViewById(R.id.btnKembali);
        kembali = findViewById(R.id.kembali);
        btnKembali.setOnClickListener(this);
        kembali.setOnClickListener(this);
        getIncomingExtra();
    }


    private void getIncomingExtra() {

        if (getIntent().hasExtra("GAMBAR_WISATA") &&
                getIntent().hasExtra("NAMA_WISATA") &&
                getIntent().hasExtra("KATEGORI") &&
                getIntent().hasExtra("LOKASI") &&
                getIntent().hasExtra("HARGA_TIKET") &&
                getIntent().hasExtra("DESKRIPSI")) {

            gambarWisata = getIntent().getStringExtra("GAMBAR_WISATA");
            namaWisata = getIntent().getStringExtra("NAMA_WISATA");
            kategori = getIntent().getStringExtra("KATEGORI");
            lokasi = getIntent().getStringExtra("LOKASI");
            hargaTiket = getIntent().getStringExtra("HARGA_TIKET");
            deskripsi = getIntent().getStringExtra("DESKRIPSI");

            // load gambar
            Glide.with(DetailWisata.this).load(RetrofitClient.getImageUrl() + gambarWisata).into(ivGambarWisata);

            tvNamaWisata.setText(namaWisata);
            tvKategori.setText(kategori);
            tvLokasi.setText(lokasi);
            tvHargaTiket.setText(String.valueOf(hargaTiket));
            tvDeskripsi.setText(deskripsi);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnKembali:
                Intent kembali = new Intent(this, MainActivity.class);
                startActivity(kembali);
                break;
//            case R.id.kembali:
//                Intent kembali2 = new Intent(this, MainActivity.class);
//                startActivity(kembali2);
//                break;
        }
    }
}
