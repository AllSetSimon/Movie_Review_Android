package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReviewDBHelper extends SQLiteOpenHelper {

    public ReviewDBHelper(Context context) {
        super(context, "ReviewDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table moviereview (rating REAL, movietitle varchar2(100),moviedate varchar2(100), reviewtitle varchar2(100), reviewcontents varchar2(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists moviereview");
        onCreate(db);

    }





}
