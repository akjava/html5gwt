package com.akjava.gwt.html5.client.speechrecognition;

import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionAudioEndListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionAudioStartListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionEndListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionErrorListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionNomatchListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionResultListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSoundEndListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSoundStartListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSpeechEndListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSpeechStartListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionStartListener;
import com.google.gwt.core.client.JavaScriptObject;

public class SpeechRecognition extends JavaScriptObject{
protected SpeechRecognition(){}
	public final native static SpeechRecognition create()/*-{
		return new webkitSpeechRecognition();
  	}-*/; 
	
	public final native static boolean isSupported()/*-{
   		if($wnd.webkitSpeechRecognition){
   		return true;
   		}else{
   		return false;
   		}
  	}-*/; 
	
	public  final native void setContinuous(boolean continuous)/*-{
    this.continuous=continuous;
  	}-*/; 
	
	public  final native void setInterimResults(boolean interimResults)/*-{
    this.interimResults=interimResults;
  	}-*/; 
	
	
	public  final native void setLang(String lang)/*-{
    this.lang=lang;
  	}-*/; 
	

	public  final native String getLang()/*-{
    return this.lang;
  	}-*/; 
	
	public  final native void setMaxAlternatives(int maxAlternatives)/*-{
    this.maxAlternatives=maxAlternatives;
  	}-*/; 
	
	
	public  final native void start()/*-{
    this.start();
  	}-*/; 
	
	public  final native void abort()/*-{
    this.abort();
  	}-*/; 
	
	public  final native void stop()/*-{
    this.stop();
  	}-*/; 
	

	public final native void setOnResult(SpeechRecognitionResultListener listener)/*-{
    this.onresult=function (event) {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionResultListener::onResult(Lcom/akjava/gwt/html5/client/speechrecognition/SpeechRecognitionEvent;)(event);
	};
  	}-*/;
	
	public final native void setOnError(SpeechRecognitionErrorListener listener)/*-{
    this.onerror=function (event) {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionErrorListener::onError(Lcom/akjava/gwt/html5/client/speechrecognition/SpeechRecognitionError;)(event);
	};
  	}-*/;
	
	public final native void setOnAudioStart(SpeechRecognitionAudioStartListener listener)/*-{
    this.onaudiostart=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionAudioStartListener::onAudioStart()();
	};
  	}-*/;
	public final native void setOnSoundStart(SpeechRecognitionSoundStartListener listener)/*-{
    this.onsoundstart=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSoundStartListener::onSoundStart()();
	};
  	}-*/;
	public final native void setOnSpeechStart(SpeechRecognitionSpeechStartListener listener)/*-{
    this.onspeechstart=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSpeechStartListener::onSpeechStart()();
	};
  	}-*/;
	public final native void setOnSpeechEnd(SpeechRecognitionSpeechEndListener listener)/*-{
    this.onspeechend=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSpeechEndListener::onSpeechEnd()();
	};
  	}-*/;
	public final native void setOnSoundEnd(SpeechRecognitionSoundEndListener listener)/*-{
    this.onsoundend=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionSoundEndListener::onSoundEnd()();
	};
  	}-*/;
	public final native void setOnAudioEnd(SpeechRecognitionAudioEndListener listener)/*-{
    this.onaudioend=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionAudioEndListener::onAudioEnd()();
	};
  	}-*/;
	public final native void setOnNomatch(SpeechRecognitionNomatchListener listener)/*-{
    this.onnomatch=function (event) {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionNomatchListener::onNomatch(Lcom/akjava/gwt/html5/client/speechrecognition/SpeechRecognitionEvent;)(event);
	};
  	}-*/;
	public final native void setOnStart(SpeechRecognitionStartListener listener)/*-{
    this.onstart=function (e) {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionStartListener::onStart()();
	};
  	}-*/;
	public final native void setOnEnd(SpeechRecognitionEndListener listener)/*-{
    this.onend=function () {
	listener.@com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionEndListener::onEnd()();
	};
  	}-*/;

	
	
}
