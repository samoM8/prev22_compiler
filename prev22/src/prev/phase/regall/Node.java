package prev.phase.regall;

import prev.data.mem.MemTemp;

import java.util.Collection;
import java.util.HashSet;

public class Node {
    public MemTemp memTemp;
    public HashSet<MemTemp> neighbors;
    public int color;

    public Node(MemTemp memTemp) {
        this.memTemp = memTemp;
        this.neighbors = new HashSet<>();
        this.color = -1;
    }

    public Node(MemTemp memTemp, HashSet<MemTemp> neighbors) {
        this.memTemp = memTemp;
        this.neighbors = neighbors;
        this.color = -1;
        this.removeNeighbor(memTemp);
    }

    public void addAllNeighbors(Collection<MemTemp> temps) {
        this.neighbors.addAll(temps);
        this.removeNeighbor(memTemp);
    }

    public void addNeighbor(MemTemp temp) {
        this.neighbors.add(temp);
        this.removeNeighbor(memTemp);
    }

    public void removeNeighbor(MemTemp temp) {
        this.neighbors.remove(temp);
    }
}
