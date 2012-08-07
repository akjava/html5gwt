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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class HTML5InputRange extends FocusPanel implements InputRange{

	InputRange range;
	public HTML5InputRange(int min,int max,int current){
		String agent=Window.Navigator.getUserAgent();
		//GWT.log(agent);
		if(agent.indexOf("Safari")!=-1){
			range=new ElementInputRange(min, max, current);
		}else{
			range=new CanvasInputRange(min, max, current);
		}
		add((Widget) range);
	}

	 public int getValue(){
		return range.getValue();
	 }
	 public void setMax(int max){
		 range.setMax(max);
	 }
	 public void setValue(int value){
		
		 range.setValue(value);
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
}