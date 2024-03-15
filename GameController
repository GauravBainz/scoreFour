/*
@author Farzan Panjwani
Student ID: 230150371
*/


import java.util.Scanner;

public class GameController {
    private static final int BOARD_SIZE = 4;

    private enum PlayerType { HumanPlayer, AI }

    private enum GameState { RUNNING, WIN, TIE }

    private char[][][] board;
    private GameState gameState;
    private Scanner scanner;

    public GameController() {
        board = new char[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to ScoreFour!");
        initializeBoard();

        PlayerType currentPlayer = Math.random() < 0.5 ? PlayerType.HumanPlayer : PlayerType.AI;

        while (gameState == GameState.RUNNING) {
            displayBoard();
            makeMove(currentPlayer);

            if (checkWin()) {
                displayBoard();
                System.out.println(currentPlayer + " wins!");
                gameState = GameState.WIN;
            } else if (checkTie()) {
                System.out.println("It's a tie!");
                gameState = GameState.TIE;
            }

            currentPlayer = (currentPlayer == PlayerType.HumanPlayer) ? PlayerType.AI : PlayerType.HumanPlayer;
        }

        System.out.println("Thanks for playing ScoreFour!");
        scanner.close();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int k = 0; k < BOARD_SIZE; k++) {
                    board[i][j][k] = '-';
                }
            }
        }
        gameState = GameState.RUNNING;
    }

    private void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.println("Level " + (i + 1));
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int k = 0; k < BOARD_SIZE; k++) {
                    System.out.print(board[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void makeMove(PlayerType player) {
        if (player == PlayerType.HumanPlayer) {
            System.out.println("Enter your move (x y z): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            scanner.nextLine();
            if (isValidMove(x, y, z)) {
                board[x][y][z] = 'X';
            } else {
                System.out.println("Invalid move! Try again.");
                makeMove(PlayerType.HumanPlayer);
            }
        } else {
            int x, y, z;
            do {
                x = (int) (Math.random() * BOARD_SIZE);
                y = (int) (Math.random() * BOARD_SIZE);
                z = (int) (Math.random() * BOARD_SIZE);
            } while (!isValidMove(x, y, z));
            board[x][y][z] = 'O';
        }
    }

    private boolean isValidMove(int x, int y, int z) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && z >= 0 && z < BOARD_SIZE
                && board[x][y][z] == '-';
    }

    private boolean checkWin() {
        return false;
    }

    private boolean checkTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int k = 0; k < BOARD_SIZE; k++) {
                    if (board[i][j][k] == '-') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
