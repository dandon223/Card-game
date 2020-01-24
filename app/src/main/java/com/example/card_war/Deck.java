package com.example.card_war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> activeDeck = new ArrayList<>();
    private ArrayList<Card> disabledDeck = new ArrayList<>();

    public Deck() {
        creatingCardDeck(activeDeck);

    }

    private void creatingCardDeck(ArrayList<Card> cardList){
        for(int i = 2 ; i <15 ; i++){

            for(int j = 1 ; j<5;j++){
                cardList.add(new Card(j,i));
            }
        }

    }
    public void shuffle(){
        Collections.shuffle(activeDeck, new Random());
    }

}
