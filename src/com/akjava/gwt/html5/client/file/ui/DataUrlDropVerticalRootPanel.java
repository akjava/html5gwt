package com.akjava.gwt.html5.client.file.ui;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileReader;

/*
 * this is usefull when do nothing drop rootPanel
 */
public abstract class DataUrlDropVerticalRootPanel extends DropVerticalRootPanel{
	public DataUrlDropVerticalRootPanel(boolean addRootLayoutPanel) {
		super(addRootLayoutPanel);
	}

	
	@Override
	public void callback(final File file, String parent) {
		
		if(file==null){
			return;
		}
		final FileReader reader = FileReader.createFileReader();
		reader.setOnLoad(new FileHandler() {
			@Override
			public void onLoad() {

				String dataUrl=reader.getResultAsString();
				loadFile(file, dataUrl);
				loadFileIndex++;
			}
		});
		
		if(file!=null){
			reader.readAsDataURL(file);
		}
	}
	
	public abstract void loadFile(final File file,final String dataUrl);
}
