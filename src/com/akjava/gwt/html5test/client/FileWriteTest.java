package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FileWriteTest extends VerticalPanel{

	public FileWriteTest(){
		
		add(new Label("Download Image"));
		final Canvas canvas=Canvas.createIfSupported();
		canvas.setCoordinateSpaceHeight(200);
		canvas.setCoordinateSpaceWidth(200);
		canvas.getContext2d().setFillStyle("#ccc");
		canvas.getContext2d().fillRect(0,0,200,200);
		
		
		Image img=new Image(canvas.toDataUrl());
		add(img);
		
		GWT.log(getUserAgent());
		
		if(isChrome()){
		Anchor filea=HTML5Download.generateBase64DownloadLink(canvas.toDataUrl(), "image/png", "image.png", "Download Image vid File API", false);
		add(filea);
		}else{
			add(new Label("use right click menu and save image."));
		}
		//add(a);
		Button bt=new Button("Window open to download",new ClickHandler() {
		//not work on ie	
			@Override
			public void onClick(ClickEvent event) {
				Window.open(canvas.toDataUrl(), "blank", null);
				
			}
		});
		add(bt);
		
		add(new Label(getUserAgent()));
		
		Anchor a=new Anchor("download link", canvas.toDataUrl());
		add(a);
		
		add(new Label("Download Text"));
		if(isChrome() || isFirefox()){
		Anchor texta=HTML5Download.generateTextDownloadLink("hello world", "hello.txt", "download text via File API");
		add(texta);
		}
		
	}
	
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
	
	public static boolean isChrome(){
		return getUserAgent().indexOf("chrome")!=-1;
	}
	public static boolean isFirefox(){
		return getUserAgent().indexOf("firefox")!=-1;
	}
}
