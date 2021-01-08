package com.md15.coronavirus;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailProvinsi extends AppCompatActivity {
    private TextView textViewResult;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh_items);
        textViewResult = findViewById(R.id.provinsi);

        run();

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            run();
            mSwipeRefreshLayout.setRefreshing(false);
        });
    }
    private void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KawalcoronaAPI kawalcoronaAPI = retrofit.create(KawalcoronaAPI.class);
        Call<List<Provinsi>> call = kawalcoronaAPI.getProvinsi();
        call.enqueue(new Callback<List<Provinsi>>() {
            @Override
            public void onResponse(Call<List<Provinsi>> call, Response<List<Provinsi>> response) {
                List<Provinsi> prov = response.body();
                for (Provinsi provinsi : prov) {
                    String content = "";
                    content += "Positif: " + provinsi.getKasus_Posi() + "\n";
                    textViewResult.setText(content);
                }
            }

            @Override
            public void onFailure(Call<List<Provinsi>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}