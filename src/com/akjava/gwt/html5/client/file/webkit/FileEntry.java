package com.akjava.gwt.html5.client.file.webkit;

import com.akjava.gwt.html5.client.file.DirectoryReader;
import com.google.gwt.core.client.JavaScriptObject;

public class FileEntry extends JavaScriptObject{

	protected FileEntry(){}
	
	public  final native boolean isFile()/*-{
    return this.isFile;
  	}-*/;
	
	public  final native boolean isDirectory()/*-{
    return this.isDirectory;
  	}-*/;
	
	public  final native String getName()/*-{
    return this.name;
  	}-*/;
	
	public  final native String getFullPath()/*-{
    return this.fullPath;
  	}-*/;
	
	//TODO support error
	public  final native void file(FileCallback callback)/*-{
   
    this.file( function(file){
    	callback.@com.akjava.gwt.html5.client.file.webkit.FileCallback::callback(Lcom/akjava/gwt/html5/client/file/File;)(file)
    });
    
  	}-*/;
	
	//TODO support error
	public  final native void file(FilePathCallback callback,String parent)/*-{
   
    this.file( function(file){
    	callback.@com.akjava.gwt.html5.client.file.webkit.FilePathCallback::callback(Lcom/akjava/gwt/html5/client/file/File;Ljava/lang/String;)(file,parent)
    });
    
  	}-*/;
	

	
	public  final native DirectoryReader getReader()/*-{
    return this.createReader();
  	}-*/;
	
}
