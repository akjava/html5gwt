package com.akjava.gwt.html5.client.file.webkit;


public class WebkitStorageInfo {

	
	public  static final native void test()/*-{
    
	   
  
    
	$wnd.webkitStorageInfo.requestQuota($wnd.PERSISTENT, 100, function(size){
	console.log(size);	
	});
	
    }-*/;
	
	
	
	public  static final native void requestQuota(int type,int size,RequestQuotaCallback requestQuotaCallback)/*-{
    
   
    var quotaCallback=function(callback){
    	requestQuotaCallback.@com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo$RequestQuotaCallback::requestQuotaCallback(I)(callback);
    };
    
    
    
   
    
	$wnd.webkitStorageInfo.requestQuota(type, size, quotaCallback);
    }-*/;
	
	public  static final native void queryUsageAndQuota(int type,StorageInfoUsageCallback storageInfoUsageCallback)/*-{
    
	   
    var storageCallback=function(usage,quota){
    	storageInfoUsageCallback.@com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo$StorageInfoUsageCallback::storageInfoUsageCallback(DD)(usage,quota);
    };
    
    
	$wnd.webkitStorageInfo.queryUsageAndQuota(type, storageCallback);
    }-*/;
	
	public static interface RequestQuotaCallback{
		public void requestQuotaCallback(int grantedBytes);
	}
	
	public static interface StorageInfoUsageCallback {
		public void storageInfoUsageCallback(double currentUsageInBytes,double currentQuotaInBytes);
	}
}
