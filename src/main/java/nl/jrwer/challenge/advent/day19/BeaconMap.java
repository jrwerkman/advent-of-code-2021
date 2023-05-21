package nl.jrwer.challenge.advent.day19;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
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
	
	public void combine(List<Scanner> scanners) {
		Queue<BeaconMap> q = new ArrayDeque<>();
		for(Scanner s : scanners)
			q.add(s.getMap());

		while(!q.isEmpty()) {
			BeaconMap current = q.poll();

			// retry if nog match
			if(!this.combine(current))
				q.add(current);
		}
	}

	public boolean combine(BeaconMap otherBeaconMap) {
		for(Rotation r : Rotation.ROTATIONS) {
			Set<Beacon> rotated = r.rotate(otherBeaconMap.beacons);
			
			if(compare(otherBeaconMap, rotated))
				return true;
		}
		
		return false;
	}
	
	private boolean compare(BeaconMap otherBeaconMap, Set<Beacon> others) {
		for(Beacon current : beacons)
			for(Beacon other : others)
				if(compare(otherBeaconMap, current, other, others))
					return true;
		
		return false;
	}
	
	private boolean compare(BeaconMap otherBeaconMap, Beacon current, Beacon other, Set<Beacon> others) {
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
				otherBeaconMap.scanner.set(dX, dY, dZ);
			}
			
			return true;
		}

		return false;
	}
}
