package com.akjava.gwt.html5.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.FocusPanel;

/**
 * some how this is panel
 * @author aki
 *
 */
public abstract class InputRangeWidget extends FocusPanel implements IInputRange,IsEditor<LeafValueEditor<Integer>>,TakesValue<Integer>{
	public InputRangeWidget(){}
	
	public static InputRangeWidget createInputRange(int min,int max,int current){
		InputRangeWidget range=GWT.create(InputRangeWidget.class);
		range.setMin(min);
		range.setMax(max);
		((TakesValue<Integer>)range).setValue(current);
		return range;
	}
}
