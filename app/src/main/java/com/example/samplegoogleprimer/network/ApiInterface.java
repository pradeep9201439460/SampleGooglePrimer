package com.example.samplegoogleprimer.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("fjaqJ")
    Call<String> getSampleData();
}
