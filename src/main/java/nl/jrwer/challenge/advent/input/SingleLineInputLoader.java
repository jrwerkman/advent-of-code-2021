package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.jrwer.challenge.advent.day04.Part1;

public abstract class SingleLineInputLoader<T> {
	
	private final String file;
	
	public SingleLineInputLoader(String file) {
		this.file = file;
	}
	
	public T getInput() {
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	return handleLine(line);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	protected abstract T handleLine(String line);
}
