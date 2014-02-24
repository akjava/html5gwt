package com.akjava.gwt.html5.client.speechrecognition;

import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionEndListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionErrorListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionResultListener;
import com.akjava.gwt.html5.client.speechrecognition.listeners.SpeechRecognitionStartListener;

public class SpeechRecognitionBuilder {
	private String lang;
	private boolean interimResults;
	private boolean continuous;
	private int maxAlternatives;
	private SpeechRecognitionResultListener onResult;
	private SpeechRecognitionErrorListener onError;
	private SpeechRecognitionStartListener onStart;
	private SpeechRecognitionEndListener onEnd;
	private SpeechRecognitionBuilder(){
		
	}
	public static SpeechRecognitionBuilder fromLang(String lang){
		SpeechRecognitionBuilder builder=new SpeechRecognitionBuilder();
		builder.lang=lang;
		return builder;
	}
	
	public SpeechRecognitionBuilder interimResults(boolean value){
		interimResults=value;
		return this;
	}
	public SpeechRecognitionBuilder continuous(boolean value){
		continuous=value;
		return this;
	}
	
	public SpeechRecognitionBuilder maxAlternatives(int maxAlternatives){
		this.maxAlternatives=maxAlternatives;
		return this;
	}
	
	public SpeechRecognitionBuilder onResult(SpeechRecognitionResultListener onResult){
		this.onResult=onResult;
		return this;
	}
	public SpeechRecognitionBuilder onError(SpeechRecognitionErrorListener onError){
		this.onError=onError;
		return this;
	}
	
	public SpeechRecognitionBuilder onStart(SpeechRecognitionStartListener onStart){
		this.onStart=onStart;
		return this;
	}
	
	public SpeechRecognitionBuilder onEnd(SpeechRecognitionEndListener onEnd){
		this.onEnd=onEnd;
		return this;
	}
	
	public SpeechRecognition build(){
		SpeechRecognition speechRecognition=SpeechRecognition.create();
		if(lang!=null){
			speechRecognition.setLang(lang);
		}
		if(interimResults){
			speechRecognition.setInterimResults(interimResults);
		}
		if(continuous){
			speechRecognition.setContinuous(continuous);
		}
		if(maxAlternatives!=0){
			speechRecognition.setMaxAlternatives(maxAlternatives);
		}
		if(onError!=null){
			speechRecognition.setOnError(onError);
		}
		if(onResult!=null){
			speechRecognition.setOnResult(onResult);
		}
		if(onStart!=null){
			speechRecognition.setOnStart(onStart);
		}
		if(onEnd!=null){
			speechRecognition.setOnEnd(onEnd);
		}
		return speechRecognition;
	}
	
	
}
