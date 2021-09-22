package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RankingDBHelper extends SQLiteOpenHelper {

    public RankingDBHelper(Context context) {
        super(context, "RankingsDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table gamerank (rankname varchar2(50), ranktime INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists gamerank");
        onCreate(db);

    }




}
