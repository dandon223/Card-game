package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class RankingActivity extends AppCompatActivity {
    private SQLiteDatabase mDataBase;
    private RankingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        RankingDBHelper dbHelper = new RankingDBHelper(this);
        mDataBase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RankingAdapter(this , getAllItems());
        recyclerView.setAdapter(mAdapter);
    }
    public void back(View view){
        finish();
    }
    private Cursor getAllItems(){
        return mDataBase.query(
                Ranking.RankingEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Ranking.RankingEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
