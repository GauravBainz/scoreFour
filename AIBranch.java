/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour;

import java.util.ArrayList;

/**
 * A class representing a Branch of potential moves
 */
public class AIBranch implements AIBranchObject {
	private ArrayList<Board> boards;
	private ArrayList<AIBranch> subBranches;
	/**
	 * Creates a new AIBranch object
	 */
	public AIBranch() {
		boards = new ArrayList<Board>();
		branches = new ArrayList<AIBranchObject>();
	}
	
	/**
	 * Returns the number of wins on this Branch
	 */
	public int getNumOfWins() {
		int boardWins, branchWins;
		for (Board board : boards) {
			if (board.checkWin())
				boardWins++;
		}
		
		for (AIBranch branch : subBranches)
			branchWins += branch.getnumOfWins();
		return boardWins + branchWins;
	}

	/**
	 * If this method returns true then there are nio subbranches of this branch
	 * @return A boolean saying if the branch terminates or not
	 */
	public boolean terminates() {
		if (subBranches.isEmpty())
			return true;
		else
			return false;
	}
}
