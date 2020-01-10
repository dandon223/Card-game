package com.example.card_war;

public class Card {
    private int colour;
    private int number;
    private boolean used;

    public Card(int colour, int number) {
        this.colour = colour;
        this.number = number;
        this.used = false;
    }

    public int getColour() {
        return colour;
    }

    public int getNumber() {
        return number;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
