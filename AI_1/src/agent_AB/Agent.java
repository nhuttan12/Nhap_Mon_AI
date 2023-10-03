package agent_AB;

public class Agent {
	private AgentProgram program;
	
	public Agent() {
	}

	public Agent(AgentProgram aProgram) {
		program = aProgram;
	}

	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}
	public String number_to_string() {
		if (program != null) {
			return program.number_to_string();
		}
		return null;
	}
}
