package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.callback.FileErrorCallback;

public class RequestFileSystem {
/*
 * window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;

window.requestFileSystem(type, size, successCallback, opt_errorCallback)
 */
	
	public static final int TEMPORARY=0;
	public static final int PERSISTENT=1;
	
	public  static final native void requestFileSystem(int type,double size,FileSystemCallback successCallback,FileErrorCallback errorCallback)/*-{
    
    $wnd.requestFileSystem  = $wnd.requestFileSystem || $wnd.webkitRequestFileSystem;
    
    
    var success=function(filesystem){
    	successCallback.@com.akjava.gwt.html5.client.file.RequestFileSystem$FileSystemCallback::fileSystemCallback(Lcom/akjava/gwt/html5/client/file/FileSystem;)(filesystem);
    };
    var error=function(fileerror){
    	errorCallback.@com.akjava.gwt.html5.client.file.callback.FileErrorCallback::fileErrorCallback(Lcom/akjava/gwt/html5/client/file/FileError;)(fileerror);
    };
    
   
    
	$wnd.requestFileSystem(type, size, success, error)
    }-*/;
	
	
	public static interface FileSystemCallback{
		public void fileSystemCallback(FileSystem fileSystem);
	}
}
