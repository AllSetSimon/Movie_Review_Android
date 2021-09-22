package com.movie.review.reviewmovie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import db.SayingDBHelper;

public class HomeActivity extends AppCompatActivity {

    SayingDBHelper sayhelper;
    SQLiteDatabase db;
    Cursor cursor;

    TextView txtThink;

    String[][] say = {{"문제에만 초점을 맞추면 절대 문제를 풀수없어 - 패치아담스 中"},{"가치있는것을 하는데 있어서 늦었다는 것은 없어 - 벤자민 버튼의 시간은 거꾸로 간다 中"},
            {"네가 없인 아무것도 기억이 나질 않아 - 이터널선샤인"},
            {"그래, 과거는 아플수있어 하지만 너는 과거로 부터 도망칠수도 배울수도 있어 - 라이온 킹 中"},
            {"운명이랑 말이지... 노력하는 사람에겐 우연이라는 다리를 놓아주지 - 엽기적인 그녀 中"},
            {"사랑은 몰래 온 손님이라더니, 내쫓을 수도 없고.. 도대체 나이를 얼마나 먹어야 이딴 사랑 타령을 안 하게 될까요 - 스물 中"},
            {"그 때 날 좋아해줘서 고마워. 나도 그 때 널 좋아했던 내가 좋아 - 그 시절, 우리가 좋아했던 소녀 中"},
            {"잠이 드는 것처럼 그와 사랑에 빠졌다. - 안녕, 헤이즐 中"},
            {"다시 널 만나지 못할지라도, 혹은 네가 날 잊게 될지라도, 한 가지 비밀만은 말해주고 싶어. 난 널 사랑해. - 말할 수 없는 비밀 中"},
            {"사랑에 빠지면 다 미치게 돼. 사랑은, 사회적으로 용인된 미친 짓이거든. - her 中"},
            {"난 비록 죽으면 잊힐 평범한 사람일지라도 영혼을 바쳐 평생 한 여자를 사랑했으니 내 인생은 성공한 인생이다. - 노트북 中"},
            {"함장님, 논리적인 결론은 단 하나, 전진입니다. - 스탠바이, 웬디 中 "},
            {"네 거짓말에 네가 속지 않았으면 좋겠어. - 거인 中"},
            {"진실의 방으로 - 범죄도시"},
            {"처음엔 싫지만 차츰 길들여지지. 그리고 세월이 지나면 벗어날 수 없어. 그게 길들여지는 거야. - 쇼생크 탈출 中"},
            {"어른이 영원히라고 얘기할 때는 아주 오랫동안을 뜻하기도 한단다. - 찰리와 초콜릿 공장 中"},
            {"난 음악을 믿는다. 어떤 이들이 동화를 믿는 것처럼. - 어거스트 러쉬 中 "},
            {"음악은 항상 우리 곁에 있어요. 귀 기울이기만 하면 돼요. - 어거스트 러쉬 中"},
            {"행복해... 이런 느낌 처음이야.. 비로소 내 자리를 찾았어. - 이터널선샤인 中 "},
            {"이게 재판입니까 개판이지. - 부러진 화살 中"},
            {"세상에서 성공하려면 딱 두 가지만 알면 돼. 나한테 필요한 사람이 누군지, 그리고 그 사람이 뭘 필요로 하는지. - 비열한 거리 中"},
            {"인생의 비극은 왜 이렇게 행복한 순간에 찾아오는거야.. - 좋은 놈, 나쁜 놈, 이상한 놈 中"},
            {"기억하지 못한다고 제 행동이 의미 없는 건 아니에요. 눈을 감는다고 세상이 사라지는 건 아니듯. - 메멘토 中"},
            {"인류는 열정으로 가득 차 있어. 의학, 법률, 경제, 기술 같은 것들은 삶을 유지하기 위해 필요해. 그러나 시와 미, 낭만, 사랑 이들은 삶의 목적인 거야. - 죽은 시인의 사회 中"},
            {"태어날 때부터 우리 인생은 레이스라고 배웠다. 빨리 달리지 않으면 짓밟힐 것이다. - 세얼간이 中"},
            {"아버지가 말했다. 너는 공학자가 될 거야 - 세얼간이 中"},
            {"내 운명은 이렇게 정해졌다. 내가 무엇이 되고 싶은지는 아무도 묻지 않았다. - 세얼간이 中"},
            {"말은 제대로 하자. 넌 노력하지 않아. 넌 징징대는 거야. - 악마는 프라다를 입는다 中"},
            {"사람을 믿지마라. 상황을 믿어야지. - 불한당 中"},
            {"도살장처럼 변해버린 이 잔혹한 세상에도 한 줄기 희망은 존재하지. - 그랜드 부다페스트 호텔 中"},
            {"우리가 가는 곳엔 길따윈 필요 없다. - 빽 투 더 퓨쳐 中"},
            {"음악가들은 은퇴하지 않는다는 말을 들은 적이 있어요. 그들은 그들 안에 음악이 없을 때 멈춰요. 저는 제 안에 아직 음악이 있다고 장담합니다. - 인턴 中 "},
            {"세상은 넓어요. 왜 어릴 때는 정말 그렇잖아요. 자라면서 다 잊어서 그렇지 - 예스맨 中"},
            {"소중한 순간이 오면 따지지 말고 누릴 것, 우리에게 내일이 있으리란 보장은 없으니까. - 창문넘어 도망친 백세 노인 中"},
            {"내가 누구를 아끼는지는 그 사람을 잃고 나서야 알게 되지 - 다크나이트 라이즈 中"},
            {"엄마가 그랬죠. 과거를 옮길수만 있다면 뒤에 두라구요. 그게 제가 뛰는 이유의 전부인 것 같아요 - 포레스트 검프 中"},
            {"진짜 절망은 헛된 희망을 동반한다. - 다크나이트 라이즈 中"},
            {"비밀에는 그만큼에 대가가 따른단다 - 어메이징 스파이더맨"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnOne = (Button) findViewById(R.id.btn1);
        Button btnTwo = (Button) findViewById(R.id.btn2);
        Button btnFour = (Button) findViewById(R.id.btn4);
        Button btnThree = (Button) findViewById(R.id.btn3);

        txtThink = (TextView) findViewById(R.id.txtThink);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/HoonDdukbokki.ttf");
        btnOne.setTypeface(typeface);
        btnFour.setTypeface(typeface);
        txtThink.setTypeface(typeface);

        /*sayhelper = new SayingDBHelper(this);

        db=sayhelper.getWritableDatabase();

        db = sayhelper.getWritableDatabase();
        for (int i = 0; i < say.length; i++) {
            String saytb = say[i][0];

            db.execSQL("insert into moviesaying (saymain) select '" + saytb + "'where not exists (select * from moviesaying where saymain = '" + saytb + "') LIMIT 1;");
        }
        db.close();

        db=sayhelper.getReadableDatabase();
        cursor=db.rawQuery("select * from moviesaying order by random();",null);

        String say = "";

        while(cursor.moveToNext()){
            say = cursor.getString(0);
        }

        txtThink.setText(say);
        cursor.close();
        db.close();*/

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CurrentMovieActivity.class);
                startActivity(intent);
            }
        });





        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PremierActivity.class);
                startActivity(intent);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(HomeActivity.this,OneToFiftyActivity.class);
             startActivity(intent);
                                        }
                                    }
        );


    }

    @Override
    protected void onResume() {
        super.onResume();

        sayhelper = new SayingDBHelper(this);

        db=sayhelper.getWritableDatabase();

        db = sayhelper.getWritableDatabase();
        for (int i = 0; i < say.length; i++) {
            String saytb = say[i][0];

            db.execSQL("insert into moviesaying (saymain) select '" + saytb + "'where not exists (select * from moviesaying where saymain = '" + saytb + "') LIMIT 1;");
        }
        db.close();

        db=sayhelper.getReadableDatabase();
        cursor=db.rawQuery("select * from moviesaying order by random();",null);

        String say = "";

        while(cursor.moveToNext()){
            say = cursor.getString(0);
        }

        txtThink.setText(say);
        cursor.close();
        db.close();

    }
}
