package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Interface extends Application {
    int count = 0;
    int sum = 0;


    public static void main(String[] args) {
        //Game.start();
        launch(args);
        // System.out.println(getImageByCard(0, 0));


    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {


        primaryStage.setTitle("poker!");
        Scene scene = new Scene(draw(), 1500, 900);


        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static String getImageByCard(Card card) {
        String s = "D://cards/";
        s = s + card.getCardValue().ordinal() + "." + card.getCardSuit().ordinal() + ".jpg";
        System.out.println(s);
        return s;
    }

    public Image getDefaultCard() throws FileNotFoundException {
        return new Image(new FileInputStream("D://cards/default.png"));
    }

    public Group DrawTableCards(int count, Table table) throws FileNotFoundException {
        if (count <= 3) {
            return new Group(idk(table, 0), idk(table, 1), idk(table, 2), constructImage(3, getDefaultCard()), constructImage(4, getDefaultCard()));
        } else if (count == 4) {
            return new Group(idk(table, 0), idk(table, 1), idk(table, 2), idk(table, 3), constructImage(4, getDefaultCard()));
        } else {
            return new Group(idk(table, 0), idk(table, 1), idk(table, 2), idk(table, 3), idk(table, 4));
        }
    }

    public ImageView idk(Table table, int i) {
        return constructImage(i, new Image(getImageByCard(table.getCardsOnTheTable().get(i))));
    }

    public ImageView constructImage(int count, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setX(300 + (200 * count));
        imageView.setY(150);
        imageView.setFitHeight(270);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public ImageView constructPlayerImage(int count, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setX(300 + (200 * count));
        imageView.setY(150);
        imageView.setFitHeight(270);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public Group draw() throws FileNotFoundException {
        Group root = new Group();
        Table table = new Table();
        Croupier croupier = new Croupier();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new Player());
        }
        croupier.giveCardsToPlayers(players);

        for (int i = 3; i < 6; i++) {
            croupier.giveCardsToTable(table, i);
        }
        Button bet = new Button("make a bet");
        bet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                players.get(0).bet(croupier, sum);
                sum = 0;
                openCards(players, root, table, croupier);
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

                openCards(players, root, table, croupier);
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
                openCards(players, root, table, croupier);
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
                System.out.println(10);

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
                System.out.println(50);

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
                System.out.println(100);

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
                System.out.println(500);

            }
        });
        fiveHundred.setLayoutX(1125);
        fiveHundred.setLayoutY(810);
        fiveHundred.setPrefSize(100, 40);
        fiveHundred.setStyle("-fx-background-color: red;");
        root.getChildren().add(fiveHundred);


        Image ip1 = new Image(new FileInputStream(getImageByCard(players.get(0).getCards().get(0))));
        ImageView ipv1 = new ImageView(ip1);
        ipv1.setX(600);
        ipv1.setY(500);
        ipv1.setFitHeight(270);
        ipv1.setFitWidth(150);
        ipv1.setPreserveRatio(true);
        root.getChildren().add(ipv1);

        Image ip2 = new Image(new FileInputStream(getImageByCard(players.get(0).getCards().get(1))));
        ImageView ipv2 = new ImageView(ip2);
        ipv2.setX(800);
        ipv2.setY(500);
        ipv2.setFitHeight(270);
        ipv2.setFitWidth(150);
        ipv2.setPreserveRatio(true);
        root.getChildren().add(ipv2);

        return root;
    }

    public void openCards(List<Player> players, Group root, Table table, Croupier croupier) {
        if (count < 1) {
            try {
                root.getChildren().add(DrawTableCards(3, table));
                count++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else
            try {
                root.getChildren().remove(DrawTableCards(count + 2, table));
                root.getChildren().add(DrawTableCards(count + 3, table));
                count++;
                if (count == 4) {
                    findWinner(players, table, root, croupier);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }

    public void nullx(Group root){
        root.getChildren().clear();
        root.getChildren().add(new Text("vsdvsnjvnsd"));
    }

    public Group findWinner(List<Player> players, Table table, Group root, Croupier croupier) {
        root.getChildren().clear();
        Text text = new Text();
        text.setX(750);
        text.setY(450);
        text.setTabSize(45);
        text.setFill(Color.BLACK);
        Combination combination = new Combination();
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            result.add(combination.countCombinationPower(players.get(i), table));
        }
        double max = result.get(0);
        for (int i = 1; i < result.size(); i++) {
            if (max < result.get(i)) {
                max = result.get(i);
            }
        }
        text.setText("cnhfyyj");
        if (result.indexOf(max) == 0) {
            text.setText("You win and get" + croupier.getBank() * players.size());
            players.get(0).setBet(croupier.getBank() * players.size());
        } else {
            text.setText("You lose " + croupier.getBank());
        }
        croupier.setBank(-croupier.getBank());
        root.getChildren().add(text);
        return root;
    }
}
