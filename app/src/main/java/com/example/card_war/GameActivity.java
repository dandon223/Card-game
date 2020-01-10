package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private int numberofCardsinHand;
    ArrayList<Card> cardList1 = new ArrayList<>();
    ArrayList<Card> cardList2 = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__game);
        cardList1.clear();
        cardList2.clear();
        numberofCardsinHand = 26;
        creatingCardDeck(cardList1);
        creatingCardDeck(cardList2);
        startGame();
    }
    public void back(View view){
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
    private void startGame(){
        Collections.shuffle(cardList1, new Random());
        Collections.shuffle(cardList2, new Random());
    }


}
