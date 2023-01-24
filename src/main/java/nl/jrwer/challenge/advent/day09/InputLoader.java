package nl.jrwer.challenge.advent.day09;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<HeightMap>{

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected HeightMap handleLines(List<String> lines) {
		int height = lines.size();
		int width = lines.get(0).length();
		byte map[][] = new byte[height][width];
		
		for(int y=0; y<height; y++) {
			char[] chars = lines.get(y).toCharArray();
			
			for(int x=0; x<width; x++)
				map[y][x] = (byte) (chars[x] - 48); // '0' in char is 48
		}
		
		return new HeightMap(map);
	}


}