package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fx extends Application {
    int countDo = 0;
    int sum = 0;

    public String message;

    public static void main(String[] args) {
        //Game.start();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Croupier croupier = new Croupier();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new Player());
        }
        draw(players);


    }

    public void draw(List<Player> players) throws FileNotFoundException {
        Stage stage = new Stage();
        Group root = new Group();
       // Table table = new Table();
        Croupier croupier = new Croupier();
        croupier.giveCardsToPlayers(players);
        drawPersonalCards(players.get(0), root);
        /*for (int i = 3; i < 6; i++) {
            croupier.giveCardsToTable(table, i);
        }

         */
        String money = "Ваш Банк : " + players.get(0).getBet();
        Text text = new Text(money);
        root.getChildren().add(text);
        text.setX(10);
        text.setY(10);
        Button bet = new Button("make a bet");
        bet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 1; i < players.size(); i++) {
                    players.get(i).botBet(croupier);
                }
                text.setText("Ваш Банк : " + players.get(0).getBet());
                sum = 0;
                message = "bet";
                /*try {
                    control(players, table, croupier, root, stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                 */
            }
        });
        bet.setLayoutX(750);
        bet.setLayoutY(750);
        bet.setPrefSize(200, 100);
        bet.setStyle("-fx-background-color: orange;");
        root.getChildren().add(bet);
        Button fold = new Button("fold");
        fold.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                message = "fold";
                /*try {
                    control(players, table, croupier, root, stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                 */
            }
        });
        fold.setLayoutX(500);
        fold.setLayoutY(750);
        fold.setStyle("-fx-background-color: orange;");
        fold.setPrefSize(200, 100);
        root.getChildren().add(fold);

        Button pass = new Button("pass");
        pass.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                message = "pass";
                /*
                try {
                    for (int i = countDo; i < 4; i++) {
                        control(players, table, croupier, root, stage);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                    */
                }
        });
        pass.setLayoutX(250);
        pass.setLayoutY(750);
        pass.setPrefSize(200, 100);
        pass.setStyle("-fx-background-color: orange;");
        root.getChildren().add(pass);

        Button ten = new Button("10");
        ten.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sum += 10;
            }
        });
        ten.setLayoutX(1000);
        ten.setLayoutY(750);
        ten.setPrefSize(100, 40);
        ten.setStyle("-fx-background-color: red;");
        root.getChildren().add(ten);

        Button fifty = new Button("50");
        fifty.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sum += 50;

            }
        });
        fifty.setLayoutX(1125);
        fifty.setLayoutY(750);
        fifty.setPrefSize(100, 40);
        fifty.setStyle("-fx-background-color: red;");
        root.getChildren().add(fifty);


        Button hundred = new Button("100");
        hundred.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sum += 100;
            }
        });
        hundred.setLayoutX(1000);
        hundred.setLayoutY(810);
        hundred.setPrefSize(100, 40);
        hundred.setStyle("-fx-background-color: red;");
        root.getChildren().add(hundred);

        Button fiveHundred = new Button("500");
        fiveHundred.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sum += 500;
            }
        });
        fiveHundred.setLayoutX(1125);
        fiveHundred.setLayoutY(810);
        fiveHundred.setPrefSize(100, 40);
        fiveHundred.setStyle("-fx-background-color: red;");
        root.getChildren().add(fiveHundred);
        stage.setTitle("poker");
        Scene scene = new Scene(root, 1500, 900, Color.LIGHTGREEN);
        stage.setScene(scene);
        stage.show();

    }


    private void drawPersonalCards(Player player, Group group) throws FileNotFoundException {
        for (int i = 0; i < 2; i++) {
            Card card = player.getCards().get(i);
            String s = "D://cards/";
            s = s + card.getCardValue().ordinal() + "." + card.getCardSuit().ordinal() + ".jpg";
            Image image = new Image(new FileInputStream(s));
            ImageView imageView = new ImageView(image);
            imageView.setX(550 + (200 * i));
            imageView.setY(500);
            imageView.setFitHeight(270);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
            group.getChildren().add(imageView);
        }
    }


    private ImageView getImageOfCard(Card card, int count) throws FileNotFoundException {
        String s = "D://cards/";
        s = s + card.getCardValue().ordinal() + "." + card.getCardSuit().ordinal() + ".jpg";
        Image image = new Image(new FileInputStream(s));
        ImageView imageView = new ImageView(image);
        imageView.setX(300 + (200 * count));
        imageView.setY(150);
        imageView.setFitHeight(270);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        return imageView;
    }

   /* private void control(List<Player> players, Table table, Croupier croupier, Group root, Stage stage) throws FileNotFoundException {
        if (countDo == 0) {
            for (int i = 0; i < 3; i++) {
                root.getChildren().add(getImageOfCard(table.getCardsOnTheTable().get(i), i));
            }
            countDo++;
        } else if (countDo == 3) {
            findWinner(players, table, croupier, root, stage);
        } else {
            root.getChildren().add(getImageOfCard(table.getCardsOnTheTable().get(countDo + 2), countDo + 2));
            countDo++;
        }
    }

    private  void findWinner(List<Player> players, Table table, Croupier croupier, Group root, Stage stage) {
        String string = " ";
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
        if (max == results.get(players.get(0))) {
            string = "Вы победили и выиграли " + croupier.getBank() + "!";
            System.out.println(max);
            players.get(0).setBet(croupier.getBank());
        } else {
            string = "Победил игрок под номером "
                    + (ind + " и выиграл " + croupier.getBank());
            System.out.println(max);
            players.get(ind).setBet(croupier.getBank());
            if (players.get(0).getBet() == 0) {
                string = "У вас закончились деньги, вы проиграли :(";
            }
        }
        Text text = new Text(string);
        text.setLayoutX(525);
        text.setLayoutY(300);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        root.getChildren().clear();
        root.getChildren().add(text);
        croupier.setBank(-croupier.getBank());
        Button close = new Button("Закончить игру");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        close.setLayoutX(800);
        close.setLayoutY(600);
        close.setPrefSize(200, 100);
        close.setStyle("-fx-background-color: orange;");
        root.getChildren().add(close);
        Button proceed = new Button("Продолжить");
        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                countDo = 0;
                sum = 0;
                for (int i = 0; i <players.size() ; i++) {
                    players.get(0).clearCards();
                }
                try {
                    draw(players);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        proceed.setLayoutX(500);
        proceed.setLayoutY(600);
        proceed.setPrefSize(200, 100);
        proceed.setStyle("-fx-background-color: orange;");
        root.getChildren().add(proceed);
    }

    */

}
