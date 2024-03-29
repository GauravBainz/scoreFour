import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import ScoreIV.BeadColour;
import ScoreIV.Board;
import ScoreIV.Peg;
import ScoreIV.PlayerType;
/** 
 * This file is the ASCII version/terminal interface for playing ScoreFour.
 * It is part of the ScoreFour term project for CPSC101. 
 * Author: Ursula Holler-Busch
 **/

public class TextInterface extends Board {
	static Scanner s = new Scanner(System.in);
	static Board board = new Board();
	
	public static void startNewGame() {
			
		System.out.println("Would you like to start a new game of Score IV? Please enter Yes or No.");
			String start_response = s.nextLine(); 
					if  ((start_response.equals("Yes")) || (start_response.equals("yes"))) { 
							System.out.println("Okay, starting the game now!");
							System.out.println("Would you like to play the text version or the GUI version?");
							System.out.print("Please enter 'text' to play in the terminal, or 'GUI' to play with the GUI.");
							String mode_response = s.nextLine();
							if ((mode_response.equals("text"))||mode_response.equals("Text")) {
								board.displayBoard();
								System.out.println("The characters here represent the ScoreIV board of 16 pegs a top-down view.");
								score4Rules(); 
							}
							
							else if ((mode_response.equals("GUI"))||mode_response.equals("gui")) { 
								goGraphic();
							}
					}
					else if ((start_response.equals("No")) || (start_response.equals("no"))) { System.out.println("Okay :( Have a nice day!");  
								quitGame();	}
					else {
							System.out.println("Sorry, I don't know that prompt. Let's try again!");
							startNewGame();					
	}					
}	
	public static void score4Rules() { 
		
		System.out.println("Would you like to review the rules of the game? Enter Yes or No.");
		
		String rules_response = s.nextLine();

		if  ((rules_response.equals("Yes")) || (rules_response.equals("yes"))) { 
			
			System.out.println("The rules of the game are as follows:");
			System.out.println("There is a board before you which has an array of 16 pegs...");
			System.out.println("The goal of the game is to place as many beads of your colour in a row as possible!");
			System.out.println("You can place your bead on any peg that has less than four beads on it. Placing your bead over another player's bead will block them from completing a row.");
			System.out.println("When you place a bead overtop of another bead, the value will change to it's current colour.");
			System.out.println("If you place a bead on a peg that already holds four beads, you will receive an error message!"); 
			System.out.println("As the human player, you will start the game, and your bead colour will be WHITE.");
			System.out.println("Please note: The first position (intersection of Row 1, Column 1, is 0,0.");
				readyOrNot();
		}
		else if ((rules_response.equals("No")) || (rules_response.equals("no"))) { 	
			System.out.println("Okay, good luck!");
				readyOrNot();
		}
	else { 
			System.out.println("Sorry, I don't know that prompt. Can you try something else?");
				score4Rules();
}
	}
	
	public static void readyOrNot() {
		System.out.println("Ready to start?");
		String start_response = s.nextLine();
		if ((start_response.equals("yes")||start_response.equals("Yes"))) {
			humanTurn();
		}
			else if ((start_response.equals("no")||start_response.equals("No"))) {
				System.out.println("Please enter Start when ready.");
				String ready_response = s.nextLine();
				if ((ready_response.equals("Start"))||ready_response.equals("start")) {
					humanTurn(); }
			}
			else {
				System.out.println("Sorry, I don't know that prompt. Please try again with either yes or no!");
				readyOrNot();
			} 
	}
	
	public static void goGraphic() {
			ScoreIVGUI goGUI = new ScoreIVGUI();
			goGUI.setVisible(true);
	}
	
	public static void humanTurn() { 
		System.out.println("Your turn!");
		System.out.println("Where would you like to place a bead? Please enter the row number:");
			int play_row = s.nextInt();
		System.out.println("Please enter the column number:");
			int play_column = s.nextInt();
			
			System.out.println("Please confirm: place bead at coordinate " + play_row + " , " + play_column + ".");
			
			String str1 = s.nextLine(); //This is just here so that the next String confirm actually takes a value
			//will break if removed!

			String confirm = s.nextLine();
			if (confirm.equals("Yes") || confirm.equals("yes")) {
				if (coordinateCheck(play_column, play_row, BeadColour.WHITE) == true) 
				{ System.out.println("Your move is confirmed."); {
					board.placeBead(play_row, play_column, PlayerType.HUMAN);
					board.displayBoard(); }} 

				else if (coordinateCheck(play_column, play_row, BeadColour.WHITE) == false) { 
						System.out.println("Those coordinates aren't valid! Please try to place the bead somewhere else.");
						System.out.println("Remember, the first row is Row 0, and the fourth row is Row 3.");
						humanTurn();
					}
			}
					else if (coordinateCheck(play_column, play_row, BeadColour.WHITE) == true) { 
						if (confirm.equals("No")|| confirm.equals("no")) { 
							System.out.println("Okay, the move will be cancelled and you can try again.");
							humanTurn();
						}
					}
	
		if (checkWin(null) == true) {
				Winner();
			} 
			else if (checkWin(null)== false) {
				
			} 
		
			
			else if (confirm.equals("No")|| confirm.equals("no")) { System.out.println("Okay, let's retry."); 
				humanTurn(); //Restart the turn to try again. 	
				
			}
			else { System.out.println("Please try yes or no to confirm your move! Trying again.");
			humanTurn();
			
		}
				
		System.out.println("Would you like to continue?");
		String continue_yn = s.nextLine();
			if (continue_yn.equals("Yes")||continue_yn.equals("yes")) { 
				System.out.println("The AI's turn is next.");
				
			}
			else if (continue_yn.equals("No")||continue_yn.equals("no")) { 
				quitGame();
			}
			else { 
				System.out.println("Please respond yes or no. Trying again!");
				humanTurn();
			} 
			System.out.println("The AI is thinking about where to play...");
			aiTurn();
			
			if (checkWin(null) == true) {
				Winner();
			} 
			else if (checkWin(null)== false) {
				humanTurn();
			} 
	} 
	public static void aiTurn() { 
		AIPlayer.playNextMove(); 
		displayBoard();
		
	} 
	
	
	public static void Winner() { 
	System.out.println("You win!!!! Congratulations!");
	startNewGame();
	}
	public static void quitGame() { 
		System.exit(0);	
	}
	public static void main(String[] args) {  
		startNewGame();
			}}

