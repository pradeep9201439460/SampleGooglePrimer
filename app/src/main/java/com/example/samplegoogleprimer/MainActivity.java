package com.example.samplegoogleprimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samplegoogleprimer.network.APIClient;
import com.example.samplegoogleprimer.network.ApiInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
    }
}