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
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Don't forget add both addDropHandler & addDragOverHandler
 * addDragLeaveHandler is option
 do like
 
 rootPanel.addDragOverHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
			}
		});
		rootPanel.addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
			}
		});
		rootPanel.addDropHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();
				final FileReader reader = FileReader.createFileReader();
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				if (files.length() > 0) {
					reader.setOnLoad(new FileHandler() {
						@Override
						public void onLoad() {
							String text=reader.getResultAsString();
							//do something
						}
					});
					reader.readAsDataURL(files.get(0));	
				}
			}
		});
		
 */
public class DropVerticalPanelBase extends VerticalPanel implements HasDropHandlers, HasDragOverHandlers,
HasDragLeaveHandlers{

	/**
	 * 
	 */
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
	
	    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
	        return addDomHandler(handler, MouseWheelEvent.getType());
	      }
}