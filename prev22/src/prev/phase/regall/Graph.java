package prev.phase.regall;

import prev.data.mem.MemTemp;

import java.util.*;

public class Graph {
    public HashMap<MemTemp, Node> graph;

    public Graph () {
        graph = new HashMap<>();
    }

    public Node getNode(MemTemp memTemp) {
        return graph.get(memTemp);
    }

    public void addNode(Node node) {
        graph.put(node.memTemp, node);
    }

    public void removeNode(MemTemp memTemp) {
        Node n = graph.get(memTemp);
        if (n == null)
            return;

        graph.remove(memTemp);

        // remove node from all the neighbors
        for (MemTemp neighbor : n.neighbors) {
            Node neigh = graph.get(neighbor);
            neigh.removeNeighbor(memTemp);
        }
    }

    public boolean contains(MemTemp memTemp) {
        return graph.containsKey(memTemp);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (MemTemp tmp : graph.keySet()) {
            Node node = graph.get(tmp);
            str.append(String.format("%s: ", tmp));
            for (MemTemp neighbor : node.neighbors) {
                str.append(String.format("%s, ", neighbor));
            }
            str.append("\n");
        }

        return str.toString();
    }
}
