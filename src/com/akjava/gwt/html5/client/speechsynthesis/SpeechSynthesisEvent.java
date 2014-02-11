package com.akjava.gwt.html5.client.speechsynthesis;

import com.google.gwt.core.client.JavaScriptObject;

public class SpeechSynthesisEvent extends JavaScriptObject{
protected SpeechSynthesisEvent(){}

public   final native double getCharIndex()/*-{
return this.charIndex;
	}-*/;

public   final native double getElapsedTime()/*-{
return this.elapsedTime;
	}-*/;

public   final native String getName()/*-{
return this.name;
	}-*/;


}
