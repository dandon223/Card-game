package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
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

public class GameActivity extends AppCompatActivity implements FinishDialog.FinishDialogListener {
    private long mLastClickTime = 0;
    private  boolean isMyCardSeen;
    private Deck myDeck;
    private Deck enemyDeck;
    private int numberOfClicks;
    private int points;
    MediaPlayer cardEffect;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__game);
        restart();
        cardEffect =  MediaPlayer.create(this , R.raw.card_sound );
    }
    public void restart(){
        ArrayList<String> deck = new ArrayList<>();
        deck =randomCards(deck);
        ArrayList<Card> myCards = new ArrayList<>();
        ArrayList<Card> enemyCards = new ArrayList<>();
        numberOfClicks = 0;
        points = 10;
        int even = 0;
        for(int i = 0 ; i <deck.size(); i++){
            even++;
            String name = deck.get(i);
            if(name.charAt(0)=='1'){
                Card card = new Card(String.valueOf(name.charAt(0))+String.valueOf(name.charAt(1)) , String.valueOf(name.charAt(2)));
                if(even%2 ==0)
                    myCards.add(card);
                else
                    enemyCards.add(card);
            }
            else{
                Card card = new Card(String.valueOf(name.charAt(0)) , String.valueOf(name.charAt(1)));
                if(even%2 ==0)
                    myCards.add(card);
                else
                    enemyCards.add(card);
            }
        }
        myDeck = new Deck(myCards);
        enemyDeck = new Deck(enemyCards);
        isMyCardSeen = false;
        myDeck.shuffle();
        enemyDeck.shuffle();
        countPoints();
        updateTextView("26/52 \nPoints: " +getPoints());
        myDeck.print();
    }

    public void back(View view){
        cardEffect.release();
        finish();
    }
    public int getPoints(){
        return points;
    }
    private void updateTextView(String toThis) {
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(toThis);
    }
    private ArrayList<String> randomCards(ArrayList<String> deck){
        for(int i = 2 ; i <15 ; i++){

            for(int j = 1 ; j<5;j++){
                deck.add(String.valueOf(i)+String.valueOf(j));
            }
        }
        Collections.shuffle(deck, new Random());
        return new ArrayList<String>(deck);
    }
    public void onCardClick(View view){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 800){
            return;
        }
        final ImageButton card =(ImageButton) findViewById(R.id.card);
        final ImageButton enemyCard =(ImageButton) findViewById(R.id.enemyCard);
        mLastClickTime = SystemClock.elapsedRealtime();
        cardEffect.start();
        Context c = getApplicationContext();
        if(isMyCardSeen){
            isMyCardSeen = false;
            card.setImageResource(R.drawable.purple_back);
            ObjectAnimator animation = ObjectAnimator.ofFloat(enemyCard, "translationY", 0f);
            animation.setDuration(700);
            animation.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyCard.setImageResource(R.drawable.purple_back);
                }
            },700);
        }
        else{
            numberOfClicks++;
            isMyCardSeen = true;
            Card myCardDrawn = myDeck.getLastCard();
            Card enemyCardDrawn = enemyDeck.getLastCard();
            String myCardName = "c"+ myCardDrawn.getNumber() +myCardDrawn.getColour();
            String enemyCardName = "c"+ enemyCardDrawn.getNumber() +enemyCardDrawn.getColour();
            Log.println(Log.DEBUG , "y" , myCardName +" "+enemyCardName);
            final int id = c.getResources().getIdentifier("drawable/"+myCardName, null, c.getPackageName());
            final int id2 = c.getResources().getIdentifier("drawable/"+enemyCardName, null, c.getPackageName());
            ObjectAnimator animation = ObjectAnimator.ofFloat(enemyCard, "translationY", 400f);
            animation.setDuration(700);
            animation.start();
            card.setImageResource(id);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyCard.setImageResource(id2);
                }
            }, 700);
            enemyDeck.addToNumberOfCardsActive(-1);
            myDeck.addToNumberOfCardsActive(-1);
            if(myCardDrawn.compareTo(enemyCardDrawn) > 0){
                myDeck.removeCard(0);
                myDeck.addToDisabled(myCardDrawn);
                myDeck.addToDisabled(enemyCardDrawn);
                enemyDeck.removeCard(0);
                myDeck.addToNumberOfCards(1);
                enemyDeck.addToNumberOfCards(-1);
            }
            else{
                myDeck.removeCard(0);
                enemyDeck.removeCard(0);
                enemyDeck.addToDisabled(myCardDrawn);
                enemyDeck.addToDisabled(enemyCardDrawn);
                myDeck.addToNumberOfCards(-1);
                enemyDeck.addToNumberOfCards(1);
            }
            if(myDeck.getNumberOfCardsActive()==0){
                int numberOfCards = myDeck.disabledToActive();
                myDeck.setNumberOfCardsActive(numberOfCards);
                myDeck.shuffle();
            }
            if(enemyDeck.getNumberOfCardsActive()==0){
                int numberOfCards = enemyDeck.disabledToActive();
                enemyDeck.setNumberOfCardsActive(numberOfCards);
                enemyDeck.shuffle();
            }
            if(numberOfClicks>=2){
                openDialog();
            }
            countPoints();
            updateTextView(myDeck.getNumberOfCards()+"/52 \nPoints:"+ getPoints());
            Log.println(Log.INFO , "1" , myDeck.getNumberOfCardsActive()+" "+myDeck.getNumberOfCards() +" "+ myCardDrawn.compareTo(enemyCardDrawn) );
        }
    }
    public void openDialog(){
        FinishDialog finishDialog = new FinishDialog();
        countPoints();
        finishDialog.setPoints(points);
        finishDialog.show(getSupportFragmentManager(), "finish dialog");
    }

    @Override
    public void applyTexts(String username) {

    }
    public void countPoints(){
        points = myDeck.getPoints();
    }
}
