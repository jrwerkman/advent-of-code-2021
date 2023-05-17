package nl.jrwer.challenge.advent.day18;

public class Part1Test {
	public static void main(String[] args) {
		try {
			Part1Test day = new Part1Test();
//			day.testMagnitude();
//			day.testExplode();
//			day.testSplit();
//			day.test();
//			day.test2();
//			day.testSet1();
//			day.testSet2();
//			day.testSet3();
//			day.testSet4();
			day.testSet5();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void testMagnitude() {
		testMagnitude("[9,1]");
		testMagnitude("[1,9]");
		testMagnitude("[[9,1],[1,9]]");
		
	}
	private void testMagnitude(String str) {
		System.out.println(str +" - " + new SnailFishNumber(str).magnitude());
	}
	
	public void testExplode() {
		SnailFishNumber sfn = null;
		sfn = new SnailFishNumber("[[[[[9,8],1],2],3],4]");
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
		System.out.println("   org            --> [[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
		sfn.reduce();
		System.out.println("   should be      --> [[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		System.out.println("   should be      --> [[3,[2,[8,0]]],[9,[5,[7,0]]]]");
		System.out.println("   reduce         --> " + sfn);
	}
	
	private void testSplit() {
		String result = "[[[[7,7],[7,8]],[[9,5],[8,0]]],[[[9,[5,5]],20],[8,[9,0]]]]";
		
		SnailFishNumber sfn = new SnailFishNumber("[[[[7,7],[7,8]],[[9,5],[8,0]]],[[[9,10],20],[8,[9,0]]]]");
		System.out.println("start:          [[[[7,7],[7,8]],[[9,5],[8,0]]],[[[9,10],20],[8,[9,0]]]]");
		System.out.println("in:             " + sfn);

		sfn.checkSplit(0);
		
		System.out.println("\nresult:         " + sfn);
		System.out.println("should be:      " + result);
		System.out.println("correct:        " + sfn.toString().equals(result));
	}
	
	private void test() {
		String result = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]";
		SnailFishNumber sfn = SnailFishNumber.addition(new SnailFishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]"), new SnailFishNumber("[1,1]"));
		sfn.reduce();
		System.out.println("should be:      " + result);
		System.out.println("correct:        " + sfn.toString().equals(result));
	}
	
	private void test2() {
		SnailFishNumber.log = true;
		String result = "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]";
		SnailFishNumber sfn = SnailFishNumber.addition(
				new SnailFishNumber("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"), 
				new SnailFishNumber("[7,[5,[[3,8],[1,4]]]"));
		sfn.reduce();
		System.out.println("should be:      " + result);
		System.out.println("correct:        " + sfn.toString().equals(result));
	}	
	
	private void testSet3() {
		testSet(new String[] {"[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]", "[6,6]"}, 
				"[[[[5,0],[7,4]],[5,5]],[6,6]]");
	}
	
	private void testSet4() {
		testSet(new String[] {
						"[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
						"[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
						"[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
						"[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
						"[7,[5,[[3,8],[1,4]]]]",
						"[[2,[2,2]],[8,[8,1]]]",
						"[2,9]",
						"[1,[[[9,3],9],[[9,0],[0,7]]]]",
						"[[[5,[7,4]],7],1]",
						"[[[[4,2],2],6],[8,7]]"
				}, "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]");
	}
	
	private void testSet5() {
		testSet(new String[] {
						"[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
						"[[[5,[2,8]],4],[5,[[9,9],0]]]",
						"[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
						"[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
						"[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
						"[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
						"[[[[5,4],[7,7]],8],[[8,3],8]]",
						"[[9,3],[[9,9],[6,[4,9]]]]",
						"[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
						"[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
				}, 
				"[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]",
				4140);
	}
	
	private void testSet(String[] sfns, String result) {
		testSet(sfns, result, -1);
	}
	
	private void testSet(String[] sfns, String result, int resultM) {
		SnailFishNumber.log = false;
		SnailFishNumber sfn = new SnailFishNumber(sfns[0]);
		
		for(int i=1; i<sfns.length; i++) {
			SnailFishNumber sfnAdd = new SnailFishNumber(sfns[i]);
			sfn = SnailFishNumber.addition(sfn, sfnAdd);
			sfn.reduce();
			System.out.println(sfn);
		}

		System.out.println("\nresult:         " + sfn);
		System.out.println("should be:      " + result);
		System.out.println("correct:        " + sfn.toString().equals(result));
		
		if(resultM > -1) {
			System.out.println("\nmagnitude:      " + sfn.magnitude());
			System.out.println("should be:      " + resultM);
		}
	}
}
