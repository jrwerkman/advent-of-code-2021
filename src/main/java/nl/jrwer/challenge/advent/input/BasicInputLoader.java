package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.day04.Part1;

public abstract class BasicInputLoader<T> {
	
	private final String file;
	private List<T> list = new ArrayList<>();
	
	public BasicInputLoader(String file) {
		this.file = file;
	}
	
	public List<T> getInput() {
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	T obj = handleLine(line);
            	
            	if(obj != null)
            		list.add(obj);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	protected abstract T handleLine(String line);
	
	public List<T> getList() {
		return list;
	}
}
