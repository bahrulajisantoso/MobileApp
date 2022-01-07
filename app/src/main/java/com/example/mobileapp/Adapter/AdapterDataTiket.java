package com.example.mobileapp.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Model.DataTiketModel;
import com.example.mobileapp.R;

import java.util.List;

public class AdapterDataTiket extends RecyclerView.Adapter<AdapterDataTiket.DataTiketViewHolder> {

    List<DataTiketModel> list;

    public AdapterDataTiket(List<DataTiketModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DataTiketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_tiket_item, parent, false);
        return new DataTiketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataTiketViewHolder holder, int position) {
        holder.txt_nama_wisata.setText("Nama Wisata: " + list.get(position).getNama_wisata());
        holder.txt_tgl_transaksi_tiket.setText("Tanggal Transaksi : " + list.get(position).getTgl_transaksi());
        holder.txt_no_hp.setText(list.get(position).getNo_hp());
        holder.txt_jumlah_tiket.setText("Jumlah Tiket: " + list.get(position).getJumlah_tiket());
        holder.txt_total_harga.setText("Total Bayar: Rp." + list.get(position).getTotal_harga());
        holder.txt_status.setText(list.get(position).getStatus());
        holder.txt_no_rekening.setText("- Tf Ke: " + list.get(position).getNo_rekening());

        holder.btn_hubungi.setOnClickListener(v -> {
            String no_hp = holder.txt_no_hp.getText().toString();
            String phone_number = no_hp.replaceFirst("0", "+62");
            String message = "Saya%20ingin%20mengkonfirmasi%20pembayaran%20tiket%20dengan%20tujuan%20wisata%20%3a%20" + holder.txt_nama_wisata.getText().toString() + ".%0aBerikut%20bukti%20pembayaran%3a";
            System.out.println(phone_number);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phone_number +
                    "&text=" + message));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DataTiketViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nama_wisata, txt_tgl_transaksi_tiket, txt_jumlah_tiket, txt_total_harga, txt_status,
                txt_no_rekening, txt_no_hp;

        private Button btn_hubungi;

        public DataTiketViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_hubungi = itemView.findViewById(R.id.btn_hubungi);
            txt_nama_wisata = itemView.findViewById(R.id.txt_nama_wisata);
            txt_tgl_transaksi_tiket = itemView.findViewById(R.id.txt_tgl_transaksi_tiket);
            txt_jumlah_tiket = itemView.findViewById(R.id.txt_jumlah_tiket);
            txt_total_harga = itemView.findViewById(R.id.txt_total_harga);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_no_rekening = itemView.findViewById(R.id.txt_no_rekening);
            txt_no_hp = itemView.findViewById(R.id.txt_no_hp);
        }
    }
}
