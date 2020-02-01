package com.example.card_war;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> activeDeck;
    private ArrayList<Card> disabledDeck = new ArrayList<>();
    private int numberOfCards;
    private int numberOfCardsActive;

    public Deck(ArrayList<Card> deck) {
        activeDeck = new ArrayList<>(deck);
        numberOfCards = 26;
        numberOfCardsActive = 26;
        shuffle();
    }
    public void addToNumberOfCardsActive(int i){
        numberOfCardsActive = numberOfCardsActive +i;
    }
    public int getNumberOfCardsActive() {
        return numberOfCardsActive;
    }

    public void setNumberOfCardsActive(int numberOfCardsActive) {
        this.numberOfCardsActive = numberOfCardsActive;
    }

    public void print(){
        for(int i=0 ; i <activeDeck.size();i++){
            Log.println(Log.INFO , "1" , ""+activeDeck.get(i).getNumber()+" "+activeDeck.get(i).getColour()+" :"+i);
        }

    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
    public void addToNumberOfCards(int i){
        numberOfCards = numberOfCards +i;
    }

    public void addToDisabled(Card card){
        disabledDeck.add(card);
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
    public int disabledToActive(){
        activeDeck = new ArrayList<Card>(disabledDeck);
        disabledDeck = new ArrayList<Card>();
        return activeDeck.size();
    }

    public int getPoints(){
        int points=0;
        for(int i = 0 ; i <activeDeck.size();i++)
        {
            int first;
            int second;
            try {
                first = Integer.parseInt(activeDeck.get(i).getNumber());
                second = Integer.parseInt(activeDeck.get(i).getColour());
            }
            catch (NumberFormatException e)
            {
                first = 0;
                second = 0;
            }
            points = points +first +second ;
        }
        for(int i = 0 ; i <disabledDeck.size();i++)
        {
            int first;
            int second;
            try {
                first = Integer.parseInt(disabledDeck.get(i).getNumber());
                second = Integer.parseInt(disabledDeck.get(i).getColour());
            }
            catch (NumberFormatException e)
            {
                first = 0;
                second = 0;
            }
            points = points +first +second ;
        }
        return points;
    }
}
