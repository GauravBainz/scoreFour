package board;
import board.interfaces.BeadColour;
import board.interfaces.Pegs;
import board.interfaces.PlayerType;


public class Board {
    private Peg[][] pegs;
    private final int size = 4;

    public Board(){
        initializeBoard();
    }
    public void initializeBoard(){
        //iterating through each peg and initializing it as a Peg
        for(int i = 0; i<size; i++){
            for(int j = 0; j <size; j++){
                pegs[i][j] = new Peg();
            }
        }
    }
    //checks vertical wins by using validator
    public boolean checkVerticalWin(){}

    //checks horizontal wins with validator
    public boolean checkHorizontalWin(){}

    //checks diagonal wins with validator
    public boolean checkDiagonalWin(){}

    //puts bead at specified location, takes in coordinates, and player type for colour
    public void putBeadAt(int row, int column, PlayerType player){
        if(coordinateCheck(row, column)) {
            Peg peg = pegs[row][column];
            if (!peg.isFull()) {
                BeadColour beadColour = (player == PlayerType.PLAYER_ONE) ? BeadColour.BLACK : BeadColour.WHITE;
                peg.addBead(beadColour);
            } else {
                System.out.println("Unable to place bead: Peg is full");
            }
        }
            else{
                System.out.println("Coordinates not valid");
            }
        }

     //checks if coordinates given are valid for the board
    public boolean coordinateCheck(int row, int column){
        if(row < 0 || column < 0 || row >3 || column > 3){
            return false;
        }
        else{
            return true;
        }
    }

    //returns all the empty pegs
    public Peg getEmptyPegs(){
        //iterates thorough board and if one is empty is will return it
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                Peg peg = pegs[i][j];
                if(peg.isEmpty());
                return peg;
            }
        }
        return null; //When no empty peg is found
    }

    //resets the board
    public void reset(){
        //iterating through array and then using clear() to clear each Peg
        for(int i = 0; i < size ; i++){
            for(int j = 0; j<size; j++){
                Peg peg = pegs[i][j];
                peg.clear();
            }
        }
    }
}

