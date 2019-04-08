package org.pace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PD_MAX_DETAILS")
public class Max {
	
	@Id
	@Column(name="MD_ID")
	private int id;
	
	@Column(name="MD_VALUE")
	private int maxValue;
	
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getMaxId() {
		return id;
	}
	
	public void setMaxValue(int maxValue) {
		this.maxValue=maxValue;
	}
	
	public int getMaxValue() {
		return maxValue;
	}
	

}
