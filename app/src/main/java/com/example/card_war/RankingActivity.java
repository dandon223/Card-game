package com.example.card_war;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
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
                Ranking.RankingEntry.COLUMN_POINTS + " DESC"
        );
    }
    private void removeItem(long id){
        mDataBase.delete(Ranking.RankingEntry.TABLE_NAME , Ranking.RankingEntry._ID +"="+id,null);
        mAdapter.swapCursor(getAllItems());
    }
}
