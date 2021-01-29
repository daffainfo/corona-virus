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

public class DetailGlobal extends AppCompatActivity {
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
        String setRegion = getIntent().getExtras().getString("region");
        String setPositif = getIntent().getExtras().getString("active");
        String setSembuh = getIntent().getExtras().getString("recovered");
        String setMeninggal = getIntent().getExtras().getString("deaths");
        String setTotal = getIntent().getExtras().getString("confirmed");

        //Set text
        provinsi.setText(setRegion);
        positif.setText("Positif\n" + setPositif);
        meninggal.setText("Meninggal\n" + setMeninggal);
        sembuh.setText("Sembuh\n" + setSembuh);

        //String -> int agar bisa dijadikan chart
        int positif = Integer.parseInt(setPositif);
        int sembuh = Integer.parseInt(setSembuh);
        int meninggal = Integer.parseInt(setMeninggal);
        int totalKasus = Integer.parseInt(setTotal);

        //Persentase untuk pie chart
        float persenPositif = (positif * 100) / totalKasus;
        float persenSembuh = (sembuh * 100) / totalKasus;
        float persenMeninggal = (meninggal * 100) / totalKasus;

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