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
}
