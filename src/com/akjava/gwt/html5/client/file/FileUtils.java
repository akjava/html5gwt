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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Element;

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
	
	
	/*
	 * dont work yet
	 */
	public static final native void addDropListener(Element element,DropListener listener)/*-{
	element.addEventListener("dragenter", function(e){
	console.log("enter");	
	 if(e.preventDefault) { e.preventDefault(); }
                return false;
	}, false);
		
    element.addEventListener("drop",function(e){
    	e.preventDefault();
    	console.log("dropped");
    listener.@com.akjava.gwt.html5.client.file.DropListener::onDrop(Lcom/google/gwt/core/client/JsArray;)(e.dataTransfer.files);
     },false);
  }-*/;
	
	//TODO support other case
	/*
	 * why need reset because sometime you would like to re-upload modified same file.in such case it need reset though it's use change-handler.
	 */
	public static FileUploadForm createSingleFileUploadForm(final DataURLListener listener,final boolean reset){
		final FileUploadForm form=new FileUploadForm();
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
	
	public interface DataURLListener{
		public void uploaded(File file,String value);
	}
	
	public interface DataURLsListener{
		public void uploaded(List<File> files,List<String> values);
	}
	

}
