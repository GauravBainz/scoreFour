/**
 * This file is part of a solution to the CSPC 101 Group project.
 * 
 * @author Logan White
 * Student Number: 230161077
 * @version 1
 */
package scoreFour.AI;

import scoreFour.Peg;
import scoreFour.Board;
import java.util.ArrayList;
import scoreFour.PlayerType;

/**
 * A class representing a Branch of potential moves from a board
 */
public class AIBranch {
	private boolean isWinning;
	private boolean isTerminating;
	private Board baseBoard;
	private AIBranch previous;
	private ArrayList<AIBranch> subBranches;

	private static final PlayerType humanPlayerType = PLAYER_ONE;
	private static final PlayerType aiPlayerType = PLAYER_TWO;

	/**
	 * Creates a new AIBranch object assuming turn starts on AI's turn
	 */
	public AIBranch(AIBranch previous, Board board, int simulatedTurn) {
		this.baseBoard = board;
		this.previous = previous;
		// Won for AI on provided board
		if (baseBoard.checkWin(aiPlayerType)) {
			isTerminating = true;
			isWinning = true;
			subBranches = null;
		}
		// Won for human on provided board
		else if (baseBoard.checkWin(humanPlayerType)) {
			isTerminating = true;
			isWinning = false;
			subBranches = null;
		}
		// Neither won on provided board
		else {
			isTerminating = false;
			isWinning = false;
			subBranches = new ArrayList<AIBranch>();

			PlayerType currentPlayerType;
			// If turn is even set the branch's player to the AIPlayer and the human player if it is not
			if (simulatedTurn % 2 == 0)
				currentPlayersType = aiPlayerType;
			else
				currentPlayersType = humanPlayerType;

			
			// Fill branch with potential moves from both players
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Board potentialMove = new Board(baseBoard.getBoard());
					boolean isValid = potentialMove.placeBead(i, j, currentPlayerType);
					if (isValid) {
						AIBranch branch = new AIBranch(this, potentialMove, simulatedTurn+1);
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
	 * Returns the previous branch
	 *
	 * @return The previous branch
	 */
	public AIBranch getPreviousBranch() {
		return previous;
	}

	/**
	 * Returns the number of wins on this Branch
	 */
	public int getNumOfWins() {
		int wins = 0;
		if (!isWinning && !isTerminating) {
			for (AIBranch branch : subBranches)
				wins += branch.getnumOfWins();
		}
		else if (isWinning && isTerminating) {
			wins = 1;
		}
		// if the branch is not winning and it is terminating then the human won and the number of
		// wins is zero
		return wins;
	}

	/**
	 * Returns a ArrayList of sub branches
	 *
	 * @return An ArrayList of sub branches
	 */
	ArrayList<AIBranch> getSubBranches() {
		return subBranches;
	}

	/**
	 * Returns the board contained by the AIBranch
	 *
	 * @return The board contained by the object
	 */
	Board getBoard() {
		return baseBoard;
	}
    
	/**
	 * If this method returns true then there are nio subbranches of this branch
	 * @return A boolean saying if the branch terminates or not
	 */
	public boolean terminates() {
		return isTerminating;
	}
}
