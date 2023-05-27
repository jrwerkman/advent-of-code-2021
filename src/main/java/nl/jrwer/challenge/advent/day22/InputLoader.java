package nl.jrwer.challenge.advent.day22;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<RebootStep>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected RebootStep handleLine(String line) {
		boolean on = false;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0, z1 = 0, z2 = 0;
		
		if(line.startsWith("on")) {
			on = true;
			line = line.substring(3);
		} else {
			line = line.substring(4);
		}

		String[] coords = line.replaceAll("[zyx=]", "").split(",");

		String[] x = coords[0].split("\\.\\.");
		String[] y = coords[1].split("\\.\\.");
		String[] z = coords[2].split("\\.\\.");

		x1 = Integer.parseInt(x[0]);
		x2 = Integer.parseInt(x[1]);
		y1 = Integer.parseInt(y[0]);
		y2 = Integer.parseInt(y[1]);
		z1 = Integer.parseInt(z[0]);
		z2 = Integer.parseInt(z[1]);
		
		RebootStep r = new RebootStep(on, 
				new Cuboid(
						new Coord(x1, y1, z1), 
						new Coord(x2, y2, z2)));
		
		return r;
	}
}