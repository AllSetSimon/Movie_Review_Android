package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WishMovieDBHelper extends SQLiteOpenHelper {
    public WishMovieDBHelper(Context context) {
        super(context, "WishMovieDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table WishMovie (movietitle varchar2(20), premierdate varchar2(20));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists WishMovie");
        onCreate(db);

    }
}