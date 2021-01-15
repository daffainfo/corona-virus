package com.md15.coronavirus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Provinsi> provinsiList;

    public RecyclerAdapter(List<Provinsi> provinsiList, Context context) {
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
        Attributes attributes = provinsiList.get(position).getAttributes();
        viewHolder.nama.setText(attributes.getProvinsi());
    }

    //Untuk mendapatkan berapa banyak data
    @Override
    public int getItemCount() {
        return provinsiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            nama = view.findViewById(R.id.nameProvinsi);

            //Fungsi onclick yang nanti akan bisa melihat detail setiap provinsi dan menampilkan toast
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Attributes attributes = provinsiList.get(getAdapterPosition()).getAttributes();
                    Toast.makeText(context, "Nama Provinsi: " + attributes.getProvinsi(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,DetailProvinsi.class);
                    intent.putExtra("provinsi",attributes.getProvinsi());
                    intent.putExtra("positif", attributes.getKasus_Posi());
                    intent.putExtra("sembuh", attributes.getKasus_Semb());
                    intent.putExtra("meninggal", attributes. getKasus_Meni());
                    context.startActivity(intent);
                }
            });
        }
    }
}