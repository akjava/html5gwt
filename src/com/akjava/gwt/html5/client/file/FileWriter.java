package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.callback.ProgressEventCallback;
import com.google.gwt.core.client.JavaScriptObject;

public class FileWriter extends JavaScriptObject {

	protected FileWriter(){}

	public  final native void test()/*-{
    this.onwriteend = function(e) {//ProgressEvent
        console.log('Write completed.');
        console.log(e);
      };

      this.onerror = function(e) {
        console.log('Write failed: ' + e.toString());
        console.log(e);
      };

      // Create a new Blob and write it to log.txt.
      var bb = new $wnd.BlobBuilder(); // Note: window.WebKitBlobBuilder in Chrome 12.
      var text='hello';
      
      this.write(bb.getBlob('text/plain'));
  	}-*/;
	
	
	public  final native void write(JavaScriptObject blob)/*-{
    this.write(blob);
	}-*/;
	
	public final long getLength(){
		return (long)length();
	}
	
	

	public  final native void truncate(double length)/*-{
    this.truncate(length);
	}-*/;

	
	public  final native void seek(double value)/*-{
    return this.seek(value);
	}-*/;
	
	public  final native double length()/*-{
    return this.length;
	}-*/;
	
	public  final native void setOnWriteEnd(ProgressEventCallback callback)/*-{
    this.onwriteend = function(e) {//ProgressEvent
        callback.@com.akjava.gwt.html5.client.file.callback.ProgressEventCallback::progressEventCallback(Lcom/akjava/gwt/html5/client/file/ProgressEvent;)(e)
      };
  	}-*/;
	
	public  final native void setOnError(ProgressEventCallback callback)/*-{
    this.onerror = function(e) {//ProgressEvent
        callback.@com.akjava.gwt.html5.client.file.callback.ProgressEventCallback::progressEventCallback(Lcom/akjava/gwt/html5/client/file/ProgressEvent;)(e)
      };
  	}-*/;
}
