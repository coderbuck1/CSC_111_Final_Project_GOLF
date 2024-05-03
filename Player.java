/**********************************************************************
 * @file Player.java
 * @brief Final problem set for CSC111. This class is to be used with
 * the Golf.java program.
 * @author Ben Alcaide, Ben Buckley, Ben Snavely
 * @date: 5/3/2024
 * @acknowledgement: N/A
 **********************************************************************/

public class Player {
    private String name;
    private int[] scores;

    public Player(String name) {
        this.name = name;
        // initialize the scores array with the appropriate length (9 holes)
        this.scores = new int[9];
    }

    // method to set player scores
    public void setScores(int hole, int score) {
        scores[hole - 1] = score;
    }

    // method to get player names
    public String getName() {
        return name;
    }

    // method to get player scores
    public int getScore(int hole) {
        return scores [hole - 1];
    }
}
