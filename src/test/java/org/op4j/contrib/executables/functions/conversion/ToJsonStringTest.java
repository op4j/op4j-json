package org.op4j.contrib.executables.functions.conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Before;
import org.junit.Test;
import org.op4j.Op;
import org.op4j.contrib.utils.json.CalendarJsonValueProcessor;

public class ToJsonStringTest extends TestCase {

	ArrayList<Long> firstLongsList = new ArrayList<Long>();
	ABean firstABean;
	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
	ArrayList<Long> secondLongsList = new ArrayList<Long>();
	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
	BBean firstBBean;
	ABean secondABean;
	
	@Override
	@Before
	public void setUp() throws Exception {
		this.firstLongsList.add(Long.valueOf(4));
		this.firstLongsList.add(Long.valueOf(3));
		this.firstLongsList.add(Long.valueOf(2));
		this.firstLongsList.add(Long.valueOf(1));
		
		this.firstABean = new ABean("The string property value of ABean - firstBean", 
    			13, 
    			Integer.valueOf(22), 
    			this.firstLongsList, 
				new ArrayList<Object>(), 
				Calendar.getInstance());
		
		this.firstObjectsList.add(Long.valueOf(3));
		this.firstObjectsList.add(this.firstABean);  
		
		this.secondLongsList.add(Long.valueOf(34));
		
		this.secondObjectsList.add("one");
		this.secondObjectsList.add(Long.valueOf(45));
		this.secondObjectsList.add(Calendar.getInstance());
		this.secondObjectsList.add("A string property in the secondObjectsList");
		this.secondObjectsList.add(Long.valueOf(45));
		this.secondObjectsList.add(new ArrayList<Long>());
		this.secondObjectsList.add(Calendar.getInstance());
		
		this.firstBBean = new BBean("bStringProperty", 
    			34, 
    			Integer.valueOf(4), 
    			this.secondLongsList, 
    			this.secondObjectsList, 
    			Calendar.getInstance(), 
    			"A transient string property", 
    			34, 
    			this.secondLongsList, 
    			Calendar.getInstance(), 
    			Calendar.getInstance());
		
		this.secondABean = new ABean("Test json - secondABean string property value", 
    			10, 
    			Integer.valueOf(5), 
    			this.firstLongsList,
    			this.firstObjectsList, 
				Calendar.getInstance());
	}
	
	@Test
	public final void test1() {
		assertEquals(JSONSerializer.toJSON(Arrays.asList(Integer.valueOf(4))).toString(),
    			Op.on(Arrays.asList(Integer.valueOf(4))).exec(ToJsonString.fromObject()).get());    	
	}

	@Test
	public final void test2() {
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	assertEquals(JSONSerializer.toJSON(this.firstABean, jsonConfig).toString(), 
    			Op.on(this.firstABean).exec(ToJsonString.fromObject()).get());    	    	
	}

	@Test
	public final void test3() {
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	jsonConfig.registerJsonValueProcessor(GregorianCalendar.class, new CalendarJsonValueProcessor());
    	
    	assertEquals(JSONSerializer.toJSON(this.firstBBean, jsonConfig).toString(), 
    			Op.on(this.firstBBean).exec(ToJsonString.fromObject()).get());     			 
	}

	@Test
	public final void test4() {
    			    	    	   	
    	JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	jsonConfig.setIgnoreTransientFields(false);
    	jsonConfig.setIgnoreJPATransient(false);
    	assertEquals(JSONSerializer.toJSON(this.firstBBean, jsonConfig).toString(), 
    			Op.on(this.firstBBean).exec(ToJsonString.fromObject(jsonConfig)).get());      	
	}

	@Test
	public final void test5() {
				
    	JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.setIgnoreTransientFields(true);
    	jsonConfig.setIgnoreJPATransient(true);
    	assertEquals(JSONSerializer.toJSON(this.firstBBean, jsonConfig).toString(), 
    			Op.on(this.firstBBean).exec(ToJsonString.fromObject(jsonConfig)).get());      	
	}

	@Test
	public final void test6() {
		
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	assertEquals(JSONObject.fromObject(this.secondABean, jsonConfig).toString(),
    			Op.on(this.secondABean).exec(ToJsonString.fromObject(jsonConfig)).get());
	}
	
	@Test
	public final void test7() {
		
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	
    	assertEquals(JSONArray.fromObject(new ABean[] {this.secondABean, this.secondABean}, jsonConfig).toString(),
    			Op.on(Arrays.asList(new ABean[] {this.secondABean, this.secondABean}))
    			.exec(ToJsonString.fromObject(jsonConfig)).get());   	
	}
}
