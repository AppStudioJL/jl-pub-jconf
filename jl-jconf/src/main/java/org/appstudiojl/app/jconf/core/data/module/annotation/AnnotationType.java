package org.appstudiojl.app.jconf.core.data.module.annotation;

public enum AnnotationType {
	CREATER("creater"), 
	UPDATE("update"), 
	COMMENT("comment"), 
	STRICT("strict", true);
	
	private String tag;
	private boolean isFlag;
	
	private AnnotationType(String tag){
		this.tag = tag;
	}
	
	private AnnotationType(String tag, boolean isFlag){
		this.tag = tag;
		this.isFlag = isFlag;
	}

	public String getTag() {
		return tag;
	}

	public boolean isFlag() {
		return isFlag;
	}

}
