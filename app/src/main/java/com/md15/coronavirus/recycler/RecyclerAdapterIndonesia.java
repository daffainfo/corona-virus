package com.md15.coronavirus.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.md15.coronavirus.DetailProvinsi;
import com.md15.coronavirus.R;
import com.md15.coronavirus.retrofit.AttributesIndo;
import com.md15.coronavirus.retrofit.Provinsi;

import java.util.List;

public class RecyclerAdapterIndonesia extends RecyclerView.Adapter<RecyclerAdapterIndonesia.ViewHolder> {
    Context context;
    List<Provinsi> provinsiList;

    public RecyclerAdapterIndonesia(List<Provinsi> provinsiList, Context context) {
        this.provinsiList = provinsiList;
        this.context = context;
    }

    //Inisialisasi class ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_adapter,viewGroup,false);
        return new ViewHolder(v);
    }

    //Untuk mengatur data nama provinsi di API kawalcorona
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AttributesIndo attributes = provinsiList.get(position).getAttributes();
        int meninggal = Integer.parseInt(attributes.getKasus_Meni());
        int positif = Integer.parseInt(attributes.getKasus_Posi());
        int sembuh = Integer.parseInt(attributes.getKasus_Semb());

        viewHolder.nama.setText(attributes.getProvinsi());
        viewHolder.total.setText("Total kasus: " + String.valueOf(meninggal + positif + sembuh));
    }

    //Untuk mendapatkan berapa banyak data
    @Override
    public int getItemCount() {
        return provinsiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, total;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            nama = view.findViewById(R.id.nameProvinsi);
            total = view.findViewById(R.id.totalKasus);

            //Fungsi onclick yang nanti akan bisa melihat detail setiap provinsi dan menampilkan toast
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AttributesIndo attributesIndo = provinsiList.get(getAdapterPosition()).getAttributes();
                    Toast.makeText(context, "Nama Provinsi: " + attributesIndo.getProvinsi(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailProvinsi.class);
                    intent.putExtra("provinsi", attributesIndo.getProvinsi());
                    intent.putExtra("positif", attributesIndo.getKasus_Posi());
                    intent.putExtra("sembuh", attributesIndo.getKasus_Semb());
                    intent.putExtra("meninggal", attributesIndo. getKasus_Meni());
                    context.startActivity(intent);
                }
            });
        }
    }
}