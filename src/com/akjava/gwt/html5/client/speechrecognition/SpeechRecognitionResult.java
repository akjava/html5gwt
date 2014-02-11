package com.akjava.gwt.html5.client.speechrecognition;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * not sure?
 * @author aki
 *
 */
public class SpeechRecognitionResult extends JavaScriptObject{
protected SpeechRecognitionResult(){}
public   final native int getLength()/*-{
return this.length;
	}-*/;

public   final native boolean isFinal()/*-{
return this.isFinal;
	}-*/;

public   final native SpeechRecognitionAlternative get(int index)/*-{
return this[index];
	}-*/;
}
