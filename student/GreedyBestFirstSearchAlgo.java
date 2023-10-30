package student;

import java.util.*;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByGn());
        ArrayList<Node> explored = new ArrayList<>();
        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) return current;
            else {
                explored.add(current);
                List<Edge> children = current.getChildren();
                for (Edge child : children) {
                    Node n = child.getEnd();
                    if (!explored.contains(n) && !frontier.contains(n)) {
                        frontier.add(n);
                        n.setParent(current);
                        n.setG(current.getG() + child.getWeight());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        boolean started = false;
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByGn());
        ArrayList<Node> explored = new ArrayList<>();
        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) return current;
            else {
                explored.add(current);
                List<Edge> children = current.getChildren();
                for (Edge edge : children) {
                    Node child=edge.getEnd();
                    if (!frontier.contains(child) && !explored.contains(child)) {
                        frontier.add(child);
                        child.setParent(current);
                        child.setG(current.getG() + edge.getWeight());
                    }
                }
                if (current.getLabel().equals(start) && started) return current;
                else {
                    frontier.clear();
                    explored.clear();
                    current.setParent(null);
                }
            }
        }
        return null;
    }
}
