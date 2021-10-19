package com.company;


public class Player {
    private Card firstCard;
    private Card secondCard;
    private int bet = 1000;

    public Player() {

    }

    public Player(Card firstCard, Card secondCard) {
        this.firstCard = firstCard;
        this.secondCard = secondCard;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(Card secondCard) {
        this.secondCard = secondCard;
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


}
