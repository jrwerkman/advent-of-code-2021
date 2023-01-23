package nl.jrwer.challenge.advent.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Part1() {
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
}
