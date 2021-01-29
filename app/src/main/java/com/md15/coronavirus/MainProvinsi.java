package com.md15.coronavirus;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.md15.coronavirus.recycler.RecyclerAdapterIndonesia;
import com.md15.coronavirus.retrofit.KawalcoronaAPI;
import com.md15.coronavirus.retrofit.provinsi.Provinsi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainProvinsi extends AppCompatActivity {

    List<Provinsi> provinsiList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapterIndonesia recyclerAdapterIndonesia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mengakses id layout untuk textview dan swipe refresh
        setContentView(R.layout.activity_adapter);
        recyclerView = findViewById(R.id.recyclerview);

        //Untuk menyusun item secara vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Instansiasi recycleradapter
        recyclerAdapterIndonesia = new RecyclerAdapterIndonesia(provinsiList, this);
        recyclerView.setAdapter(recyclerAdapterIndonesia);

        //Memanggil fungsi run
        run();
    }

    //Fungsi yang berguna untuk instansiasi library Retrofit
    private void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/indonesia/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalcoronaAPI kawalcoronaAPI = retrofit.create(KawalcoronaAPI.class);

        Call<List<Provinsi>> call = kawalcoronaAPI.getProvinsi();
        call.enqueue(new Callback<List<Provinsi>>() {
            //Jika berhasil, maka akan menampilkan recycler view yang berisi response dari API
            @Override
            public void onResponse(Call<List<Provinsi>> call, Response<List<Provinsi>> response) {
                recyclerView.setAdapter(new RecyclerAdapterIndonesia(response.body(), MainProvinsi.this));
                //Memberi batasan antara satu dengan yang lain
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }

            //Jika gagal akan menulis stacktrace dan menampilkan Toast
            @Override
            public void onFailure(Call<List<Provinsi>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainProvinsi.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}