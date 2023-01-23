package nl.jrwer.challenge.advent.day07;

class Crabs {
	int[] crabPositions;
	int lowestPosition;
	int highestPosition;

	public void loadCrabs(String input) {
		String[] split = input.split(",");

		crabPositions = new int[split.length];

		for (int i = 0; i < split.length; i++) {
			int crabPosition = Integer.parseInt(split[i]);
			crabPositions[i] = crabPosition;

			if (i == 0) {
				lowestPosition = crabPosition;
				highestPosition = crabPosition;

				continue;
			}

			if (crabPosition < lowestPosition)
				lowestPosition = crabPosition;
			else if (crabPosition > highestPosition)
				highestPosition = crabPosition;
			// set highest abd lowest
		}
	}

	public int[] calculateCheapestPosition() {
		int cheapestPosition = 0;
		int cheapestFuel = Integer.MAX_VALUE;

		for (int i = lowestPosition; i < highestPosition; i++) {
			int newFuel = fuelUsageOnPosition(i);

			if (newFuel < cheapestFuel) {
				cheapestFuel = newFuel;
				cheapestPosition = i;
			}

//				System.out.println("position: " + i + " - fuel: " + newFuel);
		}

//			System.out.println("Cheapest position: " + cheapestPosition);
//			System.out.println("Fuel usage: " + fuel);

		return new int[] { cheapestPosition, cheapestFuel };
	}

	public int fuelUsageOnPosition(int targetPosition) {
		int fuel = 0;
		for (int crabPosition : crabPositions) {
			if (targetPosition < crabPosition)
				fuel += (crabPosition - targetPosition);
			else if (targetPosition > crabPosition)
				fuel += (targetPosition - crabPosition);
		}

		return fuel;
	}
}