package com.example.mobileapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Activity.DetailWisata;
import com.example.mobileapp.Model.DataWisata;
import com.example.mobileapp.R;

import java.util.List;

public class AdapterDataWisata extends RecyclerView.Adapter<AdapterDataWisata.HolderData> {
    private Context ctx;
    private List<DataWisata> listWisata;

    public AdapterDataWisata(Context ctx, List<DataWisata> listWisata) {
        this.ctx = ctx;
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_wisata, parent, false);
        HolderData holder = new HolderData(view);
        return holder;

    }

//    holder.linearlayout.setOnClickListener(new View.OnClickListener()
//
//    {
//        @Override
//        public void onClick (View view){
//        // Toast.makeText(ctx,DataWisata.getNamaWisata(), Toast.LENGTH_LONG).show();
//
//        Intent intent = new Intent(ctx, DetailWisata.class);
//
//        intent.putExtra("gambar_wisata", DataWisata.getGambar());
//        intent.putExtra("nama_wisata", DataWisata.getNamaWisata());
//        intent.putExtra("kategori", DataWisata.getKategori());
//        intent.putExtra("lokasi", DataWisata.getLokasi());
//        intent.putExtra("harga_tiket", DataWisata.getHargaTiket());
//        intent.putExtra("deskripsi", DataWisata.getDeskripsi());
//
//        ctx.startActivity(intent);
//    }
//    });

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataWisata DataWisata = listWisata.get(position);
//        holder.tvId.setText(String.valueOf(DataItemWisata.getIdWisata()));
        holder.tvNamaWisata.setText(DataWisata.getNamaWisata());
        holder.tvKategori.setText(DataWisata.getKategori());
//        holder.tvDeskripsi.setText(DataWisata.getDeskripsi());
        holder.tvLokasi.setText(DataWisata.getLokasi());
        holder.tvGambar.setText(DataWisata.getGambar());


//        Glide.with(ctx).asBitmap().load(fotoMakanan).get(position).into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ctx, DataWisata.getNamaWisata(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ctx, DetailWisata.class);

                intent.putExtra("GAMBAR_WISATA", DataWisata.getGambar());
                intent.putExtra("NAMA_WISATA", DataWisata.getNamaWisata());
                intent.putExtra("KATEGORI", DataWisata.getKategori());
                intent.putExtra("LOKASI", DataWisata.getLokasi());
                intent.putExtra("HARGA_TIKET", String.valueOf(DataWisata.getHargaTiket()));
                intent.putExtra("DESKRIPSI", DataWisata.getDeskripsi());

                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNamaWisata, tvKategori, tvDeskripsi,
                tvLokasi, tvGambar;

        CardView cardView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

//            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvNamaWisata = (TextView) itemView.findViewById(R.id.tvNamaWisata);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategori);
//            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            tvLokasi = (TextView) itemView.findViewById(R.id.tvLokasi);
            tvGambar = (TextView) itemView.findViewById(R.id.ivGambar);

            cardView = (CardView) itemView.findViewById(R.id.cardViewWisata);
        }
    }
}
