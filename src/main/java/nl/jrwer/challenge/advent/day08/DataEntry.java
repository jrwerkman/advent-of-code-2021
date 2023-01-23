package nl.jrwer.challenge.advent.day08;
class DataEntry {
	String[] uniqueSignalPattern;
	String[] digitOutputValue;
	
	public DataEntry(String line) {
		String[] split = line.split("\\|");
		
		if(split.length != 2)
			throw new RuntimeException("Wrong input: " + line);
		
		uniqueSignalPattern = split[0].trim().split(" ");
		digitOutputValue = split[1].trim().split(" ");
		
		if(uniqueSignalPattern.length != 10)
			throw new RuntimeException("Wrong signal pattern: " + line);

		if(digitOutputValue.length != 4)
			throw new RuntimeException("Wrong digit output: " + line);
	}
}