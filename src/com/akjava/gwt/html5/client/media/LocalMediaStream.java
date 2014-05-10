package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

public class LocalMediaStream extends JavaScriptObject{
	protected LocalMediaStream(){}
	
	public final  native void stop()/*-{
	this.stop();
	}-*/;
	
	
	public final  native boolean isEnded()/*-{
	return this.ended;
	}-*/;
}
