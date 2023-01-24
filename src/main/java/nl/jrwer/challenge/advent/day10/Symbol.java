package nl.jrwer.challenge.advent.day10;

public enum Symbol {
	OPEN_PARENTHESIS(Type.PARENTHESIS,'(', true, 3),
	CLOSE_PARENTHESIS(Type.PARENTHESIS,')', false, 3),
	OPEN_SQUARE_BRACKET(Type.SQUARE_BRACKET,'[', true, 57),
	CLOSE_SQUARE_BRACKET(Type.SQUARE_BRACKET,']', false, 57),
	OPEN_CURLY_BRACKET(Type.CURLY_BRACKET,'{', true, 1197),
	CLOSE_CURLY_BRACKET(Type.CURLY_BRACKET,'}', false, 1197),
	OPEN_LESS_THAN(Type.COMPARE,'<', true, 25137),
	CLOSE_GREATER_THAN(Type.COMPARE,'>', false, 25137);
	
	private final Type type;
	private final char symbol;
	private final boolean open;
	private final int points;
	
	private Symbol(Type type, char symbol, boolean open, int points) {
		this.type = type;
		this.symbol = symbol;
		this.open = open;
		this.points = points;
	}
	
	public char symbol() {
		return symbol;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int points() {
		return points;
	}
	
	public Type type() {
		return type;
	}
	
	public static Symbol get(char in) {
		switch(in) {
		case '(':
			return OPEN_PARENTHESIS;
		case ')':
			return CLOSE_PARENTHESIS;
		case '{':
			return OPEN_CURLY_BRACKET;
		case '}':
			return CLOSE_CURLY_BRACKET;
		case '[':
			return OPEN_SQUARE_BRACKET;
		case ']':
			return CLOSE_SQUARE_BRACKET;
		case '<':
			return OPEN_LESS_THAN;
		case '>':
			return CLOSE_GREATER_THAN;
		default:
			throw new RuntimeException();
		}
	}
}
