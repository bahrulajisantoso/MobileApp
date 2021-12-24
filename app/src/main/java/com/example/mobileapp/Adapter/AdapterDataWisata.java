package com.example.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_wisata, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataWisata DataWisata = listWisata.get(position);

//        holder.tvId.setText(String.valueOf(DataItemWisata.getIdWisata()));
        holder.tvNamaWisata.setText(DataWisata.getNamaWisata());
        holder.tvKategori.setText(DataWisata.getKategori());
//        holder.tvDeskripsi.setText(DataItemWisata.getDeskripsi());
        holder.tvLokasi.setText(DataWisata.getLokasi());
        holder.tvGambar.setText(DataWisata.getGambar());
        holder.tvHargaTiket.setText(String.valueOf(DataWisata.getHargaTiket()));
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNamaWisata, tvKategori, tvDeskripsi,
                tvLokasi, tvGambar, tvHargaTiket;

        public HolderData(@NonNull View itemView) {
            super(itemView);

//            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvNamaWisata = (TextView) itemView.findViewById(R.id.tvNamaWisata);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategori);
//            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            tvLokasi = (TextView) itemView.findViewById(R.id.tvLokasi);
            tvGambar = (TextView) itemView.findViewById(R.id.ivGambar);
            tvHargaTiket = (TextView) itemView.findViewById(R.id.tvHargaTiket);
        }
    }
}
