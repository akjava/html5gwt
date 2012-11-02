package com.akjava.gwt.html5.client.download;

import com.akjava.gwt.html5.client.file.Blob;
import com.google.gwt.user.client.ui.Anchor;

public interface FileDownload {
	public DownloadURL URL();
	
	//public  DownloadBlobBuilder BlobBuilder();
	
	public  Anchor generateTextDownloadLink(String text,String fileName,String anchorText);
	public  Anchor generateTextDownloadLink(String text,String fileName,String anchorText,boolean autoRemove);
	
	public  Anchor generateBase64DownloadLink(String urlData,String mimeType,String fileName,String anchorText,boolean autoRemove);
	
	
	public  Anchor generateDownloadLink(Blob blob,String mimeType,String fileName,String anchorText);
}
