package scoreFour;

class HumanPlayer {
	private Board mainBoard;
	HumanPlayer(Board mainBoard) {
		this.mainBoard = mainBoard;
	}

	public void playNextMove(int x, int y) {
		mainBoard.putBeadAt(x, y);
	}
}
