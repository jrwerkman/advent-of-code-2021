package nl.jrwer.challenge.advent.day14;

import java.util.ArrayList;
import java.util.List;

public class PolymerTemplate {
	final String originalTemplate;
	final List<Character> polymer = new ArrayList<>();
	
	public PolymerTemplate(String template) {
		this.originalTemplate = template;

		for(int i=0; i<template.length(); i++)
			polymer.add(template.charAt(i));
	}
	
	public int size() {
		return polymer.size();
	}
	
	public char get(int index) {
		return polymer.get(index);
	}
	
	public void add(int index, char c) {
		polymer.add(index, c);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Character c : polymer)
			sb.append(c);
		
		return sb.toString(); 
	}
}
