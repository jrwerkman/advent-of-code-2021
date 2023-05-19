package nl.jrwer.challenge.advent.day19;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<List<Scanner>>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected List<Scanner> handleLines(List<String> lines) {
		List<Scanner> scanners = new ArrayList<>();
		Scanner currentScanner = null;
		
		for(String line : lines) {
			if(line.contains("scanner")) {
				int id = Integer.parseInt(line.replace("--- scanner ", "").replace(" ---", ""));
				currentScanner = new Scanner(id);
				scanners.add(currentScanner);
			} else {
				String[] strCoords = line.split(",");
				
				currentScanner.addBeacon(new Beacon(
						Integer.parseInt(strCoords[0]),
						Integer.parseInt(strCoords[1]),
						Integer.parseInt(strCoords[2])));
			}
		}
		
		return scanners;
	}
}