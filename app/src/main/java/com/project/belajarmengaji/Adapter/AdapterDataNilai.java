package com.project.belajarmengaji.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.belajarmengaji.Model.ModelData;
import com.project.belajarmengaji.R;

import java.util.List;

public class AdapterDataNilai extends RecyclerView.Adapter<AdapterDataNilai.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterDataNilai(Context context, List<ModelData> items) {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_nilai, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelData md_nilai = mItems.get(position);
        holder.tvnama_nilai.setText ("Nama  : "+md_nilai.getNama_siswa());
        holder.tvnis_nilai.setText  ("NIS      : "+md_nilai.getNis_siswa());
        holder.tvnilai_siswa.setText("Nilai     : "+md_nilai.getNilai_siswa());
        holder.md_nilai = md_nilai;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder {
        TextView tvnis_nilai, tvnama_nilai, tvnilai_siswa;
        ModelData md_nilai;

        private HolderData(View view) {
            super(view);

            tvnama_nilai = view.findViewById(R.id.nama_siswa_nilai);
            tvnis_nilai = view.findViewById(R.id.nis_nilai);
            tvnilai_siswa = view.findViewById(R.id.nilai_siswa);

        }

    }
}