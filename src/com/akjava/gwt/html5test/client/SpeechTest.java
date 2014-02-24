package com.akjava.gwt.html5test.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.html5.client.HTML5InputRange;
import com.akjava.gwt.html5.client.InputRangeWidget;
import com.akjava.gwt.html5.client.extra.HTML5Builder;
import com.akjava.gwt.html5.client.speechsynthesis.SpeechSynthesis;
import com.akjava.gwt.html5.client.speechsynthesis.SpeechSynthesisEvent;
import com.akjava.gwt.html5.client.speechsynthesis.SpeechSynthesisUtterance;
import com.akjava.gwt.html5.client.speechsynthesis.SpeechUtils;
import com.akjava.gwt.html5.client.speechsynthesis.Voice;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisBoundaryListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisEndListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisErrorListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisMarkListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisPauseListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisResumeListener;
import com.akjava.gwt.html5.client.speechsynthesis.listeners.SpeechSynthesisStartListener;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpeechTest  extends VerticalPanel{

	private TextBox textBox;

	public SpeechTest(){
		
		if(!SpeechSynthesis.supported()){
			add(new Label("SpeechSynthesis not supported.use Chrome 33 later"));
			return; 
		}
		
		
		//final List<Voice> voices=speechSynthesis.getVoicesAsList();
		
		
		
		Button bt=new Button("update",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				createWidget();
			}
		});
		add(bt);
		
		
		/*:
		 * finaly,entry,deffered
		 */
		//wait finish load
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				HTML5Test.log("deferred");
				//only work on android
				createWidget();
			}
		});
		
		Scheduler.get().scheduleFinally(new ScheduledCommand() {
			
			@Override
			public void execute() {
				HTML5Test.log("finally");
			}
		});
		Scheduler.get().scheduleEntry(new ScheduledCommand() {
			
			@Override
			public void execute() {
				HTML5Test.log("entry");
			}
		});
		
		
	}
	private SpeechSynthesis speechSynthesis;
	private Label label;
	private SpeechSynthesisUtterance utterance;
	private InputRangeWidget rateRange;
	private InputRangeWidget pitchRange;
	
	
	private TextBox defaultLangBox;
	private ValueListBox<Voice> voiceListBox;
	
	public void createWidget(){
		Timer timer=new Timer(){

			
		
			@Override
			public void run() {
				long s=System.currentTimeMillis();
				speechSynthesis = SpeechSynthesis.get();
				List<Voice> voices=speechSynthesis.getVoicesAsList();
				
				
				
				if(voices.size()>0){

					Voice voice=Objects.firstNonNull(SpeechUtils.getVoiceAtLang(voices, SpeechUtils.JA_JP), voices.get(0));
					
					voiceListBox = new ValueListBox<Voice>(new Renderer<Voice>() {
						@Override
						public String render(Voice voice) {
							return voice.getName()+"("+voice.getLang()+")";
						}

						@Override
						public void render(Voice object, Appendable appendable) throws IOException {
							
						}
					});
					voiceListBox.setValue(voice);
					voiceListBox.setAcceptableValues(voices);
					add(voiceListBox);
					
					HorizontalPanel h1=new HorizontalPanel();
					add(h1);
					
					ValueListBox<String> speakTests=new ValueListBox<String>(new Renderer<String>() {
						@Override
						public String render(String object) {
							return object;
						}

						@Override
						public void render(String object, Appendable appendable) throws IOException {
							// TODO Auto-generated method stub
							
						}
					});
					speakTests.addValueChangeHandler(new ValueChangeHandler<String>() {
						
						@Override
						public void onValueChange(ValueChangeEvent<String> event) {
							textBox.setText(event.getValue());
						}
					});
					List<String> tests=Lists.newArrayList("hello world","こんにちは",
							"<speak version=\"1.1\">Speak it</speak>",
							"<speak version=\"1.1\"> That is a <emphasis> big </emphasis> car!</speak>",
							"<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\"> That is a <emphasis> big </emphasis> car!</speak>"
							);
					speakTests.setValue(tests.get(0));
					speakTests.setAcceptableValues(tests);
					add(speakTests);
					
					textBox = new TextBox();
					//<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang=\"en-US\">Hello world<voice xml:lang=\"fr-FR\" gender=\"female\">do you know me?</voice></speak>
					textBox.setText(tests.get(0));
					h1.add(textBox);
					Button speakBt=new Button("speak",new ClickHandler() {
					

						@Override
						public void onClick(ClickEvent event) {
							execSpeak();
						}

						
					});
					h1.add(speakBt);
					
					Button cancelBt=new Button("cancel",new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							if(speechSynthesis!=null){
								speechSynthesis.cancel();
								updateStatus();
							}
						}

						
						
					});
					h1.add(cancelBt);
					Button pauseBt=new Button("pause",new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							if(speechSynthesis!=null){
								speechSynthesis.pause();
								updateStatus();
							}
						}
						
					});
					h1.add(pauseBt);
					Button resumeBt=new Button("resume",new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							if(speechSynthesis!=null){
								speechSynthesis.resume();
								updateStatus();
							}
						}
						
					});
					h1.add(resumeBt);
					
					HorizontalPanel h2=new HorizontalPanel();
					add(h2);
					rateRange = HTML5InputRange.createInputRange(1, 100, 10);
					
					Label rateLabel=HTML5Builder.createRangeLabel("rate:", rateRange, 10, 1);
					rateLabel.setWidth("50px");
					h2.add(rateLabel);
					h2.add(rateRange);
					HorizontalPanel h3=new HorizontalPanel();
					add(h3);
					pitchRange = HTML5InputRange.createInputRange(0, 20, 10);
					Label pitchLabel=HTML5Builder.createRangeLabel("pitch:", pitchRange);
					pitchLabel.setWidth("50px");
					h3.add(pitchLabel);
					h3.add(pitchRange);
					cancel();
					
					label = new Label("");
					add(label);
					
					add(new Label("defalt-lang"));
					defaultLangBox = new TextBox();
					defaultLangBox.setText(SpeechUtils.JA_JP);
					add(defaultLangBox);
					
					
					TextArea output=new TextArea();
					output.setSize("600px", "150px");
					output.setReadOnly(true);
					add(output);
					String list=Joiner.on("\n").join(Iterables.transform(voices, new VoiceToCsvFunction()));
					output.setText(list);
					HTML5Test.log(list);
					
				}
				
				if(s+30000<System.currentTimeMillis()){
					add(new Label("voices is empty"));
					cancel();
				}
			}
			
		};
		//timer.scheduleRepeating(100);
		timer.schedule(100);
		
	}
	
	private void execSpeak() {

		HTML5Test.log(""+System.currentTimeMillis()/1000);
		utterance = SpeechSynthesisUtterance.create(textBox.getText());
		utterance.setVoice(voiceListBox.getValue());
		
		String lang=voiceListBox.getValue().getLang();
		if(Strings.isNullOrEmpty(lang)){
			lang=defaultLangBox.getText();
		}
		utterance.setLang(lang);//need set lang
		HTML5Test.log("lang:"+lang);
		utterance.setRate((double)rateRange.getValue()/10);
		utterance.setPitch((double)pitchRange.getValue()/10);
		
		
		utterance.setOnStart(new SpeechSynthesisStartListener() {
			@Override
			public void onStart(SpeechSynthesisEvent event) {
				HTML5Test.log("start:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		
		utterance.setOnEnd(new SpeechSynthesisEndListener() {
			@Override
			public void onEnd(SpeechSynthesisEvent event) {
				HTML5Test.log("end:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		
		utterance.setOnPause(new SpeechSynthesisPauseListener() {
			
			@Override
			public void onPause(SpeechSynthesisEvent event) {
				HTML5Test.log("pause:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		
		utterance.setOnResume(new SpeechSynthesisResumeListener() {
			
			@Override
			public void onResume(SpeechSynthesisEvent event) {
				HTML5Test.log("resume:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		utterance.setOnError(new SpeechSynthesisErrorListener() {
			
			@Override
			public void onError(SpeechSynthesisEvent event) {
				HTML5Test.log("error:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		
		utterance.setOnBoundary(new SpeechSynthesisBoundaryListener() {
			
			@Override
			public void onBoundary(SpeechSynthesisEvent event) {
				HTML5Test.log("boundary:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		utterance.setOnMark(new SpeechSynthesisMarkListener() {
			
			@Override
			public void onMark(SpeechSynthesisEvent event) {
				HTML5Test.log("mark:"+event.getCharIndex()+","+event.getName()+","+event.getElapsedTime());
				updateStatus();
			}
		});
		
		
		
		speechSynthesis.speak(utterance);
	}
	
	public class VoiceToCsvFunction implements Function<Voice, String>{

		@Override
		public String apply(Voice input) {
			List<String> values=new ArrayList<String>();
			values.add(input.getName());
			values.add(input.getLang());
			values.add(input.getVoiceURI());
			values.add(""+input.isDefault());
			values.add(""+input.isLocalService());
			
			return Joiner.on("\t").join(values);
		}
		
	}
	
	private void updateStatus() {
		String status="paused="+speechSynthesis.isPaused()+",pending="+speechSynthesis.isPending()+",speaking="+speechSynthesis.isSpeaking();
		label.setText(status);
	}
}
