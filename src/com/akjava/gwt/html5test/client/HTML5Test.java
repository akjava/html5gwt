package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.Unit8Array;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HTML5Test implements EntryPoint {

	@Override
	public void onModuleLoad() {
		VerticalPanel root=new VerticalPanel();
		RootPanel.get().add(root);
		
		
		
		final TextArea area=new TextArea();
		area.setSize("400px","400px");
		
		
		
		
		
		area.addDragOverHandler(new DragOverHandler() {
			
			@Override
			public void onDragOver(DragOverEvent event) {
				
				
				event.preventDefault();
			}
		});
		
		
		final boolean asString=true;
		area.addDropHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
			
				event.preventDefault();
				
				final FileReader reader=FileReader.createFileReader();
				final JsArray<File> files=FileUtils.transferToFile(event.getNativeEvent());
				GWT.log("length:"+files.length());
				if(files.length()>0){
					
					
				
					
				reader.setOnLoad(new FileHandler() {
					@Override
					public void onLoad() {
						log("loaded:");
						String text="";
						
						if(asString){
							text=reader.getResultAsString();
						}else{
							Unit8Array array=reader.getResultAsBuffer();
							log("length:"+array.length());
							
						StringBuilder builder=new StringBuilder();
						for(int i=0;i<array.length();i++){
							builder.append((char)array.get(i));
						
						}
						
						builder.toString();
						}
						
						area.setText(text);
						
					}
				});
				
				if(asString){
					reader.readAsText(files.get(0),"UTF-8");
				}else{
					reader.readAsArrayBuffer(files.get(0));
				}
				
				
				
				
				
				
				}
				
				
				
				
			//	event.stopPropagation();
				
				
				
			}
		});
		root.add(area);
		
	}
	
	
	public static final native void doit(JavaScriptObject obj)/*-{
     var buf = new $wnd.Uint8Array(obj);
     $wnd.console.log(buf);
     for(var i=0;i<buf.length;i++){
     	$wnd.console.log(String.fromCharCode(buf[i]));	
     }
  }-*/;

	/*
	 * can not shift
	 */
	public static final native void log(JavaScriptObject obj)/*-{
    $wnd.console.log(""+obj);
  }-*/;
	public static final native void log(String obj)/*-{
    $wnd.console.log(obj);
  }-*/;

	

}
