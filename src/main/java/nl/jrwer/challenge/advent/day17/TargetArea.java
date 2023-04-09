package nl.jrwer.challenge.advent.day17;

public class TargetArea {
	int x1, x2, y1, y2;
	
	public TargetArea(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public boolean inXBound(int x) {
		return x >= x1 && x <= x2;
	}
	
	public boolean pastTarget(int x) {
		return x > x2;
	}
	
	public boolean belowTarget(int y) {
		return y < y1; 
	}

	public boolean inTarget(int x, int y) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2 ;
	}
}
