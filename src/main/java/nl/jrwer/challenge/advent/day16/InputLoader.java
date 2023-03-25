package nl.jrwer.challenge.advent.day16;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Decoder>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected Decoder handleLines(List<String> lines) {
		if(lines.size() != 1)
			throw new RuntimeException();

		return new Decoder(lines.get(0));
	}
}