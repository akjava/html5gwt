package com.akjava.gwt.html5.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Html5gwt implements EntryPoint {
	
	

	public void onModuleLoad() {
		
		ColorPickWidget pick=new ColorPickWidget();
		
		RootPanel.get().add(pick);
	}


}
