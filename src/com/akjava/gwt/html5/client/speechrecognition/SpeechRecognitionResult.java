package com.akjava.gwt.html5.client.speechrecognition;

import java.util.ArrayList;
import java.util.List;

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

public final List<SpeechRecognitionAlternative> getSpeechRecognitionAlternativeAsList(){
	List<SpeechRecognitionAlternative> list=new ArrayList<SpeechRecognitionAlternative>();
	for(int i=0;i<getLength();i++){
		list.add(get(i));
	}
	return list;
}
}
