package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JavaScriptObject;

public class FileError extends JavaScriptObject{
protected FileError(){}

public static final int NOT_FOUND_ERR=1;
public static final int SECURITY_ERR=2;

public static final int ABORT_ERR=3;
public static final int NOT_READABLE_ERR=4;
public static final int ENCODING_ERR=5;
public static final int NO_MODIFICATION_ALLOWED_ERR=6;
public static final int INVALID_STATE_ERR=7;
public static final int SYNTAX_ERR=8;
public static final int INVALID_MODIFICATION_ERR=9;
public static final int QUOTA_EXCEEDED_ERR=10;
public static final int TYPE_MISMATCH_ERR=11;
public static final int PATH_EXISTS_ERR=12;
public  final native int getCode() /*-{
return this.code;
}-*/;

}
