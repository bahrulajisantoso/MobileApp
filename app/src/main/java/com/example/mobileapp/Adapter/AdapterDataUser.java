package com.example.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Model.DataUser;
import com.example.mobileapp.R;

import java.util.List;

public class AdapterDataUser extends RecyclerView.Adapter<AdapterDataUser.HolderData> {
    private Context ctx;
    private List<DataUser> listUser;

    public AdapterDataUser(Context ctx, List<DataUser> listUser) {
        this.ctx = ctx;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public AdapterDataUser.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_user, parent, false);
        AdapterDataUser.HolderData holderData = new AdapterDataUser.HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataUser DataUser = listUser.get(position);

        holder.tvNama.setText(DataUser.getNamaUser());
        holder.tvNik.setText(DataUser.getNik());
        holder.tvEmail.setText(DataUser.getEmail());
        holder.tvNoHp.setText(DataUser.getNoHp());
        holder.tvTglLahir.setText(DataUser.getTglLahir());
        holder.tvJeniskel.setText(DataUser.getJenisKelamin());

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvNama, tvNik, tvEmail, tvNoHp, tvTglLahir, tvJeniskel;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvNik = (TextView) itemView.findViewById(R.id.tvNik);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvNoHp = (TextView) itemView.findViewById(R.id.tvNoHp);
            tvTglLahir = (TextView) itemView.findViewById(R.id.tvTglLahir);
            tvJeniskel = (TextView) itemView.findViewById(R.id.tvJenisKel);
        }
    }
}
