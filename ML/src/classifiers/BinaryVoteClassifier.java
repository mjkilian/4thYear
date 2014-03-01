package classifiers;

import datagroups.VoteGroup;
import datapoints.DataPoint;

public class BinaryVoteClassifier {
	/**Name of the party*/
	private String partyName;
	
	/**Prior probability that an mp belongs to this party p(M=m)*/
	private double priorOfParty; 

	/**Group of MPs belonging to this party*/
	private VoteGroup data;

	/**Reference to prior probabilities for vote outcomes in each division p(v)*/
	private VoteGroup priorVotes;
	
	public BinaryVoteClassifier(String partyName, double priorOfParty, VoteGroup data,
			VoteGroup priorVotes) {
		super();
		this.partyName = partyName;
		this.priorOfParty = priorOfParty;
		
		this.data = data;
		data.buildProbabilityTable(); //build probability tables for this group
		this.priorVotes = priorVotes;
		
		
	}
	
	
	
	/**Gives the probability that this input MP belongs to this party, 
	 * based on their votes.
	 * @param m
	 * @return
	 */
	public double likelihoodM(DataPoint m){
		//calculate the product of the independent probabilities of 
		//each vote given the party
		double MGivenV= 0 ;
		for(int i = 0; i < m.noAttributes(); i++){
			double pVGivenM = data.pGivenM(i, m.valueOfAttribute(i));
			double pV =  priorVotes.pGivenM(i, m.valueOfAttribute(i));
			MGivenV += Math.log(( pVGivenM * this.priorOfParty) / pV);
				
		}
		//System.out.println("likelihood belong to " + this.partyName +": " + vGivenM);
		return MGivenV ;
	}



	public String getPartyName() {
		return partyName;
	}



	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}



	public double getPriorOfParty() {
		return priorOfParty;
	}



	public void setPriorOfParty(double priorOfParty) {
		this.priorOfParty = priorOfParty;
	}



	public VoteGroup getData() {
		return data;
	}



	public void setData(VoteGroup data) {
		this.data = data;
	}
	
	

}
