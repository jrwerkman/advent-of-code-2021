package nl.jrwer.challenge.advent.day16;

public class Decoder {
	final String hexSequence;
	final String binarySequence;
	
	Packet packet;

	public Decoder(String hexSequence) {
		this.hexSequence = hexSequence;
		this.binarySequence = convert();
//		System.out.println(binarySequence);
	}
	
	public void decode() {
		this.packet = Packet.createPacket(binarySequence, 0);
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
	
	private String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length)
	        return inputString;

	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length())
	        sb.append('0');

	    sb.append(inputString);

	    return sb.toString();
	}
	
	public int getVersionSum() {
		return getVersionSum(packet, 0);
	}
	
	public int getVersionSum(Packet p, int sum) {
		sum += p.version;
		
		if(p instanceof OperatorPacket) {
			OperatorPacket op = (OperatorPacket) p;
			
			for(Packet sp : op.subpackets) 
				sum = getVersionSum(sp, sum);
		}
		
		return sum;
	}
}
