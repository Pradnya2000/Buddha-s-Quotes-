package com.example.buddhaquotes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.buddhaquotes.Adapters.ViewPagerAdapter;
import com.example.buddhaquotes.R;
import com.example.buddhaquotes.database.DatabaseAccess;
import com.example.buddhaquotes.model.Quote;

import java.util.ArrayList;
import java.util.Queue;

public class EachQuote extends AppCompatActivity {


    RelativeLayout relativeLayout;
    ArrayList<Quote> QuoteObjectArray;
    Quote current_quote;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    int quote_position;
    DatabaseAccess db;
    ToggleButton toggleButton;
    ImageButton mcopy,mshare,mwallpaper;
    static int imagenumber=0;
    int[] ids={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_quote);
        relativeLayout=findViewById(R.id.Quotelayout);
        viewPager=findViewById(R.id.view_pager);
        mwallpaper=findViewById(R.id.wallpaper);
        mcopy=findViewById(R.id.mcopy);
        mshare=findViewById(R.id.mshare);
        toggleButton=findViewById(R.id.toggleButton);
        final Intent intent=getIntent();
        db=DatabaseAccess.getInstance(getApplicationContext());
        db.Open();
        if(intent.getBooleanExtra("favourite",false))
            QuoteObjectArray=db.getfav();
        else
            QuoteObjectArray=db.getall();


        quote_position=intent.getIntExtra("position",0);
        viewPagerAdapter=new ViewPagerAdapter(getApplicationContext(),QuoteObjectArray);//,quote_position);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(quote_position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                current_quote = QuoteObjectArray.get(position);

                if (current_quote.isFavourite()) {
                    toggleButton.setChecked(true);
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite_active));
                } else {
                    toggleButton.setChecked(false);
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite_default));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagenumber>=ids.length)
                    imagenumber=0;
                relativeLayout.setBackgroundResource(ids[imagenumber]);
                imagenumber++;

            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!toggleButton.isChecked()) {
                    toggleButton.setChecked(false);
                    current_quote.setFavourite(false);
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite_default));
                    db.removeFromFavourite(current_quote.getQuote_data());
                    Toast.makeText(getApplicationContext(), getString(R.string.refav),Toast.LENGTH_LONG).show();
                }
                else
                {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite_active));
                    db.addToFavourite(current_quote.getQuote_data());
                    current_quote.setFavourite(true);
                    toggleButton.setChecked(true);
                    Toast.makeText(getApplicationContext(), getString(R.string.addfav),Toast.LENGTH_LONG).show();
                }
            }
        });
        mcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("Copied text","\""+current_quote.getQuote_data()+"\"");
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), getString(R.string.copied),Toast.LENGTH_SHORT).show();
            }
        });
        mshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendintent=new Intent(Intent.ACTION_SEND);
                sendintent.setType("text/plain");
                sendintent.putExtra(Intent.EXTRA_SUBJECT,"Buddha Quote");
                sendintent.putExtra(Intent.EXTRA_TEXT,"\""+current_quote.getQuote_data()+"\"");
                startActivity(Intent.createChooser(sendintent,"Share Via"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.Close();
    }
}
