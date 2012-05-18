package com.akjava.gwt.html5.client.file;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;

public class FileUploadForm extends FormPanel{
private FileUpload fileUpload;

public FileUpload getFileUpload() {
	return fileUpload;
}

public void setFileUpload(FileUpload fileUpload) {
	this.fileUpload = fileUpload;
}

public FileUploadForm(){
	fileUpload = new FileUpload();
	add(fileUpload);
}



}
