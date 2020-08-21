package com.example.buddhaquotes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.buddhaquotes.model.Quote;
import com.example.buddhaquotes.model.Story;

import java.util.ArrayList;

public class DatabaseAccess {
    private DatabaseHelper databaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess databaseAccess;

    public DatabaseAccess(Context context){
        this.databaseOpenHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context)
    {
        if (databaseAccess == null)
        {
            databaseAccess = new DatabaseAccess(context);
        }
        return databaseAccess;
    }
    public void Open()
    {
        this.sqLiteDatabase = databaseOpenHelper.getWritableDatabase();
    }

    public void Close()
    {
        if (sqLiteDatabase != null)
        {
            this.sqLiteDatabase.close();
        }
    }
    public ArrayList<String> getQuotes()
    {
        ArrayList<String> quote_list = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT quotedata from quotes", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String title = cursor.getString(0);
            quote_list.add(title);
            cursor.moveToNext();
        }
        cursor.close();
        return quote_list;
    }
    public ArrayList<String> getFavQuotes()
    {
        ArrayList<String> quote_list=new ArrayList<String>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT quotedata from Quotes where favourite=1",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            String title=cursor.getString(0);
            quote_list.add(title);
            cursor.moveToNext();
        }
        cursor.close();
        return quote_list;
    }
    public ArrayList<Quote> getall()
    {
        ArrayList<Quote> quote_list=new ArrayList<Quote>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT quotedata,favourite from Quotes ",null);
        cursor.moveToNext();
        while (!cursor.isAfterLast())
        {
            String quote_data=cursor.getString(0);
            boolean fav=false;
            if(cursor.getInt(1)==1)
                fav=true;
            quote_list.add(new Quote(quote_data,fav));
            cursor.moveToNext();
        }
        cursor.close();
        return quote_list;
    }
    public ArrayList<Quote> getfav()
    {
        ArrayList<Quote> quote_list=new ArrayList<Quote>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT quotedata,favourite from Quotes where favourite=1 ",null);
        cursor.moveToNext();
        while (!cursor.isAfterLast())
        {
            String quote_data=cursor.getString(0);
            boolean fav=false;
            if(cursor.getInt(1)==1)
                fav=true;
            quote_list.add(new Quote(quote_data,fav));
            cursor.moveToNext();
        }
        cursor.close();
        return quote_list;
    }

    public void addToFavourite(String quote)
    {

        String query="UPDATE Quotes SET favourite=1 WHERE quotedata=\""+quote+"\"";
        sqLiteDatabase.execSQL(query);
    }
    public void removeFromFavourite(String quote)
    {

        String query="UPDATE Quotes SET favourite=0 WHERE quotedata=\""+quote+"\"";
        sqLiteDatabase.execSQL(query);
    }
    public ArrayList<String> getStoryTitle()
    {
        ArrayList<String> title_list=new ArrayList<String>();
        Cursor cursor=sqLiteDatabase.rawQuery("select title from Story",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            String title=cursor.getString(0);
            title_list.add(title);
            cursor.moveToNext();
        }
        cursor.close();
        return title_list;
    }
    public Story getStory(String title)
    {
        Story temp=null;
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT storydata from Story where title="+"\""+title+"\"",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            String data=cursor.getString(0);
            temp=new Story(title,data);
            cursor.moveToNext();
        }
        cursor.close();
        return temp;
    }
}
