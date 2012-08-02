package com.akjava.gwt.html5.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusWidget;

public class ElementInputRange extends FocusWidget implements HasChangeHandlers,InputRange{

	public ElementInputRange(int min,int max,int current){
		super(RangeElement.createRangeElement(Document.get(),min,max,current));
		this.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				fireEvent();
			}
		});
	}
	 protected RangeElement getRangeElement() {
		    return getElement().cast();
		  }
	 public int getValue(){
		 return Integer.parseInt(getRangeElement().getValue());
	 }
	 public void setMax(int max){
		 getRangeElement().setMax(max);
	 }
	 public void setValue(int value){
		 getRangeElement().setValue(value);
		 
		 fireEvent();
		 /*
		  * trying  fire event
		 NativeEvent nativeEvent=(NativeEvent) NativeEvent.createObject();
		 ChangeEvent.fireNativeEvent(nativeEvent, this);
		 */
	 }
	 

	 private List<InputRangeListener> listeners=new ArrayList<InputRangeListener>();
	 public synchronized void addListener(InputRangeListener listener){
		 listeners.add(listener);
	 }
	 public synchronized void removeListener(InputRangeListener listener){
		 listeners.remove(listener);
	 }
	 
	 public synchronized void fireEvent(){
		 for(InputRangeListener listener:listeners){
			 listener.changed(getValue());
		 }
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

	 /*
	  * better to use addListener
	  * (non-Javadoc)
	  * @see com.google.gwt.event.dom.client.HasChangeHandlers#addChangeHandler(com.google.gwt.event.dom.client.ChangeHandler)
	  */
	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
	    return addDomHandler(handler, ChangeEvent.getType());
	}
	@Override
	public void setMin(int min) {
		 getRangeElement().setMin(min);
	}
}
