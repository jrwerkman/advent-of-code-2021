package nl.jrwer.challenge.advent.day14;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Polymerization>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected Polymerization handleLines(List<String> lines) {
		List<PairInsertionRule> rules = new ArrayList<>();
		PolymerTemplate template = null;

		for(String l : lines)
			if(l.contains(" -> ")) {
				char[] chars = l.replaceAll(" -> ", "").toCharArray();
				rules.add(new PairInsertionRule(
						chars[0], 
						chars[1], 
						chars[2]));
			} else {
				template = new PolymerTemplate(l);
			}
		
		return new Polymerization(rules, template);
	}
}