public class Board extends Peg {
    final int numRows = 4;
    final int numColumns = 4;
    private Piece[][] board;

    public Board() {
        // Initialize the board
        board = new Piece[numRows][numColumns];
    }

    public void placePiece(int x, int y, Piece player) {
        // Place a piece on the board
        if (isValidPosition(x, y)) {
            board[x][y] = player;
        }
    }

    private boolean isValidPosition(int x, int y) {
        // Check if the position is within the board boundaries
        return x >= 0 && x < numColumns && y >= 0 && y < numRows;
    }

    private boolean checkConsecutivePieces(int startX, int startY, int xDirection, int yDirection, Piece player) {
        for (int i = 0; i < 4; ++i) {
            int x = startX + (xDirection * i);
            int y = startY + (yDirection * i);

            if (x < 0 || x > numColumns - 1) {
                return false;
            }

            if (y < 0 || y > numRows - 1) {
                return false;
            }

            if (player != getPieceAt(x, y)) {
                return false;
            }
        }

        return true;
    }


    Piece getPieceAt(int x, int y) {
        // Get the piece at a specific position on the board
        if (isValidPosition(x, y)) {
            return board[x][y];
        }
        return null;
    }

    public boolean checkWin(int x, int y, Piece player) {
        // Peg is Full

        // Vertical line
        if (checkConsecutivePieces(x, y, 0, 1, player)) {
            return true;
        }

        for (int offset = 0; offset < 4; ++offset) {
            // Horizontal line
            if (checkConsecutivePieces(x - 3 + offset, y, 1, 0, player)) {
                return true;
            }

            // Leading diagonal
            if (checkConsecutivePieces(x - 3 + offset, y + 3 - offset, 1, -1, player)) {
                return true;
            }

            // Trailing diagonal
            if (checkConsecutivePieces(x - 3 + offset, y - 3 + offset, 1, 1, player)) {
                return true;
            }
        }

        // Check if the board is full
        if (isFull()) {
            if (numberOfBlack() == 4 || numberOfWhite() == 4) {
                return true;
            }
        }

        return false;
    }

}
