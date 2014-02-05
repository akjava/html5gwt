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
public abstract class DropDockDataUrlRootPanel extends DropDockRootPanel{
	public DropDockDataUrlRootPanel(Unit unit,boolean addRootLayoutPanel) {
		super(unit,addRootLayoutPanel);
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
			reader.readAsDataURL(file);
		}
	}
	
	public abstract void loadFile(final String pareht,final Optional<File> optional,final String dataUrl);
}
