package org.op4j.contrib.executables.functions.conversion;
 
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class ABean {
	private String stringProperty;
	private int intProperty;
	private Integer integerProperty;
	private ArrayList<Long> longCollection = new ArrayList<Long>();
	private ArrayList<Object> objectCollection = null;
	private Calendar calendar;	
	
	public ABean() {
		super();
		
	}
	public ABean(String stringProperty, int intProperty,
			Integer integerProperty, ArrayList<Long> longCollection,
			ArrayList<Object> objectCollection,
			Calendar calendar) {
		super();
		this.stringProperty = stringProperty;
		this.intProperty = intProperty;
		this.integerProperty = integerProperty;
		this.longCollection = longCollection;
		this.objectCollection = objectCollection;
		this.calendar = calendar;
	}
	public String getStringProperty() {
		return this.stringProperty;
	}
	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}
	public int getIntProperty() {
		return this.intProperty;
	}
	public void setIntProperty(int intProperty) {
		this.intProperty = intProperty;
	}
	public Integer getIntegerProperty() {
		return this.integerProperty;
	}
	public void setIntegerProperty(Integer integerProperty) {
		this.integerProperty = integerProperty;
	}
	public ArrayList<Long> getLongCollection() {
		return this.longCollection;
	}
	public void setLongCollection(ArrayList<Long> longCollection) {
		this.longCollection = longCollection;
	}
	public Calendar getCalendar() {
		return this.calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	public ArrayList<Object> getObjectCollection() {
		return this.objectCollection;
	}
	public void setObjectCollection(ArrayList<Object> objectCollection) {
		this.objectCollection = objectCollection;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("stringProperty",
				this.stringProperty).append("intProperty", this.intProperty).append(
				"integerProperty", this.integerProperty).append("longCollection",
						this.longCollection).append("objectCollection", this.objectCollection)
				.append("calendar", this.calendar).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ABean))
			return false;
		ABean castOther = (ABean) other;
		return new EqualsBuilder().append(this.stringProperty,
				castOther.stringProperty).append(this.intProperty,
				castOther.intProperty).append(this.integerProperty,
				castOther.integerProperty).append(this.longCollection,
				castOther.longCollection).append(this.objectCollection,
				castOther.objectCollection)
				.append(this.calendar, castOther.calendar).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.stringProperty).append(this.intProperty)
				.append(this.integerProperty).append(this.longCollection).append(
						this.objectCollection).append(this.calendar).toHashCode();
	}	
}
