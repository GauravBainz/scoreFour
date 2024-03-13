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
 * A class representing a Branch of potential moves
 */
public class AIBranch {
	private boolean isTerminating;
	private PlayerType aiPlayer;
	private ArrayList<Board> boards;
	private ArrayList<AIBranch> subBranches;
	/**
	 * Creates a new AIBranch object
	 */
	public AIBranch(PlayerType aiPlayer, boolean isTerminating) {
		this.aiPlayer = aiPlayer;
		this.isTerminating = isTerminating;
		boards = new ArrayList<Board>();
		branches = new ArrayList<AIBranch>();
	}

	/**
	 * Adds a potential move to the branch
	 * @param nextMove The potential next move
	 */
	public void addBranch(Board nextMove) {
		boards.add(nextMove);
		AIBranch nextMoveBranch = new AIBranch(aiPlayer);
		if (!nextMove.checkWin(aiPlayer)) {

		}
		branches.add(nextMoveBranch);
	}
	
	/**
	 * Returns the number of wins on this Branch
	 */
	public int getNumOfWins() {
		int boardWins, branchWins;
		for (Board board : boards) {
			if (board.checkWin(PLAYER_TWO))
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
		return isTerminating;
	}
}
