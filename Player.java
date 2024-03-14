/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

/**
 * A class for connecting shared functionality between the human and AI players
 */
public class Player {
    private static int currentTurn = 0;
    /**
     * Increases the turn count by one
     */
    public static void nextTurn() {
        currentTurn++;
    }

    /**
     * Returns the current turn starting at zero
     *
     * @return The current turn
     */
    public static int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Set the turn count to zero
     */
    public static void reset() {
        currentTurn = 0;
    }
}
