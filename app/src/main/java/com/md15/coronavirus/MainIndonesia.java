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

public class MainIndonesia extends AppCompatActivity {
    private TextView textViewResult;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh_items);
        textViewResult = findViewById(R.id.main);

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
            Call<List<Indonesia>> call = kawalcoronaAPI.getIndonesia();
            call.enqueue(new Callback<List<Indonesia>>() {
                @Override
                public void onResponse(Call<List<Indonesia>> call, Response<List<Indonesia>> response) {
                    List<Indonesia> indo = response.body();
                    for (Indonesia indonesia : indo) {
                        String content = "";
                        content += "Positif: " + indonesia.getPositif() + "\n";
                        content += "Sembuh: " + indonesia.getSembuh() + "\n";
                        content += "Meninggal: " + indonesia.getMeninggal() + "\n";
                        content += "Dirawat: " + indonesia.getDirawat() + "\n";
                        textViewResult.setText(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Indonesia>> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        }
    }