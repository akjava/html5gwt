package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.ui.DropVerticalPanelBase;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;

public class FileUploadForm extends FormPanel{
private FileUpload fileUpload;
private DropVerticalPanelBase dropPanel;

public FileUpload getFileUpload() {
	return fileUpload;
}

public void setFileUpload(FileUpload fileUpload) {
	this.fileUpload = fileUpload;
}

public FileUploadForm(){
	fileUpload = new FileUpload();
	dropPanel = new DropVerticalPanelBase();
	add(dropPanel);
	dropPanel.add(fileUpload);
}

public void setMultiple(boolean bool){
	if(bool){
		fileUpload.getElement().setAttribute("multiple","multiple");
	}else{
		fileUpload.getElement().removeAttribute("multiple");
	}
}
public DropVerticalPanelBase getDropPanel(){
	return dropPanel;
}

}
