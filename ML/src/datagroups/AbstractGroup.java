package datagroups;

import java.util.ArrayList;
import java.util.List;

import datapoints.DataPoint;

public abstract class AbstractGroup implements Group {
	/**The size of each datapoint vector (the number of attributes of each datapoint)*/
	private int noAttributes;
	/**The datapoints*/
	private List<DataPoint> data;
	
	public AbstractGroup(int noAttributes){
		this.noAttributes = noAttributes;
		data = new ArrayList<DataPoint>();
	}
	
	
	@Override
	public boolean addDatapoint(DataPoint d){
		if(d.noAttributes() != this.noAttributes){
			return false;
		}
		return data.add(d);
	}

	@Override
	public void emptyGroup() {
		data.clear();	
	}
	@Override
	public int noAttributes() {
		return noAttributes;
	}
	@Override
	public int size() {
		return data.size();
	}
	@Override
	public DataPoint getDatapoint(int index) {
		return data.get(index);
	}
	@Override
	public int getAttrValue(int datapointIndex, int attrIndex) {
		return  data.get(datapointIndex).valueOfAttribute(attrIndex);
	}
	@Override
	public List<DataPoint> allDataPoints() {
		return data;
	}
	
	
	
	
}
