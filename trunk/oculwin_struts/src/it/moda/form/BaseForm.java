package it.moda.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm {
	
	private Map<String, Object> values = new HashMap();

    public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }
    
    public void assignValues(Map map){
    	values=map;
    }

	public Map<String, Object> getValues() {
		return values;
	}
}
