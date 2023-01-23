package nl.jrwer.challenge.advent.day03;
class Data {
	boolean[] data;
	
	public Data(String report) {
		data = new boolean[report.length()];
		
		for(int i = 0; i<report.length(); i++)
			data[i] = report.charAt(i) == '1';
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(boolean b : data)
			sb.append(b ? '1' : '0');
		
		return sb.toString();
	}
	
	public Integer toInteger() {
		return Integer.parseInt(toString(), 2);
	}
}