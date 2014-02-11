package com.akjava.gwt.html5test.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.html5.client.speechrecognition.SpeechRecognition;
import com.akjava.gwt.html5.client.speechrecognition.SpeechRecognitionAlternative;
import com.akjava.gwt.html5.client.speechrecognition.SpeechRecognitionError;
import com.akjava.gwt.html5.client.speechrecognition.SpeechRecognitionEvent;
import com.akjava.gwt.html5.client.speechrecognition.SpeechRecognitionResult;
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
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RecognizeTest extends VerticalPanel{

	SpeechRecognition speechRecognition;
	private ValueListBox<String> langBox;
	private TextArea resultArea;
	public RecognizeTest(){
		VerticalPanel root=new VerticalPanel();
		this.add(root);
		if(!SpeechRecognition.isSupported()){
			root.add(new Label("SpeechRecognition not supported.Use Chrome"));
			return;
		}
		
		langBox = new ValueListBox<String>(new Renderer<String>() {
			@Override
			public String render(String object) {
				// TODO Auto-generated method stub
				return object;
			}

			@Override
			public void render(String object, Appendable appendable) throws IOException {
				// TODO Auto-generated method stub
			}
		});
		
		root.add(langBox);
		
		List<String> values=Lists.newArrayList("en-US","ja-JP");
		langBox.setValue(values.get(0));
		langBox.setAcceptableValues(values);
		
		
		HorizontalPanel h1=new HorizontalPanel();
		root.add(h1);
		Button start=new Button("Start",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				startRecognize();
			}
		});
		h1.add(start);
		
		Button stop=new Button("Stop",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				speechRecognition.stop();
			}
		});
		h1.add(stop);
		
Button abort=new Button("Abort",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				speechRecognition.abort();
			}
		});
		h1.add(abort);
		
		resultArea = new TextArea();
		resultArea.setSize("700px", "100px");
		root.add(resultArea);
		
		
	}

	protected void startRecognize() {
		
		speechRecognition=SpeechRecognition.create();
		//speechRecognition.setInterimResults(true);
		speechRecognition.setContinuous(true);//TODO check
		speechRecognition.setLang(langBox.getValue());
		speechRecognition.setMaxAlternatives(10);
		speechRecognition.setOnResult(new SpeechRecognitionResultListener() {
			
			@Override
			public void onResult(SpeechRecognitionEvent event) {
				//HTML5Test.log(event);
				int index=event.getResultIndex();
				HTML5Test.log("index:"+index);
				List<String> lines=new ArrayList<String>();
				for(int i=0;i<event.getResults().length();i++){
					SpeechRecognitionResult result=event.getResults().get(i);
					//HTML5Test.log(result);
					List<String> texts=new ArrayList<String>();
					//HTML5Test.log("final:"+result.isFinal());
					for(int j=0;j<result.getLength();j++){
						
						SpeechRecognitionAlternative alternative=result.get(j);
						
						HTML5Test.log(result);
						//SpeechRecognitionAlternative alternative=result.get(j);
						//HTML5Test.log(alternative);
						texts.add(alternative.getTranscript());
					}
					lines.add(Joiner.on(",").join(texts));
				}
				
				resultArea.setText(Joiner.on("\n").join(lines));
				
			}
		});
		HTML5Test.log(speechRecognition);
		
		
		speechRecognition.setOnError(new SpeechRecognitionErrorListener() {
			
			@Override
			public void onError(SpeechRecognitionError error) {
				HTML5Test.log(error.getError()+","+error.getMessage());
			}
		});
		
		speechRecognition.setOnAudioStart(new SpeechRecognitionAudioStartListener() {
			@Override
			public void onAudioStart() {
				HTML5Test.log("AudioStart:");
				
			}
		});
		speechRecognition.setOnSoundStart(new SpeechRecognitionSoundStartListener() {
			@Override
			public void onSoundStart() {
				HTML5Test.log("SoundStart:");
				
			}
		});
		speechRecognition.setOnSpeechStart(new SpeechRecognitionSpeechStartListener() {
			@Override
			public void onSpeechStart() {
				HTML5Test.log("SpeechStart:");
				
			}
		});
		speechRecognition.setOnSpeechEnd(new SpeechRecognitionSpeechEndListener() {
			@Override
			public void onSpeechEnd() {
				HTML5Test.log("SpeechEnd:");
				
			}
		});
		speechRecognition.setOnSoundEnd(new SpeechRecognitionSoundEndListener() {
			@Override
			public void onSoundEnd() {
				HTML5Test.log("SoundEnd:");
				
			}
		});
		speechRecognition.setOnAudioEnd(new SpeechRecognitionAudioEndListener() {
			@Override
			public void onAudioEnd() {
				HTML5Test.log("AudioEnd:");
				
			}
		});
		speechRecognition.setOnNomatch(new SpeechRecognitionNomatchListener() {
			@Override
			public void onNomatch(SpeechRecognitionEvent event) {
				HTML5Test.log("Nomatch:");
				HTML5Test.log(event);
			}
		});
		speechRecognition.setOnStart(new SpeechRecognitionStartListener() {
			@Override
			public void onStart() {
				HTML5Test.log("Start:");
				
			}
		});
		speechRecognition.setOnEnd(new SpeechRecognitionEndListener() {
			@Override
			public void onEnd() {
				HTML5Test.log("End:");
				
			}
		});

		
		HTML5Test.log(speechRecognition);
		
		speechRecognition.start();
	}
	
	
}
