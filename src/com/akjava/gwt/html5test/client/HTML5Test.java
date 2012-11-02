package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.Unit8Array;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.FilePathCallback;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HTML5Test implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		TabPanel tab=new TabPanel();
		RootPanel.get().add(tab);
		
		VerticalPanel root = new VerticalPanel();
		tab.add(root,"File upload");

		root.add(new Label("simple file upload,drop a text file here"));
		final TextArea area = new TextArea();
		area.setSize("400px", "200px");

		area.addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {

				event.preventDefault();
			}
		});

		final boolean asString = true;
		area.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {

				event.preventDefault();

				final FileReader reader = FileReader.createFileReader();
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				GWT.log("length:" + files.length());
				if (files.length() > 0) {

					reader.setOnLoad(new FileHandler() {
						@Override
						public void onLoad() {
							log("loaded:");
							String text = "";

							if (asString) {
								text = reader.getResultAsString();
							} else {
								Unit8Array array = reader.getResultAsBuffer();
								log("length:" + array.length());

								StringBuilder builder = new StringBuilder();
								for (int i = 0; i < array.length(); i++) {
									builder.append((char) array.get(i));

								}

								builder.toString();
							}

							area.setText(text);

						}
					});

					log(files.get(0));
					if (asString) {
						reader.readAsText(files.get(0), "UTF-8");
					} else {
						reader.readAsArrayBuffer(files.get(0));
					}

				}

				// event.stopPropagation();

			}
		});
		root.add(area);

		root.add(new Label("chrome folder support,drop multiple file here"));
		final TextArea area2 = new TextArea();
		area2.setSize("400px", "200px");

		area2.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {

				event.preventDefault();

				final FilePathCallback callback = new FilePathCallback() {
					@Override
					public void callback(File file,String path) {
						log(file);
						String name=file!=null?file.getFileName() :"";
						String size=file!=null?""+file.getFileSize():"";
						String newText = path+"/"+name+ ","
								+ size;
						
						String old = area2.getText();
						area2.setText(old + newText+"\n");

					}
				};

				final JsArray<Item> items = FileUtils.transferToItem(event
						.getNativeEvent());
				GWT.log("length:" + items.length());
				if (items.length() > 0) {
					for (int i = 0; i < items.length(); i++) {
						log(items.get(i).webkitGetAsEntry());

						FileEntry entry = items.get(i).webkitGetAsEntry();

						entryCallback(entry,callback,"");

					}

				}


			}
		});
		root.add(area2);
		
		tab.add(new InputRangeTest(),"Input Range");
		tab.add(new FileWriteTest(),"File Download");
		tab.add(new FileSystemTest(),"File System");
		tab.selectTab(3);
	}
	
	public void entryCallback(final FileEntry entry,final FilePathCallback callback,String path){
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

	public static final native void doit(JavaScriptObject obj)/*-{
		var buf = new $wnd.Uint8Array(obj);
		$wnd.console.log(buf);
		for ( var i = 0; i < buf.length; i++) {
			$wnd.console.log(String.fromCharCode(buf[i]));
		}
	}-*/;

	/*
	 * can not shift
	 */
	public static final native void log(JavaScriptObject obj)/*-{
		$wnd.console.log(obj);
	}-*/;

	public static final native void log(String obj)/*-{
		$wnd.console.log(obj);
	}-*/;

}
