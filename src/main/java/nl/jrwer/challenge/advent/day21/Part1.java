package nl.jrwer.challenge.advent.day21;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		long start = System.currentTimeMillis();
		
		GameBoard board = new GameBoard();
		// test players
//		board.addPlayer(new Player(1, 4));
//		board.addPlayer(new Player(2, 8));
		// real player
		board.addPlayer(new Player(1, 7));
		board.addPlayer(new Player(2, 3));
		System.out.println(board.practiceGame());
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
