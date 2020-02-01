package com.example.card_war;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RankingDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ranking.db";
    public static final int DATABASE_VERSION = 1;
    public RankingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RANKING_TABLE ="CREATE TABLE " +
                Ranking.RankingEntry.TABLE_NAME + " (" +
                Ranking.RankingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Ranking.RankingEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                Ranking.RankingEntry.COLUMN_POINTS + " INTEGER NOT NULL, " +
                Ranking.RankingEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_RANKING_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
