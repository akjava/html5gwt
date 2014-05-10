package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

/*
 * TODO more
 */
public class OfflineAudioContext extends AudioContext{

	protected OfflineAudioContext(){}
	
	public final static native OfflineAudioContext create(int channel,int length,int sampleRate)/*-{
	$wnd.window.OfflineAudioContext=$wnd.window.OfflineAudioContext || $wnd.window.webkitOfflineAudioContext;
	return new $wnd.window.OfflineAudioContext(channel,length,sampleRate);
	}-*/;
	
	public final static native boolean isAvailableOffline()/*-{//no need?
		if(window.OfflineAudioContext || window.webkitOfflineAudioContext){
		return true;
		}else{
		return false; 
		}
	}-*/;
	
	public final  native void startRendering()/*-{
	this.startRendering();
	}-*/;

	public final  native ScriptProcessorNode createScriptProcessor(int bufferSize,int numberOfInputChannels,int numberOfOutputChannels)/*-{
		return this.createScriptProcessor(bufferSize,numberOfInputChannels,numberOfOutputChannels);
	}-*/;
	
	public final native void setOnComplete(CompleteListener listener)/*-{
	this.oncomplete(
	function(){
	listener.@com.akjava.gwt.html5.client.media.OfflineAudioContext$CompleteListener::onComplete()();
	}
	);
	}-*/;

	
	public interface CompleteListener{
		public void onComplete();
	}
	
}
