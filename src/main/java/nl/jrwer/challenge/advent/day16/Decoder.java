package nl.jrwer.challenge.advent.day16;

public class Decoder {
	final String hexSequence;
	final String binarySequence;
	
	int version; // first three bits
	int typeId;  // second three bits
	
	
	
	public Decoder(String hexSequence) {
		this.hexSequence = hexSequence;
		this.binarySequence = convert();
	}
	
	private String convert() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<hexSequence.length(); i++) {
			int digit = Integer.parseInt(hexSequence.substring(i, i+1), 16);
			String binary = Integer.toBinaryString(digit);
			
			sb.append(padLeftZeros(binary, 4));
		}
		
		return sb.toString();
	}
	
	public String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('0');
	    }
	    sb.append(inputString);

	    return sb.toString();
	}
	
	public int sumVersionNumbers() {
		return 0;
	}
}
