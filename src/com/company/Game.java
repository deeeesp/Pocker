package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    private static int controller = 0;
    public static List<Player> players;
    public static Table table;
    public static Croupier croupier;

    /*public Game(){
        this.croupier = new Croupier();
        this.players = new ArrayList();
        this.table = new Table();
    }

     */

    public static int getController() {
        return controller;
    }

    public static List<Player> getPlayers(){
        return players;
    }

    public void start() {
        int countOfPlayers = 4;
        for (int i = 0; i < countOfPlayers; i++) {
            players.add(new Player());
        }
        croupier.shuffle();
        croupier.giveCardsToPlayers(players);
        croupier.giveCardsToTable(table, 3);
    }

    public void startOfTheGame() {
        int countOfPlayers = 4;
        players = new ArrayList<>();
        for (int i = 0; i < countOfPlayers; i++) {
            players.add(new Player());
        }
    }

    public static void setSettings() {
        table = new Table();
        croupier = new Croupier();
        croupier.shuffle();
        croupier.giveCardsToPlayers(players);
        controller = 0;
        table.getCardsOnTheTable().clear();
    }

    public static void gameController(int bet) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).bet(croupier, bet);
        }
        if (controller == 0) {
            controller = 3;
        } else if (controller == 3) {
            croupier.giveCardsToTable(table, controller);
            controller++;
        } else if (controller < 6) {
            controller++;
            croupier.giveCardsToTable(table, controller);
        }
    }

    public static String getListOfPlayerCards() {
        String s = "";
        for (int i = 0; i < 2; i++) {
            s += players.get(0).getCards().get(i).getCardValue() + " - " + players.get(0).getCards().get(i).getCardSuit() + " ";
        }
        return s;
    }

    public static String getListOfTableCards() {
        String s = "";
        if (controller == 0) {
            return "на столе пока пусто";
        } else {
            for (int i = 0; i < table.getCardsOnTheTable().size(); i++) {
                s += " " + table.getCardsOnTheTable().get(i).getCardValue() + " - " + table.getCardsOnTheTable().get(i).getCardSuit() + " ";
            }
            return s;
        }
    }

    public static String comb() {
        String s = "";
        Combination combination = new Combination();
        Map<Player, Double> results = new HashMap();
        for (int i = 0; i < players.size(); i++) {
            results.put(players.get(i), combination.countCombinationPower(players.get(i), table));
        }
        int ind = 0;
        double max = results.get(players.get(0));
        for (int i = 1; i < results.size(); i++) {
            if (max < results.get(players.get(i))) {
                max = results.get(players.get(i));
                ind = i;
            }
        }
        System.out.println(results);
        System.out.println(ind);
        if (max == results.get(players.get(0))) {
            s = "                                                       Вы победили и выиграли " + croupier.getBank() + "!";
            System.out.println(max);
            players.get(0).setBet(croupier.getBank() * 2);
        } else {
            s = "                                                 Победил игрок под номером "
                    + (ind + " и выиграл " + croupier.getBank());
            System.out.println(max);
            players.get(ind).setBet(croupier.getBank());
            if (players.get(0).getBet() == 0) {
                s = "                                                 У вас закончились деньги, вы проиграли :(";
            }
        }
        croupier.setBank(-croupier.getBank());
        return s;
    }

    public static String game(BufferedWriter os, BufferedReader is, String string) throws IOException {
        int bet = 0;
        String[] words = string.split("\\s");
        switch (words[0]) {
            case "bet":
                try {
                    bet = Integer.parseInt(words[1]);
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                }
            case "check":
                bet = 0;
                break;
            case "fold":
                for (int i = controller; i < 6; i++) {
                    gameController(0);
                }
                controller = 6;
                break;
        }
        switch (controller) {
            case 0:
                setSettings();
                gameController(bet);
                return "Поставить или Чек или Фолд (0 cards on the table) " + getListOfPlayerCards() + "|| " + getListOfTableCards() + "|| " + bet;
            case 3:
                gameController(bet);
                return "Поставить или Чек или Фолд (3 cards on the table) " + getListOfPlayerCards() + "|| " + getListOfTableCards() + "|| " + bet;
            case 4:
                gameController(bet);

                return "Поставить или Чек или Фолд (4 cards on the table) " + getListOfPlayerCards() + "|| " + getListOfTableCards() + "|| " + bet;
            case 5:
                gameController(bet);

                return "Поставить или Чек или Фолд (5 cards on the table) " + getListOfPlayerCards() + "|| " + getListOfTableCards() + "|| " + bet;
            case 6:
                controller = 0;
                return comb();
        }
        return "mistake";
    }



}
