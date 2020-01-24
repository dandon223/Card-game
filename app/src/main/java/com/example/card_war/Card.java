package com.example.card_war;

public class Card implements  Comparable{
    private int colour;
    private int number;
    private boolean used;

    public Card(int colour, int number) {
        this.colour = colour;
        this.number = number;
        this.used = false;
    }

    private int getColour() {
        return colour;
    }

    private int getNumber() {
        return number;
    }

    private boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public int compareTo(Object o) {
        Card other = (Card) o;
        if(this.number > other.getNumber())
            return 1;
        else if(this.number < other.getNumber())
            return -1;
        else
            if(this.colour > other.getColour())
                return 1;
            else if(this.colour < other.getColour())
                return -1;
            else
                return 0;
    }
}
