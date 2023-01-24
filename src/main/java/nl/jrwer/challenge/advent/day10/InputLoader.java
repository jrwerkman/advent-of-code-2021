package nl.jrwer.challenge.advent.day10;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Line>{

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Line handleLine(String line) {
		return new Line(line);
	}


}