package com.akjava.gwt.html5.client.file.webkit;

import com.google.gwt.core.client.JsArray;

/**
 * watch out callback max entry size is 100.
 * https://code.google.com/p/chromium/issues/detail?id=378883
 * so you should call 
 * @author aki
 *
 */
public interface DirectoryCallback {
	public void callback(JsArray<FileEntry> entries);
}
