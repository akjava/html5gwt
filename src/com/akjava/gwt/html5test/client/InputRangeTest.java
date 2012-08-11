package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.CanvasInputRange;
import com.akjava.gwt.html5.client.InputRangeWidget;
import com.akjava.gwt.html5.client.extra.HTML5Builder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InputRangeTest extends VerticalPanel{

	public InputRangeTest(){
		
		add(new Label("Native"));
		HorizontalPanel h1=new HorizontalPanel();
		add(h1);
		InputRangeWidget range=InputRangeWidget.createInputRange(0, 100, 50);
		h1.add(range);
		range.setEnabled(false);
		
		HorizontalPanel h1b=new HorizontalPanel();
		add(h1b);
		InputRangeWidget range2=InputRangeWidget.createInputRange(0, 100, 50);
		h1b.add(range2);
	
		
		add(new Label("Canvas"));
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
		add(create(1,1,1));
		add(create(1,2,1));
		add(create(1,3,2));
		
		HorizontalPanel h4=new HorizontalPanel();
		final CanvasInputRange crange4=new CanvasInputRange(1,5, 1);
		crange4.setWidth(200);
		h4.add(crange4);
		h4.add(HTML5Builder.createRangeLabel("value:", crange4));
		add(h4);
		
Button prev=new Button("prev",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				crange4.setValue(crange4.getValue()-1);
			}
		});
h4.add(prev);

		Button next=new Button("next",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				crange4.setValue(crange4.getValue()+1);
			}
		});
		h4.add(next);
		

	};
	
	public HorizontalPanel create(int min,int max,int value){
		HorizontalPanel panel=new HorizontalPanel();
		final CanvasInputRange crange2=new CanvasInputRange(min,max,value);
		crange2.setWidth(200);
		panel.add(crange2);
		panel.add(HTML5Builder.createRangeLabel("value:", crange2));
		return panel;
	}
}
