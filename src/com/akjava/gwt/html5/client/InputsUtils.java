package com.akjava.gwt.html5.client;

import com.google.gwt.user.client.ui.TextBox;

public class InputsUtils {

	public static TextBox setTypeUrl(TextBox box){
		if(box==null){
			return null;
		}
		box.getElement().setAttribute("type", "url");
		return box;
	}
	
	public static TextBox setTypeEmail(TextBox box){
		if(box==null){
			return null;
		}
		box.getElement().setAttribute("type", "email");
		return box;
	}
	
	public static TextBox setTypeTel(TextBox box){
		if(box==null){
			return null;
		}
		box.getElement().setAttribute("type", "tel");
		return box;
	}
}
