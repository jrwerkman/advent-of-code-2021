package nl.jrwer.challenge.advent.day19;

import java.util.HashSet;
import java.util.Set;

public class Rotation {
	public static final Rotation[] ROTATIONS = new Rotation[] {
			new Rotation(new Change(false, 'x'), new Change(false, 'y'), new Change(false, 'z')),
			new Rotation(new Change(false, 'x'), new Change(true , 'z'), new Change(false, 'y')),
			new Rotation(new Change(true , 'z'), new Change(false, 'y'), new Change(false, 'x')),
			new Rotation(new Change(false, 'x'), new Change(true , 'y'), new Change(true , 'z')),
			new Rotation(new Change(true , 'y'), new Change(true , 'z'), new Change(false, 'x')),
			new Rotation(new Change(true , 'z'), new Change(true , 'x'), new Change(false, 'y')),
			new Rotation(new Change(true , 'x'), new Change(false, 'y'), new Change(true , 'z')),
			new Rotation(new Change(false, 'x'), new Change(false, 'z'), new Change(true , 'y')),
			new Rotation(new Change(false, 'z'), new Change(true , 'y'), new Change(false, 'x')),
			new Rotation(new Change(true , 'y'), new Change(true , 'x'), new Change(true , 'z')),
			new Rotation(new Change(true , 'x'), new Change(true , 'z'), new Change(true , 'y')),
			new Rotation(new Change(true , 'z'), new Change(true , 'y'), new Change(true , 'x')),
			new Rotation(new Change(true , 'x'), new Change(false, 'z'), new Change(false, 'y')),
			new Rotation(new Change(false, 'z'), new Change(false, 'y'), new Change(true , 'x')),
			new Rotation(new Change(false, 'y'), new Change(false, 'z'), new Change(false, 'x')),
			new Rotation(new Change(false, 'z'), new Change(true , 'x'), new Change(true , 'y')),
			new Rotation(new Change(true , 'x'), new Change(true , 'y'), new Change(false, 'z')),
			new Rotation(new Change(true , 'y'), new Change(false, 'z'), new Change(true , 'x')),
			new Rotation(new Change(false, 'y'), new Change(true , 'z'), new Change(true , 'x')),
			new Rotation(new Change(true , 'z'), new Change(false, 'x'), new Change(true , 'y')),
			new Rotation(new Change(false, 'z'), new Change(false, 'x'), new Change(false, 'y')),
			new Rotation(new Change(false, 'y'), new Change(true , 'x'), new Change(false, 'z')),
			new Rotation(new Change(true , 'y'), new Change(false, 'x'), new Change(false, 'z')),
			new Rotation(new Change(false, 'y'), new Change(false, 'x'), new Change(true , 'z')),
	};
	
	
	final Change n; 
	final Change m; 
	final Change p; 
	
	protected Rotation(Change x, Change y, Change z) {
		this.n = x;
		this.m = y;
		this.p = z;
	}
	
	public Set<Beacon> rotate(Set<Beacon> in) {
		Set<Beacon> out = new HashSet<>();
		
		for(Beacon i : in)
			out.add(rotate(i));
		
		return out;
	}
	
	public Beacon rotate(Beacon in) {
		int x = n.newValue(in);
		int y = m.newValue(in);
		int z = p.newValue(in);
		
		return new Beacon(x, y, z);
	}
	
	static class Change {
		final boolean negative;
		final char place;
		
		protected Change(boolean negative, char place) {
			this.negative = negative;
			this.place = place;
		}
		
		public int newValue(Beacon i) {
			if(place == 'x') {
				return negative ? i.x * -1 : i.x;
			} else if(place == 'y') {
				return negative ? i.y * -1 : i.y;
			} else if(place == 'z') {
				return negative ? i.z * -1 : i.z;
			}
			
			throw new RuntimeException("Unsupported character: " + place);
		}
	}
}
