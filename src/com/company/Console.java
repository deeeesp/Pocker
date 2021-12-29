package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    public static void start() {
        System.out.println("Введите количество игроков : ");
        Scanner sc = new Scanner(System.in);
        int countOfPlayers = sc.nextInt();
        System.out.println("Под каким номером вы хотите играть?");
        int number = sc.nextInt();
        while (number > countOfPlayers) {
            System.out.println("Введите корректный номер игрока ");
            number = sc.nextInt();
        }
        Croupier croupier = new Croupier();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < countOfPlayers; i++) {
            players.add(new Player());
        }
        String str = " ";
        while (!str.equals("Выйти")) {
            croupier.shuffle();
            gaming(players, croupier, number);
            System.out.println("Введите 'Продолжить', если хотите сыграть ещё или 'Выйти', если хотите закончить играть");
            str = sc.next();
        }
        System.out.println("                                                            Спасибо за игру! До встречи!");
    }

    private static void gaming(ArrayList<Player> players, Croupier croupier, int number) {
        croupier.giveCardsToPlayers(players);
        Table table = new Table();
        drawGame(players, table, number);
        write(players.get(number - 1), croupier);
        croupier.giveCardsToTable(table, 3);
        drawGame(players, table, number);
        write(players.get(number - 1), croupier);
        croupier.giveCardsToTable(table, 1);
        drawGame(players, table, number);
        write(players.get(number - 1), croupier);
        croupier.giveCardsToTable(table, 1);
        drawGame(players, table, number);
        write(players.get(number - 1), croupier);
        findWinner(players, table, croupier, number);
    }

    private static void drawGame(ArrayList<Player> players, Table table, int number) {
        for (int i = 0; i < 200; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        for (int i = 0; i < 70; i++) {
            System.out.print(" ");
        }
        String space = "                                                      ";
        if (table.getCardsOnTheTable().size() > 0) {
            System.out.println("На столе лежат");
            System.out.print(space + "|| ");
            for (int i = 0; i < table.getCardsOnTheTable().size(); i++) {
                System.out.print(table.getCardsOnTheTable().get(i).getCardValue() + " " + table.getCardsOnTheTable().get(i).getCardSuit() + " || ");
            }
        } else {
            System.out.println("На столе пока пусто.");
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("\n");
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCards().get(0) == null) {
                if (i + 1 == number) {
                    System.out.println(space + "Вы скинули свои карты. ");
                }
            }else if (i + 1 == number) {
                System.out.println(space + " Ваши карты" + " : " + players.get(i).getCards().get(0).getCardValue() +
                        " " + players.get(i).getCards().get(0).getCardSuit() + ", " + players.get(i).getCards().get(1).getCardValue() +
                        " " + players.get(i).getCards().get(1).getCardSuit() + "     ");
            } else {
                System.out.println(space + " Карты игрока под номером " + (i + 1) + " : " + players.get(i).getCards().get(0).getCardValue() +
                        " " + players.get(i).getCards().get(0).getCardSuit() + ", " + players.get(i).getCards().get(1).getCardValue() +
                        " " + players.get(i).getCards().get(1).getCardSuit() + "     ");
            }
        }
    }

    private static void write(Player player, Croupier croupier) {
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите 'Поставить n'(n - размер ставки)," +
                " чтобы поставить некоторую сумму, 'Банк', чтобы узнать размер своего банка или 'Продолжить', чтобы продолжить.");
        str = sc.nextLine();
        while (!str.equals("Продолжить")){
            String[] mas = str.split(" ");
            if (mas[0].equals("Поставить")) {
                player.bet(croupier, Integer.parseInt(mas[1]));
                break;
            }
            if (mas[0].equals("Банк")) {
                System.out.println("У вас осталось " + player.getBet());
            }
            System.out.println("Введите 'Поставить n'(n - размер ставки), " +
                    " чтобы поставить некоторую сумму, 'Банк', чтобы узнать размер своего банка или 'Продолжить', чтобы продолжить.");
            str = sc.nextLine();
        }
    }

    private static void findWinner(ArrayList<Player> players, Table table, Croupier croupier, int number) {
        Combination combination = new Combination();
        Map<Player, Double> results = new HashMap();
        for (int i = 0; i < players.size(); i++) {
            results.put(players.get(i),combination.countCombinationPower(players.get(i), table));
        }
        int ind  = 0;
        double max = results.get(players.get(0));
        for (int i = 1; i < results.size(); i++) {
            if (max < results.get(players.get(i))) {
                max = results.get(players.get(i));
                ind  = i;
            }
        }
        System.out.println(results);
        if (max == results.get(players.get(0))) {
            System.out.println("                                                       Вы победили и выиграли " + croupier.getBank() + "!");
            System.out.println(max);
            players.get(number - 1).setBet(croupier.getBank()*2);
        } else {
            System.out.println("                                                 Победил игрок под номером "
                    + (ind + " и выиграл " + croupier.getBank()));
            System.out.println(max);
            players.get(ind).setBet(croupier.getBank());
            if (players.get(number - 1).getBet() == 0) {
                System.out.println("                                                 У вас закончились деньги, вы проиграли :(");
            }
        }
        croupier.setBank(-croupier.getBank());
    }

}
