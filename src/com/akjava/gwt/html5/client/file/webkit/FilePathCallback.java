package com.akjava.gwt.html5.client.file.webkit;

import com.akjava.gwt.html5.client.file.File;

public interface FilePathCallback {
	/**
	 * 
	 * @param file possible null,when directory
	 * @param parent possible empty when root-file ,but folder has folder-name
	 */
	public void callback(final File file,final String parent);
}
