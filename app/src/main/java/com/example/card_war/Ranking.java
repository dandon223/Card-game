package com.example.card_war;

import android.provider.BaseColumns;

public class Ranking {
    private Ranking() {}
    public static  final  class  RankingEntry implements BaseColumns{
        public static final String TABLE_NAME = "Ranking";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
