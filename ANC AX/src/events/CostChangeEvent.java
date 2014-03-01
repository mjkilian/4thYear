package events;

public class CostChangeEvent extends NetworkEvent {
	private int newCost;
	
	public CostChangeEvent(int iteration, String node1, String node2, int newCost) {
		super(iteration, node1, node2);
		this.newCost = newCost;
	}

	public int getNewCost() {
		return newCost;
	}

	
}
