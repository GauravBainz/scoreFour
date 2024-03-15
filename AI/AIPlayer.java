/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package board.player;

import board.player.*;
import board.BoardV2.Board;
import board.BoardV2.Peg;
import board.player.AIBranch;
import board.interfaces.PlayerType;
import java.util.Random;

/**
 * A class representing the AI player.
 */
public class AIPlayer extends Player {
    private Board mainBoard;
    private PlayerType startsFirst;

    private AIBranch firstBranch;

    private static final PlayerType aiPlayer = PlayerType.PLAYER_TWO; // Change if needed

    /**
     * Creates a new AI player which will play on the provided board

     * @param mainBoard The board the AIPlayer will play on
     * @param startsFirst The player who starts first
     */
    public AIPlayer(Board mainBoard, PlayerType startsFirst) {
        this.startsFirst = startsFirst;
        this.mainBoard = mainBoard;
    }


    /**
     * Plays a move at a board position determined by the AI
     */
    public void playNextMove() {
        int derivedX, derivedY;
        Peg[][] contents = mainBoard.getBoard();

        // Generate Move tree if turn is not zero and the first AIBranch is null

        // So game has just started And AI goes first then start at the bottom corner to
        // Open up the most moves
        package board.player;

import board.player.*;
import board.BoardV2.Board;
import board.BoardV2.Peg;
import board.player.AIBranch;
import board.interfaces.PlayerType;

import java.util.Random;

/**
 * A class representing the AI player.
 */
public class AIPlayer extends Player {
    private Board mainBoard;
    private PlayerType startsFirst;

    private AIBranch firstBranch;

    private static final PlayerType aiPlayer = PlayerType.PLAYER_TWO; // Change if needed

    /**
     * Creates a new AI player which will play on the provided board

     * @param mainBoard The board the AIPlayer will play on
     * @param startsFirst The player who starts first
     */
    public AIPlayer(Board mainBoard, PlayerType startsFirst) {
        this.startsFirst = startsFirst;
        this.mainBoard = mainBoard;
    }


    /**
     * Plays a move at a board position determined by the AI
     */
    public void playNextMove() {
        int derivedX, derivedY;
        Peg[][] contents = mainBoard.getBoard();

        // Generate Move tree if turn is not zero and the first AIBranch is null

        // So game has just started And AI goes first then start at the bottom corner to
        // Open up the most moves
        if ((startsFirst == aiPlayer) && getCurrentTurn() == 0) {
            derivedX = derivedY = 0;
        } else {
            Random random = new Random();
            // Keep generating random coordinates until finding an empty peg
            do {
                derivedX = random.nextInt(4);
                derivedY = random.nextInt(4);
            } while (contents[derivedX][derivedY].height() != 0); // Check if the peg is empty

        }
        // x and y start at 0, 1, 2...
        mainBoard.placeBead(derivedX, derivedY, aiPlayer);
        nextTurn();
    }
}

        }
        // x and y start at 0, 1, 2...
        mainBoard.placeBead(derivedX, derivedY, aiPlayer);
        nextTurn();
    }
}

