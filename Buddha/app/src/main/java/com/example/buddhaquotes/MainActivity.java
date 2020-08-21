package com.example.buddhaquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buddhaquotes.Activites.ListActivity;
import com.example.buddhaquotes.Activites.StoryActivity;

public class MainActivity extends AppCompatActivity {

    Button all,favquote,story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("favourite",false);
                startActivity(intent);
            }
        });
        favquote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("favourite",true);
                startActivity(intent);
            }
        });
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), StoryActivity.class);
                intent.putExtra("Story",true);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        all=findViewById(R.id.allquotes);
        favquote=findViewById(R.id.favorite);
        story=findViewById(R.id.stories);
    }
}
