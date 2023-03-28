package nl.jrwer.challenge.advent.day16;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
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
		System.out.println(d.getVersionSum());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	public void test() {
		System.out.println(new Decoder("8A004A801A8002F478").decode().getVersionSum());
		System.out.println(new Decoder("620080001611562C8802118E34").decode().getVersionSum());
		System.out.println(new Decoder("C0015000016115A2E0802F182340").decode().getVersionSum());
		System.out.println(new Decoder("A0016C880162017C3686B18A3D4780").decode().getVersionSum());
	}
}
