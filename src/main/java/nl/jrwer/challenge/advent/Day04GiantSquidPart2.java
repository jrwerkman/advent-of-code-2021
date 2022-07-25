package nl.jrwer.challenge.advent;

public class Day04GiantSquidPart2 extends Day04GiantSquid {

	/*

		--- Part Two ---
		
		On the other hand, it might be wise to try a different strategy: let the giant squid win.
		
		You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time counting its arms, the safe thing to do is to figure out which board will win last and choose that one. That way, no matter which boards it picks, it will win for sure.
		
		In the above example, the second board is the last to win, which happens after 13 is eventually called and its middle column is completely marked. If you were to keep playing until this point, the second board would have a sum of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.
		
		Figure out which board will win last. Once it wins, what would its final score be?

	 */
	
	public static void main(String[] args) {
		try {
			Day04GiantSquidPart2 day = new Day04GiantSquidPart2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Day04GiantSquidPart2() {
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
