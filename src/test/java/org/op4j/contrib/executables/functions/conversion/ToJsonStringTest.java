package org.op4j.contrib.executables.functions.conversion;

import java.util.ArrayList;
import java.util.Calendar;

import junit.framework.TestCase;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;
import org.op4j.Op;
import org.op4j.contrib.utils.json.CalendarJsonValueProcessor;

public class ToJsonStringTest extends TestCase {

//	@Test
//	public final void test1() {
//		
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	assertEquals(JSONSerializer.toJSON(Arrays.asList(Integer.valueOf(4))).toString(),
//    			Op.on(Arrays.asList(Integer.valueOf(4))).exec(ToJsonString.forObject()).get());
//    	
//	}
//
//	@Test
//	public final void test2() {
//		
//		JsonConfig jsonConfig = new JsonConfig();
//	
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	jsonConfig = new JsonConfig();
//    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
//    	assertEquals(JSONSerializer.toJSON(firstABean, jsonConfig).toString(), 
//    			Op.on(firstABean).exec(ToJsonString.forObject()).get());    			
//    	
//	}

	@Test
	public final void test3() {
		
		JsonConfig jsonConfig = new JsonConfig();
	
    	
    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
    	secondLongsList.add(Long.valueOf(34));
    	
    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
    	secondObjectsList.add("one");
    	secondObjectsList.add(Long.valueOf(45));
    	secondObjectsList.add(Calendar.getInstance());
    	secondObjectsList.add("A string property in the secondObjectsList");
    	secondObjectsList.add(Long.valueOf(45));
    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
    	
    	BBean firstBBean = new BBean("bStringProperty", 
    			34, 
    			Integer.valueOf(4), 
    			secondLongsList, 
    			secondObjectsList, 
    			Calendar.getInstance(), 
    			"A transient string property", 
    			34, 
    			secondLongsList, 
    			Calendar.getInstance(), 
    			Calendar.getInstance());
    	
    	
    	jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
    	assertEquals(JSONSerializer.toJSON(firstBBean, jsonConfig).toString(), 
    			Op.on(firstBBean).exec(ToJsonString.forObject()).get());   
    			 
	}

//	@Test
//	public final void test4() {
//		
//		JsonConfig jsonConfig = new JsonConfig();
//	
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	BBean firstBBean = new BBean("bStringProperty", 
//    			34, 
//    			Integer.valueOf(4), 
//    			secondLongsList, 
//    			secondObjectsList, 
//    			Calendar.getInstance(), 
//    			"A transient string property", 
//    			34, 
//    			secondLongsList, 
//    			Calendar.getInstance(), 
//    			Calendar.getInstance());
//    	
//    	
//    	
//    			    	    	   	
//    	jsonConfig = new JsonConfig();
//    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
//    	jsonConfig.setIgnoreTransientFields(false);
//    	jsonConfig.setIgnoreJPATransient(false);
//    	assertEquals(JSONSerializer.toJSON(firstBBean, jsonConfig).toString(), 
//    			Op.on(firstBBean).exec(ToJsonString.forObject(jsonConfig)).get());   
//    	
//	}
//
//	@Test
//	public final void test5() {
//		
//		JsonConfig jsonConfig = new JsonConfig();
//	
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	BBean firstBBean = new BBean("bStringProperty", 
//    			34, 
//    			Integer.valueOf(4), 
//    			secondLongsList, 
//    			secondObjectsList, 
//    			Calendar.getInstance(), 
//    			"A transient string property", 
//    			34, 
//    			secondLongsList, 
//    			Calendar.getInstance(), 
//    			Calendar.getInstance());
//    	
//    	
//    			
//    	jsonConfig = new JsonConfig();
//    	jsonConfig.setIgnoreTransientFields(true);
//    	jsonConfig.setIgnoreJPATransient(true);
//    	assertEquals(JSONSerializer.toJSON(firstBBean, jsonConfig).toString(), 
//    			Op.on(firstBBean).exec(ToJsonString.forObject(jsonConfig)).get());   
//    	    	 	
//    	
//	}
//
//	@Test
//	public final void test6() {
//		
//		JsonConfig jsonConfig = new JsonConfig();
//	
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	ABean secondABean = new ABean("Test json - secondABean string property value", 
//    			10, 
//    			Integer.valueOf(5), 
//    			firstLongsList,
//				firstObjectsList, 
//				Calendar.getInstance());
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	
//    	
//    	
//    	jsonConfig = new JsonConfig();
//    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
//    	assertEquals(JSONObject.fromObject(secondABean, jsonConfig).toString(),
//    			Op.on(secondABean).exec(ToJsonString.forObject(jsonConfig)).get());
//    	
//    	
//    	
//	}
//	
//	@Test
//	public final void test7() {
//		
//		JsonConfig jsonConfig = new JsonConfig();
//	
//		ArrayList<Long> firstLongsList = new ArrayList<Long>();
//    	firstLongsList.add(Long.valueOf(4));
//    	firstLongsList.add(Long.valueOf(3));
//    	firstLongsList.add(Long.valueOf(2));
//    	firstLongsList.add(Long.valueOf(1));		
//    	
//    	ABean firstABean = new ABean("The string property value of ABean - firstBean", 
//    			13, 
//    			Integer.valueOf(22), 
//    			firstLongsList, 
//				new ArrayList<Object>(), 
//				Calendar.getInstance());   
//    	
//    	ArrayList<Object> firstObjectsList = new ArrayList<Object>();
//    	firstObjectsList.add(Long.valueOf(3));
//    	firstObjectsList.add(firstABean);   
//    	
//    	ABean secondABean = new ABean("Test json - secondABean string property value", 
//    			10, 
//    			Integer.valueOf(5), 
//    			firstLongsList,
//				firstObjectsList, 
//				Calendar.getInstance());
//    	
//    	ArrayList<Long> secondLongsList = new ArrayList<Long>();
//    	secondLongsList.add(Long.valueOf(34));
//    	
//    	ArrayList<Object> secondObjectsList = new ArrayList<Object>();
//    	secondObjectsList.add("one");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(Calendar.getInstance());
//    	secondObjectsList.add("A string property in the secondObjectsList");
//    	secondObjectsList.add(Long.valueOf(45));
//    	secondObjectsList.add(new ArrayList<Long>());
//    	secondObjectsList.add(Calendar.getInstance());
//    	
//    	
//    	
//    	
//    	jsonConfig = new JsonConfig();
//    	jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
//    	
//    	assertEquals(JSONArray.fromObject(new ABean[] {secondABean, secondABean}, jsonConfig).toString(),
//    			Op.on(Arrays.asList(new ABean[] {secondABean, secondABean})).exec(ToJsonString.forObject(jsonConfig)).get());
//    	
//    	
//	}
}
