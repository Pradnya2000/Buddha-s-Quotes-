package com.example.buddhaquotes.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Quote  {
   // private int sr_no;
    private String quote_data;
    private static int id=0;

    /*public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }*/


    public String getQuote_data() {
        return quote_data;
    }

    public void setQuote_data(String quote_data) {
        this.quote_data = quote_data;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    private boolean favourite;

    public Quote(String quote_data, boolean favourite) {
       // this.sr_no = ++id;
        this.quote_data = quote_data;
        this.favourite = favourite;
    }

}
