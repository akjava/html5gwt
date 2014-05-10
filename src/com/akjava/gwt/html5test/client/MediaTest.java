package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.file.Uint8Array;
import com.akjava.gwt.html5.client.media.AudioContext.DecodeAudioListener;
import com.akjava.gwt.html5.client.media.AudioContext.DecodeErrorListener;
import com.akjava.gwt.html5.client.media.AudioProcessingEvent;
import com.akjava.gwt.html5.client.media.OfflineAudioContext;
import com.akjava.gwt.html5.client.media.OfflineAudioContext.CompleteListener;
import com.akjava.gwt.html5.client.media.ScriptProcessorNode;
import com.akjava.gwt.html5.client.media.ScriptProcessorNode.AudioProcessingEventListener;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;
import com.google.gwt.xhr.client.XMLHttpRequest.ResponseType;

/**
 * 
 * TODO after chrome34 there are ScriptProcessorNode has bugs
 * @author aki
 *
 */
public class MediaTest extends VerticalPanel{
	int index=0;
public MediaTest(){
	super();
	add(new Label("offline"));
	loadWav("test.wav",new LoadWavListener() {
		@Override
		public void onLoad(ArrayBuffer buffer) {
			
			OfflineAudioContext context=OfflineAudioContext.create(1,100,48000);
			final ScriptProcessorNode processor=context.createScriptProcessor(100, 1, 1);
			processor.connect(context.getDestination());
			index=0;
			context.setOnComplete(new CompleteListener() {
				@Override
				public void onComplete() {
					HTML5Test.log("index:"+index);
				}
			});
			processor.setOnAudioprocess(new AudioProcessingEventListener() {
				@Override
				public void onAudioProcess(AudioProcessingEvent event) {
					if(index<5){
						HTML5Test.log(event);
					}
					index++;
					//HTML5Test.log(""+index);
				}
			});
			//context.crea
			context.decodeAudioData(new DecodeAudioListener() {
				@Override
				public void onDecode(ArrayBuffer decodeBuffer) {
					HTML5Test.log(Uint8Array.createUint8(decodeBuffer));
					
				}
			}, new DecodeErrorListener(){

				@Override
				public void onError(JavaScriptObject error) {
					HTML5Test.log(error);
				}});
		}
	});
}

public static interface LoadWavListener{
	public void onLoad(ArrayBuffer buffer);
}

private void loadWav(String path,final LoadWavListener listener) {
	XMLHttpRequest request=XMLHttpRequest.create();
	request.setResponseType(ResponseType.ArrayBuffer);
	request.setOnReadyStateChange(new ReadyStateChangeHandler() {
		@Override
		public void onReadyStateChange(XMLHttpRequest xhr) {
			if(xhr.getResponseArrayBuffer()==null){//pre loading
				return;
			}
			ArrayBuffer arrayBufer=xhr.getResponseArrayBuffer();
			listener.onLoad(arrayBufer);	
		}
	});
	request.open("GET",path);
	request.send();
}
}
