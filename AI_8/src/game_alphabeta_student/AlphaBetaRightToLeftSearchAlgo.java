package game_alphabeta_student;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo{

	@Override
	public void execute(Node node) {
		// TODO Auto-generated method stub
		int v=maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("values:"+v);
	}
	public int maxValue(Node node, int alpha, int beta) {
		if(node.isTerminal()) return node.getValue();
		else {
			int v=Integer.MAX_VALUE;
			List<Node>successors=node.getChildren();
			List<Node>pruned=new ArrayList<>();
			pruned.addAll(successors);
			successors.sort(Node.LabelComparator);
			for (Node s : successors) {
				pruned.remove(s);
				v=Math.max(v, minValue(s, alpha, beta));
				if(v>=beta) {
					for (Node p: pruned) {
						print(p);
					}
					return v;
				}
				alpha=Math.max(alpha, v);
			}
			return v;
		}
	}
	public int minValue(Node node, int alpha, int beta) {
		if(node.isTerminal()) return node.getValue();
		else {
			int v=Integer.MAX_VALUE;
			List<Node>successors=node.getChildren();
			List<Node>pruned=new ArrayList<>();
			pruned.addAll(successors);
			successors.sort(Node.LabelComparator);
			for (Node s: successors) {
				pruned.remove(s);
				v=Math.min(v, maxValue(s, alpha, beta));
				if(v<=alpha) {
					for (Node p: pruned) {
						print(p);
					}
					return v;
				}
				beta=Math.min(beta, v);
			}
			return v;
		}
	}
	public void print(Node node) {
		if(node.isTerminal()) System.out.println(node.getLabel()+":"+node.getValue());
		else {
			System.out.println(node.getLabel());
			for (Node child: node.getChildren()) {
				if(child.isTerminal()) {
					System.out.println(child.getLabel()+":"+child.getValue());
					continue;
				}
				print(child);
			}
		}
	}
}
