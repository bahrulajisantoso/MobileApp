package com.example.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.R;

public class DetailWisata extends AppCompatActivity implements View.OnClickListener {

    TextView  tvNamaWisata, tvKategori, tvLokasi, tvHargaTiket, tvDeskripsi,kembali;
    String gambarWisata, namaWisata, kategori, lokasi, hargaTiket, deskripsi;
    ImageView tvGambarWisata;
    ImageButton btnKembali;

    public static String img="http://192.168.0.102/app%20landing%20page/Arah%20Kita%20WEB/img/wisata/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        tvGambarWisata = findViewById(R.id.ivGambarDetail);
        tvNamaWisata = findViewById(R.id.tvNamaWisataDetail);
        tvKategori = findViewById(R.id.tvKategoriDetail);
        tvLokasi = findViewById(R.id.tvLokasiDetail);
        tvHargaTiket = findViewById(R.id.tvHargaTiketDetail);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        btnKembali= findViewById(R.id.btnKembali);
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

            Glide.with(DetailWisata.this).load(img+gambarWisata).into(tvGambarWisata);
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
            case R.id.kembali:
                Intent kembali2 = new Intent(this, MainActivity.class);
                startActivity(kembali2);
                break;
        }
    }
}
