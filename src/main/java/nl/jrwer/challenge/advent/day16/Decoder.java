package nl.jrwer.challenge.advent.day16;

public class Decoder {
	final String sequence;
	
	int version; // first three bits
	int typeId;  // second three bits
	
	
	
	public Decoder(String sequence) {
		this.sequence = sequence;
	}
	
	public int sumVersionNumbers() {
		return 0;
	}
}
