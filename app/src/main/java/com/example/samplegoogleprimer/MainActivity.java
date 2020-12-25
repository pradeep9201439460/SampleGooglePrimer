package com.example.samplegoogleprimer;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private SampleAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        doApiCall();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        loadingView = findViewById(R.id.loadingView);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        adapter = new SampleAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void doApiCall() {
        Call<String> call = apiInterface.getSampleData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SampleResponse sampleResponse = new Gson().fromJson(response.body().replaceFirst("/", ""), SampleResponse.class);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(sampleResponse.data.size());
                adapter.update(sampleResponse.data);
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}