package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;

public class Uint8Array extends JavaScriptObject{
protected Uint8Array(){}
	public  final native int length()/*-{
    return this.length;
  }-*/;
	
	public  final native int get(int index)/*-{
    return this[index];
  }-*/;
	
	public static final Uint8Array createUint8(byte[] bytes){
		JsArrayInteger array=(JsArrayInteger) JsArrayInteger.createArray();
		for(int i=0;i<bytes.length;i++){
			array.push(bytes[i]);
		}
		return createUInt8(array);
	}
	public  static final native Uint8Array createUInt8(JsArrayInteger array)/*-{
   var uInt8Array = new $wnd.Uint8Array(array.length);
	  for (var i = 0; i < rawLength; ++i) {
	    uInt8Array[i] = array[i];
	  }
	  return uInt8Array;
  	}-*/;
	
	
	
}
