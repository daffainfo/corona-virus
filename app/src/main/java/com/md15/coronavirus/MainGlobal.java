package com.md15.coronavirus;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.md15.coronavirus.recycler.RecyclerAdapterGlobal;
import com.md15.coronavirus.retrofit.KawalcoronaAPI;
import com.md15.coronavirus.retrofit.global.Global;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainGlobal extends AppCompatActivity {

    List<Global> globalList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapterGlobal recyclerAdapterGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mengakses id layout untuk textview dan swipe refresh
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerview);

        //Untuk menyusun item secara vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Instansiasi recycleradapter
        recyclerAdapterGlobal = new RecyclerAdapterGlobal(globalList, this);
        recyclerView.setAdapter(recyclerAdapterGlobal);

        //Memanggil fungsi run
        run();
    }

    //Fungsi yang berguna untuk instansiasi library Retrofit
    private void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalcoronaAPI kawalcoronaAPI = retrofit.create(KawalcoronaAPI.class);

        Call<List<Global>> call = kawalcoronaAPI.getGlobal("/");
        call.enqueue(new Callback<List<Global>>() {
            //Jika berhasil, maka akan menampilkan recycler view yang berisi response dari API
            @Override
            public void onResponse(Call<List<Global>> call, Response<List<Global>> response) {
                Log.d("Global", response.body().toString());
                recyclerView.setAdapter(new RecyclerAdapterGlobal(response.body(), MainGlobal.this));
                //Memberi batasan antara satu dengan yang lain
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }

            //Jika gagal akan menulis stacktrace dan menampilkan Toast
            @Override
            public void onFailure(Call<List<Global>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainGlobal.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}