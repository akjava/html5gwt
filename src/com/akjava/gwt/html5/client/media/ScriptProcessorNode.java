package com.akjava.gwt.html5.client.media;


public class ScriptProcessorNode extends AudioNode{
	protected ScriptProcessorNode(){}
	
	public final native void setOnAudioprocess (AudioProcessingEventListener listener)/*-{
	this.onaudioprocess(
	function(event){
	listener.@com.akjava.gwt.html5.client.media.ScriptProcessorNode$AudioProcessingEventListener::onAudioProcess(Lcom/akjava/gwt/html5/client/media/AudioProcessingEvent;)(event);
	}
	);
	}-*/;


	public static interface AudioProcessingEventListener{
		public void onAudioProcess(AudioProcessingEvent event);
	}
}
