package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day04GiantSquid {

	/*

		--- Day 4: Giant Squid ---
		
		You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.
		
		Maybe it wants to play bingo?
		
		Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)
		
		The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input). For example:
		
		7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
		
		22 13 17 11  0
		 8  2 23  4 24
		21  9 14 16  7
		 6 10  3 18  5
		 1 12 20 15 19
		
		 3 15  0  2 22
		 9 18 13 17  5
		19  8  7 25 23
		20 11 10 24  4
		14 21 16 12  6
		
		14 21 17 24  4
		10 16 15  9 19
		18  8 23 26 20
		22 11 13  6  5
		 2  0 12  3  7
		
		After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):
		
		22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
		 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
		21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
		 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
		 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
		
		After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:
		
		22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
		 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
		21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
		 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
		 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
		
		Finally, 24 is drawn:
		
		22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
		 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
		21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
		 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
		 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
		
		At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire top row is marked: 14 21 17 24 4).
		
		The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.
		
		To guarantee victory against the giant squid, figure out which board will win first. What will your final score be if you choose that board?

	 */
	
	public static void main(String[] args) {
		try {
			Day04GiantSquid day = new Day04GiantSquid();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Day04GiantSquid() {
		getInput();
	}
	
	protected BingoDraws numbers;
	protected List<Board> boards = new ArrayList<>();
	
	public void start() {
		Board winningBoard = null;
		
		while(numbers.hasNextDraw()) {
			int draw = numbers.nexDraw();

			for(Board board: boards)
				if(board.mark(draw)) 
					winningBoard = board;
			
			if(winningBoard != null)
				break;
		}

		System.out.println("Winning board: ");
		System.out.println(winningBoard.toString());
		System.out.println("Final score: " + winningBoard.getFinalScore());
		
	}

	public void getInput() {
		boolean firstLine = true;
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-04.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Board board = null;
            int rowNumber = 0;
            
            while ((line = reader.readLine()) != null) {
            	
            	if(firstLine) {
            		numbers = new BingoDraws(line);
            		firstLine = false;
            	} else {
                	if(line.isBlank()) {
                		board = new Board();
                		rowNumber = 0;
                	} else {
                		board.addRow(line, rowNumber);
                		rowNumber++;
                	}
                	
                	if(rowNumber == 5)
                		boards.add(board);
            	}
            	
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class BingoDraws {
		int index = 0;
		private int[] numbers;
		
		public BingoDraws(String numbersLine) {
			String[] strNumbers = numbersLine.split(",");
			
			numbers = new int[strNumbers.length];
			
			for(int i=0; i<strNumbers.length; i++)
				numbers[i] = Integer.parseInt(strNumbers[i]);
		}
		
		public boolean hasNextDraw() {
			return index < numbers.length;
		}
		
		public Integer nexDraw() {
			try {
				return numbers[index];
			} finally {
				index++;
			}
		}
	}
	
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
	
	class BoardNumber {
		int value;
		boolean marked = false;

		public BoardNumber(int value) {
			this.value = value;
		}
		
	}
}
