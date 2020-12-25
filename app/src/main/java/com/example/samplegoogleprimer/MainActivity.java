package com.example.samplegoogleprimer;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
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
    private ProgressBar loadingView;
    private LinearLayoutManager linearlayoutmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        doApiCall();
        paggination();

    }

    private void paggination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                linearlayoutmanager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearlayoutmanager.getItemCount();
                int lastVisibleItemPosition = linearlayoutmanager.findLastVisibleItemPosition();
                if (dy > 3 && lastVisibleItemPosition == totalItemCount - 1 ) {
                    loadingView.setVisibility(View.VISIBLE);
                    doApiCall();
                }

            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        loadingView = findViewById(R.id.loadingView);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        adapter = new SampleAdapter();
        linearlayoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearlayoutmanager);
        recyclerView.setAdapter(adapter);
    }

    private void doApiCall() {
        Call<String> call = apiInterface.getSampleData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SampleResponse sampleResponse = new Gson().fromJson(response.body().replaceFirst("/", ""), SampleResponse.class);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.update(sampleResponse.data);
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}