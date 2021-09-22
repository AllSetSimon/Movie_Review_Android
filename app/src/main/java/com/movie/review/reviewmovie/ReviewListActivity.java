package com.movie.review.reviewmovie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import db.ReviewDBHelper;
import db.WishMovieDBHelper;

public class ReviewListActivity extends AppCompatActivity {

    ArrayList<MyItem> arItem;

    ReviewDBHelper reviewhelper;
    SQLiteDatabase db;
    Cursor cursor;

    Button btnHome ,btnWrite;
    View lstptn;

    TextView listmvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewlist);

        reviewhelper = new ReviewDBHelper(this);

        btnHome = (Button) findViewById(R.id.btnHOME);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        arItem = new ArrayList<MyItem>();
        MyItem mi;

        db = reviewhelper.getReadableDatabase();
        cursor = db.rawQuery("select * from moviereview", null);

        float rating ;
        String movietitle = "" ;
        String moviedate = "";

        while(cursor.moveToNext()) {

            rating = cursor.getFloat(0) ;
            movietitle = cursor.getString(1) ;
            moviedate = cursor.getString(2);

            mi = new MyItem(rating,movietitle,moviedate);
            arItem.add(mi);

        }






        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewRegiActivity.class);
                startActivity(intent);
                finish();
            }
        });



        /*mi = new MyItem(3,"아니야","2018-00-12");
        arItem.add(mi);
        mi = new MyItem(3,"아니야","2018-00-12");
        arItem.add(mi);
        mi = new MyItem(0,"아니야","2018-00-12");
        arItem.add(mi);*/
        lstptn = (View) View.inflate(getApplicationContext(),R.layout.list_ptrn,null);
        listmvName = (TextView) lstptn.findViewById(R.id.listmvName);
        MyListAdapter myListAdapter = new MyListAdapter(this, R.layout.list_ptrn, arItem);
        ListView MyList;
        MyList = (ListView) findViewById(R.id.listView1);
        MyList.setAdapter(myListAdapter);
        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                db = reviewhelper.getReadableDatabase();
                cursor = db.rawQuery("select * from moviereview where movietitle = '" + arItem.get(i).listmvName + "'", null);

                while(cursor.moveToNext()) {

                    float rating ;
                    String movietitle = "" ;
                    String moviedate = "";
                    String reviewNM = "";
                    String review = "";

                    rating = cursor.getFloat(0) ;
                    movietitle = cursor.getString(1) ;
                    moviedate = cursor.getString(2);
                    reviewNM = cursor.getString(3);
                    review = cursor.getString(4);

                    Intent intent = new Intent(getApplicationContext(),ReviewReadActivity.class);

                    intent.putExtra("rbarReview",rating);
                    intent.putExtra("tvMovie",movietitle);
                    intent.putExtra("tvDate",moviedate);
                    intent.putExtra("tvReviewNM",reviewNM);
                    intent.putExtra("tvReview",review);

                    startActivity(intent);

                    finish();

                }




            }
        });

    }
}
class MyItem{
    MyItem(float rtb, String Name, String day){
        listRtbar = rtb;
        listmvName = Name;
        listmvPremierdate = day;
    }
    float listRtbar;
    String listmvName;
    String listmvPremierdate;
}

class MyListAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc){
        maincon = context;
        Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = aarSrc;
        layout = alayout;
    }

    public int getCount() { return arSrc.size();}
    public String getItem(int position){
        return arSrc.get(position).listmvName;
    }
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView ==null){
            convertView = Inflater.inflate(layout,parent,false);
        }
        RatingBar rtb = (RatingBar) convertView.findViewById(R.id.listRtbar);
        rtb.setRating(arSrc.get(position).listRtbar);

        TextView txt = (TextView)convertView.findViewById(R.id.listmvName);
        txt.setText(arSrc.get(position).listmvName);

        TextView txt1 = (TextView)convertView.findViewById(R.id.listmvPremierdate);
        txt1.setText(arSrc.get(position).listmvPremierdate);
        return convertView;
    }
}