package com.akjava.gwt.html5.client.speechsynthesis;

import com.google.gwt.core.client.JavaScriptObject;

public class Voice extends JavaScriptObject{
	protected Voice(){}

	public  final native boolean isDefault()/*-{
    return this['default'];
  	}-*/;
	
	public  final native boolean isLocalService()/*-{
    return this['localService'];
  	}-*/;
	public  final native String getName()/*-{
    return this['name'];
  	}-*/;
	
	public  final native String getLang()/*-{
    return this['lang'];
  	}-*/;
	
	public  final native String getVoiceURI()/*-{
    return this['voiceURI'];
  	}-*/;
	
}
