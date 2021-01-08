package com.md15.coronavirus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Provinsi> provinsiList;

    public RecyclerAdapter(Context context, List<Provinsi> provinsiList) {
        this.context = context;
        this.provinsiList = provinsiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attributes attributes = provinsiList.get(position).getAttributes();
        holder.t1.setText(attributes.getProvinsi());
    }

    @Override
    public int getItemCount() {
        return provinsiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.nameProvinsi);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Attributes attributes = provinsiList.get(position).getAttributes();
//                Intent intent = new Intent(context, DetailProvinsi.class);
//                intent.putExtra("", attributes.getProvinsi());
//                context.startActivity(intent);
                Toast.makeText(context, "Nama Provinsi: " + attributes.getProvinsi(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}