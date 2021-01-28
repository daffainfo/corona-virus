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
    private TextView provinsi;
    private TextView positif;
    private TextView sembuh;
    private TextView meninggal;
    private PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Mengakses ID layout untuk textview
        provinsi = findViewById(R.id.provinsiDetails);
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
        sembuh.setText("Sembuh\n" + setSembuh);
        meninggal.setText("Wafat\n" + setMeninggal);

        //String -> int agar bisa dijadikan chart
        int meninggal = Integer.parseInt(setMeninggal);
        int positif = Integer.parseInt(setPositif);
        int sembuh = Integer.parseInt(setSembuh);

        //Pie chart
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(positif, Color.parseColor("#C7C000")).setLabel("Positif"));
        pieData.add(new SliceValue(meninggal, Color.parseColor("#FF0011")).setLabel("Meninggal"));
        pieData.add(new SliceValue(sembuh, Color.parseColor("#00BF03")).setLabel("Sembuh"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Total kasus: " + String.valueOf(meninggal + positif + sembuh)).setCenterText1FontSize(20).setCenterText1Color(Color.GRAY);
        pieChartView.setPieChartData(pieChartData);
    }
}