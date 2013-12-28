package com.akjava.gwt.html5.client.file.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class FileNameAndTextCell extends AbstractCell<FileNameAndText>{
	public FileNameAndTextCell(){
		
	}
	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			FileNameAndText value, SafeHtmlBuilder sb) {
		 if(value == null){
		      return;
		    }
		 sb.appendHtmlConstant(value.getName());
	}

}
