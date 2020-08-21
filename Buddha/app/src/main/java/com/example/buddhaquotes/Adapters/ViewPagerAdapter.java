package com.example.buddhaquotes.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.buddhaquotes.R;
import com.example.buddhaquotes.database.DatabaseAccess;
import com.example.buddhaquotes.model.Quote;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quote> QuoteObjectArray;
   // int Quote_position;
    TextView textView;
    public ViewPagerAdapter(Context context, ArrayList<Quote> QuoteObjectArray){//, int quote_position) {
        this.context = context;

        this.QuoteObjectArray=QuoteObjectArray;

    }

    @Override
    public int getCount() {
        return QuoteObjectArray.size();//quotes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
       // return super.instantiateItem(container, position);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.single_quote,container,false);
        container.addView(itemView);
        textView=itemView.findViewById(R.id.textView);
        textView.setText("\""+QuoteObjectArray.get(position).getQuote_data()+"\"");
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
