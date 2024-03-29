package nl.jrwer.challenge.advent.day10;

import java.util.LinkedList;

public class Line {
	private final String line;
	private final Symbol[] symbols; 
	private int index = 0;
	
	public Line(String line) {
		this.line = line;
		this.symbols = convert(line);
	}
	
	private Symbol[] convert(String line) {
		Symbol[] symbols = new Symbol[line.length()];
		char[] chars = line.toCharArray();
		
		for(int i=0; i< line.length(); i++)
			symbols[i] = Symbol.get(chars[i]); 
		
		return symbols;
	}
	
	private Symbol next() {
		Symbol s = symbols[index];
		index++;
		
		return s;
	}
	
	private boolean hasNext() {
		return index < symbols.length;
	}
	
	
	
	/**
	 * If returns 0, than is not corrupted
	 * 
	 * @return
	 */
	public int isCorrupted() {
		index = 0;
		LinkedList<Symbol> open = new LinkedList<>();
		
		while(hasNext()) {
			Symbol next = next();

			if(!next.isOpen()) {
				Symbol last = open.removeLast();
				
				if(last.type() != next.type())
					return next.corruptedPoints();
			} else {
				open.add(next);
			}
		}

		return 0;
	}
	
	public long repair() {
		index = 0;
		LinkedList<Symbol> open = new LinkedList<>();
		while(hasNext()) {
			Symbol next = next();

			if(!next.isOpen())
				open.removeLast();
			else
				open.add(next);
		}
		
		long score = 0;
		for(int i=open.size()-1; i>=0; i--) {
			score = (score * 5) + open.get(i).autoCompletePoints();
		}
		
		return score;
	}
	
	public boolean isIncomplete() {
		int open = 0, close = 0;
		
		for(Symbol s : symbols)
			if(s.isOpen())
				open++;
			else
				close++;

		return open != close;
	}
	
	@Override
	public String toString() {
		return line;
	}
}
