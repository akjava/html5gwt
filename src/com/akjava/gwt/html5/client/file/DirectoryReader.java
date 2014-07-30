package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.google.gwt.core.client.JavaScriptObject;

public class DirectoryReader extends JavaScriptObject{
protected DirectoryReader(){}

/*only callback 100 entries,so call again and again until empty
 * https://code.google.com/p/chromium/issues/detail?id=378883
 */
//TODO support error
public  final native void readEntries(DirectoryCallback callback)/*-{

this.readEntries( function(entries){
	//$wnd.console.log(entries);
	callback.@com.akjava.gwt.html5.client.file.webkit.DirectoryCallback::callback(Lcom/google/gwt/core/client/JsArray;)(entries)
});
	}-*/;

}
