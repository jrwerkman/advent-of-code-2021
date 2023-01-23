package nl.jrwer.challenge.advent.day04;

public class Part2 extends Part1 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Part2() {
		super();
	}
	
	@Override
	public void start() {
		Board lastWinningBoard = null;
		
		while(numbers.hasNextDraw()) {
			int draw = numbers.nexDraw();
			System.out.print(draw + " ");
			
			for(Board board: boards)
				if(board.mark(draw)) 
					lastWinningBoard = board;
		}

		System.out.println(" ");
		System.out.println("Last winning board: ");
		System.out.println(lastWinningBoard.toString());
		System.out.println("Final score: " + lastWinningBoard.getFinalScore());
		
	}
}
