package com.akjava.gwt.html5.client.file.ui;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.google.gwt.user.client.ui.Anchor;

public class FileNameAndText {
private String name;
private String text;
public FileNameAndText(){}
public FileNameAndText(String name,String text){
	this.name=name;
	this.text=text;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}

public Anchor createDownloadLink(String linkText){
	return new HTML5Download().generateTextDownloadLink(text, name, linkText);
}
public String toString(){
	return name+":"+text;
}
}
