/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour.AI;

import scoreFour.Board;
import scoreFour.Player;
import scoreFour.PlayerType;

import java.util.ArrayList;

/**
 * A class representing the AI player.
 */
public class AIPlayer extends Player {
	private board.BoardV2.Board mainBoard;
	private PlayerType startsFirst;

	private AIBranch firstBranch;

	private static final PlayerType aiPlayer = PLAYER_TWO; // Change if needed

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
		if (getCurrentTurn() != 0 && firstBranch == null) {
			firstBranch = new AIBranch(null, mainBoard, getCurrentTurn());
		}

		// So game has just started And AI goes first then start at the bottom corner to
		// Open up the most moves
		if ((startsFirst == aiPlayer) && getCurrentTurn() == 0) {
			mainBoard.placeBead(0, 0, aiPlayer);
		}
		else {
			// Get branch matching board played by player
			for (AIBranch branch : firstBranch.getSubBranches()) {
				if (branch.getBoard().equals(baseBoard)) {
					firstBranch = branch;
					break;
				}
			}

			// Find Branch starting from best move for AI
			int mostWins = 0;
			AIBranch bestBranch;
			for (AIBranch branch : firstBranch.getSubBranches()) {
				int currentNumWins = branch.getNumofWins();
				if (currentNumWins > mostWins) {
					mostWins = currentNumWins;
					bestBranch = branch;
				}
			}
			firstBranch = bestBranch;
			mainBoard = firstBranch.getBoard();
		}

		nextTurn();
	}
}
