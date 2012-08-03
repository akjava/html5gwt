package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.CanvasInputRange;
import com.akjava.gwt.html5.client.HTML5InputRange;
import com.akjava.gwt.html5.client.extra.HTML5Builder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InputRangeTest extends VerticalPanel{

	public InputRangeTest(){
		
		HorizontalPanel h1=new HorizontalPanel();
		add(h1);
		HTML5InputRange range=new HTML5InputRange(0, 100, 50);
		h1.add(range);
		h1.add(HTML5Builder.createRangeLabel("value:", range));
		
		
		range.setEnabled(false);
		
		
		HorizontalPanel h2=new HorizontalPanel();
		add(h2);
		CanvasInputRange crange=new CanvasInputRange(0, 100, 50);
		h2.add(crange);
		h2.add(HTML5Builder.createRangeLabel("value:", crange));
		
		
		
		HorizontalPanel h3=new HorizontalPanel();
		add(h3);
		final CanvasInputRange crange2=new CanvasInputRange(-180, 180, 0);
		crange2.setWidth(200);
		h3.add(crange2);
		h3.add(HTML5Builder.createRangeLabel("value:", crange2));
		Button reset=new Button("reset",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				crange2.setValue(0);
			}
		});
		add(reset);
	};
}
