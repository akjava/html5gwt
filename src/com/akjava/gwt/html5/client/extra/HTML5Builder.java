package com.akjava.gwt.html5.client.extra;

import com.akjava.gwt.html5.client.IInputRange;
import com.akjava.gwt.html5.client.InputRangeListener;
import com.google.gwt.user.client.ui.Label;

public class HTML5Builder {
	public static Label createRangeLabel(final String text,final IInputRange range){
		final Label label=new Label();
		label.setText(text+""+range.getValue());
		label.setStylePrimaryName("title");
		range.addInputRangeListener(new InputRangeListener() {
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
	//TODO add point
	public static Label createRangeLabel(final String text,final IInputRange range,final int divided){
		final Label label=new Label();
		label.setText(text+""+((double)range.getValue()/divided));
		label.setStylePrimaryName("title");
		range.addInputRangeListener(new InputRangeListener() {
			@Override
			public void changed(int newValue) {
				label.setText(text+""+((double)newValue/divided));
			}
		});
		return label;
	}
	
	public static Label createRangeLabel(final String text,final IInputRange range,final int divided,final int point){
		final Label label=new Label();
		label.setText(text+""+((double)range.getValue()/divided));
		label.setStylePrimaryName("title");
		range.addInputRangeListener(new InputRangeListener() {
			@Override
			public void changed(int newValue) {
				String v=""+((double)newValue/divided);
				int index=v.indexOf(".");
				if(index!=-1){
					v=v.substring(0,Math.min(v.length(), index+1+point));//this is easy than numberformat.
				}
				label.setText(text+""+v);
			}
		});
		return label;
	}
	
	
}
