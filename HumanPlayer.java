/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

/**
 * A class representing the human player.
 */
class HumanPlayer {
	private Board mainBoard;
	/**
	 * Creates a new human player which will play on the provided board
	 *
	 * @param mainBoard The board the HumanPlayer will play on
	 */
	HumanPlayer(Board mainBoard) {
		this.mainBoard = mainBoard;
	}

	/**
	 * Plays a move at boardPosition (x, y)
	 *
	 * @param x The x-pos of the move
	 * @param y The y-pos of the move
	 */
	public void playNextMove(int x, int y) {
		// x and y start at 0, 1, 2...
		mainBoard.placeBead(x, y, PLAYER_ONE);
	}
}
