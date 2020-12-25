package com.example.samplegoogleprimer.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SampleResponse {
    @SerializedName("data")
    @Expose
    public List<SampleData> data = null;

    public List<SampleData> getData() {
        return data;
    }

    public void setData(List<SampleData> data) {
        this.data = data;
    }
}
