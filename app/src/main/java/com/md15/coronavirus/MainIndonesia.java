package com.md15.coronavirus;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainIndonesia extends AppCompatActivity {
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mengakses id layout untuk textview dan swipe refresh
        swipeRefreshLayout = findViewById(R.id.swiperefresh_items);
        textView = findViewById(R.id.main);

        //Memanggil fungsi run
        run();

        //Untuk merefresh data
        swipeRefreshLayout.setOnRefreshListener(() -> {
            run();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    //Fungsi yang berguna untuk instansiasi library Retrofit
    private void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalcoronaAPI kawalcoronaAPI = retrofit.create(KawalcoronaAPI.class);

        Call<List<Indonesia>> call = kawalcoronaAPI.getIndonesia();
        call.enqueue(new Callback<List<Indonesia>>() {
            //Jika berhasil maka, akan menampilkan response
            @Override
            public void onResponse(Call<List<Indonesia>> call, Response<List<Indonesia>> response) {
                List<Indonesia> indo = response.body();
                for (Indonesia indonesia : indo) {
                    String string = "";
                    string += "Positif: " + indonesia.getPositif() + "\n";
                    string += "Sembuh: " + indonesia.getSembuh() + "\n";
                    string += "Meninggal: " + indonesia.getMeninggal() + "\n";
                    string += "Dirawat: " + indonesia.getDirawat() + "\n";
                    textView.setText(string);
                }
            }
            //Jika gagal mengambil data, maka akan muncul Toast
            @Override
            public void onFailure(Call<List<Indonesia>> call, Throwable t) {
                Toast.makeText(MainIndonesia.this, "Check your connectivity!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}