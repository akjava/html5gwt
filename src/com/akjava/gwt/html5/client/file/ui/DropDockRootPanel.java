package com.akjava.gwt.html5.client.file.ui;

import java.util.List;

import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.FilePathCallback;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/*
 * this is usefull when do nothing drop rootPanel
 */
public abstract class DropDockRootPanel extends DockLayoutPanel implements FilePathCallback,HasDropHandlers, HasDragOverHandlers,
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



	public DropDockRootPanel(Unit unit,boolean addRootLayoutPanel){
		super(unit);
		
		//this.setStylePrimaryName("html5_drag_border");
		
		this.addDragOverHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
				dragOver();
				
			}
		});
		this.addDropHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();
				final JsArray<Item> items = FileUtils.transferToItem(event
						.getNativeEvent());
				
				List<FileEntry> entries=FileUtils.itemsToFileEntryList(items);
				onDropFiles(entries);
				dragEnd();
			}
		});
		this.addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
				dragEnd();
			}
		});
		
		if(addRootLayoutPanel){
			RootLayoutPanel.get().add(this);
		}
	}
	
	public void entryCallback(final FileEntry entry,final FilePathCallback callback,String path){
		if(entry==null){
			return;
		}
		if (entry.isFile()) {
			entry.file(callback,path);
		} else if (entry.isDirectory()) {
			entry.getReader().readEntries(
					new DirectoryCallback() {
						@Override
						public void callback(
								JsArray<FileEntry> entries) {
							callback.callback(null, entry.getFullPath());
							for (int j = 0; j < entries
									.length(); j++) {
								entryCallback(entries.get(j),callback,entry.getFullPath());
							}
						}
					});
		}
	}
	
	public  void onDropFiles(List<FileEntry> files){
		for(FileEntry file:files){
			entryCallback(file,this,"");
		}
	}

	public  void dragOver(){
	
	}
	public  void dragEnd(){
		
	}
}
