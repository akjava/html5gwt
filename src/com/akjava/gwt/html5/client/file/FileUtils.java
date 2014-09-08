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
package com.akjava.gwt.html5.client.file;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.Item;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Timer;


public class FileUtils {

	/*
	 * can not shift
	 */
	public static final native JsArray<File> toFile(NativeEvent event)/*-{
    return event.target.files;
  }-*/;
	
	public static final native JsArray<File> transferToFile(NativeEvent event)/*-{
    return event.dataTransfer.files;
  }-*/;
	public static final native JsArray<Item> transferToItem(NativeEvent event)/*-{
    return event.dataTransfer.items;
  }-*/;
	
	
	public static List<FileEntry> itemsToFileEntryList(JsArray<Item> items){
		List<FileEntry> entrys=new ArrayList<FileEntry>();
		for(int i=0;i<items.length();i++){
			entrys.add(items.get(i).webkitGetAsEntry());
		}
		return entrys;
	}



	public static FileUploadForm createSingleFileUploadForm(final DataURLListener listener){
		return createSingleFileUploadForm(listener, true, true);//change default support drop
	}
	public static FileUploadForm createSingleFileUploadForm(final DataURLListener listener,final boolean reset){
		return createSingleFileUploadForm(listener, reset, true);//change default support drop
	}
		
	//TODO support other case
	/*
	 * why need reset because sometime you would like to re-upload modified same file.in such case it need reset though it's use change-handler.
	 */
	public static FileUploadForm createSingleFileUploadForm(final DataURLListener listener,final boolean reset,boolean supportOnDrop){
		final FileUploadForm form=new FileUploadForm();
		form.getDropPanel().addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
					form.getDropPanel().setBorderWidth(2);
				}
			}
		});
		form.getDropPanel().addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}
			}
		});
		if(supportOnDrop){
		form.getDropPanel().addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {

				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}

				final FileReader reader = FileReader.createFileReader();
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				if (files.length() > 0) {
					reader.setOnLoad(new FileHandler() {
						@Override
						public void onLoad() {
							listener.uploaded(files.get(0), reader.getResultAsString());
							if(reset){
								form.reset();
							}
						}
					});
					reader.readAsDataURL(files.get(0));	
				}
			event.stopPropagation();//maybe stop root drop,//TODO 
			}
		});
		}
			
		form.getFileUpload().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				final FileReader reader=FileReader.createFileReader();
				final JsArray<File> files=FileUtils.toFile(event.getNativeEvent());
				if(files.length()>0){
				reader.setOnLoad(new FileHandler() {
					@Override
					public void onLoad() {
						listener.uploaded(files.get(0), reader.getResultAsString());
						if(reset){
							form.reset();
						}
					}
				});
				reader.readAsDataURL(files.get(0));
				}
				
			}
		});
		return form;
	}
	
	public static final native void log(String obj)/*-{
	$wnd.console.log(obj);
}-*/;
	
	
	public static FileUploadForm createSingleFileUploadForm(final DataArrayListener listener){
		return createSingleFileUploadForm(listener,true,true);
	}
	
	public static FileUploadForm createSingleFileUploadForm(final DataArrayListener listener,final boolean reset){
		return createSingleFileUploadForm(listener,reset,true);
	}
	/**
	 * 
	 * @param listener
	 * @param reset
	 * @param supportOnDrop ,if root dock catch drop nothing happen here.
	 * @return
	 */
	public static FileUploadForm createSingleFileUploadForm(final DataArrayListener listener,final boolean reset,boolean supportOnDrop){
		final FileUploadForm form=new FileUploadForm();
		form.getDropPanel().addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
					form.getDropPanel().setBorderWidth(2);
				}
			}
		});
		form.getDropPanel().addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}
			}
		});
		if(supportOnDrop){
		form.getDropPanel().addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {

				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}

				final FileReader reader = FileReader.createFileReader();
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				if (files.length() > 0) {
					reader.setOnLoad(new FileHandler() {
						@Override
						public void onLoad() {
							listener.uploaded(files.get(0), reader.getResultAsBuffer());
							if(reset){
								
								form.reset();
							}
						}
					});
					reader.readAsArrayBuffer(files.get(0));	
				}
				event.stopPropagation();//maybe stop root drop,//TODO 
			}
		});
		}
			
		form.getFileUpload().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				final FileReader reader=FileReader.createFileReader();
				final JsArray<File> files=FileUtils.toFile(event.getNativeEvent());
				if(files.length()>0){
				reader.setOnLoad(new FileHandler() {
					@Override
					public void onLoad() {
						listener.uploaded(files.get(0), reader.getResultAsBuffer());
						if(reset){
							
							form.reset();
							
						}
					}
				});
				reader.readAsArrayBuffer(files.get(0));
				}
				
			}
		});
		return form;
	}
	
	
	public static String getExtension(String name){
	int last=name.lastIndexOf(".");
	if(last!=-1){
		return name.substring(last+1);
	}
	return null;
	}
	
	/**
	 * 
	 * @param listener DataURLListener which get text-file
	 * @param reset do reset selection file name for same file re-upload.
	 * @return
	 */
	public static FileUploadForm createSingleTextFileUploadForm(final DataURLListener listener,final boolean reset){
		return createSingleTextFileUploadForm(listener,reset,"UTF-8");
	}
	public static FileUploadForm createSingleTextFileUploadForm(final DataURLListener listener,final boolean reset,final String textEncode){
		final FileUploadForm form=new FileUploadForm();
		form.getDropPanel().addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
					form.getDropPanel().setBorderWidth(2);
				}
			}
		});
		form.getDropPanel().addDragLeaveHandler(new DragLeaveHandler() {
			
			@Override
			public void onDragLeave(DragLeaveEvent event) {
				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}
			}
		});
		
		form.getDropPanel().addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {

				event.preventDefault();
				if(form.isShowDragOverBorder()){
				form.getDropPanel().setBorderWidth(0);
				}

				final FileReader reader = FileReader.createFileReader();
				final JsArray<File> files = FileUtils.transferToFile(event
						.getNativeEvent());
				if (files.length() > 0) {
					reader.setOnLoad(new FileHandler() {
						@Override
						public void onLoad() {
							listener.uploaded(files.get(0), reader.getResultAsString());
							if(reset){
								form.reset();
							}
						}
					});
					reader.readAsText(files.get(0),textEncode);	
				}
				event.stopPropagation();//maybe stop root drop,//TODO 
			}
			
		});
		//form.getFileUpload().add
		form.getFileUpload().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//GWT.log("onChange:");
				final FileReader reader=FileReader.createFileReader();
				final JsArray<File> files=FileUtils.toFile(event.getNativeEvent());
				if(files.length()>0){
				reader.setOnLoad(new FileHandler() {
					@Override
					public void onLoad() {
						listener.uploaded(files.get(0), reader.getResultAsString());
						if(reset){
							form.reset();
						}
					}
				});
				reader.readAsText(files.get(0),textEncode);
				}
				
			}
		});
		return form;
	}
	
	//TODO support drag&drop
	public static FileUploadForm createMultiFileUploadForm(final DataURLsListener listener,final boolean reset){
		final FileUploadForm form=new FileUploadForm();
		form.setMultiple(true);
		form.getFileUpload().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				final FileReader reader=FileReader.createFileReader();
				final JsArray<File> tmp=FileUtils.toFile(event.getNativeEvent());
				@SuppressWarnings("unchecked")
				final JsArray<File> files=(JsArray<File>) JsArray.createArray();
				for(int i=0;i<tmp.length();i++){
					files.push(tmp.get(i));
				}
				
				final List<File> dataFiles=new ArrayList<File>();
				final List<String> values=new ArrayList<String>();
				if(files.length()>0){
				reader.setOnLoad(new FileHandler() {
					@Override
					public void onLoad() {
						dataFiles.add(files.get(0));
						values.add(reader.getResultAsString());
						
						files.shift();
						if(files.length()==0){
							listener.uploaded(dataFiles,values);
							if(reset){
								form.reset();
							}
						}else{
							reader.readAsDataURL(files.get(0));
						}
						
						
					}
				});
				reader.readAsDataURL(files.get(0));
				}
				
			}
		});
		return form;
	}
	
	public interface DataArrayListener{
		public void uploaded(final File file,Uint8Array array);
	}
	
	public interface DataURLListener{//utf-8 text or base64
		public void uploaded(final File file,String text);
	}
	
	public interface DataURLsListener{
		public void uploaded(final List<File> files,List<String> values);
	}
	
	public interface DirectoryFileListListener<T>{
		public void onList(List<T> files);
	}
	
	public static void readDirectryFileNames(FileEntry directoryFile,final DirectoryFileListListener<String> listener){
		readDirectryFileNames(directoryFile,Predicates.<FileEntry>alwaysTrue(),listener);
	}
	public static void readDirectryFileNames(FileEntry directoryFile,final Predicate<FileEntry> fileEntryPredicate,final DirectoryFileListListener<String> listener){
		checkState(directoryFile.isDirectory(),"directoryFile is not directory");
		final DirectoryReader reader=directoryFile.getReader();
		
		final List<String> fileNames=new ArrayList<String>();
		
		DirectoryCallback callback=new DirectoryCallback() {
			@Override
			public void callback(JsArray<FileEntry> entries) {
				for(int i=0;i<entries.length();i++){
					FileEntry entry=entries.get(i);
					if(fileEntryPredicate.apply(entry)){
						fileNames.add(entry.getName());
					}
				}
				
				if(entries.length()>0){
					reader.readEntries(this);
				}else{
					listener.onList(fileNames);
				}
			}
		};
		
		
		reader.readEntries(callback);
	}
	
	public static void readDirectryFileUrls(FileEntry directoryFile,final Predicate<FileEntry> fileEntryPredicate,final DirectoryFileListListener<String> listener){
		checkState(directoryFile.isDirectory(),"directoryFile is not directory");
		final DirectoryReader reader=directoryFile.getReader();
		
		final List<String> fileNames=new ArrayList<String>();
		
		DirectoryCallback callback=new DirectoryCallback() {
			@Override
			public void callback(JsArray<FileEntry> entries) {
				for(int i=0;i<entries.length();i++){
					FileEntry entry=entries.get(i);
					if(fileEntryPredicate.apply(entry)){
						fileNames.add(entry.toURL());
					}
				}
				
				if(entries.length()>0){
					reader.readEntries(this);
				}else{
					listener.onList(fileNames);
				}
			}
		};
		
		
		reader.readEntries(callback);
	}
	
	/*
	 
	 FileUtils.readDirectryFileNames(file, new Predicate<FileEntry>() {
						
						@Override
						public boolean apply(FileEntry input) {
							// TODO Auto-generated method stub
							return false;
						}
					}, new DirectoryFileListListener<String>() {
						
						@Override
						public void onList(List<String> files) {
							// TODO Auto-generated method stub
							
						}
					});
	 
	 */
	public static void readDirectryFileEntries(FileEntry directoryFile,final DirectoryFileListListener<FileEntry> listener){
		readDirectryFileEntries(directoryFile,Predicates.<FileEntry>alwaysTrue(),listener);
	}
	public static void readDirectryFileEntries(FileEntry directoryFile,final Predicate<FileEntry> fileEntryPredicate,final DirectoryFileListListener<FileEntry> listener){
		checkState(directoryFile.isDirectory(),"directoryFile is not directory");
		final DirectoryReader reader=directoryFile.getReader();
		
		final List<FileEntry> files=new ArrayList<FileEntry>();
		
		DirectoryCallback callback=new DirectoryCallback() {
			@Override
			public void callback(JsArray<FileEntry> entries) {
				for(int i=0;i<entries.length();i++){
					FileEntry entry=entries.get(i);
					if(fileEntryPredicate.apply(entry)){
						files.add(entry);
					}
				}
				
				if(entries.length()>0){
					reader.readEntries(this);
				}else{
					listener.onList(files);
				}
			}
		};
		
		
		reader.readEntries(callback);
	}
	

}
