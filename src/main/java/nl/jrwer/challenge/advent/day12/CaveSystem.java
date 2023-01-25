package nl.jrwer.challenge.advent.day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CaveSystem {
    private final List<Tunnel> tunnels;
    private final Cave start = new Cave("start");
    private final Cave end = new Cave("end");
    
    public CaveSystem(List<Tunnel> paths) {
        this.tunnels = paths;
    }
    
    List<Path> paths = new ArrayList<>();
    
    public int numberOfPaths() {
        Queue<Path> q = new ArrayDeque<>();
        q.add(new Path(start));
        
        while(!q.isEmpty()) {
            Path p = q.poll();
            
            if(p.currentCave.equals(end)) {
                paths.add(p);
            } else {
                Set<Cave> next = getNextCaves(p.currentCave);
                
                for(Cave c : next)
                    if(!(c.smallCave && p.visistedCaves.contains(c)))
                        q.add(new Path(c, p.visistedCaves));
            }
        }
        
        return paths.size();
    }
    
    public Set<Cave> getNextCaves(Cave cave) {
        Set<Cave> caves = new HashSet<>();
        
        for(Tunnel t : tunnels)
            if(t.a.equals(cave))
                caves.add(t.b);
            else if (t.b.equals(cave))
                caves.add(t.a);
        
        return caves;
    }
    
    public void printRoutes() {
        for(Path p : paths)
            System.out.println(p);
    }
}
