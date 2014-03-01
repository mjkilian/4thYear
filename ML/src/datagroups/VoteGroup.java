package datagroups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datapoints.Politician;

public class VoteGroup extends AbstractGroup{
	/**For each vote, holds the probability that a member of this party voted
	 * for or against the motion. I.e. each collection holds p(V=1|m) and p(V=-1|m).
	 * p(V=0|m) can be inferred.
	 */
	private List<List<Double>> pGivenM;

	public VoteGroup(int noAttributes) {
		super(noAttributes);	
		pGivenM = new ArrayList<List<Double>>();
		
		//balance out the dataset
		//add one mp who voted all 1s
		//one mp who vote all -1
		//one mp who voted all 0
		int[] data;
		for(int i = -1; i < 2; i++){
			data = new int[this.noAttributes()];
			Arrays.fill(data, i);
			Politician d = new Politician(data,"");
			this.addDatapoint(d);
		}
		

	}

	/**
	 * Builds the probability table for this group.
	 * Must be done after all datapoints have been added.
	 */
	public void buildProbabilityTable(){
		//for each vote
		double total = (double) this.size(); //total mps in group
		for(int i = 0; i < this.noAttributes(); i++){
			List<Double> vprob = new ArrayList<Double>();
			double totalFor = 0; //total for this motion
			double totalAgainst = 0; //total against this motion

			//for each mp
			for(int j =0; j < total; j++){
				int vote = this.getAttrValue(j, i);
				if(vote == 1)
					totalFor++;
				else if(vote == -1)
					totalAgainst++;
			}

			/*The probability that an mp in this party is for the motion is
			 * the total number for the motion over the total number of mps in 
			 * the party.
			 * Likewise for against.
			 */
			double probFor = totalFor / total;
			double probAgainst = totalAgainst / total;

			vprob.add(probFor);
			vprob.add(probAgainst);
			pGivenM.add(vprob);
		}
	}
	
	/**Returns the probability that a party member voted v on vote voteNo
	 * Before this is called, a probability table for this group must be instantiated*/
	public double pGivenM(int voteNo, int v){
		if(v > 1 || v < -1)
			return 0.0;

		if(v == 1) //p(V=1|M)
			return pGivenM.get(voteNo).get(0);
		else if(v == -1)//p(V=-1|M)
			return pGivenM.get(voteNo).get(1);
		else //p(V=0|M) = 1 - (p(V=1|M) + p(V=-1|M))
			return 1- (pGivenM.get(voteNo).get(0) + pGivenM.get(voteNo).get(1));
	}

	/**Prints a human readable view of the probability table
	 * Again this means the probability table for this group must be generated first.
	 *
	 */
	public void printProbTable(){
		System.out.print("VOTE: \t");
		for(int i=0; i < pGivenM.size(); i++){
			System.out.print(i + " ");
		}
		System.out.println();

		System.out.print("p(V=1|M) ");
		for(int i=0; i < pGivenM.size(); i++){
			System.out.print(this.pGivenM(i, 1) + " ");
		}
		System.out.println();

		System.out.print("p(V=-1|M) ");
		for(int i=0; i < pGivenM.size(); i++){
			System.out.print(this.pGivenM(i, -1) + " ");
		}
		System.out.println();

		System.out.print("p(V=0|M) ");
		for(int i=0; i < pGivenM.size(); i++){
			System.out.print(this.pGivenM(i,0) + " ");
		}
		System.out.println();
	}

	


}
