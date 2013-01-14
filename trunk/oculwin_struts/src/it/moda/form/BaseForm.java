package it.moda.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

public class BaseForm extends ValidatorForm
implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8974835459429893311L;
	private Map<String, Object> baseMap = new HashMap();

	public void setBaseMap(Map<String, Object> baseMap) {
		this.baseMap = baseMap;
	}
	public Map<String, Object> getBaseMap() {
		return this.baseMap;
	}

	public void setValue(String key, Object value) {
		this.baseMap.put(key, value);
	}

	public Object getValue(String key) {
		return this.baseMap.get(key);
	}
}
