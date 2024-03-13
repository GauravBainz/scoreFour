package board.BoardV2;

public class BoardDisplay {
    public static void displayBoard(Peg[][] board){
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
}
