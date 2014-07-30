package com.akjava.gwt.html5.client.file.webkit;

import com.akjava.gwt.html5.client.file.DirectoryReader;
import com.akjava.gwt.html5.client.file.callback.FileEntryCallback;
import com.akjava.gwt.html5.client.file.callback.FileErrorCallback;
import com.akjava.gwt.html5.client.file.callback.FileWriterCallback;
import com.akjava.gwt.html5.client.file.callback.VoidCallback;
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
	

	//for directory only
	public  final native DirectoryReader getReader()/*-{
    return this.createReader();
  	}-*/;
	
	//TODO implement copyTo,moveTo

	//TODO implement resolveLocalFileSystemURL 
	
	public  final native String toURL()/*-{
    return this.toURL();
  	}-*/;
	
	
	
	
public final native void remove(VoidCallback simpleCallback,FileErrorCallback errorCallback)/*-{
    
    var callback=function(){
    	simpleCallback.@com.akjava.gwt.html5.client.file.callback.VoidCallback::callback()();
    }
    
     var error=function(fileerror){
    	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
    }
    
    this.remove(callback,error);
  	}-*/;

public final native void removeRecursively(VoidCallback simpleCallback,FileErrorCallback errorCallback)/*-{

var callback=function(){
	simpleCallback.@com.akjava.gwt.html5.client.file.callback.VoidCallback::callback()();
}

 var error=function(fileerror){
	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
}

this.removeRecursively(callback,error);
	}-*/;

//TODO implement  removeRecursively()
	
//TODO implement copyTo,moveTo;

	
	public final native void getFile(String name,boolean create,boolean exclusive,FileEntryCallback callback,FileErrorCallback errorCallback)/*-{
    
    var getfile=function(fileentry){
    	callback.@com.akjava.gwt.html5.client.file.callback.FileEntryCallback::fileEntryCallback(Lcom/akjava/gwt/html5/client/file/webkit/FileEntry;)(fileentry);
    
    }
    
    var error=function(fileerror){
    	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
    }
    
    this.getFile(name,{create:create,exclusive:exclusive},getfile,error);
  	}-*/;
	
	public final native void getDirectory(String name,boolean create,boolean exclusive,FileEntryCallback callback,FileErrorCallback errorCallback)/*-{
    
    var getfile=function(fileentry){
    	callback.@com.akjava.gwt.html5.client.file.callback.FileEntryCallback::fileEntryCallback(Lcom/akjava/gwt/html5/client/file/webkit/FileEntry;)(fileentry);
    
    }
    
    var error=function(fileerror){
    	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
    }
    
    this.getDirectory(name,{create:create,exclusive:exclusive},getfile,error);
  	}-*/;
	
public final native void createWriter(FileWriterCallback writerCallback,FileErrorCallback errorCallback)/*-{
    
    var createWriter=function(filewriter){
    	writerCallback.@com.akjava.gwt.html5.client.file.callback.FileWriterCallback::createWriterCallback(Lcom/akjava/gwt/html5/client/file/FileWriter;)(filewriter);
    }
    
    var error=function(fileerror){
    	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
    }
    
    this.createWriter(createWriter,error);
  	}-*/;
	
}
