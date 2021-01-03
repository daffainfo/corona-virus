package com.md15.coronavirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Attributes> attributesList;

    public RecyclerAdapter(Context context, List<Provinsi> provinsiList) {
        this.context = context;
        this.attributesList = attributesList;
    }

    public void setProvinsiList() {
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        holder.provinsiName.setText(attributesList.get(position).getProvinsi());
    }

    @Override
    public int getItemCount() {
        if(attributesList != null){
            return attributesList.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView provinsiName;

        public MyviewHolder(View itemView) {
            super(itemView);
            provinsiName = itemView.findViewById(R.id.nameProvinsi);
        }
    }
}