package com.example.buddhaquotes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buddhaquotes.R;
import com.example.buddhaquotes.database.DatabaseAccess;
import com.example.buddhaquotes.model.Story;

import java.util.ArrayList;

public class EachStory extends AppCompatActivity {

    TextView title_view;
    TextView story_view;
    DatabaseAccess db;
    Story mstory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_story);
        title_view=findViewById(R.id.title);
        story_view=findViewById(R.id.story_data);
        Intent intent=getIntent();
        String temp_title=intent.getStringExtra("title");
        db=DatabaseAccess.getInstance(getApplicationContext());
        db.Open();
        mstory=db.getStory(temp_title);
        db.Close();
        title_view.setText(mstory.getTitle());
        story_view.setText(mstory.getStoryData());

    }
}
