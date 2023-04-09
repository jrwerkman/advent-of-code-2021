package nl.jrwer.challenge.advent.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VelocityCalculator {
	final TargetArea target;
	final Position start = new Position(0, 0);
	Set<Velocity> velocityCandidates = new HashSet<>();
	
	public VelocityCalculator(TargetArea target) {
		this.target = target;
	}
	
	/**
	 * Calculate the velocity with the highest Y
	 * @return
	 */
	public void calculateVelocity() {
		Set<XSteps> xVelocityCandidates = getXCandidates();
//		for(XSteps xs : xVelocityCandidates)
//			System.out.println(xs);
		
		this.velocityCandidates = getVelocityCandidates(xVelocityCandidates);
//		for(Velocity v : velocityCandidates)
//			System.out.println(v);
	}
	
	public int highestY() {
		int highestY = Integer.MIN_VALUE;
		
		for(Velocity v : velocityCandidates) {
			int velHeight = highestY(v);
			
			if(highestY < velHeight)
				highestY = velHeight;
		}
		
		return highestY;
	}
	
	private int highestY(Velocity v) {
		int yVel = v.y;
		int height = 0;
		
		while(yVel >= 0) {
			height += yVel;
			yVel --;
		}
		
		return height;
	}
	
	private Set<XSteps> getXCandidates() {
		Set<XSteps> candidates = new HashSet<>();

		for(int x = target.x1; x<=target.x2; x++)
			candidates.addAll(getXCandidates(x));
		
		return candidates;
	}
	
	private List<XSteps> getXCandidates(int xTarget) {
		List<XSteps> candidates = new ArrayList<>();
		
		for(int xMinVel=1; xMinVel<=xTarget; xMinVel++) {
			XSteps xStartVel = getStartXVelocity(xMinVel, xTarget);
			
			if(xStartVel != null)
				candidates.add(xStartVel);
		}
		
		return candidates;
	}
	
	private XSteps getStartXVelocity(int xMinVel, int xTarget) {
		int steps = 1;
		int xTotalVel = xMinVel;
		int xStartVel = xMinVel;
		
		while(xTotalVel < xTarget) {
			steps++;
			xMinVel--;
			xTotalVel += xMinVel;
			
			if(xMinVel == 0)
				break;
		}
		
		return xTotalVel == xTarget ? new XSteps(xStartVel, steps, xTarget) : null;
	}
	
	private Set<Velocity> getVelocityCandidates(Set<XSteps> xVelocityCandidates) {
		Set<Velocity> candidates = new HashSet<>();
		
		// get unique xVels
		Integer[] xVels = xVelocityCandidates.stream()
				.map(XSteps::getX)
				.distinct()
				.toArray(Integer[]::new);
		
		for(Integer xVel : xVels) 
			candidates.addAll(getVelocityCandidates(xVel));
		
		return candidates;
	}
	
	private Set<Velocity> getVelocityCandidates(Integer xVel) {
		Set<Velocity> candidates = new HashSet<>();
		
		for(int yVel=0; yVel<Math.abs(target.y1); yVel++) {
			Velocity vel = getVelocityCandidate(xVel, yVel);
			
			if(vel != null)
				candidates.add(vel);
		}
		
		return candidates;
	}
	
	private Velocity getVelocityCandidate(int orgXVel, int orgYVel) {
		int xVel = orgXVel;
		int yVel = orgYVel;
		boolean overshoot = false;
		int x = 0, y = 0;
		
		while(!overshoot) {
			x += xVel;
			y += yVel;
			
			if(xVel > 0)
				xVel--;
			
			yVel--;
			
			if(target.pastTarget(x) || target.belowTarget(y)) {
				overshoot = true;
			}
			
			if(target.inTarget(x, y))
				return new Velocity(orgXVel, orgYVel);
		}
		
		return null;
	}

}
