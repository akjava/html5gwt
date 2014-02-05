package com.akjava.gwt.html5.client.file.ui;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.gwt.dom.client.Style.Unit;

/*
 * this is usefull when do nothing drop rootPanel
 */
public abstract class DropDockTextRootPanel extends DropDockRootPanel{
	private String encoding;
	public DropDockTextRootPanel(Unit unit,String encoding,boolean addRootLayoutPanel) {
		super(unit,addRootLayoutPanel);
		this.encoding=encoding==null?"UTF-8":encoding;
	}

	private Predicate<File> filePredicate;
	
	public Predicate<File> getFilePredicate() {
		return filePredicate;
	}

	public void setFilePredicate(Predicate<File> filePredicate) {
		this.filePredicate = filePredicate;
	}

	@Override
	public void callback(final File file, final String parent) {
		if(file==null){
			return;
		}
		if(filePredicate!=null && !filePredicate.apply(file)){
			return;
		}
		
	
		
		final FileReader reader = FileReader.createFileReader();
		reader.setOnLoad(new FileHandler() {
			@Override
			public void onLoad() {
				
				String dataUrl=reader.getResultAsString();
				loadFile(parent,Optional.fromNullable(file), dataUrl);
			}
		});
		
		if(file!=null){
			reader.readAsText(file,encoding);
		}
	}
	
	public abstract void loadFile(final String pareht,final Optional<File> optional,final String text);
}
