package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class ProgressEvent extends JavaScriptObject{
protected ProgressEvent(){}

public  final native String getType()/*-{
return this.type;
}-*/;

}
