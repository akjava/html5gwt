package com.akjava.gwt.html5.client.file.ui;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.ui.DropVerticalPanelBase;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.FilePathCallback;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.common.base.Optional;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/*
 * this is usefull when do nothing drop rootPanel
 */
public abstract class DropVerticalRootPanel extends DropVerticalPanelBase implements FilePathCallback{

	public DropVerticalRootPanel(boolean addRootLayoutPanel){
		this.setSize("100%", "100%");
		this.setBorderWidth(0);
		this.setStylePrimaryName("html5_drag_border");
		
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
	
	protected int loadFileIndex;//reset on drop start,increment in callback by yourselef
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
		loadFileIndex=0;
		for(FileEntry file:files){
			entryCallback(file,this,"");
		}
	}

	public  void dragOver(){
		this.setBorderWidth(4);
	}
	public  void dragEnd(){
		this.setBorderWidth(0);
	}
}
