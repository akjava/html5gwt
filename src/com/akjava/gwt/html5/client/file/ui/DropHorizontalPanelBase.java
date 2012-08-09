package com.akjava.gwt.html5.client.file.ui;

import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.HasDragLeaveHandlers;
import com.google.gwt.event.dom.client.HasDragOverHandlers;
import com.google.gwt.event.dom.client.HasDropHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author aki
 *
 */
public class DropHorizontalPanelBase extends VerticalPanel implements HasDropHandlers, HasDragOverHandlers,
HasDragLeaveHandlers{

	  @Override
	    public HandlerRegistration addDropHandler(DropHandler handler) {
	        return addBitlessDomHandler(handler, DropEvent.getType());
	    }

	    @Override
	    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
	        return addBitlessDomHandler(handler, DragOverEvent.getType());
	    }

	    @Override
	    public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
	        return addBitlessDomHandler(handler, DragLeaveEvent.getType());
	    }
	
}