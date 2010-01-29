package org.op4j.contrib.executables.functions.conversion;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Before;
import org.junit.Test;
import org.op4j.contrib.utils.json.NewCalendarBeanInstanceStrategy;
import org.op4j.op.Op;
import org.op4j.operation.CastParam;
import org.op4j.operation.GenericTarget;
import org.op4j.operation.Operations;
import org.op4j.operation.Result;
import org.op4j.operation.Target;
import org.op4j.type.Types;

public class FromJsonTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		Op.on("");			
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testFromJson() {
		
		JsonConfig jsonConfig = new JsonConfig();
		
		jsonConfig.setRootClass(ABean.class);
    	String jsonString = "{intProperty: 7, stringProperty: \"the value\"}";
    	Result result = Operations.executeOperation(FromJson.OPERATION_NAME, 
    			Arrays.asList((Target<String>[]) new Target[] {new GenericTarget<String>(Types.STRING, jsonString)}), 
    			Arrays.asList(new Object[] {ABean.class, CastParam.to("Map<String, Class>", 
    					new HashMap<String, Class<?>>())}));
    	assertEquals(JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig), result.getResultObject());
    	    
    	jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(ABean.class);
    	jsonString = "{intProperty: 7, stringProperty: \"the value\"," +
			"objectCollection: [{stringProperty: \"the value\"}]}";
    	result = Operations.executeOperation(FromJson.OPERATION_NAME, 
    			Arrays.asList((Target<String>[]) new Target[] {new GenericTarget<String>(Types.STRING, jsonString)}), 
    			Arrays.asList(new Object[] {ABean.class}));
    	assertEquals(JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig), result.getResultObject());
    	   	
    	jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(ABean.class);
    	jsonConfig.setClassMap(Op.onAll("objectCollection", ABean.class,
				"calendar", Calendar.class).buildMap().get());
    	jsonConfig.setNewBeanInstanceStrategy(new NewCalendarBeanInstanceStrategy());
    	jsonString = "{intProperty: 7, " +
			"stringProperty: \"the value\"," +
			"longCollection: [5,6,0]," +
			"calendar: {timeInMillis: 1223230496013},"+
			"objectCollection: [{stringProperty: \"the value\"}]}";
    	result = Operations.executeOperation(FromJson.OPERATION_NAME, 
    			Arrays.asList((Target<String>[]) new Target[] {new GenericTarget<String>(Types.STRING, jsonString)}), 
    			Arrays.asList(new Object[] {ABean.class, CastParam.to("Map<String, Class>", Op.onAll("objectCollection", ABean.class,
    					"calendar", Calendar.class).buildMap().get())}));
    	assertEquals(JSONSerializer.toJava(JSONObject.fromObject(jsonString), jsonConfig), result.getResultObject());
    	
    	jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(String.class);
    	jsonString = "['56','89', '6', '0', '-56']";
    	result = Operations.executeOperation(FromJson.OPERATION_NAME, 
    			Arrays.asList((Target<String>[]) new Target[] {new GenericTarget<String>(Types.STRING, jsonString)}), 
    			Arrays.asList(new Object[] {String.class, CastParam.to("Map<String, Class>", 
    					new HashMap<String, Class<?>>())}));
    	assertEquals(JSONSerializer.toJava(JSONArray.fromObject(jsonString, jsonConfig)), result.getResultObject());
    	
    	jsonConfig = new JsonConfig();
    	jsonConfig.setRootClass(Long.class);
    	jsonString = "[[56,89, 6, 0, -56],[676, -89,45]]";
    	result = Operations.executeOperation(FromJson.OPERATION_NAME, 
    			Arrays.asList((Target<String>[]) new Target[] {new GenericTarget<String>(Types.STRING, jsonString)}), 
    			Arrays.asList(new Object[] {Long.class}));
    	assertEquals(JSONSerializer.toJava(JSONArray.fromObject(jsonString, jsonConfig)), result.getResultObject());
    	    	
    	//TODO call Op...
    	//TODO Check type
	}

}
