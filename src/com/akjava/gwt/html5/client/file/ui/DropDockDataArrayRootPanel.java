package com.akjava.gwt.html5.client.file.ui;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.gwt.dom.client.Style.Unit;

/*
 * this is usefull when do nothing drop rootPanel
 */
public abstract class DropDockDataArrayRootPanel extends DropDockRootPanel{
	public DropDockDataArrayRootPanel(Unit unit,boolean addRootLayoutPanel) {
		super(unit,addRootLayoutPanel);
	}
	
	public DropDockDataArrayRootPanel() {
		super(Unit.PX,false);
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
				
				Uint8Array dataUrl=reader.getResultAsBuffer();
				loadFile(parent,Optional.fromNullable(file), dataUrl);
			}
		});
		
		if(file!=null){
			reader.readAsArrayBuffer(file);
		}
	}
	
	/*
	 * if you'd like to know load all end.check lastmodifiedtime each loadfile and call timer after 1 second.and compare and decide end or not
	 */
	public abstract void loadFile(final String pareht,final Optional<File> optional,final Uint8Array array);
}
