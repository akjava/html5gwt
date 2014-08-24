package com.akjava.gwt.html5.client.file;

import com.google.common.base.Ascii;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class FilePredicates {
	public static class ListedExtensionOnly implements Predicate<File>{
		private Iterable<String> extensions;
		
		public ListedExtensionOnly(Iterable<String> extensions){
			this.extensions=extensions;
		}
		@Override
		public boolean apply(File file) {
			if(file==null){
				return false;
			}
			String extension=FileUtils.getExtension(file.getFileName());
			for(String ext:extensions){
				boolean match=Ascii.equalsIgnoreCase(extension, ext);
				if(match){
					return true;
				}
			}
			
			return false;
		}
	}
	
	
	//for ImagePredicate
	//TODO supplier?
	public static ListedExtensionOnly getImageExtensionOnly(){
		return ImageExtensionOnly;
	}
	private static ListedExtensionOnly ImageExtensionOnly=new ListedExtensionOnly(Lists.newArrayList("png","jpg","jpeg","gif","webp","bmp","ico"));
}
