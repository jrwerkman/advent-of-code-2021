package nl.jrwer.challenge.advent.day17;

public class XSteps {
	final int x, steps, target;
	
	public XSteps(int x, int steps, int target) {
		this.x = x;
		this.steps = steps;
		this.target = target;
	}
	
	public int getX() {
		return x;
	}
	
	@Override
	public int hashCode() {
		return x << 16 | target << 8 | steps;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof XSteps) {
			XSteps xSteps = (XSteps) o;
			
			return xSteps.x == x && xSteps.steps == steps && xSteps.target == target;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x+" -> (steps: "+steps+", target: "+target+")";
	}
}
