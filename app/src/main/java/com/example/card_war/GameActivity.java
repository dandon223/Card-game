package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private long mLastClickTime = 0;
    private int numberofCardsinHand;
    private  boolean isMyCardSeen;
    ArrayList<Card> cardList1 = new ArrayList<>();
    ArrayList<Card> cardList2 = new ArrayList<>();
    MediaPlayer cardEffect;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__game);
        cardList1.clear();
        cardList2.clear();
        numberofCardsinHand = 26;
        isMyCardSeen = false;
        creatingCardDeck(cardList1);
        creatingCardDeck(cardList2);
        cardEffect =  MediaPlayer.create(this , R.raw.card_sound );
        startGame();
    }

    public void back(View view){
        cardEffect.release();
        finish();
    }
    private void creatingCardDeck(ArrayList<Card> cardList){
        for(int i = 2 ; i <15 ; i++){

            for(int j = 1 ; j<5;j++){
                cardList.add(new Card(j,i));
            }
        }

    }
    private void updateTextView(String toThis) {
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(toThis);
    }
    private void startGame(){
        Collections.shuffle(cardList1, new Random());
        Collections.shuffle(cardList2, new Random());
        updateTextView("26/52");
    }
    public void onCardClick(View view){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1100){
            return;
        }
        final ImageButton card =(ImageButton) findViewById(R.id.card);
        final ImageButton enemyCard =(ImageButton) findViewById(R.id.enemyCard);
        mLastClickTime = SystemClock.elapsedRealtime();
        cardEffect.start();

        Context c = getApplicationContext();
        final int id = c.getResources().getIdentifier("drawable/"+"as_pik", null, c.getPackageName());
        if(isMyCardSeen){
            isMyCardSeen = false;
            card.setImageResource(R.drawable.card_back);
            ObjectAnimator animation = ObjectAnimator.ofFloat(enemyCard, "translationY", 0f);
            animation.setDuration(1000);
            animation.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyCard.setImageResource(R.drawable.card_back);
                }
            },1000);
        }
        else{
            isMyCardSeen = true;
            ObjectAnimator animation = ObjectAnimator.ofFloat(enemyCard, "translationY", 400f);
            animation.setDuration(1000);
            animation.start();
            card.setImageResource(id);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyCard.setImageResource(id);
                }
            }, 1000);
        }
    }


}
