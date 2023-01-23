package nl.jrwer.challenge.advent.day06;

class MegaSchool {
	long[] school = new long[9];

	public void addFish(String input) {
		System.out.println(input);
		String[] split = input.split(",");

		for (String strDay : split) {
			int day = Integer.parseInt(strDay);

			school[day]++;
		}
	}

	public long breed(int days) {
		for (int i = 0; i < days; i++) {
			long nextFish = 0;
			long currentFish = school[school.length - 1];

			for (int j = (school.length - 1); j >= 0; j--) {

				if (j == 0) {
					school[school.length - 1] = currentFish;
					school[school.length - 3] += currentFish;
				} else {
					nextFish = school[j - 1];
					school[j - 1] = currentFish;
					currentFish = nextFish;
				}
			}

//				System.out.println(getSchoolSize());
		}

		return getSchoolSize();
	}

	public long getSchoolSize() {
		long size = 0;

		for (long fish : school)
			size += fish;

		return size;
	}
}