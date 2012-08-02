package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.CanvasInputRange;
import com.akjava.gwt.html5.client.HTML5InputRange;
import com.akjava.gwt.html5.client.extra.HTML5Builder;
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
		
	};
}
