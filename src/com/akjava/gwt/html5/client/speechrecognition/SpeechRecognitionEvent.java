package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class SpeechRecognitionEvent extends JavaScriptObject{
protected SpeechRecognitionEvent(){}

public   final native double getTimeStamp()/*-{
return this.timeStamp;
	}-*/;

public   final native int getResultIndex()/*-{
return this.resultIndex;
	}-*/;

public   final native JsArray<SpeechRecognitionResult> getResults()/*-{
return this.results;
	}-*/;


}
