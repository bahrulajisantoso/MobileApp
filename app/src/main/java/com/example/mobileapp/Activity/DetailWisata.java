package com.example.mobileapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobileapp.API.ApiInterface;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Model.UserModel;
import com.example.mobileapp.R;
import com.example.mobileapp.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWisata extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNamaWisata;
    private TextView tvKategori;
    private TextView tvLokasi;
    private TextView tvHargaTiket;
    private TextView tvDeskripsi;
    private TextView tvJumlahKuota;
    private TextView tvNoHp;
    private TextView tvNoRekening;
    private String gambarWisata;
    private String namaWisata;
    private String hargaTiket;
    private int id_wisata;
    private ImageView ivGambarWisata;
    private String id_user;

    public String nama_user, no_hp, new_email;


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
        Button btnPesanTiket = findViewById(R.id.btnPesanTiket);
        tvJumlahKuota = findViewById(R.id.tvJumlahKuota);
        tvNoRekening = findViewById(R.id.tvNoRekening);
        tvNoHp = findViewById(R.id.tvNoHp);


        btnPesanTiket.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FormTransaction.class);
            intent.putExtra("img_wisata", gambarWisata);
            intent.putExtra("nama_wisata", namaWisata);
            intent.putExtra("id_user", id_user);
            intent.putExtra("id_wisata", id_wisata);
            intent.putExtra("harga", hargaTiket);
            intent.putExtra("nama_user", nama_user);
            intent.putExtra("email", new_email);
            intent.putExtra("no_hp", no_hp);
            startActivity(intent);
        });


        ImageButton btnKembali = findViewById(R.id.btnKembali);
        TextView kembali = findViewById(R.id.kembali);
        btnKembali.setOnClickListener(this);
        kembali.setOnClickListener(this);
        getIncomingExtra();

        SessionManager sessionManager = new SessionManager(DetailWisata.this);
        String email = sessionManager.getUserData().get(SessionManager.getEMAIL());
        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
        getDataUser(email);
    }

    private void getDataUser(String email) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<UserModel> userModelCall = apiInterface.getDataUserByID(email);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NonNull Call<UserModel> call, @NonNull Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userModel = response.body();
                    assert userModel != null;
                    id_user = userModel.getId_user();
                    nama_user = userModel.getNama_user();
                    new_email = userModel.getEmail();
                    no_hp = userModel.getNo_hp();
                    System.out.println(id_user + " " + nama_user + " " + email + " " + no_hp);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {

            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void getIncomingExtra() {

        if (getIntent().hasExtra("GAMBAR_WISATA") &&
                getIntent().hasExtra("NAMA_WISATA") &&
                getIntent().hasExtra("KATEGORI") &&
                getIntent().hasExtra("LOKASI") &&
                getIntent().hasExtra("HARGA_TIKET") &&
                getIntent().hasExtra("DESKRIPSI") &&
                getIntent().hasExtra("ID_WISATA") &&
                getIntent().hasExtra("NO_REKENING") &&
                getIntent().hasExtra("NO_HP") &&
                getIntent().hasExtra("JUMLAH_KUOTA")) {

            gambarWisata = getIntent().getStringExtra("GAMBAR_WISATA");
            namaWisata = getIntent().getStringExtra("NAMA_WISATA");
            String kategori = getIntent().getStringExtra("KATEGORI");
            String lokasi = getIntent().getStringExtra("LOKASI");
            hargaTiket = getIntent().getStringExtra("HARGA_TIKET");
            String deskripsi = getIntent().getStringExtra("DESKRIPSI");
            int jumlahKuota = getIntent().getIntExtra("JUMLAH_KUOTA", 0);
            id_wisata = getIntent().getIntExtra("ID_WISATA", 0);
            String no_rekening = getIntent().getStringExtra("NO_REKENING");
            String no_hp = getIntent().getStringExtra("NO_HP");

            // load gambar
            Glide.with(DetailWisata.this).load(RetrofitClient.IMAGE_URL + gambarWisata).into(ivGambarWisata);
            tvNamaWisata.setText(namaWisata);
            tvKategori.setText(kategori);
            tvNoRekening.setText(no_rekening);
            tvLokasi.setText(lokasi);
            tvNoHp.setText(no_hp);
            tvHargaTiket.setText(String.valueOf(hargaTiket));
            tvDeskripsi.setText(deskripsi);
            tvJumlahKuota.setText("Kuota " + jumlahKuota + " -orang");
            System.out.println(id_wisata);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnKembali) {
            Intent kembali = new Intent(this, MainActivity.class);
            startActivity(kembali);
        }
    }
}
