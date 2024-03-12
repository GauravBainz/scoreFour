import java.util.Scanner;
import java.util.Random;

public class Rounds {
    private int rounds;
    private int i;
    private boolean win = false;
    private boolean draw = false;
    private int randomNumber = getRandomNumber(); // Get a random number to find out which player will play 1st
    private Player p1; // Player 1
    private Player p2; // Player 2
    private board das; // board
    private Columns col; // Columns

    Scanner keyboard = new Scanner(System.in);

    public static int getRandomNumber() {
        Random randomNumber = new Random();
        return (randomNumber.nextInt(2));
    }

    public Rounds() {
        setRounds(0);
    }

    public void Gameplay() // Start game
    {
        while (true) {
            if (randomNumber == 0)// Player1
            {
                System.out.print(p1.getName() + ", your turn. Select column: ");
                i = keyboard.nextInt();
                while (i > col.getColumns() || i <= 0) // If the players select a non-existing column
                {
                    System.out.print("Nonexistent column select again: ");
                    i = keyboard.nextInt();
                }
                das.Gameboard(i, p1); // Add the new chip
                das.printboard(); // Print the new board
                win = das.checkForWin(); // Check if someone won
                draw = das.checkForDraw(); // Check for a draw
            } else// Player2
            {
                System.out.print(p2.getName() + ", your turn. Select column: ");
                i = keyboard.nextInt();
                while (i > col.getColumns() || i <= 0) // If the players select a non-existing column
                {
                    System.out.print("Nonexistent column select again: ");
                    i = keyboard.nextInt();
                }
                das.Gameboard(i, p2); // Add the new chip
                das.printboard(); // Print the new board
                win = das.checkForWin(); // Check if someone won
                if (!win) {
                    draw = das.checkForDraw(); // Check for a draw
                }
            }
            if (randomNumber == 0)
                randomNumber = 1; // Change Player
            else
                randomNumber = 0; // Change Player
            if (win == true) {
                break; // There Is A Win
            }
            if (draw == true) {
                break;// There Is A Draw
            }
        }
    }

    public void setMyPlayer1(Player c) {
        p1 = c; // Set player 1
    }

    public void setMyPlayer2(Player c) {
        p2 = c; // Set player 2
    }

    public void setDas(board das) {
        this.das = das; // Set the dashboard
    }

    public void setMyColumn(Columns c) {
        col = c; // Set the columns
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}