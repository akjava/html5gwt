package com.akjava.gwt.html5.client.file.ui;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.FilePathCallback;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;

/**
 * TODO 
 * how to know drop end?
 * @author aki
 *
 */
public abstract class AbstractVerticalDropPanel extends DropVerticalPanelBase {

	
	public abstract void dropStart();
	//public abstract void dropEnd();
	public abstract void dropFile(File file,String parent);
	public AbstractVerticalDropPanel(){
		this.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();

				
				dropStart();
				
				
				
				final JsArray<Item> items = FileUtils.transferToItem(event
						.getNativeEvent());
				final FilePathCallback callback = new FilePathCallback() {
					@Override
					public void callback(File file, String parent) {
						if(file!=null){
						dropFile(file,parent);
						}
					}
					
				};
			
				for(int i=0;i<items.length();i++){
					FileEntry entry = items.get(i).webkitGetAsEntry();
					entryCallback(entry,callback,"");
				}
				
			
			}
		});
	}
	private void entryCallback(final FileEntry entry,final FilePathCallback callback,String path){
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
	
}
