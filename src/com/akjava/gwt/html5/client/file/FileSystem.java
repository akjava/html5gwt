package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.google.gwt.core.client.JavaScriptObject;

public class FileSystem extends JavaScriptObject{
protected FileSystem(){}


public  final native String getName() /*-{
return this.name;
}-*/;

public  final native FileEntry getRoot() /*-{
return this.root;
}-*/;


}
