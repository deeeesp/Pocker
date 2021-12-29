package com.company;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards;
    private int bet = 1000;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> list) {
        for (int i = 0; i < 2; i++) {
            cards.add(i, list.get(i));
        }
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet += bet;
    }

    public void bet(Croupier croupier, int sum) {
        if (sum > bet) {
            System.out.println("Вы не можете поставить больше чем есть в вашем банке.");
            return;
        }
        croupier.setBank(sum);
        this.bet -= sum;
    }

    public void botBet(Croupier croupier) {
        int sum = (int) (50 + Math.random()*500);
        if (sum > bet) {
            System.out.println("Вы не можете поставить больше чем есть в вашем банке.");
            return;
        }
        croupier.setBank(sum);
        this.bet -= sum;
    }

    public void clearCards() {
        this.cards = new ArrayList<>();
    }

}
