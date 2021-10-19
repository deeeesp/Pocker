package com.company;

import java.util.ArrayList;
import java.util.List;


public class Table {
    private List<Card> cardsOnTheTable;

    public List<Card> getCardsOnTheTable() {
        return cardsOnTheTable;
    }

    public Table() {
        this.cardsOnTheTable = new ArrayList<>();
    }
}
