package board;

public class Board {
    private Peg[][] board;
    private int size = 4;
    private boolean gameInProgress;
    private Validator validator;
  
    //constructor
    public Board(){
        this.board = new Peg[size][size];
        initializeBoard();
        validator = new Validator();
        gameInProgress = true;
        }

    public void initializeBoard(){
        for(int i = 0 ; i<size; i++){
            for(int j = 0; j<size; j++){
                board[i][j] = new Peg();
            }
        }
    }
    //checks vertical wins by using validator
    public boolean checkVerticalWin(){}

    //checks horizontal wins with validator
    public boolean checkHorizontalWin(){}

    //checks diagonal wins with validator
    public boolean checkDiagonalWin(){}

    //puts bead at specified location
    public void putBeadAt(int x, int y){}

    //returns all the empty pegs
    public Peg getEmptyPegs(){}

    //resets the board
    public void reset(){
        board = new Peg[size][size];
        initializeBoard();
    }
}
