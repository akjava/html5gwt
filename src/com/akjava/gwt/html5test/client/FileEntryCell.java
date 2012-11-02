package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class FileEntryCell  extends AbstractCell<FileEntry>{

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			FileEntry value, SafeHtmlBuilder sb) {
		if(value==null){
			return;
		}
		
		String title;
		if(value.isFile()){
			title=value.getFullPath();
		}else{
			title="["+value.getFullPath()+"]";
		}
		sb.appendEscaped(title);
	}

}
