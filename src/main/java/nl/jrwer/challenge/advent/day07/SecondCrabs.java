package nl.jrwer.challenge.advent.day07;

class SecondCrabs extends Crabs {
	@Override
	public int fuelUsageOnPosition(int targetPosition) {
		int fuel = 0;
		for (int crabPosition : crabPositions) {
			int steps = 0;

			if (targetPosition < crabPosition)
				steps = crabPosition - targetPosition;
			else if (targetPosition > crabPosition)
				steps = targetPosition - crabPosition;

			fuel += getFuelConsumption(steps);
		}

		return fuel;
	}

	private int getFuelConsumption(float steps) {
		return (int) (steps * (0.5 + (steps * 0.5)));
	}
}