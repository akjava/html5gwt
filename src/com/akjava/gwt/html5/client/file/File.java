package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class File extends JavaScriptObject{

	protected File(){}
	
	public  final native String getFileName()/*-{
    return this.name;
  }-*/;
	
	public  final native int getFileSize()/*-{
    return this.size;
  }-*/;
	

	
	public  final native String getFileType()/*-{
    return this.type;
  }-*/;
}
