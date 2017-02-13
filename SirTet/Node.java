import java.util.*;

public class Node {
    public State state;
    public ArrayList<Node> neighbours;
    public int number;
    
    public Node (State state, int number) {
        this.state = state;
        neighbours = new ArrayList<Node>();
        this.number = number;
    }
    
    public void addNeighbour (Node neighbour) {
        if (!neighbours.contains(neighbour)) {
            this.neighbours.add(neighbour);
        }
    }
    
    public String toString() {
    	return this.state.toString();
    }
    
    
}