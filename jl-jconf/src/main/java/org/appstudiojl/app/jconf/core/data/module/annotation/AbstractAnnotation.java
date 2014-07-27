package org.appstudiojl.app.jconf.core.data.module.annotation;

import org.appstudiojl.app.jconf.core.data.module.AbstractModuleElement;

public abstract class AbstractAnnotation extends AbstractModuleElement {
	
	protected static final String PREFIX = "@";
	protected AnnotationType type;
	
	@Override
	public abstract String getElementKey();

	public AnnotationType getType() {
		return type;
	}

	public void setType(AnnotationType type) {
		this.type = type;
	}
	
	public String getTag(){
		return PREFIX + type.getTag();
	}
}
