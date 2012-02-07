package com.akjava.gwt.html5.client.download;

import com.google.gwt.core.client.JavaScriptObject;

public class DownloadBlobBuilder extends JavaScriptObject{

	protected DownloadBlobBuilder(){}
	
	public  native final void append(String text)/*-{
	this.append(text);
	}-*/;
	
	public  native final JavaScriptObject getBlob(String mimeType)/*-{
	return this.getBlob(mimeType);
	}-*/;
}
