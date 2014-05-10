package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

public class UserMedia extends JavaScriptObject{
	protected UserMedia(){}

	public final static native void getUserMedia(boolean audio,SuccessListener onSuccess,ErrorListener onError)/*-{
	$wnd.navigator.getUserMedia=$wnd.navigator.getUserMedia || $wnd.navigator.webkitGetUserMedia;

	$wnd.navigator.getUserMedia({audio:audio}
	, function(loaclMediaStream){
	onSuccess.@com.akjava.gwt.html5.client.media.UserMedia.SuccessListener::onSuccess(Lcom/akjava/gwt/html5/client/media/LocalMediaStream;)(loaclMediaStream);
	}
	, function(error){
	onError.@com.akjava.gwt.html5.client.media.UserMedia.ErrorListener::onError(Lcom/akjava/gwt/html5/client/media/UserMediaError;)(error);	
	}
	);
	console.log("called");
	}-*/;
	
	public final static native boolean isAvailable()/*-{
	if($wnd.navigator.getUserMedia || $wnd.navigator.webkitGetUserMedia){
		return true;
	}else{
		return false ;
	}
	}-*/;
	
	
	public static interface SuccessListener{
		public void onSuccess(LocalMediaStream loaclMediaStream);
	}
	
	public static interface ErrorListener{
		public void onError(UserMediaError error);
	}
}
