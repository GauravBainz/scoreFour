package board.BoardV2;

import board.interfaces.BeadColour;
import board.interfaces.PlayerType;

public class Board {
        private Peg[][] board;
        private final int size = 4;

        public Board(Peg[][] baseBoard) {
                board = baseBoard.clone();
        }


        public Board() {
                //iterating through and initializing each spot with a Peg
                board = new Peg[4][4];
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                board[i][j] = new Peg();
                        }
                }
        }

        public void displayBoard() {
                BoardDisplay.displayBoard(board);
        }

        // Modified to return false if a move is not vaild and true if it is valid
        public boolean placeBead(int row, int column, PlayerType player) {
                BeadColour bead;
                //if the player is Player one then it will place a white bead
                if (player == PlayerType.PLAYER_ONE) {
                        bead = BeadColour.WHITE;
                } else {
                        //if player two then will place black bead
                        bead = BeadColour.BLACK;

                }
                //if the coordinates are valid then it will place
                if (coordinateCheck(row, column, bead)) {
                        Peg peg = board[row][column];
                        if (peg.isFull())
                                return false;
                        peg.addBead(bead);
                }
                //if coordinates not valid it will not place the coordinates
                else {
                        System.out.println("Invalid Coordinates");
                }
                return true;
        }

        //checking if the coordinates used are within the bounds
        public boolean coordinateCheck(int row, int column, BeadColour bead) {
                if (row < 0 || row > 3 || column < 0 || column > 3) {
                        return false;
                }
                return true;
        }

        //checking if there are 4 beads of same colour on a peg
        public boolean checkVerticalWin() {
                //iterating through the array and using checkVerticalWin() from the peg class
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                Peg peg = board[i][j];
                                if (peg.checkVerticalWin()) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        //checking if there are 4 beads horizontally on the board, levels 1-4
        public boolean checkHorizontalWin() {
                // Iterating each row and level and column
                for (int row = 0; row < size; row++) {
                        for (int level = 0; level < size; level++) {
                                for (int col = 0; col <= size - 4; col++) {
                                        // Get the color of the first bead in the sequence
                                        BeadColour color = board[row][col].getColourAt(level);
                                        if (color != null) {
                                                // if next three beads are also same colour will be a win
                                                boolean win = true;
                                                for (int i = 1; i < 4; i++) {
                                                        if (board[row][col + i].getColourAt(level) != color) {
                                                                win = false;
                                                                break; // Exit loop early if consecutive colors don't match
                                                        }
                                                }
                                                if (win) {
                                                        return true; // Return true if a horizontal win is found
                                                }
                                        }
                                }
                        }
                }
                return false; // No horizontal win found
        }

        //checks if there is a win going up and down on the board
        public boolean checkUpWin() {
                //iterate through the array
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < size; j++) {
                                Peg peg = board[i][j];
                                //if it is not empty, continue
                                if (!peg.isEmpty()) {
                                        //iterating through each level of the peg
                                        for (int level = 0; level < peg.height(); level++) {
                                                //getting the bead colour at each level
                                                BeadColour color = peg.getColourAt(level);
                                                //if there are 4 in a row then there is a win
                                                if (i + 3 < size) {
                                                        boolean win = true;
                                                        for (int k = 1; k < 4; k++) {
                                                                Peg currentPeg = board[i + k][j];
                                                                //if the peg is empty or it is a different colour, then there is no win
                                                                if (currentPeg.isEmpty() || currentPeg.getColourAt(level) != color) {
                                                                        win = false;
                                                                        break;
                                                                }
                                                        }
                                                        //if win is true then return true
                                                        if (win) {
                                                                return true;
                                                        }
                                                }
                                        }
                                }
                        }
                }
                //if no win return false
                return false;
        }

        //returns the board array
        public Peg[][] getBoard() {
                return board;
        }

        //checks if there is a win going diagonally on all 4 levels
        public boolean checkDiagonalWin() {
                // Check main diagonals (from top-left to bottom-right)
                for (int row = 0; row <= size - 4; row++) {
                        for (int col = 0; col <= size - 4; col++) {
                                // Check each level of the peg
                                for (int level = 0; level < size; level++) {
                                        // Get the color of the first bead in the sequence
                                        BeadColour color = board[row][col].getColourAt(level);
                                        if (color != null) {
                                                // Check if the next 3 beads in the sequence have the same color
                                                boolean win = true;
                                                for (int i = 1; i < 4; i++) {
                                                        if (board[row + i][col + i].getColourAt(level) != color) {
                                                                win = false;
                                                                break; // Exit loop early if consecutive colors don't match
                                                        }
                                                }
                                                if (win) {
                                                        return true; // Return true if a diagonal win is found
                                                }
                                        }
                                }
                        }
                }

                // Check anti-diagonals (from top-right to bottom-left)
                for (int row = 0; row <= size - 4; row++) {
                        for (int col = 3; col < size; col++) {
                                // Check each level of the peg
                                for (int level = 0; level < size; level++) {
                                        // Get the color of the first bead in the sequence
                                        BeadColour color = board[row][col].getColourAt(level);
                                        if (color != null) {
                                                // Check if the next 3 beads in the sequence have the same color
                                                boolean win = true;
                                                for (int i = 1; i < 4; i++) {
                                                        if (board[row + i][col - i].getColourAt(level) != color) {
                                                                win = false;
                                                                break; // Exit loop early if consecutive colors don't match
                                                        }
                                                }
                                                if (win) {
                                                        return true; // Return true if a diagonal win is found
                                                }
                                        }
                                }
                        }
                }

                return false; // No diagonal win found
        }

        //checks if there is a win anywhere on the board
        public boolean checkWin() {
                if (checkVerticalWin() || checkHorizontalWin() || checkDiagonalWin() || checkUpWin()) {
                        return true;
                }
                return false;
        }

        //will clear the board of all beads
        public void clear() {
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                Peg peg = board[i][j];
                                peg.clear();
                        }
                }
        }
}
