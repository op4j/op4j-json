package org.op4j.contrib.executables.functions.conversion;

import java.util.Calendar;

import junit.framework.TestCase;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.javaruntype.type.Types;
import org.junit.Test;
import org.op4j.Op;
import org.op4j.contrib.utils.json.NewCalendarBeanInstanceStrategy;

public class ToObjectTest extends TestCase {

	@Test
	public final void test1() {
				
    	String jsonString = "{intProperty: 7, stringProperty: \"the value\"}";
    	
    	ABean result1 = org.op4j.Op.on(jsonString).exec(ToObject.fromJsonString(ABean.class)).get();
    	    	
    	JsonConfig jsonConfig = new JsonConfig();		
		jsonConfig.setRootClass(ABean.class);
    	ABean result2 = (ABean)JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig);
    	
    	assertEquals(result1.getIntProperty(), result2.getIntProperty());
    	assertEquals(result1.getStringProperty(), result2.getStringProperty());
    	assertEquals(result1.getCalendar(), result2.getCalendar());
    	assertEquals(result1.getIntegerProperty(), result2.getIntegerProperty());
    	assertEquals(result1.getLongCollection(), result2.getLongCollection());
    	assertEquals(result1.getObjectCollection(), result2.getObjectCollection());
	}

	@Test
	public final void test2() {
		
		String jsonString = "{intProperty: 7, stringProperty: \"the value\"," +
			"objectCollection: [{stringProperty: \"the value\"}]}";
    	
		ABean result1 = Op.on(jsonString).exec(ToObject.fromJsonString(ABean.class)).get();
    	
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ABean.class);
		ABean result2 = (ABean) JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig);
			
		assertEquals(result1.getIntProperty(), result2.getIntProperty());
    	assertEquals(result1.getStringProperty(), result2.getStringProperty());
    	assertEquals(result1.getCalendar(), result2.getCalendar());
    	assertEquals(result1.getIntegerProperty(), result2.getIntegerProperty());
    	assertEquals(result1.getLongCollection(), result2.getLongCollection());
    	assertEquals(result1.getObjectCollection(), result2.getObjectCollection());
	}
	
	@Test
	public final void test3() {
		
    	String jsonString = "{intProperty: 7, " +
			"stringProperty: \"the value\"," +
			"longCollection: [5,6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"the value\"}]}";
    	
		ABean result1 = Op.on(jsonString).exec(ToObject.fromJsonString(ABean.class,
    			Op.onAll("objectCollection", ABean.class,
    					"calendar", Calendar.class).buildMap().asMapOf(Types.STRING, 
    							Types.CLASS_OF_UNKNOWN).get())).get();
    	
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(ABean.class);
    	jsonConfig.setClassMap(Op.onAll("objectCollection", ABean.class,
				"calendar", Calendar.class).buildMap().get());
    	jsonConfig.setNewBeanInstanceStrategy(new NewCalendarBeanInstanceStrategy());
    	ABean result2 = (ABean)JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig);
    	
    	assertEquals(result1.getIntProperty(), result2.getIntProperty());
    	assertEquals(result1.getStringProperty(), result2.getStringProperty());
    	assertEquals(result1.getCalendar(), result2.getCalendar());
    	assertEquals(result1.getIntegerProperty(), result2.getIntegerProperty());
    	assertEquals(result1.getLongCollection(), result2.getLongCollection());
    	assertEquals(result1.getObjectCollection(), result2.getObjectCollection());
    	
	}
}
