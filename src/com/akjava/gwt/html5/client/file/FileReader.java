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

import com.google.gwt.core.client.JavaScriptObject;

public class FileReader extends JavaScriptObject {
	protected FileReader(){}
	  public static final native FileReader createFileReader() /*-{
	     return new $wnd.FileReader();
	   }-*/;
	  
	  
	  public  final native void readAsText(File blob,String encode) /*-{
	    this.readAsText(blob,encode);
	   }-*/;
	  public  final native void readAsArrayBuffer(File blob) /*-{
	    this.readAsArrayBuffer(blob);
	   }-*/;
	  
	  public  final native void readAsDataURL(File blob) /*-{
	    this.readAsDataURL(blob);
	   }-*/;
	  
		public  final native String getResultAsString()/*-{
	    return this.result;
	  }-*/;
		
		public  final native Uint8Array getResultAsBuffer()/*-{
			if(this.result){
				return new $wnd.Uint8Array(this.result);
			}else{
				return null;
			}
	    
	  }-*/;
		
	  
	  public  final native void setOnLoad(FileHandler handler) /*-{
	  	this.onload=function(event){
	  		handler.@com.akjava.gwt.html5.client.file.FileHandler::onLoad()();
	  	};
	   }-*/;
	  
}
