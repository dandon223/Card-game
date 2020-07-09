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
        int number1;
        int number2;
        int colour1;
        int colour2;
        try
        {
            number1 = Integer.parseInt(getNumber());
            number2 = Integer.parseInt(other.getNumber());
            colour1 = Integer.parseInt(getColour());
            colour2 = Integer.parseInt(other.getColour());
        }
        catch (NumberFormatException e)
        {
            number1 =0;
            number2 =0;
            colour1 =0;
            colour2 =0;
        }

        if( number1 >number2)
            return 1;
        else if(number1 <number2)
            return -1;
        if(colour1 >colour2)
            return 1;
        else if(colour1 <colour2)
            return -1;
        else
            return 0;
    }
}
