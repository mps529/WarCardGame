package com.company;

import java.util.ArrayList;

public class WarGame {

    //currently only supports two players
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Card> pool; //stores cards during 'War' sessions

    //default constructor. Builds, shuffles, and splits deck between two players
    public WarGame() {
        Deck deck = new Deck();
        deck.shuffle();
        this.playerOne = new Player(deck, 1);
        this.playerTwo = new Player(deck, 2);
        pool = new ArrayList<Card>();
    }

    private void battle(Card p1Draw, Card p2Draw) {
        switch (compareCards(p1Draw, p2Draw)) {
            case 1:
                System.out.println("Player One beat Player Two's " + p2Draw.toString() + " with " + p1Draw.toString());
                addToPool(p2Draw, p1Draw);
                playerOne.winPool(pool);
                dumpPool();
                break;
            case -1:
                System.out.println("Player Two beat Player One's " + p1Draw.toString() + " with " + p2Draw.toString());
                addToPool(p1Draw, p2Draw);
                playerTwo.winPool(pool);
                dumpPool();
                break;
            case 0:
                System.out.println(p1Draw.toString()+" matches "+p2Draw.toString()+"\n"+
                        "It's War!\n"+
                        "The players draw one face-down card");
                war(p1Draw, p2Draw, pool);
                break;
        }
    }




    private void war(Card p1Card, Card p2Card, ArrayList<Card> pool) {

        addToPool(p1Card, p2Card);
        while (compareCards(p1Card, p2Card) == 0) {

            p1Card = this.playerOne.draw();
            p2Card = this.playerTwo.draw();

            if (compareCards(p1Card, p2Card)!=2) {
                addToPool(p1Card, p2Card);
                p1Card = this.playerOne.draw();
                p2Card = this.playerTwo.draw();
                if (compareCards(p1Card, p2Card)!=2) battle(p1Card, p2Card);
                else {
                    addToPool(p1Card, p2Card);
                    return;
                }
            } else {
                addToPool(p1Card, p2Card);
                return;
            }
        }
    }

    private int compareCards(Card c1, Card c2) {
        if (c1 == null || c2 == null) return 2;
        else return Integer.compare(c1.getValue(), c2.getValue());

    }
    //auto-plays an entire game
    public void play(){
        Card c1 = playerOne.draw();
        Card c2 = playerTwo.draw();
        while(compareCards(c1, c2) !=2) {
            //continue as long as drawn cards aren't null
            battle(c1, c2);
            if (compareCards(c1, c2) != 2) {
                c1 = playerOne.draw();
                c2 = playerTwo.draw();
            }
        }
        addToPool(c1, c2); //adds remaining drawn cards to pool to be added to the winner
        System.out.println(declareWinner(c1, c2));
        System.out.println(playerOne.getDeck().size());
        System.out.println(playerTwo.getDeck().size());
    }

    private void addToPool(Card winnerCard, Card loserCard) {
        if (winnerCard == null) {
            pool.add(loserCard);
            return;
        } else if (loserCard == null) {
            pool.add(winnerCard);
            return;
        } else { //adds loser card first to ensure games do not run endlessly
            pool.add(loserCard);
            pool.add(winnerCard);
        }
    }
    private void dumpPool() {
        pool.clear();
    }
    private void winnerTakesPool() {
        if (playerOne.deckIsEmpty()) {
            playerTwo.winPool(pool);
        } else if (playerTwo.deckIsEmpty())  {
            playerOne.winPool(pool);
        }
    }

    private String declareWinner(Card c1, Card c2) {
        //helper method to eliminate re-used code
        winnerTakesPool();
        return (c1 == null) ? "Player Two wins! Player One ran out of cards." : (c2 == null) ?
                                                                            "Player One wins! Player Two ran out of cards" : "";
    }


}
