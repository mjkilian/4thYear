package datasets;

import java.util.Collection;
import java.util.List;

import datagroups.Group;
import datapoints.DataPoint;

public interface DataSet {
	/**
	 * Adds a datapoint to the dataset belonging to class label.
	 * Returns true if the datapoint was added successfully.
	 * False if it could no be added.
	 * Note the point must have the same number of attributes as the
	 * dataset allows.
	 * @param label
	 * @param dp
	 * @return
	 */
	public boolean addDataPoint(String label, DataPoint dp);
	
	/**Gets the groups with the specified label.
	 * Returns null if no such group exists
	 * @param label
	 * @return
	 */
	public Group getGroup(String label);
	
	/**Gets all the groups int the dataset.
	 * 
	 * @return
	 */
	public Collection<Group> getAllGroups();
	
	/**
	 * Returns the total number of datapoints in the set
	 * @return
	 */
	public int noDataPoints();
	
	/**
	 * Returns the number of attributes of the datapoints in the set.
	 * @return
	 */
	public int noAttributes();
	
	/**
	 * Returns the names of all the classes (groups) in the dataset.
	 * @return
	 */
	public Collection<String> getLabels();

	/**
	 * Returns a collections of all the datapoints balanced out by class as much
	 * as possible.
	 * This can be used as the basis for forming validation folds.
	 * @return
	 */
	public List<DataPoint> balancedData();
	
}
