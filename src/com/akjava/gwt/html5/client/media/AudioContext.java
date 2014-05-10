package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

public class AudioContext extends JavaScriptObject{

	protected AudioContext(){}
	
	public final static native AudioContext create()/*-{
	$wnd.window.AudioContext=$wnd.window.AudioContext || $wnd.window.webkitAudioContext;
	return new $wnd.window.AudioContext();
	}-*/;
	
	public final static native boolean isAvailable()/*-{
		if(window.AudioContext || window.webkitAudioContext){
		return true;
		}else{
		return false; 
		}
	}-*/;
	
	public final  native MediaStreamSource createBufferSource()/*-{
	return this.createBufferSource();
	}-*/;
	
	public final  native MediaStreamSource createMediaStreamSource(LocalMediaStream stream)/*-{
	return this.createMediaStreamSource(stream);
	}-*/;
	
	
	public final  native AudioDestinationNode getDestination()/*-{
	return this.destination;
	}-*/;
	
	public final  native Buffer createBuffer(ArrayBuffer buffer,boolean mono)/*-{
	return this.createBuffer(buffer,mono);
	}-*/;
	
	public final  native AnalyserNode createAnalyser()/*-{
	return this.createAnalyser();
	}-*/;
	
	public final native void decodeAudioData(DecodeAudioListener listener,DecodeErrorListener errorlistener)/*-{
	this.decodeAudioData(
	function(array){
		listener.@com.akjava.gwt.html5.client.media.AudioContext$DecodeAudioListener::onDecode(Lcom/google/gwt/typedarrays/shared/ArrayBuffer;)(array);
	},
	function(error){
		errorlistener.@com.akjava.gwt.html5.client.media.AudioContext$DecodeErrorListener::onError(Lcom/google/gwt/core/client/JavaScriptObject;)(error);
	}
	);
	}-*/;

	public final  native int getSampleRate()/*-{
	return this.sampleRate;
	}-*/;
	public static interface DecodeAudioListener{
		public void onDecode(ArrayBuffer buffer);
	}
	public static interface DecodeErrorListener{
		public void onError(JavaScriptObject error);
	}
}
