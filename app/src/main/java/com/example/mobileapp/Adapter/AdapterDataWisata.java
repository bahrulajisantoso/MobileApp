package com.example.mobileapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.API.RetrofitClient;
import com.example.mobileapp.Activity.DetailWisata;
import com.example.mobileapp.Model.DataWisata;
import com.example.mobileapp.R;

import java.util.List;

public class AdapterDataWisata extends RecyclerView.Adapter<AdapterDataWisata.DataWisataViewHolder> {
    private Context ctx;
    private List<DataWisata> listWisata;

    public AdapterDataWisata(Context ctx, List<DataWisata> listWisata) {
        this.ctx = ctx;
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public DataWisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_wisata, parent, false);
        DataWisataViewHolder holder = new DataWisataViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DataWisataViewHolder holder, int position) {
        DataWisata dataWisata = listWisata.get(position);
//        holder.tvId.setText(String.valueOf(DataItemWisata.getIdWisata()));
        holder.tvNamaWisata.setText(dataWisata.getNamaWisata());
        holder.tvKategori.setText(dataWisata.getKategori());
//        holder.tvDeskripsi.setText(dataWisata.getDeskripsi());
        holder.tvLokasi.setText(dataWisata.getLokasi());

        // load gambar
        Glide.with(ctx).asBitmap().load(RetrofitClient.IMAGE_URL + dataWisata.getGambar()).into(holder.ivGambar);

        holder.cardView.setOnClickListener(view -> {
//                Toast.makeText(ctx, dataWisata.getNamaWisata(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(ctx, DetailWisata.class);

            intent.putExtra("GAMBAR_WISATA", dataWisata.getGambar());
            intent.putExtra("NAMA_WISATA", dataWisata.getNamaWisata());
            intent.putExtra("KATEGORI", dataWisata.getKategori());
            intent.putExtra("LOKASI", dataWisata.getLokasi());
            intent.putExtra("HARGA_TIKET", String.valueOf(dataWisata.getHargaTiket()));
            intent.putExtra("DESKRIPSI", dataWisata.getDeskripsi());
            intent.putExtra("JUMLAH_KUOTA", dataWisata.getJumlah_kuota());
            intent.putExtra("ID_WISATA", dataWisata.getIdWisata());
            intent.putExtra("NO_REKENING", dataWisata.getNo_rekening());
            intent.putExtra("NO_HP", dataWisata.getNo_hp());

            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (listWisata == null) {
            return 0;
        } else {
            return listWisata.size();
        }
    }

    public class DataWisataViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvNamaWisata, tvKategori, tvDeskripsi, tvLokasi;
        private ImageView ivGambar;
        private CardView cardView;

        public DataWisataViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvNamaWisata = (TextView) itemView.findViewById(R.id.tvNamaWisata);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategori);
//            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            tvLokasi = (TextView) itemView.findViewById(R.id.tvLokasi);
            ivGambar = (ImageView) itemView.findViewById(R.id.tvGambar);

            cardView = (CardView) itemView.findViewById(R.id.cardViewWisata);
        }
    }
}
