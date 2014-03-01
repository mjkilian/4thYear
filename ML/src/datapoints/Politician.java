package datapoints;

/**Represents an MP in the vote classification problem
 * Each attribute is either 1,-1 or 0 representing a vote. 1 is for the motion, 
 * -1 is against the motion and 0 is no vote/abstain.
 * @author michael
 *
 */
public class Politician extends AbstractDataPoint {
	private String name;
	
	public Politician(int[] attributes,String name) {
		super(attributes);
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	

}
