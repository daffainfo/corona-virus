package com.md15.coronavirus;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DetailProvinsi extends AppCompatActivity {
    private TextView provinsi, positif, sembuh, meninggal;
    private PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Mengakses ID layout untuk textview
        provinsi = findViewById(R.id.nameDetails);
        positif = findViewById(R.id.positifDetails);
        sembuh = findViewById(R.id.sembuhDetails);
        meninggal = findViewById(R.id.meninggalDetails);
        pieChartView = findViewById(R.id.chart);

        //Mendapatkan data yang didapat dari activity lain melalui fungsi onClick
        String setProvinsi = getIntent().getExtras().getString("provinsi");
        String setPositif = getIntent().getExtras().getString("positif");
        String setSembuh = getIntent().getExtras().getString("sembuh");
        String setMeninggal = getIntent().getExtras().getString("meninggal");

        //Set text
        provinsi.setText(setProvinsi);
        positif.setText("Positif\n" + setPositif);
        meninggal.setText("Meninggal\n" + setMeninggal);
        sembuh.setText("Sembuh\n" + setSembuh);

        //Convert string menjadi int agar bisa dijadikan chart
        int positif = Integer.parseInt(setPositif);
        int sembuh = Integer.parseInt(setSembuh);
        int meninggal = Integer.parseInt(setMeninggal);
        int totalKasus = positif + sembuh + meninggal;

        //Persentase untuk pie chart
        int persenPositif = (positif * 100) / totalKasus;
        int persenSembuh = (sembuh * 100) / totalKasus;
        int persenMeninggal = (meninggal * 100) / totalKasus;

        //Pie chart
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(positif, Color.parseColor("#ffae01")).setLabel("Positif " + persenPositif + "%"));
        pieData.add(new SliceValue(meninggal, Color.parseColor("#ce0000")).setLabel("Meninggal " + persenMeninggal + "%"));
        pieData.add(new SliceValue(sembuh, Color.parseColor("#22b700")).setLabel("Sembuh " + persenSembuh + "%"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartData.setHasCenterCircle(true).setCenterText1("Total kasus: " + String.valueOf(totalKasus)).setCenterText1FontSize(18).setCenterText1Color(Color.WHITE);
        pieChartView.setPieChartData(pieChartData);
        pieChartView.setChartRotationEnabled(false);
    }
}