package com.md15.coronavirus;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.md15.coronavirus.retrofit.Indonesia;
import com.md15.coronavirus.retrofit.KawalcoronaAPI;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainIndonesia extends AppCompatActivity {
    private TextView positif, sembuh, meninggal;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mengakses id layout untuk textview dan swipe refresh
        swipeRefreshLayout = findViewById(R.id.swiperefresh_items);
        positif = findViewById(R.id.positifIndo);
        sembuh = findViewById(R.id.sembuhIndo);
        meninggal = findViewById(R.id.meninggalIndo);
        pieChartView = findViewById(R.id.chart);

        //Memanggil fungsi run
        run();

        //Untuk merefresh data
        swipeRefreshLayout.setOnRefreshListener(() -> {
            run();
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(MainIndonesia.this, "Refreshed!", Toast.LENGTH_SHORT).show();
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
                    //Regex buat menghilangkan koma di value API agar bisa masuk pie chart
                    String dataPositif = indonesia.getPositif().replaceAll(",", "");
                    String dataDirawat = indonesia.getDirawat().replaceAll(",", "");
                    String dataMeninggal = indonesia.getMeninggal().replaceAll(",", "");
                    String dataSembuh = indonesia.getSembuh().replaceAll(",", "");

                    //Persentase untuk piechart
                    int persenDirawat = (Integer.parseInt(dataDirawat) * 100) / Integer.parseInt(dataPositif);
                    int persenSembuh = (Integer.parseInt(dataSembuh) * 100) / Integer.parseInt(dataPositif);
                    int persenMeninggal = (Integer.parseInt(dataMeninggal) * 100) / Integer.parseInt(dataPositif);

                    //Membuat Pie chart
                    List pieData = new ArrayList<>();
                    pieData.add(new SliceValue(Integer.parseInt(dataDirawat), Color.parseColor("#ffae01")).setLabel("Positif " + persenDirawat + "%"));
                    pieData.add(new SliceValue(Integer.parseInt(dataSembuh), Color.parseColor("#22b700")).setLabel("Sembuh " + persenSembuh + "%"));
                    pieData.add(new SliceValue(Integer.parseInt(dataMeninggal), Color.parseColor("#ce0000")).setLabel("Meninggal " + persenMeninggal + "%"));

                    PieChartData pieChartData = new PieChartData(pieData);
                    pieChartData.setHasLabels(true).setValueLabelTextSize(13);
                    pieChartData.setHasCenterCircle(true).setCenterText1("Total kasus\n" + indonesia.getPositif()).setCenterText1FontSize(16).setCenterText1Color(Color.WHITE);
                    pieChartView.setPieChartData(pieChartData);
                    pieChartView.setChartRotationEnabled(false);

                    //Set text
                    positif.setText("Positif\n" + indonesia.getDirawat());
                    meninggal.setText("Meninggal\n" + indonesia.getMeninggal());
                    sembuh.setText("Sembuh\n" + indonesia.getSembuh());
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