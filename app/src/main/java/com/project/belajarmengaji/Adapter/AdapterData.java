package com.project.belajarmengaji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.belajarmengaji.InsertData;
import com.project.belajarmengaji.Model.ModelData;
import com.project.belajarmengaji.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterData (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama_siswa.setText("Nama : "+md.getNama_siswa());
        holder.tvnis.setText("NIS     : "+md.getNis_siswa());
        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnis, tvnama_siswa;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnama_siswa = view.findViewById(R.id.nama_siswa);
            tvnis = view.findViewById(R.id.nis);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InsertData.class);
                    update.putExtra("update",1);
                    update.putExtra("nis_siswa",md.getNis_siswa());
                    update.putExtra("nama_siswa",md.getNama_siswa());
                    update.putExtra("jenis_kelamin",md.getJenis_kelamin());
                    update.putExtra("email",md.getEmail());
                    update.putExtra("password",md.getPassword());

                    context.startActivity(update);
                }
            });
        }
    }
}
