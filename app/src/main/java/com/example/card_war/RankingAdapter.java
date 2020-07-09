package com.example.card_war;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public RankingAdapter(Context context , Cursor cursor){
        mContext = context;
        mCursor  = cursor;
    }
    public class RankingViewHolder extends RecyclerView.ViewHolder{

        TextView rankingName;
        TextView rankinPoints;
        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);
            rankingName = itemView.findViewById(R.id.ranking_name);
            rankinPoints = itemView.findViewById(R.id.ranking_points);
        }
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.ranking, parent, false);
        return  new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(Ranking.RankingEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(Ranking.RankingEntry.COLUMN_POINTS));
        long id = mCursor.getLong(mCursor.getColumnIndex(Ranking.RankingEntry._ID));
        holder.rankingName.setText(name);
        holder.rankinPoints.setText(String.valueOf(amount));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
