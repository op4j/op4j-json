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
package org.op4j.contrib.utils.json;

import java.util.Calendar;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * @since 1.0
 * 
 * @author Soraya S&aacute;nchez
 *
 */
public class CalendarJsonValueProcessor implements JsonValueProcessor {
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		JSONObject output = new JSONObject();
		output.element("timeInMillis", ((Calendar) value).getTimeInMillis());
		return output;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		JSONObject output = new JSONObject();
		if (value != null) {
			output.element("timeInMillis", ((Calendar) value).getTimeInMillis());
		}
		return output;
	}    		
}
