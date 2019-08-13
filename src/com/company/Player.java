package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> deck;
    private int playerNum;

    public Player(Deck deck, int playerNum) {
       switch (playerNum) {
           case 1:
               this.deck = new ArrayList<Card>
                       (Arrays.asList(Arrays.copyOfRange(deck.getCards(), 0, 26)));
               break;
           case 2:
               this.deck = new ArrayList<Card>
                       (Arrays.asList(Arrays.copyOfRange(deck.getCards(), 26, 52)));
               break;
       }
        this.playerNum = playerNum;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    //returns top card from deck
    public Card draw() {
        if (this.deck.size() > 0) {
            Card drawnCard = deck.get(0);
            deck.remove(deck.get(0));
            return drawnCard;
        }
        return null;
    }

    //adds all cards from war to the end of the player's deck
    public void winPool(ArrayList<Card> winnings) {
        this.deck.addAll(winnings);
    }

    public boolean deckIsEmpty() {
        return deck.size() == 0;
    }


}
