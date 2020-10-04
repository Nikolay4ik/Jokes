package com.example.jokes.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jokes.Pojo.Value;
import com.example.jokes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class AdapterJokes extends RecyclerView.Adapter<AdapterJokes.ValueViewHolder> {
   private List<Value> values;

    public void setValues(List<Value> values) {
        this.values = values;
        notifyDataSetChanged();
    }



    @androidx.annotation.NonNull
    @Override
    public ValueViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jokes_inform, viewGroup, false);
        return new ValueViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ValueViewHolder valueViewHolder, int i) {
Value value=values.get(i);
        valueViewHolder.textViewJok.setText(value.getJoke());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class ValueViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJok;
        public ValueViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            textViewJok = itemView.findViewById(R.id.textView_jok);

        }
    }
}
