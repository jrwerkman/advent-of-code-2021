package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.day04.Part1;

public abstract class AllLinesInputLoader<T> {
	
	private final String file;
	private List<String> lines = new ArrayList<>();
	
	public AllLinesInputLoader(String file) {
		this.file = file;
	}
	
	public T getInput() {
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	lines.add(line);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return handleLines(lines);
	}
	
	protected abstract T handleLines(List<String> lines);
}
