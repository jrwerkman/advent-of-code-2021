package nl.jrwer.challenge.advent.day08;

class CompleteDataEntry extends DataEntry {
	
	private Decoder decoder;
	
	public CompleteDataEntry(String line) {
		super(line);
		
		decoder = new Decoder(uniqueSignalPattern);
	}

	public int getCode() {
		return decoder.decode(digitOutputValue);
	}
	
	
}