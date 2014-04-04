package com.akjava.gwt.html5.client.speechsynthesis;

import java.util.List;

import com.google.gwt.core.client.JsArray;

public class SpeechUtils {
private SpeechUtils(){}
//came from chrome33 voice list
public static final String DE_DE="de_De";
public static final String EN_GB="en_GB";
public static final String EN_US="en_US";
public static final String ES_ES="es_ES";
public static final String ES_MX="eS_MX";
public static final String FR_BE="fr_BE";
public static final String FR_FR="fr_FR";
public static final String IT_IT="it_IT";
public static final String JA_JP="ja_JP";
public static final String KO_KR="ko_KR";
public static final String PT_BR="pt_BR";
public static final String PT_PT="pt_PT";
//TODO multiple case?

	public static Voice getVoiceAtLang(String lang){
		JsArray<Voice> voices=SpeechSynthesis.get().getVoices();
		for(int i=0;i<voices.length();i++){
			if(voices.get(i).getLang().equals(lang)){
				return voices.get(i);
			}
		}
		return null;
	}
	public static Voice getVoiceAtLang(List<Voice> voices,String lang){
		String hifun=lang.replace('_', '-');
		for(int i=0;i<voices.size();i++){
			//System.out.println(voices.get(i).getLang()+","+lang);
			if(voices.get(i).getLang().equals(lang)||voices.get(i).getLang().equals(hifun)){
				return voices.get(i);
			}
		}
		return null;
	}
}
