package com.movie.review.reviewmovie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import db.WishMovieDBHelper;

public class WishMovieActivity extends AppCompatActivity {

    WishMovieDBHelper wishmoviehelper;
    SQLiteDatabase db;
    Cursor cursor;

    Button btnINIT;


    ListView lstView;

    ArrayAdapter<String> adapter;
    ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishmovie);

        wishmoviehelper = new WishMovieDBHelper(this);

        lstView = (ListView) findViewById(R.id.lstView);
        btnINIT = (Button) findViewById(R.id.btnINIT);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");
        btnINIT.setTypeface(typeface);


        arr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lstView.setAdapter(adapter);

        btnINIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = wishmoviehelper.getWritableDatabase();
                wishmoviehelper.onUpgrade(db,1,2);
                db.close();

                db = wishmoviehelper.getReadableDatabase();
                cursor = db.rawQuery("select * from WishMovie", null);

                String movietitle = "" ;
                String primierdate = "" ;

                while(cursor.moveToNext()) {

                    movietitle = cursor.getString(0) ;
                    primierdate = cursor.getString(1) ;

                    String str = movietitle + "\t\t\t\t\t\t\t" + primierdate;

                    arr.add(str);





                }

                arr.clear();
                adapter.notifyDataSetChanged();




            }
        });




        db = wishmoviehelper.getReadableDatabase();
        cursor = db.rawQuery("select * from WishMovie", null);

        String movietitle = "" ;
        String primierdate = "" ;

        while(cursor.moveToNext()) {

            movietitle = cursor.getString(0) ;
            primierdate = cursor.getString(1) ;


            String str = movietitle + "\t\t\t\t\t\t\t" + primierdate;

            arr.add(str);





        }





    }




    }
