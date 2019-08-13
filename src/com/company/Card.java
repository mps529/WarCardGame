package com.company;

public class Card {

    private String suit;
    private String name;
    private int value;

    //constructor
    public Card(String suit, String name, int value) {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name + " of " + this.suit;
    }



}
