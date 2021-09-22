package com.movie.review.reviewmovie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import db.WishMovieDBHelper;

public class PremierActivity extends AppCompatActivity {

    WishMovieDBHelper wishmoviehelper;

    SQLiteDatabase db, db1;
    TextView tvTitle, tvPremierDate, tvPeople, tvContents;
    Button btnTrailer, btnChim, btnWishMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPremierDate = (TextView) findViewById(R.id.tvPremierDate);
        tvPeople = (TextView) findViewById(R.id.tvPeople);
        tvContents = (TextView) findViewById(R.id.tvContents);
        btnTrailer = (Button) findViewById(R.id.btnTrailer);
        btnChim = (Button) findViewById(R.id.btnChim);
        btnWishMovie = (Button) findViewById(R.id.btnWishMovie);

        wishmoviehelper = new WishMovieDBHelper(this);






        Gallery gallery = (Gallery) findViewById(R.id.gryMovie);

        PremierActivity.MyGalleryAdapter galAdapter = new PremierActivity.MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");
        tvTitle.setTypeface(typeface);
        tvPremierDate.setTypeface(typeface);
        tvPeople.setTypeface(typeface);
        tvContents.setTypeface(typeface);
        btnTrailer.setTypeface(typeface);
        btnChim.setTypeface(typeface);
        btnWishMovie.setTypeface(typeface);

    }

    public class MyGalleryAdapter extends BaseAdapter {
        RatingBar rtBar,dialrtBar;
        TextView tvRatingResult;
        TextView txtTitle, txtPremierDate, txtPeople, txtContents;
        Button btnChim;
        View dialrating;




        Context context;
        //Integer[] posterID = {R.drawable.aladin,R.drawable.alita,R.drawable.avengers,R.drawable.captain,R.drawable.cold,R.drawable.detective,R.drawable.dumbo,R.drawable.happy,R.drawable.kiss,R.drawable.lego,R.drawable.lionking,R.drawable.maninblack,R.drawable.merry, R.drawable.pappi,R.drawable.sabaha,R.drawable.spiderman,R.drawable.toystory};
        Integer[] posterID = {R.drawable.alita,R.drawable.aladin,R.drawable.avengers,R.drawable.captainmarvel,R.drawable.coldchasing,R.drawable.conan,R.drawable.dumbo,R.drawable.happy,R.drawable.kiss,R.drawable.legomovie,R.drawable.lionking,R.drawable.merry};
        String[] mvName = {"알리타:배틀엔젤","알라딘","어벤져스:엔드게임","캡틴마블","콜드체이싱","명탐정코난 : 전율의 악보","덤보(2019)","해피데스데이투유","장난스런 키스","레고 더무비","라이온킹","메리포핀스리턴스"};
        /*String[] mvPremierDate = {"2019-02-05","2019-05","2019-04","2019-05","2019-05","2019-05","2019-05","2019-05","2019-05","2019-05","2019-05","2019-05"};*/
        String[] mvTrailer = {"https://youtu.be/yTE7vwwcC7s","https://youtu.be/XN_T_nKCtm0","https://youtu.be/TaCE5sAigGQ","https://youtu.be/Z1BCujX3pw8","https://youtu.be/0phuNQQ_gHI","https://youtu.be/8kzIyFRV4i8","https://youtu.be/7NiYVoqBt-8","https://youtu.be/THq6KlWgiqw","https://youtu.be/FN6Ae4hKFHY","https://youtu.be/cksYkEzUa7k","https://youtu.be/O9EvBdzHvqI","https://youtu.be/-3jsfXDZLIY"};

        String[] mvPremierDate = {"2019-02-05","2019-05","2019-04","2019-03-07",
                "2019-02-20","2019-02-14","2019-03","2019-02-14","2019-03","2019","2019-07","2019-02-14"};
        String[] mvActor = {"로사 살라자르, 크리스토프 왈츠, 키언 존슨...","메나 마수드, 윌 스미스, 나오미 스콧...","로버트 다우니 주니어, 조슈 브롤린, 크리스 에반스...",
                "브리 라슨, 사무엘L.잭슨, 벤 멘델슨 ...","리암 니슨, 에미 로섬, 로라 던...","타카야마 미나미, 야마자키 와카나, 쿠와시마 호우코..."
                ,"에바 그린, 마이클 키튼, 콜린 파렐...","제시카 로테, 이스라엘 브로우사드, 루비 모딘...","왕대륙, 임윤, 프랭키 첸...","크리스 프렛, 엘리자베스 뱅크, 티파니 헤디쉬...",
                "도날드 글로버, 비욘세, 제임스 얼 존스","에밀리 블런트, 메릴 스트립, 콜린 퍼스..."};
        String[] mvContents = {"인간의 두뇌를 가진 기계 소녀그녀는 인간인가 기계인가 진짜 나를 깨워라 모두가 갈망하는 공중도시와 그들을 위해 존재하는 고철도시로 나누어진 26세기. 새로운 세상을 위해 통제된 세상의 무시무시한 적들과 맞서게 되는데…",
                "램프 요정 지니의 이야기","어벤져스의 마지막 이야기","공군 파일럿 캐럴 댄버스(브리 라슨)가 쉴드 요원 닉 퓨리(사무엘 L. 잭슨)를 만나 MCU 사상 가장 강력한 히어로 캡틴 마블로 거듭나는 이야기",
                "넬스 콕스맨, 올해의 모범 시민이지 마약 딜러와 마피아 집단이 꾸며낸 처참한 살인 사건. 아들이 살해된 그 잔인한 현장은 넬스를 분노의 심판자로 다시 태어나게 만든다. 복수에도 모범이 필요한 법","음악가만을 노린 연쇄살인사건이 발생하고, 콘서트 직전, 코난은 누군가의 습격을 받아 쓰러지고 음악홀은 연이은 폭발로 불바다가 된다. 하지만, 란 일행을 포함한 천 명의 관객들은 그 사실을 알지 못한 채 콘서트가 계속되는데…과연, 코난은 위기에 빠진 란을 구할 수 있을 것인가",
                "아기 코끼리 덤보의 스펙타클 이야기","제시카 로테, 이스라엘 브로우사드, 루비 모딘... 죽을 때까지 놀아준다고 했잖아 절대 끝나지 않는 생일에 또다시 갇혀버린 트리와 더 강력하게 돌아온 베이비의 끝내주는 호러테이닝 무비","뜻밖의 키스에 가슴이 두근거렸다 널 좋아하는 건, 내가 가장 용기 낸 일이야","Lego the movie 1을 이은 Lego Construction Toys의 야심작","사자들이 지배하는 사바나에서, 아버지인 킹 무파사를 이어 왕이 될 사자 심바와 동료들의 운명과 모험을 다룬 영화","행복한 상상을 이루어주는 해피메이커 메리 포핀스 체리트리 가 17번지에 살고 있는 마이클과 세 아이들은 아내와 엄마를 잃고, 집까지 빼앗길 위기에 처해 슬픔에 잠긴다. 어느 날, 바람의 방향이 바뀌고 마이클의 가족에게 다시 돌아온 메리 포핀스는 사랑스러운 마법으로 가득 찬 황홀한 경험을 선사하는데… "};




        Float[] ratingResult = new Float[posterID.length];


        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(250,400));
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            imageview.setPadding(5,5,5,5);
            imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.imgMovie);
                    txtTitle = (TextView) findViewById(R.id.txtTitle);
                    txtPremierDate = (TextView) findViewById(R.id.txtPremierDate);
                    rtBar = (RatingBar) findViewById(R.id.rtBar);
                    btnChim = (Button) findViewById(R.id.btnChim);
                    txtPeople = (TextView) findViewById(R.id.txtPeople);
                    txtContents = (TextView) findViewById(R.id.txtContents);

                    tvRatingResult = (TextView) findViewById(R.id.tvRatingResult);


                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);

                    txtTitle.setText(mvName[pos]);
                    txtPremierDate.setText(mvPremierDate[pos]);
                    txtPeople.setText(mvActor[pos]);
                    txtContents.setText(mvContents[pos]);
                    tvRatingResult.setText(ratingResult[pos] + " / 5.0");

                    if(ratingResult[pos] == null){
                        rtBar.setRating(0.0f);
                        tvRatingResult.setText("0.0 / 5.0");
                    } else {
                        rtBar.setRating(ratingResult[pos].floatValue());
                    }


                    rtBar.setIsIndicator(true);

                    btnChim.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialrating = (View) View.inflate(PremierActivity.this,R.layout.dial_rating,null);
                            dialrtBar = (RatingBar) dialrating.findViewById(R.id.dialrtBar);

                            AlertDialog.Builder dlg = new AlertDialog.Builder(PremierActivity.this);
                            dlg.setTitle("기대별점 주기");
                            dlg.setView(dialrating);

                            dlg.setPositiveButton("기대되요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    ratingResult[pos] = dialrtBar.getRating();
                                    rtBar.setRating(ratingResult[pos]);
                                    tvRatingResult.setText(ratingResult[pos] + " / 5.0");

                                }
                            });

                            dlg.show();



                           /* */
                        }
                    });

                    btnTrailer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri uri = Uri.parse(mvTrailer[pos]);
                            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                            startActivity(intent);
                        }
                    });

                    btnWishMovie.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            db1 = wishmoviehelper.getWritableDatabase();

                            db1.execSQL("insert into WishMovie (movietitle, premierdate) select '" + mvName[pos].toString() + "', '" + mvPremierDate[pos].toString() + "' where not exists (select * from WishMovie where movietitle = '" + mvName[pos].toString() + "') LIMIT 1;");

                            Toast.makeText(getApplicationContext(),"등록 성공",Toast.LENGTH_SHORT).show();


                            /*db1.execSQL("insert into wordBook values ('" + words + "', '" + mean1 + "', '" + mean2 + "');");*/

                            db1.close();
                        }
                    });







                   /*rtBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            ratingResult[pos] = ratingBar.getRating();
                            tvRatingResult.setText(ratingResult[pos] + " / 5.0");
                        }
                    });*/



                    return false;
                }
            });



            return imageview;

        }


    }


}
