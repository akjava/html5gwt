package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class Unit8Array extends JavaScriptObject{
protected Unit8Array(){}
	public  final native int length()/*-{
    return this.length;
  }-*/;
	
	public  final native int get(int index)/*-{
    return this[index];
  }-*/;
	
}
