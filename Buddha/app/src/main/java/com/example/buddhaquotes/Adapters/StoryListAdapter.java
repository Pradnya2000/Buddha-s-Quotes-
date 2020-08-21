package com.example.buddhaquotes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buddhaquotes.Activites.EachQuote;
import com.example.buddhaquotes.Activites.EachStory;
import com.example.buddhaquotes.R;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.MysHolder>
{
    Context context;
    ArrayList<String> storyTitleList;

    public StoryListAdapter(Context context, ArrayList<String> storyTitleList) {
        this.context = context;
        this.storyTitleList = storyTitleList;
    }

    @NonNull
    @Override
    public MysHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.single_list_item,parent,false);
        return new StoryListAdapter.MysHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MysHolder holder, int position) {
        final String temp_title=storyTitleList.get(position);
        holder.textView.setText(temp_title);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EachStory.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",temp_title);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyTitleList.size();
    }

    public class MysHolder extends RecyclerView.ViewHolder
    {
        View view;
        TextView textView;
        public MysHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=itemView.findViewById(R.id.textView);
            view=itemView;
        }
    }
}
