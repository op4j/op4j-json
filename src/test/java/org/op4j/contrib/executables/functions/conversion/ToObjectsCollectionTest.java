package org.op4j.contrib.executables.functions.conversion;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.javaruntype.type.Types;
import org.junit.Test;
import org.op4j.Op;
import org.op4j.contrib.utils.json.NewCalendarBeanInstanceStrategy;

public class ToObjectsCollectionTest extends TestCase {

	@SuppressWarnings("unchecked")
	@Test
	public final void test1() {
				
    	String jsonString = "[{intProperty: 7, stringProperty: \"the value\"}," +
    			"{intProperty: 17, stringProperty: \"the value asd\"}," +
    			"{intProperty: -7, stringProperty: \"the value ksjei kd k\"}]";
    	
    	Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class)).get();
    	    	
    	JsonConfig jsonConfig = new JsonConfig();		
		jsonConfig.setRootClass(ABean.class);
		Collection<ABean> result2 = 
    		JSONArray.toCollection(
    				(JSONArray)JSONSerializer.toJSON(jsonString, jsonConfig), jsonConfig);
    	
		Iterator<ABean> iterator = result2.iterator();
    	for (ABean aBean : result1) {
    		ABean aBean2 = iterator.next();
    		assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
        	assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
        	assertEquals(aBean.getCalendar(), aBean2.getCalendar());
        	assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
        	assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
        	assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
		}
    	
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void test2() {
		
		String jsonString = "[{intProperty: 7, stringProperty: \"the value\"," +
			"objectCollection: [{stringProperty: \"the value\"}]}, {intProperty: -97, stringProperty: \"the dsf d value\"," +
			"objectCollection: [{stringProperty: \"thedfdd value\"}, 34, 78, true]}]";
    	
		Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class)).get();
    	
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ABean.class);
		Collection<ABean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString, jsonConfig),
				jsonConfig);
					
		Iterator<ABean> iterator = result2.iterator();
    	for (ABean aBean : result1) {
    		ABean aBean2 = iterator.next();
    		assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
        	assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
        	assertEquals(aBean.getCalendar(), aBean2.getCalendar());
        	assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
        	assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
        	assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test3() {
		
    	String jsonString = "[{intProperty: 7, " +
			"stringProperty: \"the value\"," +
			"longCollection: [5,6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"the rrt value\"}]}," +
			"{intProperty: 7, " +
			"stringProperty: \"the value\"," +
			"longCollection: [5,6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"trtr rhe value\"}]}," +
			"{intProperty: 37, " +
			"stringProperty: \"the value\"," +
			"longCollection: [33,-6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"the value\"}]}," +
			"{intProperty: 7, " +
			"stringProperty: \"the fd value\"," +
			"longCollection: [5,6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"the value\"}, false, 45, \"asd\"]}]";
    	
    	Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class,
    			Op.onAll("objectCollection", ABean.class,
    					"calendar", Calendar.class).buildMap().asMapOf(Types.STRING, 
    							Types.CLASS_OF_UNKNOWN).get())).get();
    	
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(ABean.class);
    	jsonConfig.setClassMap(Op.onAll("objectCollection", ABean.class,
				"calendar", Calendar.class).buildMap().get());
    	jsonConfig.setNewBeanInstanceStrategy(new NewCalendarBeanInstanceStrategy());
    	Collection<ABean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString, jsonConfig),
				jsonConfig);
    	
    	Iterator<ABean> iterator = result2.iterator();
    	for (ABean aBean : result1) {
    		ABean aBean2 = iterator.next();
    		assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
        	assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
        	assertEquals(aBean.getCalendar(), aBean2.getCalendar());
        	assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
        	assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
        	assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
		}    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test4() {
		
    	String jsonString = "[7, 11, 13, 34, -12]";
    	
    	Collection<Integer> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(Integer.class)).get();
    	
		Collection<Integer> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString), Integer.class);
    	
    	Iterator<Integer> iterator = result2.iterator();
    	for (Integer integer : result1) {
    		assertEquals(integer, iterator.next());        	
		}    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test5() {
		
    	String jsonString = "[true, false, false, true]";
    	
    	Collection<Boolean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(Boolean.class)).get();
    	
		Collection<Boolean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString), Boolean.class);
    	
    	Iterator<Boolean> iterator = result2.iterator();
    	for (Boolean integer : result1) {
    		assertEquals(integer, iterator.next());        	
		}    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test6() {
		
    	String jsonString = "[true, false, false, 45, 44, 22, \"hi\", \"afternoon\", true]";
    	
    	Collection<Object> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(Object.class)).get();
    	
		Collection<Object> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString), Object.class);
    	
    	Iterator<Object> iterator = result2.iterator();
    	for (Object object : result1) {
    		Object obj = iterator.next();
    		assertEquals(object, obj);
    		assertEquals(object.getClass(), obj.getClass());
    		System.out.println("Class: "
    				+ object.getClass() + " - " + obj.getClass());
		}    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test7() {
		String jsonString = "[{intProperty: 7, stringProperty: \"the value\"," +
			"objectCollection: [{stringProperty: \"the value\"}]}, {intProperty: -97, stringProperty: \"the dsf d value\"," +
			"objectCollection: [{stringProperty: \"thedfdd value\"}, 34, 78, true]}]";
	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ABean.class);
		jsonConfig.setExcludes(new String[] {"stringProperty"});
		
		Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class, jsonConfig)).get();


		Collection<ABean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString, jsonConfig),
				jsonConfig);

		Iterator<ABean> iterator = result2.iterator();
		for (ABean aBean : result1) {
			ABean aBean2 = iterator.next();
			assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
			assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
			assertNull(aBean.getStringProperty());
			assertNull(aBean2.getStringProperty());
			assertEquals(aBean.getCalendar(), aBean2.getCalendar());
			assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
			assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
			assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test8() {
		String jsonString = "[{intProperty: 7, stringProperty: \"the value\"," +
		"objectCollection: [{stringProperty: \"the value\"}]}, {intProperty: -97, stringProperty: \"the dsf d value\"," +
		"objectCollection: [{stringProperty: \"thedfdd value\"}, 34, 78, true]}]";
	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ABean.class);
		jsonConfig.setExcludes(new String[] {"objectCollection"});
		
		Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class, jsonConfig)).get();


		Collection<ABean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString, jsonConfig),
				jsonConfig);

		Iterator<ABean> iterator = result2.iterator();
		for (ABean aBean : result1) {
			ABean aBean2 = iterator.next();
			assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
			assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
			assertEquals(aBean.getCalendar(), aBean2.getCalendar());
			assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
			assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
			assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
			assertNull(aBean.getObjectCollection());
			assertNull(aBean2.getObjectCollection());
			
		}    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void test9() {
		String jsonString = "[{intProperty: 7, stringProperty: \"the value\"," +
		"objectCollection: [{stringProperty: \"the value\"}]}, {intProperty: -97, stringProperty: \"the dsf d value\"," +
		"objectCollection: [{stringProperty: \"thedfdd value\"}, 34, {aProperty: \"a value\"}, 78, true]}]";
	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ABean.class);
		jsonConfig.setExcludes(new String[] {"stringProperty"});
		jsonConfig.setClassMap(Op.onAll("objectCollection", ABean.class,
				"calendar", Calendar.class).buildMap().get());
		
		Collection<ABean> result1 = Op.on(jsonString).exec(ToObjectsCollection.fromJsonObject(ABean.class, jsonConfig)).get();


		Collection<ABean> result2 = JSONArray.toCollection((JSONArray) JSONSerializer.toJSON(jsonString, jsonConfig),
				jsonConfig);

		Iterator<ABean> iterator = result2.iterator();
		for (ABean aBean : result1) {
			ABean aBean2 = iterator.next();
			assertEquals(aBean.getIntProperty(), aBean2.getIntProperty());
			assertEquals(aBean.getStringProperty(), aBean2.getStringProperty());
			assertNull(aBean.getStringProperty());
			assertNull(aBean2.getStringProperty());
			assertEquals(aBean.getCalendar(), aBean2.getCalendar());
			assertEquals(aBean.getIntegerProperty(), aBean2.getIntegerProperty());
			assertEquals(aBean.getLongCollection(), aBean2.getLongCollection());
			assertEquals(aBean.getObjectCollection(), aBean2.getObjectCollection());
			
			Iterator<Object> objIterator = aBean2.getObjectCollection().iterator();
			System.out.println("Iterating object collection");
			for (Object obj2 : aBean.getObjectCollection()) {
				Object obj1 = objIterator.next();
				if (ABean.class == obj1.getClass()) {
					System.out.println("ObjectCollection has an object of class ABean");
					assertEquals(((ABean)obj1).getStringProperty(), ((ABean)obj2).getStringProperty());
					assertNull(((ABean)obj1).getStringProperty());
					assertNull(((ABean)obj2).getStringProperty());
				} else {
					System.out.println("ObjectCollection has an object of class: "
							+ obj1.getClass() + " - " + obj2.getClass());
				}
			}
			
		}    	
	}
}
