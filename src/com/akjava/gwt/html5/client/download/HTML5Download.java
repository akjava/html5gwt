package com.akjava.gwt.html5.client.download;

import com.akjava.gwt.html5.client.file.Blob;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;



public class HTML5Download implements FileDownload {
	//TODO rename 
	public   native final DownloadURL URL()/*-{
	return   window.webkitURL || window.URL;
	}-*/;
	
	/**
	 * @deprecated
	 * @return
	 */
	public   native final DownloadBlobBuilder BlobBuilder()/*-{
	$wnd.BlobBuilder=   $wnd.BlobBuilder || $wnd.WebKitBlobBuilder ||
                       $wnd.MozBlobBuilder;
    return new $wnd.BlobBuilder();
	}-*/;
	
	public  Anchor generateTextDownloadLink(String text,String fileName,String anchorText){
		return generateTextDownloadLink(text,fileName,anchorText,false);
	}
	public  Anchor generateTextDownloadLink(String text,String fileName,String anchorText,boolean autoRemove){
		String mimeType="text/plain;charset=UTF-8";
		/*
		DownloadBlobBuilder bb=BlobBuilder();
		
		bb.append(text);
		*/
		
		final Anchor link=new Anchor(anchorText);
		String url=URL().createObjectURL(Blob.createBlob(text));
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
	
	/**
	 * not tested yet
	 * @param text
	 * @param mimeType
	 * @param fileName
	 * @param anchorText
	 * @param autoRemove
	 * @return
	 */
	public  Anchor generateTextDownloadLink(String text,String mimeType,String fileName,String anchorText,boolean autoRemove){
		final Anchor link=new Anchor(anchorText);
		String url=URL().createObjectURL(Blob.createBlob(text,mimeType));
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
	
	//TODO support it
	public  Anchor generateBase64DownloadLink(String urlData,String mimeType,String fileName,String anchorText,boolean autoRemove){
		
		
		final Anchor link=new Anchor(anchorText);
		String url=URL().createObjectURL(Blob.createBase64Blob(urlData,mimeType));
		//String url=URL().createObjectURL(bb.getBlob(mimeType));
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
	
	
	//blob have mimetype?
	public  Anchor generateDownloadLink(Blob blob,String mimeType,String fileName,String anchorText){
		return generateDownloadLink(blob,mimeType,fileName,anchorText,false);
	}
	
	public  Anchor generateDownloadLink(Blob blob,String mimeType,String fileName,String anchorText,boolean autoRemove){
		final Anchor link=new Anchor(anchorText);
		String url=URL().createObjectURL(blob);
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
	

}
