package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws IOException {

        ServerSocket listener = null;

        System.out.println("Server is waiting to accept user...");
        int clientNumber = 0;

        // Try to open a server socket on port 7777
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)

        try {
            listener = new ServerSocket(4352);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                // Accept client connection request
                // Get new Socket at Server.

                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }

    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static class ServiceThread extends Thread {

        private int clientNumber;
        private Socket socketOfServer;
        private boolean isStarted = false;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            // Log
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
        }

        @Override
        public void run() {

            try {

                // Open input and output streams
                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
                Game game = new Game();
                game.startOfTheGame();
                while (true) {

                    String line = is.readLine();
                    if (line.equals("stop")) {
                        os.write(">> OK");
                        os.newLine();
                        os.flush();
                        break;
                    }
                    System.out.println("Client printed " + line);
                    if (line.equals("start")) {
                        isStarted = true;
                        os.newLine();
                        os.flush();
                    } else  if (!isStarted){
                        os.write("Введите start чтобы начать игру");
                        os.newLine();
                        os.flush();
                    }
                    if (isStarted && !line.equals("start")) {
                        String s = Game.game(os,is,line);
                        System.out.println(s);
                        os.write(s);
                        os.newLine();
                        os.flush();
                    }


                }

            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }


        public static void test(BufferedWriter os, BufferedReader is) throws IOException {
            Game.setSettings();
            os.write("next or not? (0 cards on the table) " + Game.getListOfTableCards() + "|| ");
            os.newLine();
            os.flush();
            String s1 = is.readLine();
            if (s1.equals("next")) {
                Game.gameController(10);
            }
            os.write("next or not? (3 cards on the table) " + Game.getListOfPlayerCards() + "|| " + Game.getListOfTableCards() + "|| ");
            os.newLine();
            os.flush();

            String s2 = is.readLine();
            if (s2.equals("next")) {
                Game.gameController(10);
            }
            os.write("next or not? (4 cards on the table) " + Game.getListOfPlayerCards() + "|| " + Game.getListOfTableCards() + "|| ");
            os.newLine();
            os.flush();

            String s3 = is.readLine();
            if (s3.equals("next")) {
                Game.gameController(10);
            }
            os.write("next or not? (5 cards on the table) " + Game.getListOfPlayerCards() + "|| " + Game.getListOfTableCards() + "|| ");
            os.newLine();
            os.flush();

            String s4 = is.readLine();
            if (s4.equals("next")) {
                Game.gameController(10);
            }
            os.write(Game.comb());
            os.newLine();
            os.flush();
        }

    }


}