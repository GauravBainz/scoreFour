/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

import scoreFour.Peg;
import scoreFour.Board;
import java.util.ArrayList;
import scoreFour.PlayerType;

/**
 * A class representing a Branch of potential moves from a board
 */
public class AIBranch {
	private boolean isTerminating;
	private Board baseBoard;
	private ArrayList<AIBranch> subBranches;

	/**
	 * Creates a new AIBranch object
	 */
	public AIBranch(PlayerType aiPlayer, Board board) {
		this.baseBoard = board;
		if (baseBoard.checkWin(aiPlayer)) {
			isTerminating = true;
			subBranches = null;
		}
		else {
			isTerminating = false;
			subBranches = new ArrayList<AIBranch>();
			
			// Fill branch with potential moves
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Board potentialMove = new Board(baseBoard.getBoard());
					boolean isValid = potentialMove.placeBead(i, j, aiPlayer);
					if (isValid) {
						AIBranch branch = new AIBranch(aiPlayer, potentialMove);
						subBranches.add(branch);
					}
					else {
						// Do nothing
						continue;
					}
				}
			}
		}
	}

	/**
	 * Adds a potential move to the branch
	 *
	 * @param nextMove The potential next move
	 */
	public void addBranch(AIBranch nextMove) {
		if (!isTerminating) {
			branches.add(nextMove);
		}
	}
	
	/**
	 * Returns the number of wins on this Branch
	 */
	public int getNumOfWins() {
		int wins = 0;
		if (!isTerminating) {
			for (AIBranch branch : subBranches)
				wins += branch.getnumOfWins();
		}
		else {
			wins = 1;
		}
		return wins;
	}

	/**
	 * If this method returns true then there are nio subbranches of this branch
	 * @return A boolean saying if the branch terminates or not
	 */
	public boolean terminates() {
		return isTerminating;
	}
}
