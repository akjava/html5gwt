package com.akjava.gwt.html5.client.extra;

import com.akjava.gwt.html5.client.InputRange;
import com.akjava.gwt.html5.client.InputRangeListener;
import com.google.gwt.user.client.ui.Label;

public class HTML5Builder {
	public static Label createRangeLabel(final String text,final InputRange range){
		final Label label=new Label();
		label.setText(text+""+range.getValue());
		label.setStylePrimaryName("title");
		range.addListener(new InputRangeListener() {
			@Override
			public void changed(int newValue) {
				label.setText(text+""+newValue);
			}
		});
		
		/*
		range.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				label.setText(text+":"+range.getValue());
				
			}
		});*/
		/*
		range.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				label.setText(text+":"+range.getValue());
			}
		});
		*/
		
		return label;
	}
	
	public static Label createRangeLabel(final String text,final InputRange range,final int divided){
		final Label label=new Label();
		label.setText(text+""+((double)range.getValue()/divided));
		label.setStylePrimaryName("title");
		range.addListener(new InputRangeListener() {
			@Override
			public void changed(int newValue) {
				label.setText(text+""+((double)newValue/divided));
			}
		});
		return label;
	}
	
	
}
