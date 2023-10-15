package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{
	//for graph
//	@Override
//	public Node execute(Node root, String goal) {
//		// TODO Auto-generated method stub
//		Stack<Node> frontier=new Stack<>();
//		List<Node>explored=new LinkedList<>();
//		frontier.add(root);
//		while(!frontier.isEmpty()) {
//			Node current=frontier.pop();
//			if(current.getLabel().equals(goal)) return current;
//			else explored.add(current);
//			List<Node>children=current.getChildrenNodes();
//			for (Node child : children) {
//				if(!frontier.contains(child)&&!explored.contains(child)) {
//					frontier.add(child);
//					child.setParent(current);
//				}
//			}
//		}
//		return null;
//	}
	//for tree
	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier=new Stack<>();
		List<Node>explored=new LinkedList<>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current=frontier.pop();
			if(current.getLabel().equals(goal)) return current;
			else explored.add(current);
			List<Node>children=current.getChildrenNodes();
			for (Node child : children) {
				if(!frontier.contains(child)&&!explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
		}
		return null;
	}
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started=false;
		Stack<Node> frontier=new Stack<>();
		List<Node>explored=new ArrayList<>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current=frontier.pop();
			if(current.getLabel().equals(goal)) return current;
			else explored.add(current);
			List<Node>children=current.getChildrenNodes();
			for (Node child : children) {
				if(!frontier.contains(child)&&!explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
			if(current.getLabel().equals(start)&&started) return current;
			else {
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
		}
		return null;
	}

}
