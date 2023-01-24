package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.jrwer.challenge.advent.day04.Part1;

public abstract class SingleObjectsInputLoader<T> {
	
	private final String file;
	
	public SingleObjectsInputLoader(String file) {
		this.file = file;
	}
	
	public T getInput() {
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	handleLine(line);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return createObject();
	}
	
	protected abstract void handleLine(String line);
	protected abstract T createObject();
}
