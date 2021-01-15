package com.md15.coronavirus;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailProvinsi extends AppCompatActivity {
    private TextView provinsi;
    private TextView positif;
    private TextView sembuh;
    private TextView meninggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Mengakses ID layout untuk textview
        provinsi = findViewById(R.id.provinsiDetails);
        positif = findViewById(R.id.positifDetails);
        sembuh = findViewById(R.id.sembuhDetails);
        meninggal = findViewById(R.id.meninggalDetails);

        //Mendapatkan data yang didapat dari activity lain melalui fungsi onClick
        String setProvinsi = getIntent().getExtras().getString("provinsi");
        String setPositif = getIntent().getExtras().getString("positif");
        String setSembuh = getIntent().getExtras().getString("sembuh");
        String setMeninggal = getIntent().getExtras().getString("meninggal");

        //Menampilkan data di android
        provinsi.setText(setProvinsi);
        positif.setText(setPositif);
        sembuh.setText(setSembuh);
        meninggal.setText(setMeninggal);
    }
}