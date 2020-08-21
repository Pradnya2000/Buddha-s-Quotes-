package com.example.buddhaquotes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.buddhaquotes.Adapters.StoryListAdapter;
import com.example.buddhaquotes.R;
import com.example.buddhaquotes.database.DatabaseAccess;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> storyTitles;
    DatabaseAccess db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        recyclerView=findViewById(R.id.recyclerView);
        db=DatabaseAccess.getInstance(getApplicationContext());
        db.Open();
        storyTitles=db.getStoryTitle();
        db.Close();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StoryListAdapter storyListAdapter=new StoryListAdapter(getApplicationContext(),storyTitles);
        recyclerView.setAdapter(storyListAdapter);

    }
}
