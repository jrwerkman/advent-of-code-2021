package nl.jrwer.challenge.advent.day12;

import java.util.ArrayList;
import java.util.List;

public class Path {
    Cave currentCave;
    List<Cave> visistedCaves = new ArrayList<>();

    public Path(Cave currentCave) {
        this.currentCave = currentCave;
        this.visistedCaves.add(currentCave);
    }
    
    public Path(Cave currentCave,  List<Cave> visistedCaves) {
        this.currentCave = currentCave;
        this.visistedCaves.addAll(visistedCaves);
        this.visistedCaves.add(currentCave);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for(Cave c : visistedCaves)
            sb.append(c).append(',');
        
        return sb.toString();
    }
    
    
}
