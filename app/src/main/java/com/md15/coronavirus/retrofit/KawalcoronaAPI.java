package com.md15.coronavirus.retrofit;

import com.md15.coronavirus.retrofit.global.Global;
import com.md15.coronavirus.retrofit.provinsi.Provinsi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//Interface yang berisi endpoint dari API kawalcorona
public interface KawalcoronaAPI {
        @GET
        Call<List<Global>> getGlobal(@Url String url);

        @GET("indonesia")
        Call<List<Indonesia>> getIndonesia();

        @GET("provinsi")
        Call<List<Provinsi>> getProvinsi();
}