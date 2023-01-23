package nl.jrwer.challenge.advent.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	short bits = 12;
	
	public void start() {
		DiagnosticReport report = new DiagnosticReport(12);
		List<Data> dataList = getInput();
		
		for(Data data : dataList) {
			report.add(data);
		}
		
		int gamma = report.getGammaRate();
		int epsilon = report.getEpsilonRate();
		
		System.out.println("Gamma rate       : " + gamma);
		System.out.println("Epsilon rate     : " + epsilon);
		System.out.println("Power consumption: " + (gamma * epsilon));
	}
	
	public List<Data> getInput() {
		List<Data> list = new ArrayList<>();
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-03.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null)
            	list.add(new Data(line));

        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
}
