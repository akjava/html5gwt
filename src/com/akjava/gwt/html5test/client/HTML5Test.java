package com.akjava.gwt.html5test.client;

import java.util.List;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;
import com.akjava.gwt.html5.client.file.FileUploadForm;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.FileUtils.DataURLListener;
import com.akjava.gwt.html5.client.file.FileUtils.DataURLsListener;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.akjava.gwt.html5.client.file.ui.DropVerticalPanelBase;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.FilePathCallback;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HTML5Test implements EntryPoint {

	public static DropVerticalPanelBase dropPanel;
	private TextArea firstTextArea;
	public static void doneDrop(){
		dropPanel.setBorderWidth(0);
	}
	@Override
	public void onModuleLoad() {
		dropPanel=new DropVerticalPanelBase();
		dropPanel.setSize("100%", "100%");
		dropPanel.setBorderWidth(0);
		dropPanel.setStylePrimaryName("border");
		RootLayoutPanel.get().add(dropPanel);
		dropPanel.addDragOverHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
				//GWT.log("drag over");
				dropPanel.setBorderWidth(4);
			}
		});
		dropPanel.addDropHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();
				//GWT.log("drag over");
			}
		});
		dropPanel.addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
				doneDrop();
				//GWT.log("drag leave");
			}
		});
		
		
		TabPanel tab=new TabPanel();
		dropPanel.add(tab);
		
		VerticalPanel root = new VerticalPanel();
		tab.add(root,"File upload");

		root.add(new Label("Text file upload Test,drop a text file at textarea  and show text"));
		firstTextArea = new TextArea();
		firstTextArea.setSize("400px", "200px");

		firstTextArea.addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {

				event.preventDefault();
			}
		});

		FileUploadForm fileUpload=FileUtils.createSingleTextFileUploadForm(new DataURLListener() {
			@Override
			public void uploaded(File file, String text) {
				firstTextArea.setText(text);
			}
		}, false);
		root.add(fileUpload);
		
		firstTextArea.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {
				onDropText(event);
			}
		});
		root.add(firstTextArea);

		root.add(new Label("Chrome folder support test,drop folder at textarea and show file list"));
		final TextArea area2 = new TextArea();
		FileUploadForm multiUpload=FileUtils.createMultiFileUploadForm(new DataURLsListener() {
			
			@Override
			public void uploaded(List<File> files, List<String> values) {
				for(File file:files){
				String name=file!=null?file.getFileName() :"";
				String size=file!=null?""+file.getFileSize():"";
				String newText = ""+"/"+name+ ","
						+ size;
				
				String old = area2.getText();
				area2.setText(old + newText+"\n");
				}
			}
		}, false);
		root.add(multiUpload);
		multiUpload.setTitle("can select multifile,but only same directory");
		
		
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

				doneDrop();
			}
		});
		root.add(area2);
		
		
		
		DropVerticalPanelBase dropBase=new DropVerticalPanelBase();
		//need some content
		dropBase.add(new Label("Drop VerticalPanel Test,drop a file below and alert file name"));
		dropBase.addDropHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				GWT.log("length:" + files.length());
				
				Window.alert(""+files.get(0).getFileName());
				event.preventDefault();
				doneDrop();
			}
		});
		root.add(dropBase);
		//need this after chrome 27?
		dropBase.addDragOverHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				
			}
		});
		dropBase.setSize("400px", "200px");
		/*
		dropBase.add(new Button("test"));
		dropBase.add(new TextArea());
		Canvas c=Canvas.createIfSupported();
		dropBase.add(c);
		*/
		
		
		tab.add(new InputRangeTest(),"Input Range");
		tab.add(new FileWriteTest(),"File Download");
		tab.add(new FileSystemTest(),"File System");
		
		tab.add(new SpeechTest(),"Speech Synth");
		tab.add(new RecognizeTest(),"Speech Recognize");
		
		int index=0;
		String token=History.getToken();
		List<String> labels=Lists.newArrayList("upload","range","download","filesystem","synth","recognize");
		for(int i=0;i<labels.size();i++){
			if(labels.get(i).equals(token)){
				index=i;
				break;
			}
		}
		
		tab.selectTab(index);
		
		dropPanel.add(new Anchor("Github Project Page","https://github.com/akjava/html5gwt"));
	}
	private void onDropText(DropEvent event){
		final boolean asString = true;

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
						Uint8Array array = reader.getResultAsBuffer();
						log("length:" + array.length());

						StringBuilder builder = new StringBuilder();
						for (int i = 0; i < array.length(); i++) {
							builder.append((char) array.get(i));

						}

						builder.toString();
					}

					firstTextArea.setText(text);

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
		doneDrop();
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
