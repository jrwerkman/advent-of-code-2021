package nl.jrwer.challenge.advent.day18;

import java.util.List;

public class SnailFishOptimizer {
	public final List<String> input;
	
	public SnailFishOptimizer(List<String> input) {
		this.input = input;
	}
	
	private int best = 0;
	
	public int bestMagnitude() {
		for(String i1 : input) {
			for(String i2 : input) {
				int r = calcMagnitude(i2, i1);

				if(r > best)
					best = r;

				r = calcMagnitude(i1, i2);
				if(r > best)
					best = r;
			}
		}
		
		return best;
	}
	
	private int calcMagnitude(String left, String right) {
		SnailFishNumber sfn = SnailFishNumber.addition(
				new SnailFishNumber(left), 
				new SnailFishNumber(right));
		sfn.reduce();
		return sfn.magnitude();
	}
}
