package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private long mLastClickTime = 0;
    private int numberofCardsinHand;
    private boolean isCardSeen;
    ArrayList<Card> cardList1 = new ArrayList<>();
    ArrayList<Card> cardList2 = new ArrayList<>();
    MediaPlayer cardEffect;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__game);
        cardList1.clear();
        cardList2.clear();
        numberofCardsinHand = 26;
        isCardSeen = false;
        creatingCardDeck(cardList1);
        creatingCardDeck(cardList2);
        cardEffect =  MediaPlayer.create(this , R.raw.card_sound );
        startGame();
    }
    public void back(View view){
        cardEffect.release();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        cardEffect.start();
        ImageButton card =(ImageButton) findViewById(R.id.card);
        ImageButton enemyCard =(ImageButton) findViewById(R.id.enemyCard);
        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+"as_pik", null, c.getPackageName());
        if(isCardSeen){
            card.setImageResource(R.drawable.card_back);
            enemyCard.setImageResource(R.drawable.card_back);
            isCardSeen = false;
        }
        else{
            card.setImageResource(id);
            enemyCard.setImageResource(id);
            isCardSeen = true;
        }
    }


}
