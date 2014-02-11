package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;

public class SpeechRecognitionError extends JavaScriptObject{
protected SpeechRecognitionError(){}
public   final native String getError()/*-{
return this.error;
	}-*/;


public   final native String getMessage()/*-{
return this.message;
	}-*/;
}
