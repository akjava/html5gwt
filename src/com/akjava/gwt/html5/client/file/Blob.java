package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

public class Blob extends JavaScriptObject{

	public static String TYPE_APPLICATION_OCTET_STREM="application/octet-stream";
	
	protected Blob(){}
	
	public   final native double getSize()/*-{
    return this.size;
  	}-*/;
	public   final native String getType()/*-{
    return this.type;
  	}-*/;
	
	
	public  static final  Blob createBlob(JavaScriptObject object,String type){
	@SuppressWarnings("unchecked")
	JsArray<JavaScriptObject> arrays=(JsArray<JavaScriptObject>) JsArray.createArray();
	arrays.push(object);
	return createBlob(arrays,type);
	}
	
	public  static final native Blob createBlob(String text,String type)/*-{
    return new $wnd.Blob([text],{type:type});
  	}-*/;
	
	public  static final native Blob createBlob(String text)/*-{
    return new $wnd.Blob([text],{type:"text/plain;charset=UTF-8"});
  	}-*/;
	
	public  static final native Blob createBlob(JsArray<JavaScriptObject> arrays,String type)/*-{
    return new $wnd.Blob(arrays,{type:type});
  	}-*/;
	
	public static final Blob createBlob(byte[] bytes){
		Uint8Array array=Uint8Array.createUint8(bytes);
		return createBlob(array, TYPE_APPLICATION_OCTET_STREM);
	}
	
	public  static final native Blob createBlob(ArrayBuffer buffer)/*-{
    return new $wnd.Blob([buffer]);
  	}-*/;
	
	public  static final native Blob createBlob(Uint8Array arrays,String type)/*-{
    return new $wnd.Blob([arrays],{type:type});
  	}-*/;
	/**
	 * support not base64 contain text
	 * @param dataURI
	 * @param type
	 * @return
	 */
	public  static final native Blob createBase64Blob(String dataURI,String type)/*-{
	 var BASE64_MARKER = ';base64,';
	  var index=dataURI.indexOf(BASE64_MARKER);
	  var base64Index=0;
	  if(index!=-1){
	  	 base64Index=index  + BASE64_MARKER.length
	  }
	  var base64 = dataURI.substring(base64Index);
	  var raw = $wnd.atob(base64);
	  var rawLength = raw.length;
	  var uInt8Array = new $wnd.Uint8Array(rawLength);

	  for (var i = 0; i < rawLength; ++i) {
	    uInt8Array[i] = raw.charCodeAt(i);
	  }
    return new $wnd.Blob([uInt8Array],{type:type});
  	}-*/;
	
	
	

}
