package nl.jrwer.challenge.advent.day13;

public enum Axis {
	Y, X;
	
	public static Axis get(char c) {
		if(c == 'y')
			return Y;
		
		if(c == 'x')
			return X;
		
		throw new RuntimeException();
	}
}
