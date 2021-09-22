package com.movie.review.reviewmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReviewReadActivity extends AppCompatActivity {

    RatingBar rbarReview;
    TextView tvMovie, tvDate, tvReviewNM, tvReview;
    Button btnReturnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewread);

        rbarReview = (RatingBar) findViewById(R.id.rBarReview);
        tvMovie = (TextView) findViewById(R.id.tvMovie);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvReviewNM = (TextView) findViewById(R.id.tvReviewNM);
        tvReview = (TextView) findViewById(R.id.tvReview);

        btnReturnList = (Button) findViewById(R.id.btnReturnList);


        Intent intent = getIntent();

        float srbarReview = intent.getFloatExtra("rbarReview",0);
        String stvMovie = intent.getStringExtra("tvMovie");
        String stvDate = intent.getStringExtra("tvDate");
        String stvReviewNM = intent.getStringExtra("tvReviewNM");
        String stvReview = intent.getStringExtra("tvReview");


        rbarReview.setRating(srbarReview);
        tvMovie.setText(stvMovie);
        tvDate.setText(stvDate);
        tvReviewNM.setText(stvReviewNM);
        tvReview.setText(stvReview);

        btnReturnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inintent = new Intent(getApplicationContext(),ReviewListActivity.class);
                startActivity(inintent);
                finish();
            }
        });
    }
}
