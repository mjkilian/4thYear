package validators;



import java.util.ArrayList;
import java.util.List;

import classifiers.VoteClassifier;
import datapoints.DataPoint;
import datasets.DivisionsDataSet;

public class DivisionsValidator implements Validator {
	DivisionsDataSet mps;
	
	public DivisionsValidator(DivisionsDataSet d) {
		mps = d;
	}

	@Override
	public double kFoldValidation(int k) {
		double accuracy = 0.0;
		
		//get all the data (labels are associated with each data point)
		//note data is balanced for k-fold validation
		List<DataPoint> mpsAsList = mps.balancedData();
	
		//build k folds
		int sizeOfFold = mpsAsList.size() / k;
		for(int i = 0; i < k; i++){
			List<DataPoint> testSet = new ArrayList<DataPoint>();
			DivisionsDataSet trainingSet = new DivisionsDataSet(mps.noAttributes());
			
			//build test set
			for(int j = 0; j < sizeOfFold; j++){
				testSet.add(mpsAsList.get((sizeOfFold * i) + j));
			}
			
			//build training set
			for(int j = 0; j < mpsAsList.size(); j++){
				if(j>= (i * sizeOfFold) &&  j < ((i*sizeOfFold) + sizeOfFold)){
					continue;
				}
				trainingSet.addDataPoint(mpsAsList.get(j).getLabel(),
						mpsAsList.get(j));
			}
		
			//do classification
			double foldAccuracy = 0.0;
			VoteClassifier cl = new VoteClassifier(trainingSet);
			for(DataPoint p : testSet){
				String label = p.getLabel();
				if(cl.classify(p).equals(label))
					foldAccuracy++;
			}
			foldAccuracy /= (double) sizeOfFold;
			accuracy += foldAccuracy;
		}
		return accuracy / (double) k;
		
	}

	@Override
	public double looValidation() {
		return this.kFoldValidation(mps.noDataPoints());
	}

}
