package com.example.samplegoogleprimer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samplegoogleprimer.network.APIClient;
import com.example.samplegoogleprimer.network.ApiInterface;
import com.example.samplegoogleprimer.pojo.SampleResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getSampleData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SampleResponse sampleResponse = new Gson().fromJson(response.body().replaceFirst("/", ""), SampleResponse.class);
                Log.d(TAG, sampleResponse.data.get(0).text);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}