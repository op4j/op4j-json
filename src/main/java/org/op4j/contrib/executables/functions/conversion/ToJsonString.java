/*
 * =============================================================================
 * 
 *   Copyright (c) 2008, The OP4J team (http://www.op4j.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.op4j.contrib.executables.functions.conversion;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.Validate;
import org.op4j.contrib.utils.json.CalendarJsonValueProcessor;
import org.op4j.contrib.utils.json.JavaUtilDateJsonValueProcessor;
import org.op4j.contrib.utils.json.TimestampJsonValueProcessor;
import org.op4j.functions.AbstractNullAsNullFunction;
import org.op4j.functions.ExecCtx;


/**
 * 
 * @since 1.0
 * 
 * @author Soraya S&aacute;nchez
 *
 */
public final class ToJsonString {

	private static final FromObject TO_JSON_STRING = new FromObject();
	
	
	/**
	 * It converts an object to its JSON representation
	 * 
	 * @return
	 */
	public static FromObject fromObject() {
        return TO_JSON_STRING;
    }
	
	/**
	 * It converts an object to its JSON representation. The properties
	 * included in the String[] will be excluded from the result
	 * 
	 * @param properties properties excluded from the input object
	 * @return
	 */
	public static FromObject fromObject(String[] propertiesExcluded) {
        return new FromObject(propertiesExcluded);
    }
	
	/**
	 * It converts an object to its JSON representation. 
	 * 
	 * @param jsonConfig used to configure how the conversion will be done
	 * @return
	 */
	public static FromObject fromObject(JsonConfig jsonConfig) {
		return new FromObject(jsonConfig);
    }
		
	
	
	public static final class FromObject extends AbstractNullAsNullFunction<Object, String> {

		private final JsonConfig jsonConfig;
		
		public FromObject() {
			this.jsonConfig = new JsonConfig();
			this.jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
			this.jsonConfig.registerJsonValueProcessor(GregorianCalendar.class, new CalendarJsonValueProcessor());
    		this.jsonConfig.registerJsonValueProcessor(Date.class, new JavaUtilDateJsonValueProcessor());
    		this.jsonConfig.registerJsonValueProcessor(Timestamp.class, new TimestampJsonValueProcessor());  
		}
				
		public FromObject(String[] propertiesExcluded) {
			super();	
			
			Validate.notNull(propertiesExcluded, "propertiesExcluede can't be null");
			
			this.jsonConfig = new JsonConfig();
			this.jsonConfig.registerJsonValueProcessor(Calendar.class, new CalendarJsonValueProcessor());
			this.jsonConfig.registerJsonValueProcessor(GregorianCalendar.class, new CalendarJsonValueProcessor());
    		this.jsonConfig.registerJsonValueProcessor(Date.class, new JavaUtilDateJsonValueProcessor());
    		this.jsonConfig.registerJsonValueProcessor(Timestamp.class, new TimestampJsonValueProcessor());  
    		
			if (propertiesExcluded.length > 0) {
	    		this.jsonConfig.setExcludes(propertiesExcluded);
	    	}			
		}
		
		public FromObject(JsonConfig jsonConfig) {
			super();		
			this.jsonConfig = jsonConfig;	
		}
		
		@Override
		public String nullAsNullExecute(Object object, ExecCtx ctx)
				throws Exception {
			Validate.notNull(this.jsonConfig, "JsonConfig can't be null");
	    	
	    	return JSONSerializer.toJSON(object, 
	    			this.jsonConfig).toString();
		}

		
	}
}
