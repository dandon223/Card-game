package com.example.card_war;

public class Card implements  Comparable{
    private String colour;
    private String number;
    private boolean used;

    public Card(String number, String colour) {
        this.colour = colour;
        this.number = number;
        this.used = false;
    }

    public String getColour() {
        return colour;
    }

    public String getNumber() {
        return number;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public int compareTo(Object o) {
        Card other = (Card) o;
        if(this.number.compareTo(other.getNumber())>0 )
            return 1;
        else if(this.number.compareTo(other.getNumber())<0)
            return -1;
        else
            if(this.colour.compareTo(other.getColour()) > 0)
                return 1;
            else if(this.colour.compareTo(other.getColour()) <0)
                return -1;
            else
                return 0;
    }
}
