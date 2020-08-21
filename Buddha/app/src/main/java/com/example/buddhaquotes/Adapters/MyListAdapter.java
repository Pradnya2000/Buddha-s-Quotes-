package com.example.buddhaquotes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buddhaquotes.Activites.EachQuote;
import com.example.buddhaquotes.R;
import com.example.buddhaquotes.model.Quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.zip.Inflater;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyHolder>{
    Context context;
    ArrayList<String> quote;
    boolean favourite;

    public MyListAdapter(Context context, ArrayList<String> quote,boolean favourite) {
        this.context = context;
        this.quote = quote;
        this.favourite=favourite;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.single_list_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final String temp_quote=quote.get(position);
        holder.textView.setText(temp_quote);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EachQuote.class);
                       //    intent.putExtra("Quote",temp_quote);
                intent.putExtra("AllQuotes",quote);
                intent.putExtra("favourite",favourite);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return quote.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        View view;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=itemView.findViewById(R.id.textView);
            view=itemView;
        }
    }

}
