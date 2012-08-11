package com.akjava.gwt.html5.client.download;

import com.google.gwt.core.client.GWT;

public class FileDownloadBuilder {
public static FileDownload createFileDownload(){
	return GWT.create(FileDownload.class);
}
}
