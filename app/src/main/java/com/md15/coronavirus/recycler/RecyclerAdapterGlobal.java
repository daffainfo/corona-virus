package com.md15.coronavirus.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.md15.coronavirus.DetailGlobal;
import com.md15.coronavirus.DetailProvinsi;
import com.md15.coronavirus.R;
import com.md15.coronavirus.retrofit.global.Attributes;
import com.md15.coronavirus.retrofit.global.Global;

import java.util.List;

public class RecyclerAdapterGlobal extends RecyclerView.Adapter<RecyclerAdapterGlobal.ViewHolder> {
    List<Global> globalList;
    Context context;

    public RecyclerAdapterGlobal(List<Global> globalList, Context context) {
        this.globalList = globalList;
        this.context = context;
    }

    //Inisialisasi class ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_adapter,viewGroup,false);
        return new ViewHolder(v);
    }

    //Untuk mengatur data nama global di API kawalcorona
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Attributes attributes = globalList.get(position).getAttributes();

        viewHolder.nama.setText(attributes.getCountry_Region());
        viewHolder.total.setText("Total kasus: " + attributes.getConfirmed());
    }

    //Untuk mendapatkan berapa banyak data
    @Override
    public int getItemCount() {
        return globalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, total;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            nama = view.findViewById(R.id.nama);
            total = view.findViewById(R.id.totalKasus);

            //Fungsi onclick yang nanti akan bisa melihat detail setiap negara dan menampilkan toast
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Attributes attributes = globalList.get(getAdapterPosition()).getAttributes();
                    Toast.makeText(context, "Nama Negara: " + attributes.getCountry_Region(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailGlobal.class);
                    intent.putExtra("region", attributes.getCountry_Region());
                    intent.putExtra("confirmed", attributes.getConfirmed());
                    intent.putExtra("active", attributes.getActive());
                    intent.putExtra("recovered", attributes.getRecovered());
                    intent.putExtra("deaths", attributes.getDeaths());
                    context.startActivity(intent);
                }
            });
        }
    }
}