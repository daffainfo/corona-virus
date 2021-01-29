package com.md15.coronavirus.retrofit;

import com.md15.coronavirus.retrofit.provinsi.Provinsi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Interface yang berisi endpoint dari API kawalcorona
public interface KawalcoronaAPI {
        @GET("indonesia")
        Call<List<Indonesia>> getIndonesia();

        @GET("provinsi")
        Call<List<Provinsi>> getProvinsi();
}