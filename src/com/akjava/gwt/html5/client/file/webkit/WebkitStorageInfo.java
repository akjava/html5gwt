package com.akjava.gwt.html5.client.file.webkit;

/**
 * @deprecated
 * @author aki
 *
 */
public class WebkitStorageInfo {

	

	
	
	
	public  static final native void requestQuota(int type,double size,RequestQuotaCallback requestQuotaCallback)/*-{
    
   
    var quotaCallback=function(callback){
    	requestQuotaCallback.@com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo$RequestQuotaCallback::requestQuotaCallback(D)(callback);
    };
    
    
    if(type==0){
	$wnd.navigator.webkitTemporaryStorage.requestQuota(size, quotaCallback);
  	}else{
  	$wnd.navigator.webkitPersistentStorage.requestQuota(size, quotaCallback);
  	}
   
    }-*/;
	
	public  static final native void queryUsageAndQuota(int type,StorageInfoUsageCallback storageInfoUsageCallback)/*-{
    
	   
    var storageCallback=function(usage,quota){
    	storageInfoUsageCallback.@com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo$StorageInfoUsageCallback::storageInfoUsageCallback(DD)(usage,quota);
    };
  	if(type==0){
	$wnd.navigator.webkitTemporaryStorage.queryUsageAndQuota(storageCallback);
  	}else{
  	$wnd.navigator.webkitPersistentStorage.queryUsageAndQuota(storageCallback);	
  	}
    }-*/;
	
	public static interface RequestQuotaCallback{
		public void requestQuotaCallback(double grantedBytes);
	}
	
	public static interface StorageInfoUsageCallback {
		public void storageInfoUsageCallback(double currentUsageInBytes,double currentQuotaInBytes);
	}
}
