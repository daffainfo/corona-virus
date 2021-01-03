package com.md15.coronavirus;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabLayout extends TabActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        TabHost th = getTabHost();
        TabHost.TabSpec ts;
        Intent i;

        i = new Intent().setClass(this, MainIndonesia.class);
        ts = th.newTabSpec("Indonesia").setIndicator("Indonesia", null).setContent(i);
        th.addTab(ts);

        i = new Intent().setClass(this, MainProvinsi.class);
        ts = th.newTabSpec("Provinsi").setIndicator("Provinsi", null).setContent(i);
        th.addTab(ts);

    }
}