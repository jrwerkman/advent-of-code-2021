package nl.jrwer.challenge.advent.day19;

import java.util.HashSet;
import java.util.Set;

public class BeaconMap {
	static final int SIZE = 2000;
	final Scanner scanner;
	final Set<Beacon> beacons = new HashSet<>();

	public BeaconMap(Scanner scanner) {
		this.scanner = scanner;
		
		for(Beacon b : scanner.beacons)
			beacons.add(b);
	}

	public boolean combine(BeaconMap other) {
		System.out.println("Comparing: " + scanner.id + " with: " + other.scanner.id);
		
		for(Rotation r : Rotation.ROTATIONS) {
			Set<Beacon> rotated = r.rotate(other.beacons);
			
			if(compare(rotated))
				return true;
		}
		
		return false;
	}
	
	private boolean compare(Set<Beacon> others) {
		for(Beacon current : beacons)
			for(Beacon other : others)
				if(compare(current, other, others))
					return true;
		
		return false;
	}
	
	private boolean compare(Beacon current, Beacon other, Set<Beacon> others) {
		int overlapping = 0;
		int dX = current.x - other.x;
		int dY = current.y - other.y;
		int dZ = current.z - other.z;
		
		for(Beacon b : others) {
			Beacon shifted = b.shift(dX, dY, dZ);
			
			if(this.beacons.contains(shifted))
				overlapping++;
		}
		
		// when 12 or more are overlapping, add all
		if(overlapping >= 12) {
			for(Beacon b : others) {
				Beacon shifted = b.shift(dX, dY, dZ);
				
				beacons.add(shifted);
			}
			
			return true;
		}

		return false;
	}
}
