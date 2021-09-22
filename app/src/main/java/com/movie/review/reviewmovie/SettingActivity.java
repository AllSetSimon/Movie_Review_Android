package com.movie.review.reviewmovie;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button btnUpdate,btnNaverMovie,btnWishMovie,btnAbout,btnAppVersion,btnRanklist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnNaverMovie = (Button) findViewById(R.id.btnNaverMovie);
        btnWishMovie = (Button) findViewById(R.id.btnWishMovie);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAppVersion = (Button) findViewById(R.id.btnAppVersion);
        btnRanklist = (Button) findViewById(R.id.btnRanklist);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");
        btnUpdate.setTypeface(typeface);
        btnNaverMovie.setTypeface(typeface);
        btnWishMovie.setTypeface(typeface);
        btnAbout.setTypeface(typeface);
        btnAppVersion.setTypeface(typeface);
        btnRanklist.setTypeface(typeface);





        btnNaverMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://movie.naver.com/"));*/
                Intent intent = new Intent(getApplicationContext(),ReviewListActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(SettingActivity.this);
                dlg.setTitle("영화리뷰 앱, <이거어때>는?");
                dlg.setMessage("현재 상영중인 영화들의 리뷰를 작성하여 보관하고, 개봉 예정인 영화들을 위시리스트에 추가할 수 있습니다. 또한 재미난 게임을 통해 랭킹대결을 할 수 있습니다. ");

                dlg.show();

            }
        });

        btnWishMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WishMovieActivity.class);
                startActivity(intent);
            }
        });

        btnRanklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RankingActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MemberUpdateActivity.class);
                startActivity(intent);
            }
        });

       /* btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MemberUpdateActivity.class);
                startActivity(intent);
            }
        });

        btnToeicScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ToeicScoreActivity.class);
                startActivity(intent);

            }
        });

        btnAppVersion.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = new Intent(getApplicationContext(),OnetoFiftyActivity.class);
                startActivity(intent);


                return false;
            }
        });*/










    }
}
