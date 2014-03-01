package datapoints;

/**
 * Interface for data points ( a vector of attributes, where each attribute has
 * and integer value)
 * @author michael
 *
 */
public interface DataPoint{
	/**The number of attributes this datapoint has
	 * 
	 * @return
	 */
	public int noAttributes();
	
	/**The value of attribute i.
	 * 
	 * @param i
	 * @return
	 */
	public int valueOfAttribute(int i);
	
	/**Gets the label for the class to which this datapoint belongs.
	 * Note the datapoint may be unlabelled, in which case the label is null.
	 * @return
	 */
	public String getLabel();
	
	/**Set the label for this data point
	 * 
	 * @param label
	 */
	public void setLabel(String label);
	
	
}
