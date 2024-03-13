/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

/**
 * A class representing A Node in a AIBranch object
 */
public class AINode {
	private Board board;
	public AINode(Board prev, int nextMoveX, int  nextMoveY) {
		board = prev;
		board.placeBead(nextMoveX, nextmoveY, PLAYER_TWO);
	}
}
