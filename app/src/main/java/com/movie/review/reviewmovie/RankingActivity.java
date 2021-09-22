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

import java.util.ArrayList;

import db.RankingDBHelper;
import db.WishMovieDBHelper;

public class RankingActivity extends AppCompatActivity{

    RankingDBHelper rankinghelper;
    SQLiteDatabase db;
    Cursor cursor;

    Button btnReturn;


    ListView lstView;

    ArrayAdapter<String> adapter;
    ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankinghelper = new RankingDBHelper(this);

        lstView = (ListView) findViewById(R.id.lstView);
        btnReturn = (Button) findViewById(R.id.btnReturn);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");
        btnReturn.setTypeface(typeface);


        arr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lstView.setAdapter(adapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });




        db = rankinghelper.getReadableDatabase();
        cursor = db.rawQuery("select * from gamerank order by ranktime asc", null);

        String rkname = "" ;
        String rktime = "" ;

        while(cursor.moveToNext()) {

            rkname = cursor.getString(0) ;
            rktime = cursor.getString(1) ;


            String str = rkname + "\t\t\t\t\t\t\t" + rktime + "초";

            arr.add(str);





        }





    }





}

