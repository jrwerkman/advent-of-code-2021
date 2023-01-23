package nl.jrwer.challenge.advent.day04;

class Board {
	private static final int BOARD_SIZE = 5;
	BoardNumber[] board = new BoardNumber[BOARD_SIZE * BOARD_SIZE];
	boolean hasWon = false;
	int lastDrawnNumber;
	
	
	public void addRow(String line, int rowNumber) {
		String[] row = line.split(" ");
		
		int i = 0;
		
		for(String number : row) {
			if(!number.isBlank()) {
				board[(rowNumber * BOARD_SIZE) + i] = new BoardNumber(
						Integer.parseInt(number.trim()));
				i++;
			}
		}
			
	}
	
	/**
	 * Returns true when won, return false when not won or already won
	 * 
	 * @param number
	 * @return
	 */
	public boolean mark(int number) {
		// return false, can only win once
		if(hasWon)
			return false;
		
		lastDrawnNumber = number;

		setMark(number);
		hasWon = calculateResult();
		
		return hasWon;
	}
	
	private void setMark(int number) {
		for(BoardNumber boardNumber : board)
			if(boardNumber.value == number) {
				boardNumber.marked = true;
				break;
			}
	}
	
	/**
	 * returns true when won
	 * 
	 * @return
	 */
	private boolean calculateResult() {
		int marked = 0;
		
		// horizontal mark check
		for(int i=0; i<BOARD_SIZE; i++) {
			marked = 0;
			
			for(int j=0; j<BOARD_SIZE; j++) {
				if(board[(i*BOARD_SIZE) + j].marked)
					marked++;

				if(marked == BOARD_SIZE)
					return true;
			}
			
		}
		
		// vertical mark check
		for(int i=0; i<BOARD_SIZE; i++) { // loop columns
			// reset marked
			marked = 0;
			
			for(int j=0; j<BOARD_SIZE; j++) { // loop items in column
				if(board[i + (j * BOARD_SIZE)].marked)
					marked++;
				
				if(marked == BOARD_SIZE)
					return true;
			}
		}
		
		return false;
	}
	
	public int getFinalScore() {
		if(!hasWon)
			return 0;
		
		int score = 0;
		
		for(BoardNumber boardNumber : board) {
			if(!boardNumber.marked)
				score += boardNumber.value;
		}
		
		return score * lastDrawnNumber;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<board.length; i++) {
			if(board[i].marked)
				sb.append("*");
			else 
				sb.append(" ");
			
			sb.append(board[i].value);
			
			sb.append(board[i].value < 10 ? "  " : " ");
			
			if((i+1) % 5 == 0)
				sb.append("\n");
		}
		
		return sb.toString();
	}
}