package events;

public class FailureEvent extends NetworkEvent {
	public FailureEvent(int iteration,String node1, String node2) {
		super(iteration,node1, node2);
	}	
}
