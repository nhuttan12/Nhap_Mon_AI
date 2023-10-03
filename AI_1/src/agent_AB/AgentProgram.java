package agent_AB;

public class AgentProgram {
	int random_num;
	
	public Action execute(Percept p) {// location, status
		//TODO
//		if(p.getLocationState().equals(Environment.LocationState.DIRTY)){
//			return Environment.SUCK_DIRT;
//		}else if(p.getAgentLocation().equals(Environment.LOCATION_A)) {
//			return Environment.MOVE_RIGHT;
//		}else if(p.getAgentLocation().equals(Environment.LOCATION_B)) {
//			return Environment.MOVE_DOWN;
//		}else if(p.getAgentLocation().equals(Environment.LOCATION_C)) {
//			return Environment.MOVE_LEFT;
//		}else if(p.getAgentLocation().equals(Environment.LOCATION_D)) {
//			return Environment.MOVE_UP;
//		}
//		else 
		
		//1. left
		//2. right
		//3. up
		//4. down
		if(p.getLocationState().equals(Environment.LocationState.DIRTY)){
			return Environment.SUCK_DIRT;
		}
		random_num=randomize_number();
		switch (random_num) {
		case 1 ->{
			if(p.getAgentLocation().equals(Environment.LOCATION_A)
					||p.getAgentLocation().equals(Environment.LOCATION_C)){
				return Environment.STAY_IN;
			}else return Environment.MOVE_LEFT;
		}
		case 2 ->{
			if(p.getAgentLocation().equals(Environment.LOCATION_B)
					||p.getAgentLocation().equals(Environment.LOCATION_D)) {
				return Environment.STAY_IN;
			}else return Environment.MOVE_RIGHT;
		}
		case 3 ->{
			if(p.getAgentLocation().equals(Environment.LOCATION_A)
					||p.getAgentLocation().equals(Environment.LOCATION_B)) {
				return Environment.STAY_IN;
			}else return Environment.MOVE_UP;
		}
		case 4 ->{
			if(p.getAgentLocation().equals(Environment.LOCATION_C)
					||p.getAgentLocation().equals(Environment.LOCATION_D)) {
				return Environment.STAY_IN;
			}else return Environment.MOVE_DOWN;
		}
		}
		return NoOpAction.NO_OP;
	}
	public int randomize_number() {
		random_num=(int) (Math.abs(Math.random()*(4-1+1))+1);
		return random_num;
	}
	public String number_to_string() {
		//1. left
		//2. right
		//3. up
		//4. down
		int num=randomize_number();
		switch(num) {
		case 1 ->{
			return "LEFT";
		}
		case 2 ->{
			return "RIGHT";
		}
		case 3 ->{
			return "UP";
		}
		case 4 ->{
			return "DOWN";
		}
		}
		return null;
	}
}