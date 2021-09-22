package com.movie.review.reviewmovie;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import db.ReviewDBHelper;
import db.WishMovieDBHelper;

public class ReviewRegiActivity extends AppCompatActivity {
    ReviewDBHelper reviewhelper;

    SQLiteDatabase db;

    RatingBar rbarReview;
    Spinner spnmovie;
    Button btnSubject, btnInitial;
    EditText btnDate;
    EditText edtReviewNM, edtReview;


    int mvNAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewregi);

        Intent inintent = getIntent();

        mvNAME = inintent.getIntExtra("mvNAME",0);

        reviewhelper = new ReviewDBHelper(this);

        rbarReview = (RatingBar) findViewById(R.id.rBarReview);
        spnmovie = (Spinner) findViewById(R.id.spnMovie);
        btnDate = (EditText) findViewById(R.id.btnDate);
        btnInitial = (Button) findViewById(R.id.btnInitial);
        edtReviewNM = (EditText) findViewById(R.id.edtReviewNM);
        edtReview = (EditText) findViewById(R.id.edtReview);

        btnSubject = (Button) findViewById(R.id.btnSubject);
        btnInitial = (Button) findViewById(R.id.btnInitial);



        btnInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbarReview.setRating(0.0f);
                btnDate.setText("");
                edtReviewNM.setText("");
                edtReview.setText("");

                Toast.makeText(getApplicationContext(),"초기화 성공",Toast.LENGTH_SHORT).show();
            }
        });

        final String movie[]={
                "극한직업","뺑반","드래곤길들이기3","범블비",
                "아쿠아맨","버닝","말모이","내안의그놈",
                "마약왕","내안의그놈","완벽한타인","버닝",
                "말모이", "극한직업","뺑반","사랑해!진영아",
                "드래곤길들이기", "그린북","마더","우리가족 라멘샵",
                "범블비", "아쿠아맨","주먹왕랄프2","헬로카봇"};


        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, movie);
        spnmovie.setAdapter(adapter);


        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Float rbarRV = rbarReview.getRating();
                String spnmovietitle = (String)spnmovie.getSelectedItem().toString();

                db = reviewhelper.getWritableDatabase();

                db.execSQL("insert into moviereview (rating, movietitle, moviedate, reviewtitle, reviewcontents) select " + rbarRV + ", '" + spnmovietitle + "', '" + btnDate.getText().toString() + "', '" + edtReviewNM.getText().toString()+ "', '" + edtReview.getText().toString() + "'"+" where not exists (select * from moviereview where movietitle = '" + spnmovietitle + "') LIMIT 1;");

                Toast.makeText(getApplicationContext(),"리뷰 등록 성공",Toast.LENGTH_SHORT).show();


                /*db1.execSQL("insert into wordBook values ('" + words + "', '" + mean1 + "', '" + mean2 + "');");*/

                db.close();

                Intent intent = new Intent(ReviewRegiActivity.this, ReviewListActivity.class);
                startActivity(intent);


            }
        });

        if(mvNAME != 0) {
            spnmovie.setSelection(mvNAME);
        }




    }
}
