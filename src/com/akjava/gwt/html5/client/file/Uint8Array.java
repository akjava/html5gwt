package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

/*
 * i can't solve this
 * com.google.gwt.dev.jjs.InternalCompilerException: Already seen an implementing JSO subtype (Uint8ArrayImpl) for interface (ArrayBufferView) while examining newly-added type (ArrayBufferViewNative). This is a bug in JSORestrictionsChecker.

 */
public class Uint8Array extends JavaScriptObject {
protected Uint8Array(){}

/**
 * should id do boule?
 * @return
 */
	public  final native int length()/*-{
    return this.length;
  }-*/;
	
	public  final native int get(int index)/*-{
    return this[index];
  }-*/;
	
	
	public  final native ArrayBuffer getBuffer()/*-{
    return this.buffer;
  }-*/;
	
	public  static final native Uint8Array createUint8(ArrayBuffer buffer)/*-{
	  var uInt8Array = new $wnd.Uint8Array(buffer);
	  return uInt8Array;
	}-*/;
	
	public  static final native Uint8Array createUint8(int length)/*-{
    var uInt8Array = new $wnd.Uint8Array(length);
	  return uInt8Array;
	}-*/;
	
	public  static final native Uint8Array createUint8(byte[] bytes)/*-{
	      var uInt8Array = new $wnd.Uint8Array(bytes);
		  return uInt8Array;
	  	}-*/;
	
	/*
	public static final Uint8Array createUint8(byte[] bytes){
		JsArrayInteger array=(JsArrayInteger) JsArrayInteger.createArray();
		for(int i=0;i<bytes.length;i++){
			array.push(bytes[i]);
		}
		return createUInt8(array);
	}*/
	
	public  static final native Uint8Array createUInt8(JsArrayInteger array)/*-{
   var uInt8Array = new $wnd.Uint8Array(array.length);
	  for (var i = 0; i < array.length; ++i) {
	    uInt8Array[i] = array[i];
	  }
	  return uInt8Array;
  	}-*/;
	public final native  void set(int i, int v) /*-{
		this[i]=v;
	}-*/;
	
	public final byte[] toByteArray(){
		byte[] bt=new byte[this.length()];
		for(int j=0;j<this.length();j++){
			bt[j]=(byte) this.get(j);
		}
		return bt;
	}

	public final Uint8ArrayNative convertToNative(){
		return this.cast();
	}

	
	
}
