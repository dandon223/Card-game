package com.example.card_war;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> activeDeck;
    private ArrayList<Card> disabledDeck = new ArrayList<>();
    private int numberOfCardsActive;

    public Deck(ArrayList<Card> deck) {
        activeDeck = new ArrayList<>(deck);
        numberOfCardsActive = 26;
        shuffle();
    }
    public void print(){
        for(int i=0 ; i <activeDeck.size();i++){
            Log.println(Log.INFO , "1" , ""+(activeDeck.get(i).getNumber()-48)+" "+(activeDeck.get(i).getColour()-48)+" :"+i);
        }

    }
    public void shuffle(){
        Collections.shuffle(activeDeck, new Random());
    }

    public Card getLastCard(){
        Card card = new Card(activeDeck.get(0).getNumber() , activeDeck.get(0).getColour());
        return card;
    }
    public void removeCard(int i){
        activeDeck.remove(i);
    }

}
