package agent_AB;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final Action STAY_IN = new DynamicAction("STAY");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	
	private static int total_mark;
	
	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

//	public Environment(LocationState locAState, LocationState locBState) {
//		envState = new EnvironmentState(locAState, locBState);
//	}

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}
	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent=agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		if(action.equals(SUCK_DIRT)) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		}else if((action.equals(MOVE_LEFT) && envState.getAgentLocation() == Environment.LOCATION_B)
				||((action.equals(MOVE_UP)) && envState.getAgentLocation() == Environment.LOCATION_C)) {
			envState.setAgentLocation(LOCATION_A);
		}else if((action.equals(MOVE_RIGHT) && envState.getAgentLocation() == Environment.LOCATION_A)
				||(action.equals(MOVE_UP) && envState.getAgentLocation() == Environment.LOCATION_D)) {
			envState.setAgentLocation(LOCATION_B);
		}else if((action.equals(MOVE_DOWN) && envState.getAgentLocation() == Environment.LOCATION_A)
				||(action.equals(MOVE_LEFT) && envState.getAgentLocation() == Environment.LOCATION_D)) {
			envState.setAgentLocation(LOCATION_C);
		}else if((action.equals(MOVE_DOWN) && envState.getAgentLocation() == Environment.LOCATION_B)
				||(action.equals(MOVE_RIGHT) && envState.getAgentLocation() == Environment.LOCATION_C)) {
			envState.setAgentLocation(LOCATION_D);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		String current_location=envState.getAgentLocation();
		Environment.LocationState state=envState.getLocationState(current_location);
		return new Percept(current_location, state);
	}

	public void step() {
		envState.display();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);
		String agentLocation = this.envState.getAgentLocation();
		String forecast = agent.number_to_string();
//		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
		System.out.println("Agent Loc.: " + agentLocation + "\tForecast: " + forecast + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		System.out.println(count(anAction));
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}
	public int count(Action action) {
		if(action.equals(SUCK_DIRT)) {
			total_mark+=100;
		}else if(action.equals(STAY_IN)) {
			total_mark-=100;
		}else {
			total_mark-=10;
		}
		return total_mark;
	}
	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
