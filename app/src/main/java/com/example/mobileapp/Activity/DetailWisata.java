package com.example.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mobileapp.R;

public class DetailWisata extends AppCompatActivity {

    TextView tvGambarWisata, tvNamaWisata, tvKategori, tvLokasi, tvHargaTiket, tvDeskripsi;
    String gambarWisata, namaWisata, kategori, lokasi, hargaTiket, deskripsi;

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

            tvGambarWisata.setText(gambarWisata);
            tvNamaWisata.setText(namaWisata);
            tvKategori.setText(kategori);
            tvLokasi.setText(lokasi);
            tvHargaTiket.setText(String.valueOf(hargaTiket));
            tvDeskripsi.setText(deskripsi);

        }
    }
}
