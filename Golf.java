/**********************************************************************
 * @file Golf.java
 * @brief Final problem set for CSC111. This program is a virtual
 * scorecard for golf.
 * @author Ben Alcaide, Ben Buckley, Ben Snavely
 * @date: 4/30/2024
 * @acknowledgement: N/A
 ***********************************************************************/

import java.util.Scanner;

public class Golf {

    public static void main(String[] args) {

        // Initialize new scanner
        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        System.out.println("Welcome to Old Town Club!");
        System.out.println("How many players today? (1-4): ");
        int numPlayers = scanner.nextInt();

        // Initialize necessary variables/arrays

        int numHoles = 18;

        // 2D array that stores names in first column and scores in following columns
        String[][] playersAndScores = new String[numPlayers][numHoles + 1];

        int[] totalPlayerScores;

        // Set all values in playersAndScores array to 0
        for (int i = 0; i < playersAndScores.length; i++) {
            for (int j = 0; j < playersAndScores[i].length; j++) {
                playersAndScores[i][j] = "0";
            }
        }

        // Assign names to first column of playersAndScores array
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("What is Player " + (i + 1) + "'s name? ");
            playersAndScores[i][0] = scanner.next();
        }

        // Call method to get the # of spaces needed for scorecard to format correctly
        String[] spacesNeeded = getSpacesNeeded(playersAndScores);

        // Iterate through each hole and each player to get their scores
        for (int i = 0; i < 9; i++) { // Iterate through holes 1-9
            System.out.println("Hole " + (i + 1) + " results.");
            for (int j = 0; j < numPlayers; j++) { // Iterate through each player
                // Ask for players' score
                System.out.println(playersAndScores[j][0] + "'s score: ");
                // Assign answer to playersAndScores array
                playersAndScores[j][i + 1] = scanner.next();
            }
            // If on holes 1-8, call method to print scores on each hole (but not total score)
            if (i < 8) {
                printHeader();
                printPlayerScores(playersAndScores, spacesNeeded);
            } else {
                // If on hole 9, call method to calculate total score for each player and
                // then use a new method to print all scores and total score (new method
                // had to be created due to issues parsing string array into int)
                totalPlayerScores = calculateTotalScore(playersAndScores);
                printHeader();
                printTotalScores(playersAndScores, spacesNeeded, totalPlayerScores);
                LeaderBoard(playersAndScores, spacesNeeded, totalPlayerScores);
            }
        }
    }


    // Method to print default header (stays the same throughout program)
    public static void printHeader() {
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|  Old Town Club                                             Winston-Salem, NC  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Hole           |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  OUT   |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Black Tees     | 423 | 166 | 427 | 526 | 383 | 186 | 419 | 402 | 436 |  3368  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Blue Tees      | 414 | 158 | 412 | 518 | 375 | 182 | 381 | 380 | 425 |  3245  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| White Tees     | 398 | 142 | 373 | 518 | 347 | 172 | 350 | 356 | 398 |  3054  |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| Par            |  4  |  3  |  4  |  5  |  4  |  3  |  4  |  4  |  4  |   35   |");
        System.out.println("|-------------------------------------------------------------------------------|");


    }

    // Method to calculate the total score of each player at the end of 9 holes
    public static int[] calculateTotalScore(String[][] playersAndScores) {

        int numberOfPlayers = playersAndScores.length;

        // 1D int array to be assigned total scores for each player
        int[] totalPlayerScores = new int[numberOfPlayers];

        // Iterates through each player, adding up their score in holes 1-9
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 1; j < playersAndScores[i].length; j++) { // starts in column 1 because names are in column 0
                totalPlayerScores[i] += Integer.parseInt(playersAndScores[i][j]); // parses integer from String array
            }
        }
        return totalPlayerScores;
    }

    // Method to determine proper number of spaces needed after player names to format the
    // scorecard correctly
    public static String[] getSpacesNeeded (String [][] playersAndScores) {

        int[] spacesNeeded = new int[4]; // Int array that counts number of spaces needed
        String[] spaces = new String[4]; // String array that holds the correct # of spaces

        // loop that clears the String array to no value (instead of null)
        for (int i = 0; i < playersAndScores.length; i++) {
            spaces[i] = "";
        }

        // loop that iterates through the player names in playersAndScores and determines
        // how many spaces are needed after their name (scorecard name column should be 13
        // characters wide)
        for(int i = 0; i < playersAndScores.length; i++) {
            spacesNeeded[i] = 13 - playersAndScores[i][0].length();
        }

        // loop that adds correct number of spaces for each player to String array
        for (int i = 0; i < playersAndScores.length; i++) {
            for (int j = 0; j < spacesNeeded[i]; j++) {
                spaces[i] += " ";
            }
        }
        return spaces;
    }

    // Method to print player scores for holes 1-8
    public static void printPlayerScores(String[][] playersAndScores, String[] spacesNeeded) {
        for (int i = 0; i < playersAndScores.length; i++) {
            System.out.println("| " + playersAndScores[i][0] + spacesNeeded[i] + "  |  " + playersAndScores[i][1] + "  |  " + playersAndScores[i][2] + "  |  " + playersAndScores[i][3] + "  |  " + playersAndScores[i][4] + "  |  " + playersAndScores[i][5] + "  |  " + playersAndScores[i][6] + "  |  " + playersAndScores[i][7] + "  |  " + playersAndScores[i][8] + "  |  " + playersAndScores[i][9] + "  |        |");
            System.out.println("|-------------------------------------------------------------------------------|");
        }
    }


    // Method to print player scores for after the round is completed (aka once users
    // have input scores for hole 9)
    public static void printTotalScores (String[][] playersAndScores, String[] spacesNeeded, int[] totalPlayerScores) {

        for (int i = 0; i < playersAndScores.length; i++) {
            System.out.println("| " + playersAndScores[i][0] + spacesNeeded[i] + "  |  " + playersAndScores[i][1] + "  |  " + playersAndScores[i][2] + "  |  " + playersAndScores[i][3] + "  |  " + playersAndScores[i][4] + "  |  " + playersAndScores[i][5] + "  |  " + playersAndScores[i][6] + "  |  " + playersAndScores[i][7] + "  |  " + playersAndScores[i][8] + "  |  " + playersAndScores[i][9] + "  |   " + totalPlayerScores[i] + "   |");
            System.out.println("|-------------------------------------------------------------------------------|");
        }
    }

    // Method to determine and print the leaderboard at the end of the program
    public static void LeaderBoard(String[][] playersAndScores, String[] spacesNeeded, int[] totalPlayerScores) {

        int[] toPar = new int[playersAndScores.length];

        for(int i = 0; i < toPar.length; i++) {
            toPar[i] = totalPlayerScores[i] - 35;
        }

        // add new leaderboard code here (to organize leaderboard correctly)

        System.out.println();
        System.out.println("|----------------------------------------------|");
        System.out.println("|               Final Leaderboard              |");
        System.out.println("|----------------------------------------------|");
        System.out.println("|      Golfer       |  Final Score  |  To Par  |");
        System.out.println("|-------------------|---------------|----------|");

        for (int i = 0; i < playersAndScores.length; i++) {
            System.out.println("| " + playersAndScores[i][0] + spacesNeeded[i] + "  |      "  + totalPlayerScores[i] + "       |    " + toPar[i] + "    |");
            System.out.println("|---------------------------------------------|");
        }
    }
}