package com.example.samplegoogleprimer.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleData {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("text")
    @Expose
    public String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
