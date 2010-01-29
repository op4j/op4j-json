package org.op4j.contrib.executables.functions.conversion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BBean 
	extends ABean {

	private transient String transientStringPropertyB;
	private int intPropertyB;
 	
	private transient Collection<Long> transientLongCollectionB = new ArrayList<Long>();
	
	private Calendar calendarB;
	
	private transient Calendar transientCalendarB;


	public BBean() {
		super();
		
	}

	public BBean(String stringProperty, int intProperty,
			Integer integerProperty, ArrayList<Long> longCollection,
			ArrayList<Object> objectCollection, Calendar calendar,
			String transientStringPropertyB, int intPropertyB, Collection<Long> transientLongCollectionB,
			Calendar calendarB, Calendar transientCalendarB) {
		super(stringProperty, intProperty, integerProperty, longCollection,
				objectCollection, calendar);
		this.transientStringPropertyB = transientStringPropertyB;
		this.intPropertyB = intPropertyB;
		this.transientLongCollectionB = transientLongCollectionB;
		this.calendarB = calendarB;
		this.transientCalendarB = transientCalendarB;
	}
	
	public String getTransientStringPropertyB() {
		return this.transientStringPropertyB;
	}

	public void setTransientStringPropertyB(String transientStringPropertyB) {
		this.transientStringPropertyB = transientStringPropertyB;
	}

	public int getIntPropertyB() {
		return this.intPropertyB;
	}

	public void setIntPropertyB(int intPropertyB) {
		this.intPropertyB = intPropertyB;
	}

	public Collection<Long> getTransientLongCollectionB() {
		return this.transientLongCollectionB;
	}

	public void setTransientLongCollectionB(
			Collection<Long> transientLongCollectionB) {
		this.transientLongCollectionB = transientLongCollectionB;
	}

	public Calendar getCalendarB() {
		return this.calendarB;
	}

	public void setCalendarB(Calendar calendarB) {
		this.calendarB = calendarB;
	}

	public Calendar getTransientCalendarB() {
		return this.transientCalendarB;
	}

	public void setTransientCalendarB(Calendar transientCalendarB) {
		this.transientCalendarB = transientCalendarB;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("transientStringPropertyB",
				this.transientStringPropertyB).append("intPropertyB", this.intPropertyB)
				.append("transientLongCollectionB", this.transientLongCollectionB)
				.append("calendarB", this.calendarB).append("transientCalendarB",
						this.transientCalendarB).toString();
	}
	
}
