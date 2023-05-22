package nl.jrwer.challenge.advent.day20;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Image>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected Image handleLines(List<String> lines) {
		char[] ea = lines.get(0).toCharArray();
		char[][] image = new char[lines.size()-1][];
		
		for(int i=1; i<lines.size(); i++) 
			image[i-1] = lines.get(i).toCharArray();
		
		return new Image(ea, image);
	}
}