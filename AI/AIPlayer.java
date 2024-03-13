/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

import scoreFour.Board;

/**
 * A class representing the AI player.
 */
public class AIPlayer {
	private Board mainBoard;
	/**
	 * Creates a new AI player which will play on the provided board
	 
	 * @param mainBoard The board the AIPlayer will play on
	 */
	public AIPlayer(Board mainBoard) {
		this.mainBoard = mainBoard;
	}


	/**
	 * Plays a move at a board position determined by the AI
	 */
	public void playNextMove() {

		int derivedX = 0, derivedY = 0;
		Peg[][] contents = mainBoard.getBoard();

		// x and y start at 0, 1, 2...
		mainBoard.placeBead(x, y, PLAYER_TWO);
	}
}
