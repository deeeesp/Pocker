package com.company;

import java.util.Scanner;

public class Card {

    private Value cardValue;
    private Suit cardSuit;

    public Card() {

    }

    public Card(Value cardValue, Suit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public Value getCardValue() {
        return cardValue;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

}
