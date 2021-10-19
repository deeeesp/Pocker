package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Combination {
    public double countCombinationPower(Player player, Table table) {// пара, 2 пары, сет, стрит,  , фул хаус, каре
        double sum = 0;
        while (true) {
            if (containsRoyal(player, table) != 0) {
                sum = 10 + 0.01 * containsRoyal(player, table);
                break;
            } else if (containsStraightFlush(player, table) != 0) {
                sum = 9 + 0.01 * containsStraightFlush(player, table);
                break;
            } else if (containsFourOfKind(player, table) != 0) {
                sum = 8 + 0.01 * containsFourOfKind(player, table);
                break;
            } else if (containsHouse(player, table) != 0) {
                sum = 7 + 0.01 * containsHouse(player, table);
                break;
            } else if (containsFlush(player, table) != 0) {
                sum = 6 + 0.01 * containsFlush(player, table);
                break;
            } else if (containsStraight(player, table) != 0) {
                sum = 5 + 0.01 * containsStraight(player, table);
                break;
            } else if (containsSet(player, table) != 0) {
                sum = 4 + 0.01 * containsSet(player, table);
                break;
            } else if (containsPare(player, table) != 0) {
                sum = containsPare(player, table);
                break;
            }
        }
        return sum;

    }

    private double containsRoyal(Player player, Table table) {
        List<Card> cards = createList(player, table);
        if (containsFlush(player, table) > 0) {
            Suit suit = returnFlushSuit(player, table);
            for (int i = 0; i < cards.size(); i++) {
                if (!cards.get(i).getCardSuit().equals(suit)) {
                    cards.remove(i);
                }
            }
        }
        int lastStreetCard = 0;
        int street = 1;
        Value card = cards.get(0).getCardValue();
        for (int i = 1; i < cards.size(); i++) {
            if ((cards.get(i).getCardValue().ordinal() - card.ordinal()) == 1) {
                street++;
                if (street >= 5) {
                    lastStreetCard = cards.get(i).getCardValue().ordinal();
                }
            } else if (i == cards.size() - 1 && cards.get(i).getCardValue().ordinal() - card.ordinal() == 1 && street > 4) {
                lastStreetCard = cards.get(i).getCardValue().ordinal();
            } else {
                street = 1;
            }
            card = cards.get(i).getCardValue();
        }
        if (lastStreetCard == Value.ACE.ordinal()) {
            return lastStreetCard;
        } else return 0;
    }

    private double containsStraightFlush(Player player, Table table) {
        List<Card> cards = createList(player, table);
        if (containsFlush(player, table) > 0) {
            Suit suit = returnFlushSuit(player, table);
            for (int i = 0; i < cards.size(); i++) {
                if (!cards.get(i).getCardSuit().equals(suit)) {
                    cards.remove(i);
                }
            }
        }
        int lastStreetCard = 0;
        int street = 1;
        Value card = cards.get(0).getCardValue();
        for (int i = 1; i < cards.size(); i++) {
            if ((cards.get(i).getCardValue().ordinal() - card.ordinal()) == 1) {
                street++;
                if (street >= 5) {
                    lastStreetCard = cards.get(i).getCardValue().ordinal();
                }
            } else if (i == cards.size() - 1 && cards.get(i).getCardValue().ordinal() - card.ordinal() == 1 && street > 4) {
                lastStreetCard = cards.get(i).getCardValue().ordinal();
            } else {
                street = 1;
            }
            card = cards.get(i).getCardValue();
        }
        return lastStreetCard;
    }

    private Suit returnFlushSuit(Player player, Table table) {
        List<Card> cards = createList(player, table);
        List<Card> flush = new ArrayList<>();
        SortCardsBySuit(cards);
        int steak = 1;
        Card card = cards.get(0);
        flush.add(card);
        Suit lastCard = Suit.BYBN;
        for (int i = 1; i < cards.size(); i++) {
            if (card.getCardSuit().equals(cards.get(i).getCardSuit())) {
                steak++;
                lastCard = cards.get(i).getCardSuit();
                if (steak >= 5) {
                    return lastCard;
                }
            } else if (i == cards.size() - 1 && card.getCardSuit().equals(cards.get(i).getCardSuit()) && steak > 4) {
                lastCard = flush.get(flush.size() - 1).getCardSuit();
            } else {
                steak = 1;
                flush.clear();
                flush.add(cards.get(i));
            }
            card = cards.get(i);
        }
        return lastCard;
    }

    private double containsFlush(Player player, Table table) {
        List<Card> cards = createList(player, table);
        List<Card> flush = new ArrayList<>();
        SortCardsBySuit(cards);
        int steak = 1;
        Card card = cards.get(0);
        flush.add(card);
        int lastCard = 0;
        for (int i = 1; i < cards.size(); i++) {
            if (card.getCardSuit().equals(cards.get(i).getCardSuit())) {
                steak++;
                flush.add(cards.get(i));
                if (steak >= 5) {
                    SortCardsByValue(flush);
                    lastCard = flush.get(flush.size() - 1).getCardValue().ordinal();
                }
            } else if (i == cards.size() - 1 && card.getCardSuit().equals(cards.get(i).getCardSuit()) && steak > 4) {
                SortCardsByValue(flush);
                lastCard = flush.get(flush.size() - 1).getCardValue().ordinal();
            } else {
                steak = 1;
                flush.clear();
                flush.add(cards.get(i));
            }
            card = cards.get(i);
        }
        return lastCard;
    }

    private double containsPare(Player player, Table table) {
        List<Card> cards = createList(player, table);
        Value card = cards.get(0).getCardValue();
        List<Value> pars = new ArrayList();
        for (int i = 1; i < cards.size(); i++) {
            if (card.equals(cards.get(i).getCardValue())) {
                pars.add(card);
            } else card = cards.get(i).getCardValue();
        }
        if (pars.size() == 0) {
            return 1 + 0.01 * cards.get(cards.size() - 1).getCardValue().ordinal();
        } else if (pars.size() < 2) {
            return 2 + 0.01 * (pars.get(0).ordinal());
        } else {
            return 3 + 0.01 * (pars.get(pars.size() - 2).ordinal() + pars.get(pars.size() - 1).ordinal());
        }
    }

    private int containsFourOfKind(Player player, Table table) {
        List<Card> cards = createList(player, table);
        Value card = cards.get(0).getCardValue();
        for (int i = 1; i < cards.size(); i++) {
            if (card.equals(cards.get(i).getCardValue()) && card.equals(cards.get(i + 1).getCardValue()) && card.equals(cards.get(i + 2).getCardValue())) {
                return cards.get(i).getCardValue().ordinal();
            } else
                card = cards.get(i).getCardValue();
        }
        return 0;
    }

    private int containsStraight(Player player, Table table) {
        List<Card> cards = createList(player, table);
        Value card = cards.get(0).getCardValue();
        int lastStreetCard = 0;
        int street = 1;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardValue().ordinal() == card.ordinal()) {
                cards.remove(i);
            }
            card = cards.get(i).getCardValue();
        }
        card = cards.get(0).getCardValue();
        for (int i = 1; i < cards.size(); i++) {
            if ((cards.get(i).getCardValue().ordinal() - card.ordinal()) == 1) {
                street++;
                if (street >= 5) {
                    lastStreetCard = cards.get(i).getCardValue().ordinal();
                }
            } else if (i == cards.size() - 1 && cards.get(i).getCardValue().ordinal() - card.ordinal() == 1 && street > 4) {
                lastStreetCard = cards.get(i).getCardValue().ordinal();
            } else {
                street = 1;
            }
            card = cards.get(i).getCardValue();
        }
        return lastStreetCard;
    }


    private int containsSet(Player player, Table table) {
        List<Card> cards = createList(player, table);
        Value card = cards.get(1).getCardValue();
        for (int i = 1; i < cards.size() - 1; i++) {
            if (card.equals(cards.get(i).getCardValue()) && card.equals(cards.get(i + 1).getCardValue())) {
                return card.ordinal();
            } else card = cards.get(i).getCardValue();
        }
        return 0;
    }

    private int containsHouse(Player player, Table table) {
        int house = 0;
        int pare = getLastPare(player, table);
        int set = containsSet(player, table);
        if (pare > 0 && set > 0) {
            house = pare + set;
        }
        return house;
    }

    private int getLastPare(Player player, Table table) {
        List<Card> cards = createList(player, table);
        Value card = cards.get(0).getCardValue();
        int para = 0;
        for (int i = 1; i < cards.size(); i++) {
            if (card == cards.get(i).getCardValue() && card.ordinal() != containsSet(player, table)) {
                para = cards.get(i).getCardValue().ordinal();
            } else card = cards.get(i).getCardValue();
        }
        return para;
    }


    public List<Card> createList(Player player, Table table) {
        List<Card> cards = new ArrayList();
        cards.add(player.getFirstCard());
        cards.add(player.getSecondCard());
        for (int i = 0; i < table.getCardsOnTheTable().size(); i++) {
            cards.add(table.getCardsOnTheTable().get(i));
        }
        SortCardsByValue(cards);
        return cards;
    }

    private void SortCardsByValue(List<Card> cards) {
        Comparator<Card> cardComparator = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getCardValue().ordinal() - o2.getCardValue().ordinal();
            }
        };
        Collections.sort(cards, cardComparator);
    }

    private void SortCardsBySuit(List<Card> cards) {
        Comparator<Card> cardComparator = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getCardSuit().ordinal() - o2.getCardSuit().ordinal();
            }
        };
        Collections.sort(cards, cardComparator);
    }
}