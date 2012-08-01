package com.akjava.gwt.html5.client.file.webkit;

import com.google.gwt.core.client.JavaScriptObject;

public class WebkitEntry extends JavaScriptObject{

	protected WebkitEntry(){}
	
	public  final native boolean isFile()/*-{
    return this.isFile;
  	}-*/;
	
	public  final native boolean isDirectory()/*-{
    return this.isDirectory;
  	}-*/;
}
