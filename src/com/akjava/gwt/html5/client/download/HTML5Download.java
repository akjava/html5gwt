package com.akjava.gwt.html5.client.download;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;



public class HTML5Download {
	public static  native final DownloadURL URL()/*-{
	return   window.webkitURL || window.URL;
	}-*/;
	
	public static  native final DownloadBlobBuilder BlobBuilder()/*-{
	$wnd.BlobBuilder=   $wnd.BlobBuilder || $wnd.WebKitBlobBuilder ||
                       $wnd.MozBlobBuilder;
    return new $wnd.BlobBuilder();
	}-*/;
	
	public static Anchor generateTextDownloadLink(String text,String fileName,String anchorText){
		return generateTextDownloadLink(text,fileName,anchorText,false);
	}
	public static Anchor generateTextDownloadLink(String text,String fileName,String anchorText,boolean autoRemove){
		DownloadBlobBuilder bb=HTML5Download.BlobBuilder();
		String mimeType="text/plain";
		bb.append(text);
		
		final Anchor link=new Anchor(anchorText);
		String url=HTML5Download.URL().createObjectURL(bb.getBlob(mimeType));
		link.setHref(url);
		link.getElement().setAttribute("download", fileName);
		link.getElement().setAttribute("dataset.downloadurl", mimeType+":"+fileName+":"+url);
		if(autoRemove){
			link.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					link.removeFromParent();
				}
			});
		}
		return link;
	}
	
	public static Anchor generateBase64DownloadLink(String urlData,String mimeType,String fileName,String anchorText,boolean autoRemove){
		DownloadBlobBuilder bb=HTML5Download.BlobBuilder();
		bb.appendBase64(urlData);
		
		final Anchor link=new Anchor(anchorText);
		String url=HTML5Download.URL().createObjectURL(bb.getBlob(mimeType));
		link.setHref(url);
		link.getElement().setAttribute("download", fileName);
		link.getElement().setAttribute("dataset.downloadurl", mimeType+":"+fileName+":"+url);
		if(autoRemove){
			link.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					link.removeFromParent();
				}
			});
		}
		return link;
	}
	
	
	public static Anchor generateDownloadLink(DownloadBlobBuilder blob,String mimeType,String fileName,String anchorText){
		final Anchor link=new Anchor(anchorText);
		String url=HTML5Download.URL().createObjectURL(blob.getBlob(mimeType));
		link.setHref(url);
		link.getElement().setAttribute("download", fileName);
		link.getElement().setAttribute("dataset.downloadurl", mimeType+":"+fileName+":"+url);
		return link;
	}
	

}
