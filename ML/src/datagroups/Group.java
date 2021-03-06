package datagroups;

import java.util.List;

import datapoints.DataPoint;

/**
 * Interface for a Group (a.k.a. class) of data
 * All datapoints in the group must have the same number of attributes
 * @author michael
 *
 */
public interface Group {
	/**
	 * Add a datapoint to the group.
	 * Returns true if the point was added successfully
	 * False if the datapoint could not be added. This may occur because it
	 * has the wrong number of attributes.
	 * @param d
	 * @return
	 */
	public boolean addDatapoint(DataPoint d);
	
	/**Returns the number of attributes each datapoint in the group has*/
	public int noAttributes();
	
	/**
	 * Returns the number of points in the group.
	 * @return
	 */
	public int size();
	
	/**
	 * Gets a datapoint by index.
	 * @param index
	 * @return
	 */
	public DataPoint getDatapoint(int index);
	
	/**
	 * Gets the value of the given attribute for the given datapoint.
	 * @param datapointIndex
	 * @param attrIndex
	 * @return
	 */
	public int getAttrValue(int datapointIndex, int attrIndex);
	
	/**
	 * Returns a list of all the datapoints.
	 * @return
	 */
	public List<DataPoint> allDataPoints();
	
	/**Clears the group of datapoints*/
	public void emptyGroup();
}
