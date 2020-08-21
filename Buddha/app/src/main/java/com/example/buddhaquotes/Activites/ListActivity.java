package com.example.buddhaquotes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.buddhaquotes.Adapters.MyListAdapter;
import com.example.buddhaquotes.R;
import com.example.buddhaquotes.database.DatabaseAccess;
import com.example.buddhaquotes.model.Quote;

import java.util.ArrayList;
import java.util.Queue;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> quoteList;

    DatabaseAccess db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent=getIntent();
        boolean favourite=intent.getBooleanExtra("favourite",false);
        quoteList=new ArrayList<String>();
        db=DatabaseAccess.getInstance(getApplicationContext());
        db.Open();
        if(favourite)
            quoteList=db.getFavQuotes();
        else
            quoteList=db.getQuotes();
        db.Close();


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyListAdapter(this,quoteList,favourite));

    }
}
