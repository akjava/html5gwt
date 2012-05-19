package com.akjava.gwt.html5.client.download;

import com.google.gwt.core.client.JavaScriptObject;

public class DownloadBlobBuilder extends JavaScriptObject{

	protected DownloadBlobBuilder(){}
	
	public  native final void append(String text)/*-{
	this.append(text);
	}-*/;
	
	
	/*
	 * 
	 * come from
	 * https://groups.google.com/a/chromium.org/forum/?fromgroups#!topic/chromium-html5/WOxmqfDAaqo
	 * Thanks Eric Bidelman
	 */
	public  native final void appendBase64(String dataURI)/*-{
  var BASE64_MARKER = ';base64,';
  var base64Index = dataURI.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
  var base64 = dataURI.substring(base64Index);
  var raw = $wnd.atob(base64);
  var rawLength = raw.length;
  var uInt8Array = new $wnd.Uint8Array(rawLength);

  for (var i = 0; i < rawLength; ++i) {
    uInt8Array[i] = raw.charCodeAt(i);
  }
  this.append(uInt8Array.buffer);	
  }-*/;
	
	public  native final JavaScriptObject getBlob(String mimeType)/*-{
	return this.getBlob(mimeType);
	}-*/;
}
