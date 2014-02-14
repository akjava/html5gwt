package com.akjava.gwt.html5.client.speechsynthesis;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class SpeechSynthesis extends JavaScriptObject{
protected SpeechSynthesis(){}
	public final native static SpeechSynthesis get()/*-{
		if($wnd.speechSynthesis){
			
    	return $wnd.speechSynthesis;
		}
		if($wnd.SpeechSynthesis){
    	return $wnd.SpeechSynthesis;
		}
		
		return null;
  	}-*/; 
	
	public final native static boolean supported()/*-{
   		if($wnd.speechSynthesis || $wnd.SpeechSynthesis){
   		return true;
   		}else{
   		return false;
   		}
  	}-*/; 
	
	
	public  final native boolean isPaused()/*-{
    return this.paused;
  	}-*/; 
	
	public  final native boolean isPending()/*-{
    return this.pending;
  	}-*/; 
	
	public   final native boolean isSpeaking()/*-{
    return this.speaking;
  	}-*/; 
	
	
	/**
	 * possible 0 onload
	 * @return
	 */
	public  final  List<Voice> getVoicesAsList(){
		List<Voice> voices=new ArrayList<Voice>();
		JsArray<Voice> array=getVoices();
		for(int i=0;i<array.length();i++){
			voices.add(array.get(i));
		}
		return voices;
	}
	/**
	 * possible 0 onload
	 * @return
	 */
	public  final native JsArray<Voice> getVoices()/*-{
    return this.getVoices();
  	}-*/;
	
	public   final native void cancel()/*-{
    this.cancel();
  	}-*/;
	
	public  final native void pause()/*-{
    this.pause();
  	}-*/;
	
	public  final native void resume()/*-{
    this.resume();
  	}-*/;
	
	public  final native void speak(SpeechSynthesisUtterance utterance)/*-{
    this.speak(utterance);
  	}-*/;
	
}
