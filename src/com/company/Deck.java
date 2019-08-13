package com.company;

import java.util.Random;

public class Deck {

    private Card[] cards;

    public Deck() {
        cards = new Card[52];
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] names = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

        for (int i=0; i<suits.length; i++) {
            for (int j=0; j<names.length; j++) {
                cards[(13*i)+j] = new Card(suits[i], names[j], j);
            }
        }
    }
    public Card[] getCards() {
        return cards; //returns the array to be accessed outside the class
    }

    public void shuffle() {
        Random rand = new Random();
        for(int i = 0; i < this.cards.length; i++) {
            //return random index ahead of loop index that won't overlap existing swaps
            int swapIndex = rand.nextInt(i+1);

            //swap the cards
            Card tempCard = this.cards[swapIndex];
            this.cards[swapIndex] = this.cards[i];
            this.cards[i] = tempCard;
        }

    }
}
