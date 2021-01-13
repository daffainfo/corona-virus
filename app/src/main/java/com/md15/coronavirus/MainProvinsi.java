package com.md15.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainProvinsi extends AppCompatActivity {

    List<Provinsi> provinsiList;
    List<Attributes> attributesList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        provinsiList = new ArrayList<>();
        attributesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(provinsiList, this);
        recyclerView.setAdapter(recyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/indonesia/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalcoronaAPI kawalcoronaAPI = retrofit.create(KawalcoronaAPI.class);

        Call<List<Provinsi>> call = kawalcoronaAPI.getProvinsi();
        call.enqueue(new Callback<List<Provinsi>>() {
            @Override
            public void onResponse(Call<List<Provinsi>> call, Response<List<Provinsi>> response) {
                recyclerView.setAdapter(new RecyclerAdapter(response.body(), MainProvinsi.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }
            @Override
            public void onFailure(Call<List<Provinsi>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainProvinsi.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}