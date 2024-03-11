package board.BoardV2;

import board.interfaces.BeadColour;
import board.interfaces.PlayerType;

public class Board {
        private Peg[][] board;
        private final int size = 4;

        public Board() {
                board = new Peg[4][4];
                for(int i = 0; i < 4; i ++){
                        for(int j = 0; j< 4; j ++){
                                board[i][j] = new Peg();
                        }
                }
        }
        public void displayBoard(){
                System.out.println();

                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                Peg peg = board[i][j];
                                // Check if the peg is not empty
                                if (!peg.isEmpty()) {
                                        // Iterate through the peg's beads and print their colors
                                        for (int k = 0; k < peg.height(); k++) {
                                                System.out.print(peg.getColourAt(k) + " ");
                                        }
                                } else {
                                        // If the peg is empty, print "Empty"
                                        System.out.print("Empty ");
                                }
                                // Separate each peg with a pipe character
                                System.out.print("| ");
                        }
                        System.out.println();
                }
        }

    //places a bead at the desired coordinate
        public void placeBead(int row, int column, PlayerType player){
                BeadColour bead;
                if(player == PlayerType.PLAYER_ONE){
                        bead = BeadColour.WHITE;
                }
                else {
                        bead = BeadColour.BLACK;

                }
                if(coordinateCheck(row,column,bead)) {
                        Peg peg = board[row][column];
                        peg.addBead(bead);
                }
                else{
                        System.out.println("Invalid Coordinates");
                }
        }

    //checks if the coordinates given are valid coordinates
        public boolean coordinateCheck(int row, int column, BeadColour bead){
                if(row < 0 || row > 3 || column < 0 || column > 3){
                        return false;
                }
                return true;
        }

    //checks if their are 4 beads of the same colour on one peg
        public boolean checkVerticalWin(){
                for(int i = 0; i < 4; i++){
                        for(int j = 0; j<4 ; j++){
                                Peg peg = board[i][j];
                                if(peg.checkVerticalWin()){
                                        return true;
                                }
                        }
                }
                return false;
        }
    
    //check if there is a win horizonatally on the board for all 4 levels
        public boolean checkHorizontalWin() {
                // Iterate over each row
                for (int row = 0; row < size; row++) {
                        // Iterate over each level (height) of the peg
                        for (int level = 0; level < size; level++) {
                                // Iterate over each column, but stop at the 4th last column
                                for (int col = 0; col <= size - 4; col++) {
                                        // Get the color of the first bead in the sequence
                                        BeadColour color = board[row][col].getColourAt(level);
                                        if (color != null) {
                                                // Check if the next 3 beads in the sequence have the same color
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
    
    //checks if their is a win going up and down the board
        public boolean checkUpWin() {
                // Iterate through columns
                for (int col = 0; col < size; col++) {
                        // Iterate through levels, but stop at the 4th level from the top
                        for (int level = 0; level <= size - 4; level++) {
                                Peg firstPeg = board[level][col]; // Get the first peg in the column
                                if (firstPeg == null || firstPeg.isEmpty()) {
                                        continue; // If the first peg is empty or null, move to the next column
                                }

                                // Check the color of the first bead in the first peg
                                BeadColour color = firstPeg.getBeadAtLevel(0);

                                // Check the next 3 beads in the same column
                                boolean win = true;
                                for (int i = 1; i < 4; i++) {
                                        // Check if index is within bounds
                                        if (level + i >= size) {
                                                win = false;
                                                break;
                                        }

                                        Peg currentPeg = board[level + i][col];
                                        if (currentPeg == null || currentPeg.isEmpty() || currentPeg.getBeadAtLevel(0) != color) {
                                                win = false;
                                                break;
                                        }
                                }

                                // If all 4 beads are of the same color in the same column, it's a win
                                if (win) {
                                        return true;
                                }
                        }
                }
                return false; // No upward win found
        }


    //checks if their is a win diagonally on all 4 levels
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
        //checks if there is any win on the board
        public boolean checkWin(){
                if(checkVerticalWin() || checkHorizontalWin() || checkDiagonalWin() || checkUpWin()){
                        return true;
                }
                return false;
        }

        //returns the board array
        public Peg[][] getBoard(){
                return board;

        //will clear beads from the board
        public void clear(){
                for(int i = 0; i<4; i++){
                        for(int j = 0; j<4; j++){
                                Peg peg = board[i][j];
                                peg.clear();
                        }
                }
        }

        //public Peg getEmptyPegs(){}
        //public void reset(){}

}
