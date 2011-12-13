package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class FileReader extends JavaScriptObject {
	protected FileReader(){}
	  public static final native FileReader createFileReader() /*-{
	     return new $wnd.FileReader();
	   }-*/;
	  
	  
	  public  final native void readAsText(File blob,String encode) /*-{
	    this.readAsText(blob,encode);
	   }-*/;
	  
		public  final native String getResultAsString()/*-{
	    return this.result;
	  }-*/;
		
	  
	  public  final native void setOnLoad(FileHandler handler) /*-{
	  	this.onload=function(event){
	  		handler.@com.akjava.gwt.html5.client.file.FileHandler::onLoad()();
	  	};
	   }-*/;
	  
}
