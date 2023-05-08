package nl.jrwer.challenge.advent.day18;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
//			day.start();
//			day.testExplode();
			day.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {

		long start = System.currentTimeMillis();

		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	public void testExplode() {
		SnailFishNumber sfn = new SnailFishNumber("[[[[[9,8],1],2],3],4]");
		sfn.reduce();
		System.out.println("   reduce         --> " + sfn);
		System.out.println("   should be      --> [[[[0,9],2],3],4]\n");

		sfn = new SnailFishNumber("[7,[6,[5,[4,[3,2]]]]]");
		sfn.reduce();
		System.out.println("   reduce         --> " + sfn);
		System.out.println("   should be      --> [7,[6,[5,[7,0]]]]\n");

		sfn = new SnailFishNumber("[[6,[5,[4,[3,2]]]],1]");
		sfn.reduce();
		System.out.println("   reduce         --> " + sfn);
		System.out.println("   should be      --> [[6,[5,[7,0]]],3]\n");

		sfn = new SnailFishNumber("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
		sfn.reduce();
		System.out.println("   reduce         --> " + sfn);
		System.out.println("   should be      --> [[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]\n");

		sfn = new SnailFishNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		sfn.reduce();
		System.out.println("   reduce         --> " + sfn);
		System.out.println("   should be      --> [[3,[2,[8,0]]],[9,[5,[7,0]]]]\n");
	}
	
	private void test() {
		SnailFishNumber sfn1 = new SnailFishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
		SnailFishNumber sfn2 = new SnailFishNumber("[1,1]");
		sfn1.add(sfn2);
		System.out.println("   after add      --> " + sfn1);
		sfn1.reduce();
		System.out.println("   reduce         --> " + sfn1);
		System.out.println("   should be      --> [[[[0,7],4],[[7,8],[6,0]]],[8,1]]\n");
		
	}
}
