package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;

public class SpeechRecognitionResultList extends JavaScriptObject{
	protected SpeechRecognitionResultList(){}
	
	public   final native int length()/*-{
	return this.length;
		}-*/;

	public   final native SpeechRecognitionResult get(int index)/*-{
	return this[index];
		}-*/;
}
