package datapoints;

import java.util.Arrays;

/**
 * Base implementation of a datapoint.
 * @author michael
 *
 */
public abstract class AbstractDataPoint implements DataPoint{
	private int[] attrs;
	private String label = "";

	public AbstractDataPoint(int[] attributes) {
		attrs = attributes;
	}

	@Override
	public int noAttributes() {
		return attrs.length;
	}

	@Override
	public int valueOfAttribute(int i) {
		return attrs[i];
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String toString(){
		String output = "DataPoint (" + this.noAttributes() +") in group " +
				this.label + "\n" 
				+ Arrays.toString(this.attrs);
		return output;
	}


}
