import classifiers.VoteClassifier;
import validators.DivisionsValidator;
import datagroups.Group;
import datapoints.DataPoint;
import datasets.DivisionsDataSet;


public class Main {

	public static void main(String[] args) {
		DivisionsDataSet data = new DivisionsDataSet();
			data.buildFromFile(args[0]);
	
		/*VoteClassifier cl = new VoteClassifier(data);
		Group lab = cl.getGroup("Lab");
		for(DataPoint d : lab.allDataPoints()){
			System.out.println(cl.classify(d));
		}*/
		
		DivisionsValidator validator = new DivisionsValidator(data);
		System.out.println("Average accuracy on 10 folds: " + validator.kFoldValidation(10));
		
	
		
	
	}

}
