/*
 * Copyright (C) 2011 aki@akjava.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.akjava.gwt.html5.client;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.user.client.ui.Widget;

public  class HTML5InputRange extends InputRangeWidget implements IInputRange{

	
	
	


	private ElementInputRange range;

	public HTML5InputRange(){
		range = new ElementInputRange();
		
		add((Widget) range);
	}
	
	
	
	
	@Override
	public void setSize(String width, String height) {
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setWidth(String width){
		
		super.setWidth(width);
		if(width.endsWith("px")){
			try{
				int w=Integer.parseInt(width.substring(0,width.length()-2));
				range.setWidth(w);
			}catch(Exception e){}
		}
	}
	
	@Override
	public void setHeight(String height){
		super.setHeight(height);
		if(height.endsWith("px")){
			try{
				int h=Integer.parseInt(height.substring(0,height.length()-2));
				range.setHeight(h);
			}catch(Exception e){}
		}
	}



	
	


	 public void setMax(int max){
		 range.setMax(max);
	 }
	
	 
	
	
	 public synchronized void addInputRangeListener(InputRangeListener listener){
		 range.addInputRangeListener(listener);
	 }
	 public synchronized void removeInputRangeListener(InputRangeListener listener){
		 range.removeInputRangeListener(listener);
	 }
	 
	


	@Override
	public void setMin(int min) {
		 range.setMin(min);
	}

	@Override
	public void setEnabled(boolean bool) {
		range.setEnabled(bool);
	}

	@Override
	public void setWidth(int width) {
		range.setWidth(width);
	}

	@Override
	public int getMin() {
		return range.getMin();
	}

	@Override
	public int getMax() {
		return range.getMax();
	}




	@Override
	public void setHeight(int height) {
		range.setHeight(height);
	}



	private LeafValueEditor<Integer> editor;
	@Override
	public LeafValueEditor<Integer> asEditor() {
		if (editor == null) {
		      editor = TakesValueEditor.of(this);
		    }
		    return editor;
	}
	public Integer getValue(){
		return range.getValue();
	 }
	@Override
	public void setValue(Integer value) {
		 range.setValue(value);
	}

}