package com.md15.coronavirus;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface KawalcoronaAPI {
        @GET("indonesia")
        Call<List<Indonesia>> getIndonesia();

        @GET("provinsi")
        Call<List<Provinsi>> getProvinsi();
}