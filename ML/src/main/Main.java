package main;

import classifiers.VoteClassifier;
import datagroups.Group;
import datagroups.VoteGroup;
import datapoints.DataPoint;
import datasets.DivisionsDataSet;
import parsers.DivisionsParser;
import validators.DivisionsValidator;

public class Main {

	public static void main(String args[]){
		String filename = args[0];
		int noAttributes = Integer.parseInt(args[1]);
		
		DivisionsDataSet ds = DivisionsParser.buildFromFile(filename, noAttributes);

	
		DivisionsValidator vl = new DivisionsValidator(ds);
		System.out.println("Avg loo accuracy: " + vl.looValidation());
	
	}

}
