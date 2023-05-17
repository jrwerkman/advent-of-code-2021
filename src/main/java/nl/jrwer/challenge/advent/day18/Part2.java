package nl.jrwer.challenge.advent.day18;

import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
//		List<String> lines = List.of("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
//				"[[[5,[2,8]],4],[5,[[9,9],0]]]",
//				"[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
//				"[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
//				"[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
//				"[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
//				"[[[[5,4],[7,7]],8],[[8,3],8]]",
//				"[[9,3],[[9,9],[6,[4,9]]]]",
//				"[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
//				"[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]");

		List<String> lines = new InputLoader("input-day-18.txt").getInput();

		long start = System.currentTimeMillis();
		
		SnailFishOptimizer opt = new SnailFishOptimizer(lines);
		System.out.println(opt.bestMagnitude());

		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
