package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;

public class SpeechRecognitionAlternative extends JavaScriptObject{
protected SpeechRecognitionAlternative(){}


public   final native double getConfidence()/*-{
return this.confidence;
	}-*/;

public   final native String getTranscript()/*-{
return this.transcript;
	}-*/;

}
