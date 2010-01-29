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
package org.op4j.contrib.executables.functions.evaluation;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.Validate;
import org.javaruntype.type.Type;
import org.javaruntype.type.Types;
import org.op4j.contrib.utils.json.NewCalendarBeanInstanceStrategy;
import org.op4j.functions.ExecCtx;
import org.op4j.functions.evaluators.IEvaluator;


/**
 * 
 * @since 1.0
 * 
 * @author Soraya S&aacute;nchez
 *
 */
public final class FromJson implements IEvaluator<Object, String> {

	
	//FIXME Problems with types, result (bean or list)
	
	public static final String DATE_PATTERN = "yyyyMMdd G HHmmss Z";
	
	private final Class beanClass;
	private final JsonConfig jsonConfig;
	private final Map<String, Class> map;
	private final boolean setNewBeanInstanceStrategy;
	
	/**
	 * It converts the given String into a bean (or array of beans) of the given class. Every attribute
	 * of the target String matching a key in the map will be converted to the
	 * value class in the map. The keys in the map can be regular expressions as well
	 * 
	 * @param beanClass
	 * @param beanClassByAttribute
	 * @return
	 */
	public static FromJson forObject(Class beanClass, Map<String, Class> beanClassByAttribute) {
        return new FromJson(beanClass, beanClassByAttribute);
    }
	
	/**
	 * It converts the given String into a bean (or array of beans) of the given class. 
	 * If a property class can't be inferred, a DynaBean will be created for every such property
	 * 
	 * @param beanClass
	 * @param resultClassByAttribute
	 * @return
	 */
	public static FromJson forObject(Class beanClass) {
        return new FromJson(beanClass);
    }
	
	
	/**
	 * It converts the given String into a bean (or array of beans) using the configuration 
	 * given by JsonConfig
	 * 
	 * @param jsonConfig
	 * @return
	 */
	public static FromJson forObject(JsonConfig jsonConfig) {
        throw new UnsupportedOperationException();
    }
	
	
	
	public FromJson(Class beanClass, Map<String, Class> map) {
		super();
		this.beanClass = beanClass;
		this.map = map;
		this.jsonConfig =  new JsonConfig();
		this.setNewBeanInstanceStrategy = true;
	}

	public FromJson(Class beanClass) {
		super();
		this.beanClass = beanClass;
		this.map = new HashMap<String, Class>();
		this.jsonConfig =  new JsonConfig();
		this.setNewBeanInstanceStrategy = true;
	}

	public FromJson(JsonConfig jsonConfig) {
		super();
		this.jsonConfig = jsonConfig;
		this.beanClass = null;
		this.map = new HashMap<String, Class>();
		this.setNewBeanInstanceStrategy = false;
	}

	public Object execute(String object, ExecCtx ctx) throws Exception {
		
		Validate.notNull(object, "Target element can't be null...a json string is required");
		Validate.notNull(this.map, "The map can be empty but not null");
		Validate.notNull(this.jsonConfig, "JsonConfig can't be null");
			
		if (JSONUtils.mayBeJSON(object)) {
			// Is either an array or object
			
			if (object.startsWith("{")) {
				// Is a json object
				final JSONObject jsObject = (JSONObject) JSONSerializer.toJSON(object);
				if (this.beanClass != null) {
					this.jsonConfig.setRootClass(this.beanClass);
				}
				if (!this.map.isEmpty()) {
					this.jsonConfig.setClassMap(this.map);
				}
				if (this.setNewBeanInstanceStrategy) {
					this.jsonConfig.setNewBeanInstanceStrategy(new NewCalendarBeanInstanceStrategy());
				} 				
				return JSONObject.toBean(jsObject, this.jsonConfig);            		
			} 

			// Is a json array
			final JSONArray jsArray = (JSONArray) JSONSerializer.toJSON(object);
			if (this.beanClass != null) {
				this.jsonConfig.setRootClass(this.beanClass);
			}
			if (!this.map.isEmpty()) {
				this.jsonConfig.setClassMap(this.map);
			}
			if (this.setNewBeanInstanceStrategy) {
				this.jsonConfig.setNewBeanInstanceStrategy(new NewCalendarBeanInstanceStrategy());
			}
			return JSONArray.toCollection(jsArray, this.jsonConfig);      

		} 
		throw new OperationExecutionException("Not valid json string: " +
				object);  
	}

	public Type<? extends Object> getResultType(
			Type<? extends String> targetType) {
		return Types.OBJECT;
	}
}
