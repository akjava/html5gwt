package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;

public class SpeechRecognitionError extends JavaScriptObject{
public static final String 	NETWORK="network";
public static final String 	NO_SPEECH="no-speech";
public static final String 	NOT_ALLOWED="not-allowed";

protected SpeechRecognitionError(){}
public   final native String getError()/*-{
return this.error;
	}-*/;


public   final native String getMessage()/*-{
return this.message;
	}-*/;
}
