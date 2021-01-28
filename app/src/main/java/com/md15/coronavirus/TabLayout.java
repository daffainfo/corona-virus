package com.md15.coronavirus;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabLayout extends TabActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        Intent intent;

        //Menambahkan tab baru untuk menampilkan activity indonesia
        intent = new Intent().setClass(this, MainIndonesia.class);
        tabSpec = tabHost.newTabSpec("Indonesia").setIndicator("Indonesia", null).setContent(intent);
        tabHost.addTab(tabSpec);

        //Menambahkan tab baru untuk menampilkan activity provinsi
        intent = new Intent().setClass(this, MainProvinsi.class);
        tabSpec = tabHost.newTabSpec("Provinsi").setIndicator("Provinsi", null).setContent(intent);
        tabHost.addTab(tabSpec);
    }
}