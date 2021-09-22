package com.movie.review.reviewmovie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentMovieActivity extends AppCompatActivity {

    int i;
    Dialog dialog;

    TabHost tabHost;
    Button btnEnd;
    TextView tvOne,tvTwo,tvThree,tvFour,tvFive,tvSix,tvSeven,tvEight,tvNine,tvTen,tvEleven,tvTwelve,tvThirteen,tvFourteen,tvFifteen,tvSixteen,tvSeventeen,tvEighteen,tvNineteen,tvTwenty,tvTwentyOne,tvTwentyTwo,tvTwentyThree,tvTwentyFour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentmovie);

        final TextView tvNum[] = new TextView[24];

        Integer tvId[] = {R.id.tvOne,R.id.tvTwo,R.id.tvThree,R.id.tvFour,R.id.tvFive,R.id.tvSix,R.id.tvSeven,R.id.tvEight,
                R.id.tvNine,R.id.tvTen,R.id.tvEleven,R.id.tvTwelve,R.id.tvThirteen,R.id.tvFourteen,R.id.tvFifteen,R.id.tvSixteen,
                R.id.tvSeventeen,R.id.tvEighteen,R.id.tvNineteen,R.id.tvTwenty,R.id.tvTwentyOne,R.id.tvTwentyTwo,R.id.tvTwentyThree,R.id.tvTwentyFour};

        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");

        for (int i = 0 ; i < tvId.length; i++) {

            final int index;
            index = i;

            tvNum[index] = (TextView) findViewById(tvId[index]);
            tvNum[index].setTypeface(typeface);
        }



        final ImageView btnimg[] = new ImageView[24];

        Integer imageId[] = {
                R.id.img1, R.id.img2, R.id.img3, R.id.img4,
                R.id.img5, R.id.img6, R.id.img7, R.id.img8,
                R.id.img9, R.id.img10, R.id.img11, R.id.img12,
                R.id.img13, R.id.img14, R.id.img15, R.id.img16,
                R.id.img17, R.id.img18, R.id.img19, R.id.img20,
                R.id.img21, R.id.img22, R.id.img23, R.id.img24};

        final String imgName[]={
                "극한직업","뺑반","드래곤길들이기3","범블비",
                "아쿠아맨","버닝","말모이","내안의그놈",
                "마약왕","내안의그놈","완벽한타인","버닝",
                "말모이", "극한직업","뺑반","사랑해!진영아",
                "드래곤길들이기", "그린북","마더","우리가족 라멘샵",
                "범블비", "아쿠아맨","주먹왕랄프2","헬로카봇"};


        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("a").setContent(R.id.인기영화)
                .setIndicator("인기영화"));
        tabHost.addTab(tabHost.newTabSpec("b").setContent(R.id.국내영화)
                .setIndicator("국내영화"));
        tabHost.addTab(tabHost.newTabSpec("b").setContent(R.id.해외영화)
                .setIndicator("해외영화"));


        for (int i = 0; i < imageId.length; i++) {
            final int index;
            index = i;

            btnimg[index] = (ImageView) findViewById(imageId[index]);

            btnimg[index].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(CurrentMovieActivity.this);
                    builder.setTitle("리뷰 작성");
                    builder.setMessage(imgName[index] + " 리뷰작성을 하시겠습니까?");
                    builder.setPositiveButton("리뷰목록",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(),ReviewListActivity.class);
                                    startActivity(intent);

                                }
                            });
                    builder.setNegativeButton("리뷰작성",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(getApplicationContext(),"리뷰를 작성해주세요",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),ReviewRegiActivity.class);

                                    intent.putExtra("mvNAME",index);

                                    startActivity(intent);

                                }
                            });



                    builder.show();
                }



            });
        };
    }}