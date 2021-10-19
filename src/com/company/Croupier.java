package com.company;

import java.util.ArrayList;

public class Croupier {
    private Deck deck;
    private int bank;

    public Croupier() {
        this.deck = new Deck();
        this.bank = 0;
    }


    public Deck getCroupierDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank += bank;
    }

    public ArrayList<Player> giveCardsToPlayers(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setFirstCard(deck.getCard());
            players.get(i).setSecondCard(deck.getCard());
        }
        return players;
    }

    public void giveCardsToTable(Table table, int n) {
        if (n==3) {
            for (int i = 0; i < 3; i++) {
                table.getCardsOnTheTable().add(this.getCroupierDeck().getCard());
            }
        } else {
            table.getCardsOnTheTable().add(this.getCroupierDeck().getCard());
        }
    }

    public void shuffle(){
        this.deck = new Deck();
    }

}
