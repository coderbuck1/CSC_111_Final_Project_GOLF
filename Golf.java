/**********************************************************************
 * @file Golf.java
 * @brief Final problem set for CSC111. This program is a virtual
 * scorecard for golfing.
 * @author Ben Alcaide, Ben Buckley, Ben Snavely
 * @date: 5/3/2024
 * @acknowledgement: N/A
 **********************************************************************/

import java.util.Scanner;
import java.util.ArrayList;

public class Golf {
    public static void main(String[] args) {
        // initialize new scanner
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 0;

        // print welcome message
        System.out.println("Welcome to Old Town Club!");

        // make sure the number of players is an int between 1 and 4
        while (true) {
            System.out.println("How many players today? (1-4): ");
            if (scanner.hasNextInt()) {
                numPlayers = scanner.nextInt();
                if (numPlayers >= 1 && numPlayers <= 4) {
                    break; // valid input, exit loop
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next(); // clear invalid input from scanner
            }
        }

        // initialize numHoles variable. 9 holes is standard (18 would have been too repetitive)
        int numHoles = 9;

        // create an ArrayList to hold Player objects
        ArrayList<Player> players = new ArrayList<>();

        // Get player names (and create needed Player objects)
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("What is Player " + (i + 1) + "'s name? ");
            String name = scanner.next();
            Player player = new Player(name);
            players.add(player); // add the created player to the ArrayList
        }

        for (int i = 0; i < 9; i++) { // iterate through holes 1-9
            System.out.println("Hole " + (i + 1) + " results.");
            for (int j = 0; j < numPlayers; j++) { // iterate through each player
                // ask for players' score
                System.out.println(players.get(j).getName() + "'s score: ");
                // assign answer to playersAndScores array
                players.get(j).setScores(i + 1, scanner.nextInt());
            }
            printHeader();
            printPlayerScores(players);
        }
    }

    // method to print player scores for holes 1-8
    public static void printPlayerScores(ArrayList<Player> players) {
        String[] spacesNeeded = getSpacesNeeded(players);
        for (int i = 0; i < players.size(); i++) {
            System.out.print("| " + players.get(i).getName() + spacesNeeded[i] + " |  ");
            int totalScore = 0; // initialize total score
            for (int j = 0; j < 9; j++) { // assuming 9 holes
                int score = players.get(i).getScore(j + 1);
                totalScore += score; // add each hole's score to total score
                System.out.print(score + "  |  ");
            }
            System.out.print(" " + totalScore); // print total score
            if (totalScore < 10) { // formats end | correctly based on single or double-digit score
                System.out.print("     |");
            } else {
                System.out.print("    |");
            }
            System.out.println();
            System.out.println("|-------------------------------------------------------------------------------|");
        }
    }

    // method to print default header (stays the same throughout program)
    public static void printHeader() {
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|  Old Town Club                                             Winston-Salem, NC  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Hole          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   OUT   |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Black Tees    | 423 | 166 | 427 | 526 | 383 | 186 | 419 | 402 | 436 |   3368  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Blue Tees     | 414 | 158 | 412 | 518 | 375 | 182 | 381 | 380 | 425 |   3245  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| White Tees    | 398 | 142 | 373 | 518 | 347 | 172 | 350 | 356 | 398 |   3054  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Par           |  4  |  3  |  4  |  5  |  4  |  3  |  4  |  4  |  4  |   35    |");
        System.out.println("|-------------------------------------------------------------------------------|");
    }

    // method to determine proper number of spaces needed after player names to format the
    // scorecard correctly
    public static String[] getSpacesNeeded(ArrayList<Player> players) {
        int[] spacesNeeded = new int[players.size()]; // array that will count the number of spaces needed
        String[] spaces = new String[players.size()]; // string array that will hold  correct # of spaces

        // loop to clear the String array to no value (instead of null)
        for (int i = 0; i < players.size(); i++) {
            spaces[i] = "";
        }

        // loop to iterate through the player names and determine how many spaces are needed
        // after their name (scorecard name column should be 14 characters between the |'s
        // but there is also a space between first | and the name (hence 13 below)
        for (int i = 0; i < players.size(); i++) {
            spacesNeeded[i] = Math.max(0, 13 - players.get(i).getName().length());
        }

        // loop to add the correct number of spaces for each player to the String array
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < spacesNeeded[i]; j++) {
                spaces[i] += " ";
            }
        }
        return spaces;
    }
}
