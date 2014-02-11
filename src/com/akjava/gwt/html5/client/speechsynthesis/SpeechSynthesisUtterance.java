package com.akjava.gwt.html5.client.speechsynthesis;

import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisBoundaryListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisEndListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisErrorListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisMarkListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisPauseListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisResumeListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisStartListener;
import com.google.gwt.core.client.JavaScriptObject;

//TODO support event
public class SpeechSynthesisUtterance  extends JavaScriptObject{
	protected SpeechSynthesisUtterance(){}
	
	public  static final native SpeechSynthesisUtterance create()/*-{
    return new $wnd.SpeechSynthesisUtterance();
  	}-*/;
	
	public  static final native SpeechSynthesisUtterance create(String text)/*-{
    return new $wnd.SpeechSynthesisUtterance(text);
  	}-*/;
	
	public   final native void setText(String text)/*-{
    this.text=text;
  	}-*/;
	
	public   final native void setLang(String lang)/*-{
    this.lang=lang;
  	}-*/;
	
	public   final native void setVoiceURI(String voiceURI)/*-{
    this.voiceURI=voiceURI;
  	}-*/;
	
	public   final native void setVolume(double volume)/*-{
    this.volume=volume;
  	}-*/;
	
	public   final native void setRate(double rate)/*-{
	
    this.rate=rate;
  	}-*/;
	
	public   final native void setPitch(double pitch)/*-{
	
    this.pitch=pitch;
  	}-*/;
	
	public   final native void setVoice(Voice voice)/*-{
    this.voice=voice;
  	}-*/;
	
	public final native void setOnEnd(SpeechSynthesisEndListener listener)/*-{
    this.onend=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisEndListener::onEnd(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnStart(SpeechSynthesisStartListener listener)/*-{
    this.onstart=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisStartListener::onStart(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnBoundary(SpeechSynthesisBoundaryListener listener)/*-{
    this.onboundary=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisBoundaryListener::onBoundary(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnError(SpeechSynthesisErrorListener listener)/*-{
    this.onerror=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisErrorListener::onError(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnMark(SpeechSynthesisMarkListener listener)/*-{
    this.onmark=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisMarkListener::onMark(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnPause(SpeechSynthesisPauseListener listener)/*-{
    this.onpause=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisPauseListener::onPause(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnResume(SpeechSynthesisResumeListener listener)/*-{
    this.onresume=function (event) {
	listener.@com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisResumeListener::onResume(Lcom/akjava/gwt/html5/client/speechsynthesis/SpeechSynthesisEvent;)(event);
	};
  	}-*/;
	
	
}
