package com.akjava.gwt.html5.client.file;

import java.util.List;

import com.akjava.gwt.html5.client.file.ui.DropVerticalPanelBase;
import com.google.common.base.Joiner;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;

public class FileUploadForm extends FormPanel{
private FileUpload fileUpload;
private DropVerticalPanelBase dropPanel;
private boolean showDragOverBorder;

public static String ACCEPT_IMAGE_JPEG="image/jpeg";
public static String ACCEPT_IMAGE_PNG="image/png";
public static String ACCEPT_IMAGE_GIF="image/gif";
public static String ACCEPT_IMAGE="image/*";
public static String ACCEPT_AUDIO="audio/*";
public static String ACCEPT_VIDEO="video/*";


public boolean isShowDragOverBorder() {
	return showDragOverBorder;
}

/**
 * show border when file dragging to detect which area is target.(default css-name is html5dragOverBorder)
 * @param showDragOverBorder
 */
public void setShowDragOverBorder(boolean showDragOverBorder) {
	this.showDragOverBorder = showDragOverBorder;
}

public FileUpload getFileUpload() {
	return fileUpload;
}

public void setFileUpload(FileUpload fileUpload) {
	this.fileUpload = fileUpload;
}


/**
 * set accept file like image/*
 * @param accept
 */
public void setAccept(String accept){
	fileUpload.getElement().setAttribute("accept", accept);
}

public void setAccept(List<String> accepts){
	fileUpload.getElement().setAttribute("accept", Joiner.on(",").join(accepts));
}

public FileUploadForm(){
	super(Document.get().createFormElement(),false);
	fileUpload = new FileUpload();
	dropPanel = new DropVerticalPanelBase();
	dropPanel.setBorderWidth(0);
	add(dropPanel);
	dropPanel.add(fileUpload);
	dropPanel.setStylePrimaryName("html5dragOverBorder");
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
@Override
public void setTitle(String title) {
	fileUpload.setTitle(title);
}

}
