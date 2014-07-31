package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class DomError extends JavaScriptObject{
	protected DomError(){}
	public  final native String getName() /*-{
	return this.name;
	}-*/;
	
	public  final native String getMessage() /*-{
	return this.message;
	}-*/;
}
