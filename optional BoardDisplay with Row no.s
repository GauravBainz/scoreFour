**/ This is a modified BoardDisplay adapted from Gaurav's
I / Ursula just added a few lines to have row headers.
Optional on whether or not to implement in the game.
**/

public class BoardDisplay {
    public static void displayBoard(Peg[][] board){
    	System.out.println();
        for (int i = 0; i < 4; i++) {
        	System.out.println("----------------------------------");

        	 for (int x = 0; x < 1; x++) { 
        		 System.out.println("	    Row " + i);
        	
        	 }
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
                    System.out.print("Empty	");
                }
                // Separate each peg with a pipe character
                System.out.print("| ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
