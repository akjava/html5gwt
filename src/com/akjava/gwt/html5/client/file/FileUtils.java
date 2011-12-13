package com.akjava.gwt.html5.client.file;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;

public class FileUtils {

	public static final native JsArray<File> toFile(NativeEvent event)/*-{
    return event.target.files;
  }-*/;
}
