package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private int size = 54;


    public List<Card> getCards() {
        return cards;
    }

    public Deck() {
        for(Value v : Value.values()) {
            for(Suit k : Suit.values()) {
                cards.add(new Card(v, k));
            }
        };
        Collections.shuffle(cards);
    }


    public Card getCard(){
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }

}
