package com.akjava.gwt.html5.client.download;

import com.google.gwt.core.client.JavaScriptObject;

public class DownloadURL extends JavaScriptObject{
	protected DownloadURL(){}
	
	
	public  native final String createObjectURL(JavaScriptObject object)/*-{
	return this.createObjectURL(object);
	}-*/;
	

	public  native final void revokeObjectURL(String url)/*-{
	this.revokeObjectURL(url);
	}-*/;
	
	public  static  native final DownloadURL get()/*-{
	return   window.webkitURL || window.URL;
	}-*/;
}
