package nl.jrwer.challenge.advent.day06;

class LanternFish {
	public int daysTillBirth = 8;
	
	private LanternFish() {
		
	}
	
	public LanternFish(int dayTillBirth) {
		this.daysTillBirth = dayTillBirth;
	}
	
	public LanternFish nextDay() {
		daysTillBirth--;
		
		if(daysTillBirth < 0) {
			daysTillBirth = 6;
			return new LanternFish();
		}
		
		return null;
	}
}