package nl.jrwer.challenge.advent.day16;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
//			day.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		Decoder d = new InputLoader("input-day-16.txt").getInput();

		long start = System.currentTimeMillis();
		d.decode();
		System.out.println(d.calculate());

		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	public void test() {
		String[] bitss = new String[] {
			"C200B40A82", // 3
			"04005AC33890", // 54
			"880086C3E88112",  // 7
			"CE00C43D881120", // 9
			"D8005AC2A8F0", // 1
			"F600BC2D8F", // 0
			"9C005AC2F8F0", // 0
			"9C0141080250320F1802104A08", // 1
		};
		
		for(String bits : bitss) {
			Decoder d = new Decoder(bits);
			d.decode();
			System.out.println(d.calculate());
		}
	}
}
