package nl.jrwer.challenge.advent.day21;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
//			day.test();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test() {
		GameBoard board = new GameBoard(new Player(1, 4), new Player(2, 8));
		long result = board.play();
		
		System.out.println("\nresult:    " + result);
		System.out.println("should be: 444356092776315");
		System.out.println("right:     " + (result == 444356092776315L));
		
	}
	
	public void start() {
		long start = System.currentTimeMillis();
		
		GameBoard board = new GameBoard(new Player(1, 7), new Player(2, 3));
		System.out.println(board.play());
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
