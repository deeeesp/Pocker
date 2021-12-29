package com.company;

import java.util.ArrayList;
import java.util.List;

public class Croupier {
    private Deck deck;
    private int bank;
    List<Card> list = new ArrayList();

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

    public List<Player> giveCardsToPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            list.add(deck.getCard());
            list.add(deck.getCard());
            players.get(i).setCards(list);
            list.clear();
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
