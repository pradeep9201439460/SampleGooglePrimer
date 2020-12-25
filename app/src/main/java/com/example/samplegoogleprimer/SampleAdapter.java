package com.example.samplegoogleprimer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplegoogleprimer.pojo.SampleData;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private List<SampleData> sampleDataList;

    public SampleAdapter() {
        sampleDataList = new ArrayList<>();
    }

    public void update(List<SampleData> sampleDataList) {
        this.sampleDataList.addAll(sampleDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_google_primer_view, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
        holder.textView.setText(sampleDataList.get(position).text);
    }

    @Override
    public int getItemCount() {
        return sampleDataList.size();
    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public SampleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
        }
    }
}
