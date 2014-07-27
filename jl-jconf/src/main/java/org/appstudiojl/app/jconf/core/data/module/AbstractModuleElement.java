package org.appstudiojl.app.jconf.core.data.module;

public abstract class AbstractModuleElement {
	
	protected String root;
	protected String key;
	protected String value;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public abstract String getElementKey();
}
