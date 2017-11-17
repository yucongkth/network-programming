/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_hangman;

import java.io.*;
import static java.lang.String.valueOf;
import java.net.*;
import java.util.*;

public class ServerThread implements Runnable {

    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader bufin = new BufferedReader(new InputStreamReader(client.getInputStream()));

            Random random = new Random();
            LinkedList<String> guesses = getWordList();
            int score = 0;
            boolean weArePlaying = true;
            while (weArePlaying) {
                String wel = "Welcome to hangman";
                bufout.write(wel);
                bufout.newLine();
                bufout.flush();
                String answer = guesses.get(random.nextInt(guesses.size()));
                char[] randomWordToGuess = answer.toCharArray();
                int amountOfGuesses = randomWordToGuess.length;
                char[] playerGuess = new char[amountOfGuesses];

                for (int i = 0; i < playerGuess.length; i++) {
                    playerGuess[i] = '_';
                }
                boolean wordIsGuessed = false;
                int remainingfailedattempts = randomWordToGuess.length;
                String amo = "You have " + amountOfGuesses + " tries left.";
                bufout.write(amo);
                bufout.newLine();
                bufout.flush();

                while (!wordIsGuessed && remainingfailedattempts != 0) {
                    String cg = "Current guesses:";
                    cg = cg + valueOf(playerGuess);
                    bufout.write(cg);
                    bufout.newLine();
                    bufout.flush();

                    boolean flag = true;
                    String str = bufin.readLine();
                    if (str.length() == answer.length()) {
                        if (str.equals(answer)) {
                            flag = true;
                            wordIsGuessed = true;
                            String con = "Congratulations you won!";
                            bufout.write(con);
                            bufout.newLine();
                            bufout.flush();
                        } else {
                            flag = false;
                            remainingfailedattempts = remainingfailedattempts - 1;
                            String tries = "You have " + remainingfailedattempts + " tries left.";
                            bufout.write(tries);
                            bufout.newLine();
                            bufout.flush();
                        }
                    } else {
                        char input = str.charAt(0);
                        for (int i = 0; i < randomWordToGuess.length; i++) {
                            if (randomWordToGuess[i] == input) {
                                playerGuess[i] = input;
                            }
                        }

                        if (isTheGuessRight(input, randomWordToGuess) == false) {
                            remainingfailedattempts = remainingfailedattempts - 1;
                            String tries = "You have " + remainingfailedattempts + " tries left.";
                            bufout.write(tries);
                            bufout.newLine();
                            bufout.flush();
                        } else {
                            String tries = "You have " + remainingfailedattempts + " tries left.";
                            bufout.write(tries);
                            bufout.newLine();
                            bufout.flush();
                        }
                    }
                    if (isTheWordGuessed(playerGuess)) {
                        wordIsGuessed = true;
                        String con = "Congratulations you won!";
                        bufout.write(con);
                        bufout.newLine();
                        bufout.flush();
                    }
                }

                if (!wordIsGuessed) {
                    String yro = "You ran out of guesses.";
                    bufout.write(yro);
                    bufout.newLine();
                    bufout.flush();
                    String ans = "The word is " + answer;
                    bufout.write(ans);
                    bufout.newLine();
                    bufout.flush();
                    score = score - 1;
                    String ys = "Your score:" + score;
                    bufout.write(ys);
                    bufout.newLine();
                    bufout.flush();
                } else {
                    score = score + 1;
                    String ys = "Your score:" + score;
                    bufout.write(ys);
                    bufout.newLine();
                    bufout.flush();
                }
                String pa = "Do you want to play another game? (YES/NO)";
                bufout.write(pa);
                bufout.newLine();
                bufout.flush();
                String anotherGame = bufin.readLine();
                if (anotherGame.equals("NO")) {
                    weArePlaying = false;
                }
            }
            String go = "Game Over";
            bufout.write(go);
            bufout.newLine();
            bufout.flush();

            bufout.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isTheWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                return false;
            }
        }
        return true;
    }

    public static boolean isTheGuessRight(char input, char[] array) {
        for (int i = 0; i < array.length; i++) {
            while (input == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static LinkedList<String> getWordList() {
        LinkedList<String> list = new LinkedList<String>();
        try {
            URL wordlist = new URL("file:///C:/Users/yucon/Desktop/P2/NETWORK PROGRAMMING/words.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(wordlist.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                list.add(inputLine);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
