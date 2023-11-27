package game_alphabeta_student;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int v=maxValue(node);
		System.out.println("values:"+v);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		// Enter your code here
		if(node.isTerminal()) return node.getValue();
		else {
			int v=Integer.MIN_VALUE;
			for (Node child : node.getChildren()) {
				v=Math.max(v, minValue(child));
			}
			node.setValue(v);
			System.out.println("Best move node: "+node.getLabel()+"is"+v);
			return v;
		}
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		// Enter your code here
		if(node.isTerminal()) return node.getValue();
		else {
			int v=Integer.MAX_VALUE;
			for (Node child : node.getChildren()) {
				v=Math.min(v, maxValue(child));
			}
			node.setValue(v);
			System.out.println("Best move node: "+node.getLabel()+"is"+v);
			return v;
		}
	}
}
