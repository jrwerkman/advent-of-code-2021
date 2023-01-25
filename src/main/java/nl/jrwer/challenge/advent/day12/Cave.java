package nl.jrwer.challenge.advent.day12;

import java.util.Objects;

public class Cave {
    final String name;
    final boolean smallCave;
    
    public Cave(String name) {
        this.name = name;
        this.smallCave = isSmallCave(name);
    }
    
    private boolean isSmallCave(String name){
        char[] charArray = name.toCharArray();
        
        for(int i=0; i<charArray.length; i++)
            if(Character.isUpperCase(charArray[i]))
                return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Cave) {
            Cave p = (Cave) o;
            
            return name.equals(p.name);
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }    
}
