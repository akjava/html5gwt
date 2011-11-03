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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FocusWidget;

public class HTML5InputRange extends FocusWidget{

	public HTML5InputRange(int min,int max,int current){
		super(RangeElement.createRangeElement(Document.get(),min,max,current));
	}
	 protected RangeElement getRangeElement() {
		    return getElement().cast();
		  }
	 public int getValue(){
		 return Integer.parseInt(getRangeElement().getValue());
	 }
	 public void setValue(int value){
		 getRangeElement().setValue(value);
	 }
	 
	 public static class RangeElement extends Element{
		 protected RangeElement(){}
		  public final native String getValue() /*-{
		     return this.value;
		   }-*/;
		  public final native void setValue(int value) /*-{
		     this.value = value;
		   }-*/;
		  public final native int getMin() /*-{
		     return this.min;
		   }-*/;
		  public final native void setMin(int value) /*-{
		     this.min = value;
		   }-*/;
		  public final native int getMax() /*-{
		     return this.max;
		   }-*/;
		  public final native void setMax(int value) /*-{
		     this.max = value;
		   }-*/;
		  
		  public static final native RangeElement createRangeElement(Document doc,int min,int max,int value) /*-{
		    var e = doc.createElement("INPUT");
		    e.type = 'range';
		    e.min=min;
		    e.max=max;
		    e.value=value;
		    return e;
		  }-*/;
	}
}
